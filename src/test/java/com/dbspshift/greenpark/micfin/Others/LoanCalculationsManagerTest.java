package com.dbspshift.greenpark.micfin.Others;

import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class LoanCalculationsManagerTest {

    LoanCalculationsManager loanCalculationsManager = new LoanCalculationsManager();

    @Test
    void emiCalculation() {
        double v = loanCalculationsManager.getEmi(100000, 0.005, 360);
        System.out.println(v);
    }

    List<LoanSchedule> loanScheduleList = new ArrayList<>();
    @Test
    void getLoanSchedule() {
        loanCalculationsManager.getLoanSchedule(599.55,100000,6,loanScheduleList);
        for(LoanSchedule loanSchedule: loanScheduleList){
            System.out.println(loanSchedule);
        }
    }

}