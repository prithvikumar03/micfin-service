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