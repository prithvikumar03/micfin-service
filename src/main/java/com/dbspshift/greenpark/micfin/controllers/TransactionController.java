package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import com.dbspshift.greenpark.micfin.services.LoanInfoService;
import com.dbspshift.greenpark.micfin.services.ProductService;
import com.dbspshift.greenpark.micfin.services.RepaymentInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/micfin/transaction/")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final Logger log = LogManager.getLogger(TransactionController.class);
    private RepaymentInfoService repaymentInfoService;
    private LoanInfoService loanInfoService;
    //private ProductService productService;

    @Autowired
    public TransactionController(
            RepaymentInfoService repaymentInfoService,
            LoanInfoService loanInfoService) {
        this.repaymentInfoService = repaymentInfoService;
        this.loanInfoService = loanInfoService;
    }

    //-------------------------------------------LOAN DISBURSAL--------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, path= "/loan/{loanId}")
    public @ResponseBody
    LoanInfo getLoanInfoByLoanId(@PathVariable String loanId) throws Exception{
        log.debug("Request received in getLoanInfoByLoanId");
        return loanInfoService.getLoanInfoByLoanId(loanId);
    }

    @RequestMapping(method = RequestMethod.POST, path="/loan")
    public @ResponseBody
    LoanInfo registerLoanInfo(@RequestBody LoanInfo loanInfo) throws Exception{
        log.debug("Request received in registerLoanInfo");
        return loanInfoService.registerLoanInfo(loanInfo);
    }

    @RequestMapping(method = RequestMethod.GET, path= "/loans")
    public @ResponseBody
    List<LoanInfo> getAllLoanInfos() throws Exception{
        log.debug("Request received in getAllLoanInfos");
        return loanInfoService.getAllLoanInfos();
    }

    @RequestMapping(method = RequestMethod.GET, path= "/mfi/{mfiId}/loans")
    public @ResponseBody
    List<LoanInfo> getAllLoanInfosForMFI(@PathVariable String mfiId) throws Exception{
        log.debug("Request received in getAllLoanInfosForMFI");
        return loanInfoService.getAllLoanInfosForMFI(mfiId);
    }

    @RequestMapping(method = RequestMethod.GET, path= "/loan/{loanId}/loanSchedule")
    public @ResponseBody
    List<LoanSchedule> getLoanScheduleForLoanId(@PathVariable String loanId) throws Exception{
        log.debug("Request received in getLoanScheduleForLoanId");
        return loanInfoService.getLoanScheduleForLoanId(loanId);
    }


    //-------------------------------------------REPAYMENT CALLS--------------------------------------------------
    /*@RequestMapping(method = RequestMethod.GET, path= "/repayment/{id}")
    public @ResponseBody
    RepaymentInfo getRepaymentInfo(@PathVariable String id) throws Exception{
        log.debug("Request received in getTransaction");
        return repaymentInfoService.getRepaymentInfoById(id);
    }*/

    @RequestMapping(method = RequestMethod.POST, path="/repayment")
    public @ResponseBody
    RepaymentInfo registerRepaymentInfo(@RequestBody RepaymentInfo repaymentInfo) throws Exception{
        log.debug("Request received in registerTransaction");
        return repaymentInfoService.registerRepaymentInfo(repaymentInfo);
    }

    /*@RequestMapping(method = RequestMethod.GET, path= "mfi/{id}/repayments")
    public @ResponseBody
    List<RepaymentInfo> getAllRepaymentInfoForMfi(@PathVariable String id) throws Exception{
        log.debug("Request received in getTransaction");
        return repaymentInfoService.getAllRepaymentInfoByMicroEntrepreneurId(id);
    }*/

    //all repayments done for a micro-entrepreneur
    @RequestMapping(method = RequestMethod.GET, path= "micro-entrepreneur/{microEntId}/repayments")
    public @ResponseBody
    List<RepaymentInfo> getAllRepaymentInfoForMicroEntrepreneur(@PathVariable String microEntId) throws Exception{
        log.debug("Request received in getTransaction");
        return repaymentInfoService.getAllRepaymentInfoByMicroEntrepreneurId(microEntId);
    }

    //all repayments done for a loan
}
