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
    this.monthlyPayment = Double.valueOf(Math.round(monthlyPayment));
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
    this.interestAmtPaid = Double.valueOf(Math.round(interestAmtPaid));
  }

  public Double getAmtRepayPrincipal() {
    return amtRepayPrincipal;
  }

  public void setAmtRepayPrincipal(Double amtRepayPrincipal) {
    this.amtRepayPrincipal = Double.valueOf(Math.round(amtRepayPrincipal));
  }

  public Double getEndingBalance() {
    return endingBalance;
  }

  public void setEndingBalance(Double endingBalance) {
    this.endingBalance = Double.valueOf(Math.round(endingBalance));
  }
}
