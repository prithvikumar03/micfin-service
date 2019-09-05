package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

//@Document(collection = "RepaymentInfo")
public class RepaymentInfo {
    @Id
    private String id;
    private String microEntrepreneurId;
    //Is mfiId required???
    private String mfiId;
    private String loanId;
    private String loanAmount;
    private Integer payment;
    private Date date;
    //to be calculated on the server side.
    private Integer paymentDelayedInMonths;
    //private Product product;

    String productId;
    String productName;
    Integer tenure;
    Integer interestRate;

    //private String creditScore;


    public RepaymentInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMicroEntrepreneurId() {
        return microEntrepreneurId;
    }

    public void setMicroEntrepreneurId(String microEntrepreneurId) {
        this.microEntrepreneurId = microEntrepreneurId;
    }

    public String getMfiId() {
        return mfiId;
    }

    public void setMfiId(String mfiId) {
        this.mfiId = mfiId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getPaymentDelayedInMonths() {
        return paymentDelayedInMonths;
    }

    public void setPaymentDelayedInMonths(Integer paymentDelayedInMonths) {
        this.paymentDelayedInMonths = paymentDelayedInMonths;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public Integer getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Integer interestRate) {
        this.interestRate = interestRate;
    }
}