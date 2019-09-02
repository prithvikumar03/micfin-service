package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;

public interface CustomLoanInfoRepository<T,Id> {

    public LoanInfo findByLoanId(String loanId);

    public LoanInfo findByMicroEntrepreneurId(String microEntrepreneurId);
}
