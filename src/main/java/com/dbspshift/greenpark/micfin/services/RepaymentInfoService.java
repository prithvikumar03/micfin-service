package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;

import java.util.List;

public interface RepaymentInfoService {

    public RepaymentInfo registerRepaymentInfo(RepaymentInfo repaymentInfo) throws Exception;

    public RepaymentInfo getRepaymentInfoById(String id) throws Exception;

    public List<RepaymentInfo> getAllRepaymentInfoByMicroEntrepreneurId(String id) throws Exception;

    //public String deleteRepaymentInfo(String id) throws Exception;
}
