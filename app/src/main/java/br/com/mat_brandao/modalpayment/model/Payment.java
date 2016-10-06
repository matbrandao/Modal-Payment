package br.com.mat_brandao.modalpayment.model;

/**
 * Created by Mateus Brandão on 06/10/2016.
 */

public class Payment {
    public static final String MONEY_TYPE = "Dinheiro";
    public static final String CARD_TYPE = "Cartão";

    private String paymentType;
    private float price;

    public Payment() {
    }

    public Payment(String paymentType, float price) {
        this.paymentType = paymentType;
        this.price = price;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
