package org.elzacontiero.m3assessments.vendingmachine;

public class Change {
    private int amountMinusPrice;

    private int twoPoundCoins;
    private int onePoundCoins;
    private int fiftyPenceCoins;
    private int twentyPenceCoins;
    private int tenPenceCoins;
    private int fivePenceCoins;
    private int twoPenceCoins;
    private int onePennyCoins;


    public Change(int amountMinusPrice) {
        this.amountMinusPrice = amountMinusPrice;

        calculate (amountMinusPrice);
    }

    public int getAmountMinusPrice() {
        return amountMinusPrice;
    }

    public int getTwoPoundCoins() {
        return twoPoundCoins;
    }

    public int getOnePoundCoins() {
        return onePoundCoins;
    }

    public int getFiftyPenceCoins() {
        return fiftyPenceCoins;
    }

    public int getTwentyPenceCoins() {
        return twentyPenceCoins;
    }

    public int getTenPenceCoins() {
        return tenPenceCoins;
    }

    public int getFivePenceCoins() {
        return fivePenceCoins;
    }

    public int getTwoPenceCoins() {
        return twoPenceCoins;

    }

    public int getOnePennyCoins() {
        return onePennyCoins;
    }


    @Override
    public String toString() {
        return "Your Change is " + amountMinusPrice +
                ", twoPoundCoins=" + twoPoundCoins +
                ", onePoundCoins=" + onePoundCoins +
                ", fiftyPennyCoins=" + fiftyPenceCoins +
                ", twentyPennyCoins=" + twentyPenceCoins +
                ", tenPenceCoins=" + tenPenceCoins +
                ", fivePenceCoins=" + fivePenceCoins +
                ", twoPenceCoins=" + twoPenceCoins +
                ", onePennyCoins=" + onePennyCoins +
                ':';
    }

    // Overloading - More than one method with same name but different inputs
    // Not to be confused with OVERRIDING
    private void calculate(int difference)
    {
        this.twoPoundCoins = this.amountMinusPrice / 200;
        int remainder = this.amountMinusPrice % 200;
        this.onePoundCoins = remainder / 100;
        remainder = remainder % 100; // reuse variable remainder here
        this.fiftyPenceCoins = remainder / 50;
        remainder = remainder % 50;
        this.twentyPenceCoins = remainder / 20;
        remainder = remainder % 20;
        this.tenPenceCoins = remainder / 10;
        remainder = remainder % 10;
        this.fivePenceCoins = remainder / 5;
        remainder = remainder % 5;
        this.twoPenceCoins = remainder / 2;
        this.onePennyCoins = remainder % 2;
    }
}
