package com.ishan.liyanage.stock_market_prediction.predict;

import com.ishan.liyanage.stock_market_prediction.model.ChartResponse;
import com.ishan.liyanage.stock_market_prediction.model.LogResponse;
import com.ishan.liyanage.stock_market_prediction.model.Pair;
import com.ishan.liyanage.stock_market_prediction.model.RecurrentNets;
import com.ishan.liyanage.stock_market_prediction.representation.PriceCategory;
import com.ishan.liyanage.stock_market_prediction.representation.StockDataSetIterator;
//import javafx.util.Pair;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class StockPricePrediction {

    private static final Logger log = LoggerFactory.getLogger(StockPricePrediction.class);

    private static int exampleLength = 22; // time series length, assume 22 working days per month

    public LogResponse readLog() throws IOException {
        PriceCategory category = PriceCategory.CLOSE; // CLOSE: predict close price
        List<String> logs = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/StockPriceLSTM_".concat(String.valueOf(category)).concat(".log")));
        try {
            String line = br.readLine();
            while (line != null) {
                logs.add(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        return new LogResponse(logs.toArray(new String[0]));
    }
    public void train(String symbol) throws IOException {
        PriceCategory category = PriceCategory.CLOSE; // CLOSE: predict close price
        File locationToSave = new File("src/main/resources/StockPriceLSTM_".concat(String.valueOf(symbol+category)).concat(".zip"));
        PrintWriter writer = new PrintWriter("src/main/resources/StockPriceLSTM_".concat(String.valueOf(category)).concat(".log"), "UTF-8");
        writer.println("Starting the dataset training....." + symbol);
        writer.flush();
        String file = new ClassPathResource(symbol+".csv").getFile().getAbsolutePath();
        //String file = new ClassPathResource("prices-split-adjusted.csv").getFile().getAbsolutePath();
        //String symbol = "GOOG"; // stock name
        int batchSize = 64; // mini-batch size
        double splitRatio = 0.9; // 90% for training, 10% for testing
        //TODO change to 100
        int epochs = 100; // training epochs
        writer.println("Create dataSet iterator...");
        log.info("Create dataSet iterator..." + symbol);
        StockDataSetIterator iterator = new StockDataSetIterator(file, symbol, batchSize, exampleLength, splitRatio, category);
        log.info("Load test dataset...");
        writer.println("Load test dataset..." + symbol);
        List<Pair<INDArray, INDArray>> test = iterator.getTestDataSet();

        log.info("Build lstm networks...");
        writer.println("Build lstm networks..." + symbol);

        MultiLayerNetwork net = RecurrentNets.buildLstmNetworks(iterator.inputColumns(), iterator.totalOutcomes(), writer);

        log.info("Training...");
        writer.println("Training...." + symbol);

        for (int i = 0; i < epochs; i++) {
            while (iterator.hasNext()) {
                DataSet next = iterator.next();
                net.fit(next); // fit model using mini-batch data
//                writer.println(new Date() +" Training dataset......");
//                writer.flush();
            }
            iterator.reset(); // reset iterator
            net.rnnClearPreviousState(); // clear previous state
        }
        writer.println("");
        log.info("Saving model...");
        writer.println("Saving model..." + symbol);

        // saveUpdater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this to train your network more in the future
        ModelSerializer.writeModel(net, locationToSave, true);
        log.info("Load model...");
        writer.println("Load model..." + symbol);

        net = ModelSerializer.restoreMultiLayerNetwork(locationToSave);
        log.info("Done!");
        writer.println("Done! - " + symbol);

        writer.close();

    }
    public List<ChartResponse> predict(String symbol) throws IOException {
        PriceCategory category = PriceCategory.CLOSE; // CLOSE: predict close price
        File locationToSave = new File("src/main/resources/StockPriceLSTM_".concat(String.valueOf(symbol+category)).concat(".zip"));
        String file = new ClassPathResource(symbol+".csv").getFile().getAbsolutePath();
        //String file = new ClassPathResource("prices-split-adjusted.csv").getFile().getAbsolutePath();
        //String symbol = "GOOG"; // stock name
        int batchSize = 64; // mini-batch size
        double splitRatio = 0.9; // 90% for training, 10% for testing
        //TODO change to 100
//        int epochs = 100; // training epochs

        log.info("Create dataSet iterator...");
        StockDataSetIterator iterator = new StockDataSetIterator(file, symbol, batchSize, exampleLength, splitRatio, category);
        log.info("Load test dataset...");
        List<Pair<INDArray, INDArray>> test = iterator.getTestDataSet();

        log.info("Build lstm networks...");
//        MultiLayerNetwork net = RecurrentNets.buildLstmNetworks(iterator.inputColumns(), iterator.totalOutcomes(), null);

//        if(!locationToSave.exists()) {
//            log.info("Training...");
//            for (int i = 0; i < epochs; i++) {
//                while (iterator.hasNext()) net.fit(iterator.next()); // fit model using mini-batch data
//                iterator.reset(); // reset iterator
//                net.rnnClearPreviousState(); // clear previous state
//            }
//        }
//        log.info("Saving model...");
//        // saveUpdater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this to train your network more in the future
//        ModelSerializer.writeModel(net, locationToSave, true);

        log.info("Load model...");
        MultiLayerNetwork net = ModelSerializer.restoreMultiLayerNetwork(locationToSave);

        log.info("Testing...");
        if (category.equals(PriceCategory.ALL)) {
            INDArray max = Nd4j.create(iterator.getMaxArray());
            INDArray min = Nd4j.create(iterator.getMinArray());
            return predictAllCategories(net, test, max, min);
        } else {
            double max = iterator.getMaxNum(category);
            double min = iterator.getMinNum(category);
            return predictPriceOneAhead(net, test, max, min, category);
        }
//        log.info("Done...");
    }

    /** Predict one feature of a stock one-day ahead */
    private static List<ChartResponse> predictPriceOneAhead (MultiLayerNetwork net, List<Pair<INDArray, INDArray>> testData, double max, double min, PriceCategory category) {
        List<ChartResponse> response = new ArrayList<>();

        double[] predicts = new double[testData.size()];
        double[] actuals = new double[testData.size()];
        for (int i = 0; i < testData.size(); i++) {
            predicts[i] = net.rnnTimeStep(testData.get(i).key()).getDouble(exampleLength - 1) * (max - min) + min;
            actuals[i] = testData.get(i).value().getDouble(0);
        }
        log.info("Print out Predictions and Actual Values...");
        log.info("Predict,Actual");
        for (int i = 0; i < predicts.length; i++) log.info(predicts[i] + "," + actuals[i]);
        log.info("Plot...");
        double[] index = new double[predicts.length];
        for (int i = 0; i < predicts.length; i++)
            index[i] = i;
        ChartResponse res = new ChartResponse();
        res.setIndexes(index);
        res.setActuals(actuals);
        res.setPredicts(predicts);
        res.setCategory(String.valueOf(category));
        response.add(res);
        return response;
    }

    private static void predictPriceMultiple (MultiLayerNetwork net, List<Pair<INDArray, INDArray>> testData, double max, double min) {
        // TODO
    }

    /** Predict all the features (open, close, low, high prices and volume) of a stock one-day ahead */
    private static List<ChartResponse> predictAllCategories (MultiLayerNetwork net, List<Pair<INDArray, INDArray>> testData, INDArray max, INDArray min) {
        List<ChartResponse> response = new ArrayList<>();
        INDArray[] predicts = new INDArray[testData.size()];
        INDArray[] actuals = new INDArray[testData.size()];
        for (int i = 0; i < testData.size(); i++) {
            predicts[i] = net.rnnTimeStep(testData.get(i).key()).getRow(exampleLength - 1).mul(max.sub(min)).add(min);
            actuals[i] = testData.get(i).value();
        }
        log.info("Print out Predictions and Actual Values...");
        log.info("Predict\tActual");
        for (int i = 0; i < predicts.length; i++) log.info(predicts[i] + "\t" + actuals[i]);
        log.info("Plot...");
        for (int n = 0; n < 5; n++) {
            double[] pred = new double[predicts.length];
            double[] actu = new double[actuals.length];
            for (int i = 0; i < predicts.length; i++) {
                pred[i] = predicts[i].getDouble(n);
                actu[i] = actuals[i].getDouble(n);
            }
            String name;
            switch (n) {
                case 0: name = "Stock OPEN Price"; break;
                case 1: name = "Stock CLOSE Price"; break;
                case 2: name = "Stock LOW Price"; break;
                case 3: name = "Stock HIGH Price"; break;
                case 4: name = "Stock VOLUME Amount"; break;
                default: throw new NoSuchElementException();
            }
            double[] index = new double[pred.length];
            for (int i = 0; i < predicts.length; i++)
                index[i] = i;
            ChartResponse res = new ChartResponse();
            res.setIndexes(index);
            res.setActuals(actu);
            res.setPredicts(pred);
            res.setCategory(name);
            response.add(res);

        }
        return response;
    }

}
