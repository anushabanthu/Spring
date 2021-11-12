package com.cjss.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product_entity")
public class ProductEntity {
    @Id
    private Integer productCode;
    @Column
    private String productName;
    @Column
    private String Description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy= "productEntity", cascade = CascadeType.ALL )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ProductSKUEntity> productSKUEntities = new ArrayList<>();

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<ProductSKUEntity> getProductSKUEntities() {
        return productSKUEntities;
    }

    public void setProductSKUEntities(List<ProductSKUEntity> productSKUEntities) {
        this.productSKUEntities = productSKUEntities;
    }
}
