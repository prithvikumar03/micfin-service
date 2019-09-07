package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import com.dbspshift.greenpark.micfin.exceptions.LoanInfoNotFoundException;

import java.util.List;

public interface LoanInfoService {

    public LoanInfo registerLoanInfo(LoanInfo LoanInfo) throws Exception;

    public LoanInfo getLoanInfoByLoanId(String id) throws Exception;

    public List<LoanInfo> getAllLoanInfos() throws Exception;

    public LoanInfo updateLoanInfo(LoanInfo LoanInfo) throws Exception;

    public String deleteLoanInfo(String id) throws Exception;

    public List<LoanInfo> getAllLoanInfosForMFI(String mfiId) throws Exception;

    List<LoanSchedule> getLoanScheduleForLoanId(String loanId) throws LoanInfoNotFoundException;
}
