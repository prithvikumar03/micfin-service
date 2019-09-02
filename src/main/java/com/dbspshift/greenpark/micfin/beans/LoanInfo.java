package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "LoanInfo")
public class LoanInfo {

    //loan disbursement
    @Id
    private String id;
    private String loanId;
    private String mfiId;
    private String mfiName;
    private String microEntrepreneurId;
    private String microEntrepreneurName;
    private Integer loanAmount;
    //Product product;
    private String productId;
    private String productName;
    private Integer tenure;
    private Integer inerestRate;
    private Date date;
    //???
    List<LoanSchedule> listLoanSchedule;

    List<RepaymentInfo> repaymentInfoList;

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

    public List<LoanSchedule> getListLoanSchedule() {
        return listLoanSchedule;
    }

    public void setListLoanSchedule(List<LoanSchedule> listLoanSchedule) {
        this.listLoanSchedule = listLoanSchedule;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getMfiName() {
        return mfiName;
    }

    public void setMfiName(String mfiName) {
        this.mfiName = mfiName;
    }

    public String getMicroEntrepreneurName() {
        return microEntrepreneurName;
    }

    public void setMicroEntrepreneurName(String microEntrepreneurName) {
        this.microEntrepreneurName = microEntrepreneurName;
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

    public Integer getInerestRate() {
        return inerestRate;
    }

    public void setInerestRate(Integer inerestRate) {
        this.inerestRate = inerestRate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<RepaymentInfo> getRepaymentInfoList() {
        return repaymentInfoList;
    }

    public void setRepaymentInfoList(List<RepaymentInfo> repaymentInfoList) {
        this.repaymentInfoList = repaymentInfoList;
    }

    public void addToRepaymentInfoList(RepaymentInfo repaymentInfo) {
        this.repaymentInfoList.add(repaymentInfo);
    }
}
