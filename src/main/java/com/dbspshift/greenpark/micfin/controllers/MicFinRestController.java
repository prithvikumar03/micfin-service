package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.services.MFIService;
import com.dbspshift.greenpark.micfin.services.MicroEntrepreneurService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
/**
 * Created by gayathrig on 15/07/2019.
 */

@Controller
@RequestMapping("/micfin/api/")
@CrossOrigin(origins = "*")
public class MicFinRestController {

    private final Logger log = LogManager.getLogger(MicFinRestController.class);

    private MFIService mfiService;
    private MicroEntrepreneurService microEntrepreneurService;

    @Value("${sms.service.url}")
    private String smsUrl;

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
    public @ResponseBody MFI registerMfi(@RequestBody MFI mfi) throws Exception {
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
    @RequestMapping(method = RequestMethod.GET, path = "/mfi/{mfiId}")
    public @ResponseBody MFI findMFIById(@PathVariable String mfiId) throws Exception {
        log.debug("Request received in getMFIById for " + mfiId);
        //return String.valueOf((mfiService.getMFIById(mfiId)));
        return mfiService.getMFIById(mfiId);
    }

    //@Bank 1. This api is for the Bank to update details about the MFI.
    //@MFI 2. For the MFI to update their own details
    @RequestMapping(method = RequestMethod.PUT, path = "/mfi")
    public @ResponseBody MFI updateMFI(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in updateMFI for ");
        return mfiService.updateMFI(mfi);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/mfi/{mfiId}")
    public @ResponseBody String deleteMFIById(@PathVariable String mfiId) throws Exception{
        log.debug("Request received to delete MFI for " + mfiId);
        return mfiService.deleteMFI(mfiId);
    }

    //-------------------------------------------MICRO ENTREPRENEURS CALLS--------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, path= "/mfi/{mfiId}/micro-entrepreneurs")
    public @ResponseBody List<MicroEntrepreneur> getAllMicroEntrepreneurs(@PathVariable String mfiId) throws Exception{
        log.debug("Request received in getAllMicroEntrepreneurs");
        return microEntrepreneurService.getAllMicroEntrepreneursByMFIId(mfiId);
    }

    //Register a Microentrepreneur.
    @RequestMapping(method = RequestMethod.POST, path = "/mfi/{mfiId}/micro-entrepreneur")
    public @ResponseBody MicroEntrepreneur registerMicroEntrepreneur(@RequestBody MicroEntrepreneur microEntrepreneur,@PathVariable String mfiId) throws Exception {
        log.debug("Request received in register micro entrepreneur" + microEntrepreneur);
        //Improvements - Check if the MFI exists before registrering the micro-entrepreneur.
        MicroEntrepreneur microEntrepreneur1 = microEntrepreneurService.registerMicroEntrepreneur(microEntrepreneur);
        try {
            if (microEntrepreneur.getPhoneBusiness() != null && !microEntrepreneur.getPhoneBusiness().isEmpty()) {
                log.info("Subscribe to MicFin and send SMS");
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    boolean translateToHindi = false;
                    ResponseEntity<ResponseEntity> response = restTemplate.postForEntity(smsUrl + "subscribe/" + microEntrepreneur.getPhoneBusiness(), null, ResponseEntity.class);
                    restTemplate.postForEntity(smsUrl + microEntrepreneur.getPhoneBusiness() + "/" + translateToHindi, null, ResponseEntity.class);
                } catch(Exception e) {
                    log.error("Error sending SMS");
                }
            }
        }
        catch(Exception e) {
            log.info("Sending SMS unsuccessful " + microEntrepreneur.getMicroEntrepreneurId());
        }
        return microEntrepreneur1;
    }

    //Get a particular micro entrepreneur.
    //@RequestMapping(method = RequestMethod.GET, path= "/mfi/micro-entrepreneurs/{microEntId}")
    @RequestMapping(method = RequestMethod.GET, path= "/micro-entrepreneur/{microEntId}")
    public @ResponseBody MicroEntrepreneur getMicroEntrepreneur(@PathVariable String microEntId) throws Exception{
        log.debug("Request received in getMicroEntrepreneur" + microEntId);
        return (microEntrepreneurService.getMicroEntrepreneurById(microEntId));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/micro-entrepreneur/{microEntId}")
    public @ResponseBody MicroEntrepreneur updateMicroEntrepreneur(@RequestBody MicroEntrepreneur microEntrepreneur) throws Exception{
        log.debug("Request received in updateMicroEntrepreneur" + microEntrepreneur);
        return (microEntrepreneurService.updateMicroEntrepreneur(microEntrepreneur));
    }
}
