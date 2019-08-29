package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.mongodb.core.mapping.Document;

@Document( value = "Product")
public class Product {

    //id, productName, loanTenure, interestRate
    String id;
    String productName;
    Integer loanTenure;
    Integer inerestRate;
}
