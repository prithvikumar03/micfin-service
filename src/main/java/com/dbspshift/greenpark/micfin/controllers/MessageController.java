package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.proxy.MessageProxy;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/micfin/sms")
@Log4j2
public class MessageController {

    private final Logger log = LogManager.getLogger(MessageController.class);

    @Autowired
    private MessageProxy messageProxy;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public @ResponseBody String NoRequest(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Welcome to SMS " + authentication.getName();
    }

    @PostMapping("/subscribe/{phoneNumber}")
    public ResponseEntity<String> subscribe(@NotNull @PathVariable String phoneNumber) {
        ResponseEntity<String> response = messageProxy.subscribeToSMSTopic(phoneNumber);
        log.info(response.getStatusCode());
        return response;
    }

    @PostMapping("/unsubscribe/{phoneNumber}")
    public ResponseEntity<String> unsubscribe(@NotNull @PathVariable String phoneNumber) {
        ResponseEntity<String> response = messageProxy.unsubscribeToSMSTopic(phoneNumber);
        log.info(response.getStatusCode());
        return response;
    }

    @PostMapping("/bulksms")
    public ResponseEntity<Boolean> sendSMSToSubscribers() {
        ResponseEntity<Boolean> response = messageProxy.sendSMSToSubscribers();
        log.info(response.getStatusCode());
        return response;
    }

    @PostMapping("/{phoneNumber}")
    public ResponseEntity<String> sendSMSToIndividuals(@NotNull @PathVariable String phoneNumber) {
        ResponseEntity<String> response = messageProxy.sendSMSToIndividuals(phoneNumber);
        log.info(response.getStatusCode());
        return response;
    }
}
