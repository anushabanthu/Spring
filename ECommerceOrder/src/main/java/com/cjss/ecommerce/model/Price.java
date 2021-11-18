package com.cjss.ecommerce.model;

public class Price {
    private int skuCode;
    private int price;
    private String currency;

    public int getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(int skuCode) {
        this.skuCode = skuCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
