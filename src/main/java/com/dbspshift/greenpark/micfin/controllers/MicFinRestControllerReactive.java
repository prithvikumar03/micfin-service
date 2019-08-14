package com.dbspshift.greenpark.micfin.controllers;

//import com.dbspshift.greenpark.micfin.repository.AddressRepository;

//import reactor.core.Disposable;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;

/**
 * Created by gayathrig on 15/07/2019.
 */

//@RestController
//@RequestMapping("/micfin/api/")
//@CrossOrigin(origins = "http://localhost:4200")
/*public class MicFinRestControllerReactive {

    private final Logger log = LogManager.getLogger(MicFinRestController.class);

    @Autowired
    private MFIRepository mfiRepository;

    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping("/")
    public String getIndexPage(){
        return "index";
    }

    //@Bank Get all the MFI details
    @PostMapping(path = "/mfi")
    public @ResponseBody Mono<MFI> registerMFI(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in registerMFI" + mfi);
        //return (mfiService.registerMFI(mfi));
        Mono<MFI> mfiMono = addressRepository.save(mfi.getAddress()).then(mfiRepository.save(mfi));

        Disposable disposable = mfiMono.subscribe(); // to save should subscribe
        log.info("Disposed? " + disposable.isDisposed());
        return mfiMono;
    }

    //@BankGet all the MFI details
    @GetMapping(path= "/mfis")
    public @ResponseBody
    Flux<MFI> getAllMFIs() throws Exception{
        log.debug("Request received in getAllMFIs");
        return mfiRepository.findAll();
    }

    //@Bank 1. This api is for the Bank to get details about the MFI.
    //@MFI 2. For the MFI to get their own details
    @GetMapping(path = "/mfi/{id}")
    public @ResponseBody Mono<ResponseEntity<MFI>> findMFIById(@PathVariable String id) throws Exception {
        log.debug("Request received in getMFIById for " + id);
        return mfiRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //@Bank 1. This api is for the Bank to update details about the MFI.
    //@MFI 2. For the MFI to update their own details
    @PutMapping(path = "/mfi")
    public @ResponseBody Mono<ResponseEntity<MFI>>  updateMFI(@RequestBody MFI mfi) throws Exception {
        log.debug("Request received in updateMFI for ");
        return mfiRepository.findById(mfi.getId())
                .flatMap(existingMFI -> {
                    existingMFI.setAddress(mfi.getAddress());
                    existingMFI.setDirectorName(mfi.getDirectorName());
                    existingMFI.setCompanyName(mfi.getCompanyName());
                    return mfiRepository.save(existingMFI);})
                .map(updatedMFI -> new ResponseEntity<>(updatedMFI, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/mfi/{id}")
    public @ResponseBody Mono<ResponseEntity<Void>>  deleteMFIById(@PathVariable String id) throws Exception{
        log.debug("Request received to delete MFI for " + id);
        return mfiRepository.findById(id)
                .flatMap(existingMFI -> mfiRepository.delete(existingMFI)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}*/
