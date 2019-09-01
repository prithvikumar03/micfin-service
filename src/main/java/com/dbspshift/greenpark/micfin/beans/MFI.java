package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

/**
 * Created by gayathrig on 24/07/2019.
 */

@Document (collection = "MFI")
public class MFI {

    @Id
    private String id;
    private String mfiId;
    private String companyName;
    private String directorName;
    private Address address;
    private String netFundsOwned;
    private String govtRegistrationNumber;
    private Date registrationDate;
    private boolean isOperating;
    private boolean isBorrowedFromBanks;
    private boolean isAnyCasePendingInCourt;
    private String reasonForApplication;

    public MFI() {
    }

    public MFI(String companyName, String directorName) {
        this.companyName = companyName;
        this.directorName = directorName;
    }

    public MFI(String id, String companyName, String directorName, Address address) {
        this.id = id;
        this.companyName = companyName;
        this.directorName = directorName;
        this.address = address;
    }

    public MFI(String id, String companyName, String directorName, Address address, String netFundsOwned, String govtRegistrationNumber, Date registrationDate, boolean isOperating, boolean isBorrowedFromBanks, boolean isAnyCasePendingInCourt, String reasonForApplication) {
        this.id = id;
        this.companyName = companyName;
        this.directorName = directorName;
        this.address = address;
        this.netFundsOwned = netFundsOwned;
        this.govtRegistrationNumber = govtRegistrationNumber;
        this.registrationDate = registrationDate;
        this.isOperating = isOperating;
        this.isBorrowedFromBanks = isBorrowedFromBanks;
        this.isAnyCasePendingInCourt = isAnyCasePendingInCourt;
        this.reasonForApplication = reasonForApplication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNetFundsOwned() {
        return netFundsOwned;
    }

    public void setNetFundsOwned(String netFundsOwned) {
        this.netFundsOwned = netFundsOwned;
    }

    public String getGovtRegistrationNumber() {
        return govtRegistrationNumber;
    }

    public void setGovtRegistrationNumber(String govtRegistrationNumber) {
        this.govtRegistrationNumber = govtRegistrationNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isOperating() {
        return isOperating;
    }

    public void setOperating(boolean operating) {
        isOperating = operating;
    }

    public boolean isBorrowedFromBanks() {
        return isBorrowedFromBanks;
    }

    public void setBorrowedFromBanks(boolean borrowedFromBanks) {
        isBorrowedFromBanks = borrowedFromBanks;
    }

    public boolean isAnyCasePendingInCourt() {
        return isAnyCasePendingInCourt;
    }

    public void setAnyCasePendingInCourt(boolean anyCasePendingInCourt) {
        isAnyCasePendingInCourt = anyCasePendingInCourt;
    }

    public String getReasonForApplication() {
        return reasonForApplication;
    }

    public void setReasonForApplication(String reasonForApplication) {
        this.reasonForApplication = reasonForApplication;
    }

    @Override
    public String toString() {
        return "MFI{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", directorName='" + directorName + '\'' +
                ", address=" + address +
                ", netFundsOwned='" + netFundsOwned + '\'' +
                ", govtRegistrationNumber='" + govtRegistrationNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", isOperating=" + isOperating +
                ", isBorrowedFromBanks=" + isBorrowedFromBanks +
                ", isAnyCasePendingInCourt=" + isAnyCasePendingInCourt +
                ", reasonForApplication='" + reasonForApplication + '\'' +
                '}';
    }
}

//company companyName
//Director Name
//Net funds owned
//Govt Identification Number
//Date of Registration
//Company started business operations
//Reasons for applying
//Has borrowed from banks?
//Pending civil or criminal cases in any court?

//private String companyName;
//private String directorName;