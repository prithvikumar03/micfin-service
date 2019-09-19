package com.dbspshift.greenpark.micfin.Others;

import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Component
public class LoanCalculationsManager {

    private DecimalFormat df2 = new DecimalFormat("#.##");
    private Double loanAmount;
    private Double interest;
    private Integer tenure;

    public LoanCalculationsManager(Double loanAmount, Double interest, Integer tenure) {
        this.loanAmount = loanAmount;
        this.interest = interest;
        this.tenure = tenure;
    }

    public LoanCalculationsManager() {
    }

    public double getEmi(double p, double r, double t)
    {
        r=r/(100*12);
        double e= (p*r*Math.pow(1+r,t))/(Math.pow(1+r,t)-1);
        return e;
    }

    public void getLoanSchedule(double emi, double startBal, double interestRate, List<LoanSchedule> loanScheduleList){
        if(startBal<emi){
            return;
        }

        double v = (startBal * interestRate) / 1200;
        LoanSchedule loanSchedule = new LoanSchedule();
        loanSchedule.setInterestAmtPaid(v);
        double principalRepayed = emi - v;
        loanSchedule.setAmtRepayPrincipal(principalRepayed);
        loanSchedule.setEndingBalance(startBal- (principalRepayed));
        loanScheduleList.add(loanSchedule);

        getLoanSchedule(emi,startBal- (principalRepayed),interestRate,loanScheduleList);

    }

    public void getLoanSchedule(double emi, double startBal, double interestRate, Date startDate, List<LoanSchedule> loanScheduleList) throws Exception{
        if(startBal<emi){
            return;
        }

        double v = (startBal * interestRate) / 1200;
        LoanSchedule loanSchedule = new LoanSchedule();
        loanSchedule.setInterestAmtPaid(v);
        double principalRepayed = emi - v;
        loanSchedule.setAmtRepayPrincipal(principalRepayed);
        loanSchedule.setEndingBalance(startBal- (principalRepayed));

        Date dateRepayment = DateUtils.addMonths(startDate,1);
        loanSchedule.setRepaymentDate(dateRepayment);
        loanScheduleList.add(loanSchedule);

        getLoanSchedule(emi,startBal- (principalRepayed),interestRate,dateRepayment,loanScheduleList);

    }

    public double getInterest(double startBal, double interestRate){
        double v = (startBal * interestRate) / 1200;
        return v;
    }
}


//long startDatems = startDate.getTime();
//long nextRepaymentDatems = startDatems + (30 * 24 * 3600 * 1000);
//Date dateRepayment = new Date(nextRepaymentDatems);
