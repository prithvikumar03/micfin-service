package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "Product")
public class Product {

    //id, productName, loanTenure, interestRate
    @Id
    private String id;
    private String productId;
    private String productName;
    private Integer tenure;
    private Integer inerestRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public Integer getInerestRate() {
        return inerestRate;
    }

    public void setInerestRate(Integer inerestRate) {
        this.inerestRate = inerestRate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
