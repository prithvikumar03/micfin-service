package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document (collection = "MicroEntrepreneur")
public class MicroEntrepreneur {

    @Id
    private String id;
    private String microEntrepreneurId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMaritialStatus() {
        return maritialStatus;
    }

    public void setMaritialStatus(String maritialStatus) {
        this.maritialStatus = maritialStatus;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getPhoneBusiness() {
        return phoneBusiness;
    }

    public void setPhoneBusiness(String phoneBusiness) {
        this.phoneBusiness = phoneBusiness;
    }

    public Integer getDistBusinessToHome() {
        return distBusinessToHome;
    }

    public void setDistBusinessToHome(Integer distBusinessToHome) {
        this.distBusinessToHome = distBusinessToHome;
    }

    public Integer getHouseholdIncome() {
        return householdIncome;
    }

    public void setHouseholdIncome(Integer householdIncome) {
        this.householdIncome = householdIncome;
    }

    public Integer getBusinessExp() {
        return businessExp;
    }

    public void setBusinessExp(Integer businessExp) {
        this.businessExp = businessExp;
    }

    public Integer getHouseholExp() {
        return householExp;
    }

    public void setHouseholExp(Integer householExp) {
        this.householExp = householExp;
    }

    public Integer getEmiMonthly() {
        return emiMonthly;
    }

    public void setEmiMonthly(Integer emiMonthly) {
        this.emiMonthly = emiMonthly;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getNoOfNonEarningMembers() {
        return noOfNonEarningMembers;
    }

    public void setNoOfNonEarningMembers(Integer noOfNonEarningMembers) {
        this.noOfNonEarningMembers = noOfNonEarningMembers;
    }

    public String getFormalSavingsAccount() {
        return formalSavingsAccount;
    }

    public void setFormalSavingsAccount(String formalSavingsAccount) {
        this.formalSavingsAccount = formalSavingsAccount;
    }

    public Integer getTotalAssests() {
        return totalAssests;
    }

    public void setTotalAssests(Integer totalAssests) {
        this.totalAssests = totalAssests;
    }

    public Date getBusinessStartYear() {
        return businessStartYear;
    }

    public void setBusinessStartYear(Date businessStartYear) {
        this.businessStartYear = businessStartYear;
    }

    public boolean isBusinessRegistered() {
        return isBusinessRegistered;
    }

    public void setBusinessRegistered(boolean businessRegistered) {
        isBusinessRegistered = businessRegistered;
    }

    public Integer getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(Integer noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public Integer getIncomeFromOtherSources() {
        return incomeFromOtherSources;
    }

    public void setIncomeFromOtherSources(Integer incomeFromOtherSources) {
        this.incomeFromOtherSources = incomeFromOtherSources;
    }

    public boolean isDrinker() {
        return isDrinker;
    }

    public void setDrinker(boolean drinker) {
        isDrinker = drinker;
    }

    public boolean isSmoker() {
        return isSmoker;
    }

    public void setSmoker(boolean smoker) {
        isSmoker = smoker;
    }

    public String getMfiId() {
        return mfiId;
    }

    public void setMfiId(String mfiId) {
        this.mfiId = mfiId;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }
}
