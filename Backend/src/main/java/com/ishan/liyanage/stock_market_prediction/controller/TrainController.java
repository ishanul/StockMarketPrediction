package com.ishan.liyanage.stock_market_prediction.controller;

import com.ishan.liyanage.stock_market_prediction.model.ChartResponse;
import com.ishan.liyanage.stock_market_prediction.predict.StockPricePrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TrainController {

    @Autowired
    private StockPricePrediction stockPricePrediction;
    @GetMapping("/train")
    public List<ChartResponse> train(@RequestParam("symbol") String symbol) throws Exception {

        return stockPricePrediction.predict(symbol);

    }
}
