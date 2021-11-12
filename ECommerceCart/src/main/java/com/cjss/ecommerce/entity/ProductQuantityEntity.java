package com.cjss.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name="sku_quantity_entity")
public class ProductQuantityEntity {
    @Id
    private Integer orderCode;
    @Column
    private Integer skuCode;
    @Column
    private Integer qunatity;

    public Integer getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Integer skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Integer productCode) {
        this.orderCode = productCode;
    }

    public Integer getQunatity() {
        return qunatity;
    }

    public void setQunatity(Integer qunatity) {
        this.qunatity = qunatity;
    }
}
