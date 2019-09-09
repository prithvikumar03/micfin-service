package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.LoanSchedule;

import java.util.List;
import java.util.Optional;

public interface CustomLoanInfoRepository<T,Id> {

    public Optional<LoanInfo> findByLoanId(String loanId);

    public Optional<LoanInfo> findByMicroEntrepreneurId(String microEntrepreneurId);

    public Optional<List<LoanInfo>> findByMfiId(String mfiId);

    public Optional<List<LoanSchedule>> getLoanSchedule(String loanId);

    public Optional<List<LoanInfo>> findByMfiIdMicroEntrepreneurId(String mfiId,String microEntrepreneurId);
}
