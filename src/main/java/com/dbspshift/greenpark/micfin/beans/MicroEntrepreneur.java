package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document (value = "MicroEntrepreneur")
public class MicroEntrepreneur {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String aadhar;
    private Address address;
    private String gender;
    private Date dob;
    private String maritialStatus;
    private String highestEducation;
    private String phoneHome;
    private String phoneBusiness;
    private Integer distBusinessToHome;
    private Integer householdIncome;
    private Integer businessExp;
    private Integer householExp;
    private Integer emiMonthly;
    private Integer rent;
    private Integer noOfNonEarningMembers;
    private String formalSavingsAccount;
    private Integer totalAssests;
    private Date businessStartYear;
    private boolean isBusinessRegistered;
    private Integer noOfEmployees;
    private Integer incomeFromOtherSources;
    private boolean isDrinker;
    private boolean isSmoker;

    private String mfiId;
}
