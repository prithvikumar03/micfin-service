package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.services.MFIService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gayathrig on 15/07/2019.
 */

@RestController
@RequestMapping("/micfin/api/")
//@CrossOrigin(origins = "http://localhost:4200")
public class MicFinRestController {

    private final Logger log = LogManager.getLogger(MicFinRestController.class);

    private MFIService mfiService;

    public MicFinRestController(MFIService mfiService) {
        this.mfiService = mfiService;
    }

    @RequestMapping("/")
    public String getIndexPage(){
        return "index";
    }

    //@Bank Get all the MFI details
    @RequestMapping(method = RequestMethod.POST, path = "/mfi")
    public @ResponseBody MFI registerMFI(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in registerMFI" + mfi);
        return (mfiService.registerMFI(mfi));
    }

    //@BankGet all the MFI details
    @RequestMapping(method = RequestMethod.GET, path= "/mfis")
    public @ResponseBody List<MFI> getAllMFIs() throws Exception{
        log.debug("Request received in getAllMFIs");
        return mfiService.getAllMFIs();
    }

    //@Bank 1. This api is for the Bank to get details about the MFI.
    //@MFI 2. For the MFI to get their own details
    @RequestMapping(method = RequestMethod.GET, path = "/mfi/{id}")
    public @ResponseBody String findMFIById(@PathVariable String id) throws Exception {
        log.debug("Request received in getMFIById for " + id);
        return String.valueOf((mfiService.getMFIById(id)));
    }

    //@Bank 1. This api is for the Bank to update details about the MFI.
    //@MFI 2. For the MFI to update their own details
    @RequestMapping(method = RequestMethod.PUT, path = "/mfi")
    public @ResponseBody MFI updateMFI(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in updateMFI for ");
        return mfiService.updateMFI(mfi);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/mfi/{id}")
    public @ResponseBody String deleteMFIById(@PathVariable String id) throws Exception{
        log.debug("Request received to delete MFI for " + id);
        return mfiService.deleteMFI(id);
    }
    //@RequestMapping(method = RequestMethod.GET, path= "/mfi/{id}/micro-entrepreneurs")

    //@RequestMapping(method = RequestMethod.GET, path= "/mfi/{id}/micro-entrepreneurs/{id}")

}
