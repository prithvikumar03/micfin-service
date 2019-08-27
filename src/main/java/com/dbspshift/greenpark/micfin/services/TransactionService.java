package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.Transaction;

import java.util.List;

public interface TransactionService {

    public Transaction registerTransaction(Transaction transaction) throws Exception;

    public Transaction getTransactionById(String id) throws Exception;

    public List<Transaction> getAllTransactionsByMicroEntrepreneurId() throws Exception;

    public Transaction updateMFI(Transaction transaction) throws Exception;

    public String deleteTransaction(String id) throws Exception;
}
