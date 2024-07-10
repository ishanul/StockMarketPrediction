package com.ishan.liyanage.stock_market_prediction.controller;

import com.ishan.liyanage.stock_market_prediction.model.ChartResponse;
import com.ishan.liyanage.stock_market_prediction.model.LogResponse;
import com.ishan.liyanage.stock_market_prediction.predict.StockPricePrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TrainController {

    @Autowired
    private StockPricePrediction stockPricePrediction;
    @GetMapping("/train")
    public void train(@RequestParam("symbol") String symbol) throws Exception {
        stockPricePrediction.train(symbol);
    }

    @GetMapping("/training_log")
    public LogResponse trainingLog() throws Exception {
        return stockPricePrediction.readLog();
    }
}
