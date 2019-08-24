package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.proxy.MessageProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/micfin/sms")
@Log4j2
public class MessageController {

    @Autowired
    private MessageProxy messageProxy;

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
