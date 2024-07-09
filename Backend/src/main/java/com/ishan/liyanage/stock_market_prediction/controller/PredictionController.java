package com.ishan.liyanage.stock_market_prediction.controller;

import com.ishan.liyanage.stock_market_prediction.model.ChartResponse;
import com.ishan.liyanage.stock_market_prediction.predict.StockPricePrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PredictionController {

    @Autowired
    private StockPricePrediction stockPricePrediction;
    @GetMapping("/predict")
    public List<ChartResponse> predict() throws Exception {

        return stockPricePrediction.predict();

    }
}
