package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.mongodb.core.mapping.Document;

@Document( value = "Product")
public class Product {

    //id, productName, loanTenure, interestRate
    String id;
    String productName;
    Integer loanTenure;
    Integer inerestRate;

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

    public Integer getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(Integer loanTenure) {
        this.loanTenure = loanTenure;
    }

    public Integer getInerestRate() {
        return inerestRate;
    }

    public void setInerestRate(Integer inerestRate) {
        this.inerestRate = inerestRate;
    }
}
