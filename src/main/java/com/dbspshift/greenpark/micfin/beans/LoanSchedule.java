package com.dbspshift.greenpark.micfin.beans;

import java.util.Date;

public class LoanSchedule {
  Date repaymentDate;
  Double monthlyPayment;
  Double interestAmtPaid;
  Double amtRepayPrincipal;
  Double endingBalance;

  Integer rebate;

  public Date getRepaymentDate() {
    return repaymentDate;
  }

  public void setRepaymentDate(Date repaymentDate) {
    this.repaymentDate = repaymentDate;
  }

  public Double getMonthlyPayment() {
    return monthlyPayment;
  }

  public void setMonthlyPayment(Double monthlyPayment) {
    this.monthlyPayment = monthlyPayment;
  }

  public Integer getRebate() {
    return rebate;
  }

  public void setRebate(Integer rebate) {
    this.rebate = rebate;
  }

  public Double getInterestAmtPaid() {
    return interestAmtPaid;
  }

  public void setInterestAmtPaid(Double interestAmtPaid) {
    this.interestAmtPaid = interestAmtPaid;
  }

  public Double getAmtRepayPrincipal() {
    return amtRepayPrincipal;
  }

  public void setAmtRepayPrincipal(Double amtRepayPrincipal) {
    this.amtRepayPrincipal = amtRepayPrincipal;
  }

  public Double getEndingBalance() {
    return endingBalance;
  }

  public void setEndingBalance(Double endingBalance) {
    this.endingBalance = endingBalance;
  }
}
