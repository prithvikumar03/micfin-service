package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "RepaymentInfo")
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
    Integer inerestRate;



    //private String creditScore;

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

/*    public Product getProduct() {
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
    }*/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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