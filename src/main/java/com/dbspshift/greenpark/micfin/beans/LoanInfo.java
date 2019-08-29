package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "LoanInfo")
public class LoanInfo {

    //loan disbursement
    String id;
    String mfiId;
    String microEntrepreneurId;
    Integer loanAmount;
    Product product;
    List<LoanSchedule> listLoanSchedule;

}
