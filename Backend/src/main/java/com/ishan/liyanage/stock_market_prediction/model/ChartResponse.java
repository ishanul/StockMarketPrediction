package com.ishan.liyanage.stock_market_prediction.model;

public class ChartResponse {
    private double[] indexes;
    private double[] predicts;
    private double[] actuals;

    String category;

    public double[] getIndexes() {
        return indexes;
    }

    public void setIndexes(double[] indexes) {
        this.indexes = indexes;
    }

    public double[] getPredicts() {
        return predicts;
    }

    public void setPredicts(double[] predicts) {
        this.predicts = predicts;
    }

    public double[] getActuals() {
        return actuals;
    }

    public void setActuals(double[] actuals) {
        this.actuals = actuals;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
