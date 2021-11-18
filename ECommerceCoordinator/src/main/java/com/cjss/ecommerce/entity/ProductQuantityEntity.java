package com.cjss.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sku_quantity_entity")
public class ProductQuantityEntity {
//    @Id
//    private Integer orderCode;
    @Id
    private String skuCode;
    @Column
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"productQuantityList","addressEntityList","hibernateLazyInitializer", "handler"})
    OrderEntity orderEntity;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer qunatity) {
        this.quantity = qunatity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
