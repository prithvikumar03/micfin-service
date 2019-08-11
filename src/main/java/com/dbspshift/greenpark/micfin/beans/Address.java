package com.dbspshift.greenpark.micfin.beans;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "Address")
public class Address {

    @Id
    private String id;
    private String unitNo;
    private String streetName;
    private String city;
    private String country;
    private String pinCode;

    public Address() {

    }

    public Address(String id, String unitNo, String streetName, String city, String country, String pinCode) {
        this.id = id;
        this.unitNo = unitNo;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.pinCode = pinCode;
    }

    // ObjectId needs to be converted to string
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "unitNo='" + unitNo + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }
}
