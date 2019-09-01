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

/*    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }*/

    public List<LoanSchedule> getListLoanSchedule() {
        return listLoanSchedule;
    }

    public void setListLoanSchedule(List<LoanSchedule> listLoanSchedule) {
        this.listLoanSchedule = listLoanSchedule;
    }
}
