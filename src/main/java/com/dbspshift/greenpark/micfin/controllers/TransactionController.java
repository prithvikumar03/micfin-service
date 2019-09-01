package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
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
public class TransactionController {

    private final Logger log = LogManager.getLogger(TransactionController.class);
    private RepaymentInfoService repaymentInfoService;
    private LoanInfoService loanInfoService;
    private ProductService productService;

    @Autowired
    public TransactionController(
            RepaymentInfoService repaymentInfoService,
            LoanInfoService loanInfoService) {
        this.repaymentInfoService = repaymentInfoService;
        this.loanInfoService = loanInfoService;
    }

    //-------------------------------------------LOAN DISBURSAL--------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, path= "/loan/{id}")
    public @ResponseBody
    LoanInfo getLoanInfo(@PathVariable String id) throws Exception{
        log.debug("Request received in getTransaction");
        return loanInfoService.getLoanInfoById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path="/loan")
    public @ResponseBody
    LoanInfo registerLoanInfo(@RequestBody LoanInfo loanInfo) throws Exception{
        log.debug("Request received in registerTransaction");
        return loanInfoService.registerLoanInfo(loanInfo);
    }

    @RequestMapping(method = RequestMethod.GET, path= "/loans")
    public @ResponseBody
    List<LoanInfo> getAllLoanInfos() throws Exception{
        log.debug("Request received in getTransaction");
        return loanInfoService.getAllLoanInfos();
    }

    @RequestMapping(method = RequestMethod.GET, path= "/loans/{mfiId}")
    public @ResponseBody
    List<LoanInfo> getAllLoanInfosForMFI(@PathVariable String mfiId) throws Exception{
        log.debug("Request received in getTransaction");
        return loanInfoService.getAllLoanInfosForMFI(mfiId);
    }


    //-------------------------------------------REPAYMENT CALLS--------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, path= "/repayment/{id}")
    public @ResponseBody
    RepaymentInfo getRepaymentInfo(@PathVariable String id) throws Exception{
        log.debug("Request received in getTransaction");
        return repaymentInfoService.getRepaymentInfoById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path="/repayment/{id}")
    public @ResponseBody
    RepaymentInfo registerRepaymentInfo(@RequestBody RepaymentInfo repaymentInfo) throws Exception{
        log.debug("Request received in registerTransaction");
        return repaymentInfoService.registerRepaymentInfo(repaymentInfo);
    }

    @RequestMapping(method = RequestMethod.GET, path= "/repayment/mfi/{id}")
    public @ResponseBody
    List<RepaymentInfo> getAllRepaymentInfoForMfi(@PathVariable String id) throws Exception{
        log.debug("Request received in getTransaction");
        return repaymentInfoService.getAllRepaymentInfoByMicroEntrepreneurId(id);
    }
    //micro-entrepreneurs
}
