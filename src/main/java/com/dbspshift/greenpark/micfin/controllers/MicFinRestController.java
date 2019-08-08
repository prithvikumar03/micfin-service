package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.services.MFIService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gayathrig on 15/07/2019.
 */
@Controller
@RequestMapping("/micfin/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class MicFinRestController {

    private final Logger log = LogManager.getLogger(MicFinRestController.class);
    @Autowired
    private MFIService mfiService;



    @RequestMapping(method = RequestMethod.POST, path = "/mfi")
    public @ResponseBody
    MFI registerMFI(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in registerMFI" + mfi);
        return (mfiService.registerMFI(mfi));

    }

    @RequestMapping(method = RequestMethod.GET, path = "/mfi/{id}")
    public @ResponseBody
    String findMFIById(@PathVariable String id) throws Exception {
        log.debug("Request received in findMFIById for " + id);
        return String.valueOf((mfiService.findMFIById(id)));
    }

}
