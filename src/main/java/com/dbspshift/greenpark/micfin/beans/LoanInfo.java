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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMfiId() {
        return mfiId;
    }

    public void setMfiId(String mfiId) {
        this.mfiId = mfiId;
    }

    public String getMicroEntrepreneurId() {
        return microEntrepreneurId;
    }

    public void setMicroEntrepreneurId(String microEntrepreneurId) {
        this.microEntrepreneurId = microEntrepreneurId;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<LoanSchedule> getListLoanSchedule() {
        return listLoanSchedule;
    }

    public void setListLoanSchedule(List<LoanSchedule> listLoanSchedule) {
        this.listLoanSchedule = listLoanSchedule;
    }
}
