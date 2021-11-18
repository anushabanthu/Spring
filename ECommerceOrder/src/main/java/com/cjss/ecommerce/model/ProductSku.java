package com.cjss.ecommerce.model;

public class ProductSku {
    private int skuCode;
    private Integer productCode;
    private String size;
    private Price price;

    public int getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(int skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
