package com.dbspshift.greenpark.micfin.beans;

import java.util.Date;

public class LoanSchedule {
  Date repaymentDate;
  Integer monthlyPayment;
  Integer rebate;

  public Date getRepaymentDate() {
    return repaymentDate;
  }

  public void setRepaymentDate(Date repaymentDate) {
    this.repaymentDate = repaymentDate;
  }

  public Integer getMonthlyPayment() {
    return monthlyPayment;
  }

  public void setMonthlyPayment(Integer monthlyPayment) {
    this.monthlyPayment = monthlyPayment;
  }

  public Integer getRebate() {
    return rebate;
  }

  public void setRebate(Integer rebate) {
    this.rebate = rebate;
  }
}
