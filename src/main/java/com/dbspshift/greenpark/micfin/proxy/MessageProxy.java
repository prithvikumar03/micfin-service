package com.dbspshift.greenpark.micfin.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

@FeignClient(name="sms-service", url = "http://localhost:8002")
@RibbonClient(name="sms-service")
public interface MessageProxy {

    @PostMapping("/subscribe/{phoneNumber}")
    public ResponseEntity<String> subscribeToSMSTopic(@NotNull @PathVariable String phoneNumber);

    @PostMapping("/bulksms")
    public ResponseEntity<Boolean> sendSMSToSubscribers();

    @PostMapping("/{phoneNumber}")
    public ResponseEntity<String> sendSMSToIndividuals(@NotNull @PathVariable String phoneNumber);

    @PostMapping("/unsubscribe/{phoneNumber}")
    public ResponseEntity<String> unsubscribeToSMSTopic(@NotNull @PathVariable String phoneNumber);
}
