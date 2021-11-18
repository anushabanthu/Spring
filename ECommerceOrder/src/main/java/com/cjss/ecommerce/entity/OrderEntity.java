package com.cjss.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="order_entity")
public class OrderEntity {
    @Id
    private Integer orderCode;
//    @OneToOne(cascade = CascadeType.ALL)
//    private ShippingAddressEntity shippingAddressEntity;
//    @OneToOne(cascade = CascadeType.ALL)
//    private AddressEntity billingAddressEntity;
//    @OneToMany(fetch = FetchType.EAGER, mappedBy= "orderEntity", cascade = CascadeType.ALL )
    @OneToMany(fetch = FetchType.LAZY, mappedBy= "orderEntity", cascade = CascadeType.ALL )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<AddressEntity> addressEntityList = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy= "orderEntity", cascade = CascadeType.ALL )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ProductQuantityEntity> productQuantityList = new ArrayList<>();
    private String status;

    public Integer getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Integer orderId) {
        this.orderCode = orderId;
    }

    public List<AddressEntity> getAddressEntityList() {
        return addressEntityList;
    }

    public void setAddressEntityList(List<AddressEntity> addressEntityList) {
        this.addressEntityList = addressEntityList;
    }

    public List<ProductQuantityEntity> getProductQuantityList() {
        return productQuantityList;
    }

    public void setProductQuantityList(List<ProductQuantityEntity> productQuantityList) {
        this.productQuantityList = productQuantityList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
