package com.dbspshift.greenpark.micfin.reactivecontroller;

/**
 * Created by gayathrig on 15/07/2019.
 */
import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.reactiveservices.MFIReactiveService;
import com.dbspshift.greenpark.micfin.reactiveservices.MicroEntrepreneurReactiveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Controller
@RequestMapping("/micfinreactive/api/")
@CrossOrigin(origins = "*")
public class MicFinRestControllerReactive {

    private final Logger log = LogManager.getLogger(MicFinRestControllerReactive.class);

    private MFIReactiveService mfiReactiveService;
    private MicroEntrepreneurReactiveService microEntrepreneurReactiveService;


    @Autowired
    public MicFinRestControllerReactive(MFIReactiveService mfiReactiveService, MicroEntrepreneurReactiveService microEntrepreneurReactiveService) {
        this.mfiReactiveService = mfiReactiveService;
        this.microEntrepreneurReactiveService = microEntrepreneurReactiveService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public @ResponseBody String NoRequest(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Welcome to DBS MicFin " + authentication.getName();
    }

    //@Bank Get all the MFI details
    @RequestMapping(method = RequestMethod.POST, path = "/mfi")
    public @ResponseBody Mono<MFI> registerMFI(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in registerMFI" + mfi);
        return mfiReactiveService.registerMFI(mfi);
    }

    //@BankGet all the MFI details
    @RequestMapping(method = RequestMethod.GET, path= "/mfis")
    public @ResponseBody Flux<MFI> getAllMFIs() throws Exception{
        log.debug("Request received in getAllMFIs");
        return mfiReactiveService.getAllMFIs();
    }

    //@Bank 1. This api is for the Bank to get details about the MFI.
    //@MFI 2. For the MFI to get their own details
    @RequestMapping(method = RequestMethod.GET, path = "/mfi/{mfiId}")
    public @ResponseBody Mono<ResponseEntity<MFI>> findMFIById(@PathVariable String mfiId) throws Exception {
        log.debug("Request received in getMFIById for " + mfiId);
        return mfiReactiveService.getMFIById(mfiId)
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

    @DeleteMapping(path = "/mfi/{mfiId}")
    public @ResponseBody Mono<ResponseEntity<Void>>  deleteMFIById(@PathVariable String mfiId) throws Exception{
        log.debug("Request received to delete MFI for " + mfiId);
        return mfiReactiveService.deleteMFI(mfiId)
                .map(deletedMFI -> new ResponseEntity<Void>(HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //-------------------------------------------MICRO ENTREPRENEURS CALLS--------------------------------------------------
    @GetMapping(path= "/mfi/{mfiId}/micro-entrepreneurs")
    public @ResponseBody
    Flux<MicroEntrepreneur> getAllMicroEntrepreneurs(@PathVariable String mfiId) throws Exception{
        log.debug("Request received in getAllMicroEntrepreneurs");
        return microEntrepreneurReactiveService.getAllMicroEntrepreneursByMFIId(mfiId);
    }

    //Register and MFI.
    @PostMapping(path = "/mfi/{mfiId}/micro-entrepreneur")
    public @ResponseBody Mono<MicroEntrepreneur> registerMicroEntrepreneur(@RequestBody MicroEntrepreneur microEntrepreneur) throws Exception {
        log.debug("Request received in register micro entrepreneur" + microEntrepreneur);
        return (microEntrepreneurReactiveService.registerMicroEntrepreneur(microEntrepreneur));
    }

    //Get a particular micro entrepreneur.
    @GetMapping(path= "/micro-entrepreneur/{microEntId}")
    public @ResponseBody Mono<MicroEntrepreneur> getMicroEntrepreneur(@PathVariable String microEntId) throws Exception{
        log.debug("Request received in getMicroEntrepreneur" + microEntId);
        return (microEntrepreneurReactiveService.getMicroEntrepreneurByMicroEntrepreneurId(microEntId));
    }

    @PutMapping(path = "/mfi/micro-entrepreneur/{microEntId")
    public @ResponseBody Mono<MicroEntrepreneur> updateMicroEntrepreneur(@RequestBody MicroEntrepreneur microEntrepreneur) throws Exception{
        log.debug("Request received in updateMicroEntrepreneur" + microEntrepreneur);
        return (microEntrepreneurReactiveService.updateMicroEntrepreneur(microEntrepreneur));
    }
}
