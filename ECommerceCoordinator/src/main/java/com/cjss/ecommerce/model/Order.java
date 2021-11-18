package com.cjss.ecommerce.model;


import java.util.List;

public class Order {
    private Integer orderCode;
    private String shippingAddressId;
    private String billingAddressId;
    private List<ProductQuantity> quantities;

    public Integer getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    public String getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(String shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public String getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(String billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public List<ProductQuantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<ProductQuantity> quantities) {
        this.quantities = quantities;
    }
}
