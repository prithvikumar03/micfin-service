package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "RepaymentInfo")
public class RepaymentInfo {
    @Id
    private String id;
    private String microEntrepreneurId;
    private String mfiId;
    private String loanId;
    //private boolean isCredit;
    //private boolean isDebit;
    private Integer amount;
    private Integer paymentDelayedInMonths;
    private Product product;
    private String creditScore;


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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPaymentDelayedInMonths() {
        return paymentDelayedInMonths;
    }

    public void setPaymentDelayedInMonths(Integer paymentDelayedInMonths) {
        this.paymentDelayedInMonths = paymentDelayedInMonths;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }
}

//loan disbursement
//id
//mfiId
//microEntrepreneurId
//loanAmount
//product --- separate bean
//List<LoanSchedule>

//Loan Schedule
//Date
//Amount to be repaid
//Rebate