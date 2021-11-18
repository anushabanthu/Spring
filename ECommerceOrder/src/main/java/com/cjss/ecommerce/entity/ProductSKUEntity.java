package com.cjss.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="product_sku_entity")
public class ProductSKUEntity {
    @Id
    private Integer skuCode;
    @Column
    private Integer productCode;
    @Column
    private String size;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"productSKUEntities","hibernateLazyInitializer", "handler"})
    private ProductEntity productEntity;
    @OneToOne(cascade = CascadeType.ALL)
    private PriceEntity priceEntity;

    public ProductSKUEntity() {
    }

    public Integer getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Integer skuCode) {
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

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public PriceEntity getPriceEntity() {
        return priceEntity;
    }

    public void setPriceEntity(PriceEntity priceEntity) {
        this.priceEntity = priceEntity;
    }
}
