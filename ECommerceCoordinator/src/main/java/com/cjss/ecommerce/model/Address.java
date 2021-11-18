package com.cjss.ecommerce.model;


public class Address {
	private String id;
	private String line1;
	private String line2;
	private Integer postalCode;
	private String state;
	private String city;
	private Boolean shippingAddress;
	private Boolean billingAddress;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
