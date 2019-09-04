package com.dbspshift.greenpark.micfin.controllers;

//import com.dbspshift.greenpark.micfin.repository.AddressRepository;

//import reactor.core.Disposable;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

/**
 * Created by gayathrig on 15/07/2019.
 */

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.repository.AddressRepository;
import com.dbspshift.greenpark.micfin.repository.MFIReactiveRepository;
import com.dbspshift.greenpark.micfin.services.MFIReactiveService;
import com.dbspshift.greenpark.micfin.services.MFIService;
import com.dbspshift.greenpark.micfin.services.MicroEntrepreneurReactiveService;
import com.dbspshift.greenpark.micfin.services.MicroEntrepreneurService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("/micfinreactive/api/")
@CrossOrigin(origins = "*")
public class MicFinRestControllerReactive {

    private final Logger log = LogManager.getLogger(MicFinRestControllerReactive.class);

    @Autowired
    private MFIReactiveService mfiReactiveService;

    @Autowired
    private AddressRepository addressRepository;

    private MicroEntrepreneurReactiveService microEntrepreneurReactiveService;

    @Autowired
    public MicFinRestControllerReactive(MFIReactiveService mfiReactiveService, MicroEntrepreneurReactiveService microEntrepreneurReactiveService) {
        this.mfiReactiveService = mfiReactiveService;
        this.microEntrepreneurReactiveService = microEntrepreneurReactiveService;
    }

    @RequestMapping("/")
    public String getIndexPage(){
        return "index";
    }

    //@Bank Get all the MFI details
    @PostMapping(path = "/mfi")
    public @ResponseBody
    Mono<MFI> registerMFI(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in registerMFI" + mfi);
        //return (mfiService.registerMFI(mfi));
        Mono<MFI> mfiMono = addressRepository.save(mfi.getAddress()).then(mfiReactiveService.registerMFI(mfi));

        Disposable disposable = mfiMono.subscribe(); // to save should subscribe
        log.info("Disposed? " + disposable.isDisposed());
        return mfiMono;
    }

    //@BankGet all the MFI details
    @GetMapping(path= "/mfis")
    public @ResponseBody
    Flux<MFI> getAllMFIs() throws Exception{
        log.debug("Request received in getAllMFIs");
        return mfiReactiveService.getAllMFIs();
    }

    //@Bank 1. This api is for the Bank to get details about the MFI.
    //@MFI 2. For the MFI to get their own details
    @GetMapping(path = "/mfi/{id}")
    public @ResponseBody Mono<ResponseEntity<MFI>> findMFIById(@PathVariable String id) throws Exception {
        log.debug("Request received in getMFIById for " + id);
        return mfiReactiveService.getMFIById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //@Bank 1. This api is for the Bank to update details about the MFI.
    //@MFI 2. For the MFI to update their own details
    @PutMapping(path = "/mfi")
    public @ResponseBody Mono<ResponseEntity<MFI>>  updateMFI(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in updateMFI for ");
        return mfiReactiveService.updateMFI(mfi)
                .map(updatedMFI -> new ResponseEntity<>(updatedMFI, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/mfi/{id}")
    public @ResponseBody Mono<ResponseEntity<Void>>  deleteMFIById(@PathVariable String id) throws Exception{
        log.debug("Request received to delete MFI for " + id);
        return mfiReactiveService.deleteMFI(id)
                .map(deletedMFI -> new ResponseEntity<Void>(HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //-------------------------------------------MICRO ENTREPRENEURS CALLS--------------------------------------------------
    @GetMapping(path= "/mfi/{id}/micro-entrepreneurs")
    public @ResponseBody
    Flux<MicroEntrepreneur> getAllMicroEntrepreneurs(@PathVariable String id) throws Exception{
        log.debug("Request received in getAllMicroEntrepreneurs");
        return microEntrepreneurReactiveService.getAllMicroEntrepreneursByMFIId(id);
    }

    //Register and MFI.
    @PostMapping(path = "/mfi/{id}/micro-entrepreneur")
    public @ResponseBody Mono<MicroEntrepreneur> registerMicroEntrepreneur(@RequestBody MicroEntrepreneur microEntrepreneur) throws Exception {
        log.debug("Request received in register micro entrepreneur" + microEntrepreneur);
        return (microEntrepreneurReactiveService.registerMicroEntrepreneur(microEntrepreneur));
    }

    //Get a particular micro entrepreneur.
    @GetMapping(path= "/mfi/micro-entrepreneurs/{microEntId}")
    public @ResponseBody Mono<MicroEntrepreneur> getMicroEntrepreneur(@PathVariable String microEntId) throws Exception{
        log.debug("Request received in getMicroEntrepreneur" + microEntId);
        return (microEntrepreneurReactiveService.getMicroEntrepreneurById(microEntId));
    }

    @PutMapping(path = "/mfi/micro-entrepreneurs/{microEntId")
    public @ResponseBody Mono<MicroEntrepreneur> updateMicroEntrepreneur(@RequestBody MicroEntrepreneur microEntrepreneur) throws Exception{
        log.debug("Request received in updateMicroEntrepreneur" + microEntrepreneur);
        return (microEntrepreneurReactiveService.updateMicroEntrepreneur(microEntrepreneur));
    }
}
