package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

/**
 * Created by gayathrig on 24/07/2019.
 */

@Document (value = "MFI")
public class MFI {

    @Id
    private String id;
    private String companyName;
    private String directorName;
    //private String companyName;
    //private String directorName;
    private Address address;
    private String netFundsOwned;
    private String govtRegistrationNumber;
    private Date registrationDate;
    private boolean isOperating;
    private boolean isBorrowedFromBanks;
    private boolean isAnyCasePendingInCourt;
    private String reasonForApplication;

    //company companyName
    //Director Name
    //Net funds owned
    //Govt Identification Number
    //Date of Registration
    //Company started business operations
    //Reasons for applying
    //Has borrowed from banks?
    //Pending civil or criminal cases in any court?

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

    @Override
    public String toString() {
        return "MFI{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", directorName='" + directorName + '\'' +
                ", address=" + address +
                '}';
    }
}
