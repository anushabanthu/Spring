package com.cjss.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="address_entity")
public class AddressEntity {
    @Id
    private String id;
    @Column
    private String line1;
    @Column
    private String line2;
    @Column
    private Integer postalCode;
    @Column
    private String state;
    @Column
    private String city;
    @Column
    private Boolean shippingAddress;
    @Column
    private Boolean billingAddress;
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnoreProperties({"addressEntityList","hibernateLazyInitializer", "handler"})
//    private RegisterCustomerEntity registerCustomerEntity;
//    @OneToOne(cascade = CascadeType.ALL)
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnoreProperties({"addressEntityList","hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"productQuantityList","addressEntityList","hibernateLazyInitializer", "handler"})
    private OrderEntity orderEntity;

    public String getId() {
        return id;
    }

    public void setId(String email) {
        this.id = email;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Boolean shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Boolean getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Boolean billingAddress) {
        this.billingAddress = billingAddress;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
