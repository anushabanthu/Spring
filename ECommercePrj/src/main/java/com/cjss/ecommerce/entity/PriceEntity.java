package com.cjss.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="price_entity")
public class PriceEntity {
    @Id
    private Integer skuCode;
    @Column
    private Integer price;
    @Column
    private String currency;

    public Integer getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Integer skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String curreny) {
        this.currency = curreny;
    }

}
