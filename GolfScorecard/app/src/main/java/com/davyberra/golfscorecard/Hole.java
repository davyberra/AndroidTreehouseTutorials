package com.davyberra.golfscorecard;

public class Hole {
    private final int order_id;
    private int strokes = 0;

    public Hole(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getStrokes() {
        return strokes;
    }

    public void setStrokes(int strokes) {
        this.strokes = strokes;
    }
}
