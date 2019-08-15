package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.services.MFIService;
import com.dbspshift.greenpark.micfin.services.MicroEntrepreneurService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gayathrig on 15/07/2019.
 */

@Controller
@RequestMapping("/micfin/api/")
//@CrossOrigin(origins = "http://localhost:4200")
public class MicFinRestController {

    private final Logger log = LogManager.getLogger(MicFinRestController.class);

    private MFIService mfiService;
    private MicroEntrepreneurService microEntrepreneurService;

    @Autowired
    public MicFinRestController(MFIService mfiService, MicroEntrepreneurService microEntrepreneurService) {
        this.mfiService = mfiService;
        this.microEntrepreneurService = microEntrepreneurService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public @ResponseBody String NoRequest(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Welcome to DBS MicFin " + authentication.getName();
    }

    //Register and MFI.
    @RequestMapping(method = RequestMethod.POST, path = "/mfi")
    public @ResponseBody MFI registerMicroEntrepreneur(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in register MFI" + mfi);
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

    //-------------------------------------------MICRO ENTREPRENEURS CALLS--------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, path= "/mfi/{id}/micro-entrepreneurs")
    public @ResponseBody List<MicroEntrepreneur> getAllMicroEntrepreneurs(@PathVariable String id) throws Exception{
        log.debug("Request received in getAllMicroEntrepreneurs");
        return microEntrepreneurService.getAllMicroEntrepreneursByMFIId(id);
    }

    //Register and MFI.
    @RequestMapping(method = RequestMethod.POST, path = "/mfi/{id}/micro-entrepreneur")
    public @ResponseBody MicroEntrepreneur registerMicroEntrepreneur(@RequestBody MicroEntrepreneur microEntrepreneur) throws Exception {
        log.debug("Request received in register micro entrepreneur" + microEntrepreneur);
        return (microEntrepreneurService.registerMicroEntrepreneur(microEntrepreneur));
    }

    //Get a particular micro entrepreneur.
    @RequestMapping(method = RequestMethod.GET, path= "/mfi/micro-entrepreneurs/{microEntId}")
    public @ResponseBody MicroEntrepreneur getMicroEntrepreneur(@PathVariable String microEntId) throws Exception{
        log.debug("Request received in getMicroEntrepreneur" + microEntId);
        return (microEntrepreneurService.getMicroEntrepreneurById(microEntId));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/mfi/micro-entrepreneurs/{microEntId")
    public @ResponseBody MicroEntrepreneur updateMicroEntrepreneur(@RequestBody MicroEntrepreneur microEntrepreneur) throws Exception{
        log.debug("Request received in updateMicroEntrepreneur" + microEntrepreneur);
        return (microEntrepreneurService.updateMicroEntrepreneur(microEntrepreneur));
    }

}
