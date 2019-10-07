package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionControllerTest {

  /*  TransactionController transactionController;

    @Mock
    LoanInfoService loanInfoService;
    @Mock
    RepaymentInfoService repaymentInfoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionController = new TransactionController(repaymentInfoService,loanInfoService);
    }

    @Test
    public void getLoanInfoByLoanId() throws Exception {
        LoanInfo loanInfo = new LoanInfo("L200","MFI1","OmGanesh Pte Ltd","ME101",20000,"P400","QuickCash",12,10);

        when(loanInfoService.getLoanInfoByLoanId("L200")).thenReturn(loanInfo);
        LoanInfo retValue = transactionController.getLoanInfoByLoanId("L200");
        assertTrue(retValue.getMfiId().contains("MFI1"));
        System.out.println(retValue);
    }

    @Test
    public void registerLoanInfo() throws Exception {
        LoanInfo loanInfo = new LoanInfo("L200","MFI1","OmGanesh Pte Ltd","ME101",20000,"P400","QuickCash",12,10);

        when(loanInfoService.registerLoanInfo(loanInfo)).thenReturn(loanInfo);
        LoanInfo retValue = transactionController.registerLoanInfo(loanInfo);
        assertTrue(retValue.getMfiId().contains("MFI1"));
        System.out.println(retValue);
    }

    @Test
    public void getAllLoanInfos() throws Exception {
        LoanInfo loanInfo = new LoanInfo("L200","MFI1","OmGanesh Pte Ltd","ME101",20000,"P400","QuickCash",12,10);
        LoanInfo loanInfo1 = new LoanInfo("L201","MFI2","Krishna Pte Ltd","ME105",60000,"P403","QuickCash1",24,15);

        List<LoanInfo> loanInfoList = new ArrayList<>();
        loanInfoList.add(loanInfo);
        loanInfoList.add(loanInfo1);

        when(loanInfoService.getAllLoanInfos()).thenReturn(loanInfoList);
        List<LoanInfo> retValue = transactionController.getAllLoanInfos();
        assertTrue(retValue.size()==2);
        System.out.println(retValue);
    }

    @Test
    public void getAllLoanInfosForMFI() throws Exception {
        LoanInfo loanInfo = new LoanInfo("L200","MFI1","OmGanesh Pte Ltd","ME101",20000,"P400","QuickCash",12,10);
        LoanInfo loanInfo1 = new LoanInfo("L201","MFI2","Krishna Pte Ltd","ME105",60000,"P403","QuickCash1",24,15);
        LoanInfo loanInfo2 = new LoanInfo("L202","MFI2","Krishna Pte Ltd","ME107",80000,"P403","QuickCash1",24,15);

        List<LoanInfo> loanInfoList = new ArrayList<>();
        loanInfoList.add(loanInfo);
        loanInfoList.add(loanInfo1);
        loanInfoList.add(loanInfo2);

        when(loanInfoService.getAllLoanInfosForMFI("MFI2")).thenReturn(loanInfoList.subList(1,3));
        List<LoanInfo> retValue = transactionController.getAllLoanInfosForMFI("MFI2");
        assertTrue(retValue.size()==2);
        System.out.println(retValue);
    }

    @Test
    public void getLoanScheduleForLoanId() {
    }

    @Test
    public void registerRepaymentInfo() {
    }

    @Test
    public void getAllRepaymentInfoForMicroEntrepreneur() {
    }*/

}