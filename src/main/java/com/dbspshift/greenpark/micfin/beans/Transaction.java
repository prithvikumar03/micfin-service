package com.dbspshift.greenpark.micfin.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Transactions")
public class Transaction {

    @Id
    private String id;
    private String microEntrepreneurId;
    private String mfiId;
    private boolean isCredit;
    private boolean isDebit;
    private Integer amount;
    private Integer paymentDelayedInMonths;

}
