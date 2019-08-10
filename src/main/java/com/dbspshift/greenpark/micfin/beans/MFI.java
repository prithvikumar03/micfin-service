package com.dbspshift.greenpark.micfin.beans;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by gayathrig on 24/07/2019.
 */

@Document (value = "MFI")
public class MFI {

    @Id
    private String id;
    private String name;
    private String fullName;
    private Address address;

    public MFI(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
    }

    public MFI(String id, String name, String fullName, Address address) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address=" + address +
                '}';
    }
}
