package com.dbspshift.greenpark.micfin.Others;

import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanCalculationsManager {

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

    public double getInterest(double startBal, double interestRate){
        double v = (startBal * interestRate) / 1200;
        return v;
    }
}
