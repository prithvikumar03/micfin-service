package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.Transaction;
import com.dbspshift.greenpark.micfin.services.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/micfin/transaction/")
public class TransactionController {

    private final Logger log = LogManager.getLogger(TransactionController.class);
    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //-------------------------------------------TRANSACTION CALLS--------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, path= "/{id}")
    public @ResponseBody Transaction getTransaction(@PathVariable String id) throws Exception{
        log.debug("Request received in getTransaction");
        return transactionService.getTransactionById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path="/{id}")
    public @ResponseBody Transaction registerTransaction(@RequestBody Transaction transaction) throws Exception{
        log.debug("Request received in registerTransaction");
        return transactionService.registerTransaction(transaction);
    }

    @RequestMapping(method = RequestMethod.GET, path= "/mfi/{id}")
    public @ResponseBody Transaction getAllTransactionsForMfi(@PathVariable String id) throws Exception{
        log.debug("Request received in getTransaction");
        return transactionService.getTransactionById(id);
    }
    //micro-entrepreneurs
}
