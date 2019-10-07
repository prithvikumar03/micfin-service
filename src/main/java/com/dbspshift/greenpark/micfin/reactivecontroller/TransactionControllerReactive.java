package com.dbspshift.greenpark.micfin.reactivecontroller;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import com.dbspshift.greenpark.micfin.reactiveservices.LoanInfoReactiveService;
import com.dbspshift.greenpark.micfin.reactiveservices.RepaymentInfoReactiveService;
//import com.dbspshift.greenpark.micfin.services.LoanInfoService;
//import com.dbspshift.greenpark.micfin.services.RepaymentInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

@Controller
@RequestMapping("/micfinreactive/transaction/")
@CrossOrigin(origins = "*")
public class TransactionControllerReactive {

    private final Logger log = LogManager.getLogger(TransactionControllerReactive.class);
    private RepaymentInfoReactiveService repaymentInfoService;
    private LoanInfoReactiveService loanInfoService;
    //private ProductService productService;

    @Autowired
    public TransactionControllerReactive(
            RepaymentInfoReactiveService repaymentInfoService,
            LoanInfoReactiveService loanInfoService) {
        this.repaymentInfoService = repaymentInfoService;
        this.loanInfoService = loanInfoService;
    }

    //-------------------------------------------LOAN DISBURSAL--------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, path= "/loan/{loanId}")
    public @ResponseBody
    Mono<LoanInfo> getLoanInfoByLoanId(@PathVariable String loanId) throws Exception{
        log.debug("Request received in getLoanInfoByLoanId");
        return loanInfoService.getLoanInfoByLoanId(loanId);
    }

    @RequestMapping(method = RequestMethod.POST, path="/loan")
    public @ResponseBody
    Mono<LoanInfo> registerLoanInfo(@RequestBody LoanInfo loanInfo) throws Exception{
        log.debug("Request received in registerLoanInfo");
        return loanInfoService.registerLoanInfo(loanInfo);
    }

    @RequestMapping(method = RequestMethod.GET, path= "/loans")
    public @ResponseBody
    Flux<LoanInfo> getAllLoanInfos() throws Exception{
        log.debug("Request received in getAllLoanInfos");
        return loanInfoService.getAllLoanInfos();
    }

    @RequestMapping(method = RequestMethod.GET, path= "/mfi/{mfiId}/loans")
    public @ResponseBody
    Flux<LoanInfo> getAllLoanInfosForMFI(@PathVariable String mfiId) throws Exception{
        log.debug("Request received in getAllLoanInfosForMFI");
        return loanInfoService.getAllLoanInfosForMFI(mfiId);
    }

    @RequestMapping(method = RequestMethod.GET, path= "/loan/{loanId}/loanSchedule")
    public @ResponseBody
    Flux<LoanSchedule> getLoanScheduleForLoanId(@PathVariable String loanId) throws Exception{
        log.debug("Request received in getLoanScheduleForLoanId");
        return loanInfoService.getLoanScheduleForLoanId(loanId);
    }

    @RequestMapping(method = RequestMethod.GET, path= "/mfi/{mfiId}/micro-entrepreneur/{microEntId}/loans")
    public @ResponseBody
    Flux<LoanInfo> getAllLoanInfosForMfiForMicroEntrepreneur(@PathVariable String mfiId,@PathVariable String microEntId) throws Exception{
        log.debug("Request received in getAllLoanInfosForMFI");
        return loanInfoService.getAllLoanInfosForMfiAndMicroEntrepreneur(mfiId,microEntId);
    }

    //-------------------------------------------REPAYMENT CALLS--------------------------------------------------

    @RequestMapping(method = RequestMethod.POST, path="/repayment")
    public @ResponseBody
    Mono<RepaymentInfo> registerRepaymentInfo(@RequestBody RepaymentInfo repaymentInfo) throws Exception{
        log.debug("Request received in registerTransaction");
        return repaymentInfoService.registerRepaymentInfo(repaymentInfo);
    }

    //all repayments done for a micro-entrepreneur
    @RequestMapping(method = RequestMethod.GET, path= "micro-entrepreneur/{microEntId}/repayments")
    public @ResponseBody
    Flux<RepaymentInfo> getAllRepaymentInfoForMicroEntrepreneur(@PathVariable String microEntId) throws Exception{
        log.debug("Request received in getTransaction");
        return repaymentInfoService.getAllRepaymentInfoByMicroEntrepreneurId(microEntId);
    }

}
