package com.teladoc.sumnumbers.model;

public class PartialResult {
    private String sum;
    private int carryOver = 0;

    public PartialResult() { }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public int getCarryOver() {
        return carryOver;
    }

    public void setCarryOver(int carryOver) {
        this.carryOver = carryOver;
    }
}
