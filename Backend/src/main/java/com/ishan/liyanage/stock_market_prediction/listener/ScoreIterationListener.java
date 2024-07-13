package com.ishan.liyanage.stock_market_prediction.listener;

import org.deeplearning4j.nn.api.Model;
import org.deeplearning4j.optimize.api.IterationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

public class ScoreIterationListener implements IterationListener {
    private int printIterations = 10;
    private static final Logger log = LoggerFactory.getLogger(org.deeplearning4j.optimize.listeners.ScoreIterationListener.class);
    private boolean invoked = false;
    private long iterCount = 0L;

    private PrintWriter writer;

    public ScoreIterationListener(int printIterations, PrintWriter writer ) {
        this.printIterations = printIterations;
        this.writer = writer;
    }

    public ScoreIterationListener() {
    }

    public boolean invoked() {
        return this.invoked;
    }

    public void invoke() {
        this.invoked = true;
    }

    public void iterationDone(Model model, int iteration) {
        if (this.printIterations <= 0) {
            this.printIterations = 1;
        }

        if (this.iterCount % (long)this.printIterations == 0L) {
            this.invoke();
            double result = model.score();
            log.info("Score at iteration " + this.iterCount + " is " + result);
            writer.println("");
            writer.println("Score at iteration " + this.iterCount + " is " + result);
            writer.flush();
        }
        else{
            writer.print(".");
            writer.flush();
        }

        ++this.iterCount;
    }
}