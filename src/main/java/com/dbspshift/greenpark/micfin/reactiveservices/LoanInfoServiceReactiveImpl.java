package com.dbspshift.greenpark.micfin.reactiveservices;

import com.dbspshift.greenpark.micfin.Others.LoanCalculationsManager;
import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.LoanSchedule;
import com.dbspshift.greenpark.micfin.exceptions.LoanInfoNotFoundException;
import com.dbspshift.greenpark.micfin.reactiverepo.LoanInfoReactiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import static com.dbspshift.greenpark.micfin.Others.MicFinPropererties.LOAN_ID_BEG;

@Service
public class LoanInfoServiceReactiveImpl implements LoanInfoReactiveService{

    @Autowired
    LoanInfoReactiveRepo loanInfoRepository;
    @Autowired
    LoanCalculationsManager loanCalculationsManager;

    @Override
    public Mono<LoanInfo> registerLoanInfo(LoanInfo loanInfo) throws Exception {

        /*Mono<Integer> byMaxLoanId = loanInfoRepository.getMaxLoanId();
        String newLoanId = "";
        if(byMaxLoanId.blockOptional().isPresent()){
            Integer integer = byMaxLoanId.block();
            newLoanId = LOAN_ID_BEG + String.valueOf(integer + 1);
        }
        else{
            newLoanId = LOAN_ID_BEG + "1";
        }
        loanInfo.setLoanId(newLoanId);*/
        double emi = loanCalculationsManager.getEmi(loanInfo.getLoanAmount(), loanInfo.getInterestRate(), loanInfo.getTenure());
        loanCalculationsManager.getLoanSchedule(emi, loanInfo.getLoanAmount(), loanInfo.getInterestRate(),loanInfo.getDate(), loanInfo.getListLoanSchedule());
        loanInfo.setEmi(emi);
        double i = loanInfo.getLoanAmount();
        loanInfo.setLoanBalance(i);
        return loanInfoRepository.save(loanInfo);
    }

    @Override
    public Mono<LoanInfo> getLoanInfoByLoanId(String loanId) throws Exception {
        return Mono.just(loanId)
                .flatMap(loanInfoRepository::findByLoanId)
                .switchIfEmpty(Mono.error(new LoanInfoNotFoundException("Could not find details for LoanInfo - [ID = "+loanId+"  ]")));
    }

    @Override
    public Flux<LoanInfo> getAllLoanInfos() throws Exception {
        return loanInfoRepository.findAll();
    }

    @Override
    public Flux<LoanInfo> getAllLoanInfosForMFI(String mfiId) throws Exception {
        return Flux.just(mfiId)
                .flatMap(loanInfoRepository::findByMfiId)
                .switchIfEmpty(Mono.error(new LoanInfoNotFoundException("Could not find loan details for MFI- [ID = "+mfiId+"  ]")));
    }

    @Override
    public Flux<LoanSchedule> getLoanScheduleForLoanId(String loanId) throws LoanInfoNotFoundException {
        return Flux.just(loanId)
                .flatMap(loanInfoRepository::getLoanSchedule)
                .switchIfEmpty(Mono.error(new LoanInfoNotFoundException("Could not find loan schedule for loanId- [ID = "+loanId+"  ]")));
    }

    @Override
    public Flux<LoanInfo> getAllLoanInfosForMfiAndMicroEntrepreneur(String mfiId, String microEntId) throws Exception{
        /*Optional<List<LoanInfo>> byMfiIdMEId = loanInfoRepository.findByMfiIdMicroEntrepreneurId(mfiId,microEntId);
        if(byMfiIdMEId.isPresent())
            return byMfiIdMEId.get();
        else
            throw new LoanInfoNotFoundException("Could not find loan details for MFI- [ID = "+mfiId+"  ] and ME- [ID = \"+microEntId+\"  ]");*/

        Mono<String> monoMfiId = Mono.just(mfiId);
        Mono<String> monoMicroEntId = Mono.just(microEntId);

        String s = "Could not find loan details for MFI- [ID = " + mfiId + "  ] and ME- [ID = \"+microEntId+\"  ]";

        Mono<Flux<LoanInfo>> fluxMono = Mono.zip(monoMfiId, monoMicroEntId).map(tuple -> loanInfoRepository.findByMfiIdMicroEntrepreneurId(tuple.getT1(), tuple.getT2()))
                .switchIfEmpty(Mono.error(new LoanInfoNotFoundException("Could not find loan schedule for loanId- [ID = ")));

        return fluxMono.block();
    }

    @Override
    public Mono<LoanInfo> updateLoanInfo(LoanInfo loanInfo) throws Exception {
        /*Optional<LoanInfo> byId = loanInfoRepository.findByLoanId(loanInfo.getLoanId());
        if(byId.isPresent()) {
            return loanInfoRepository.save(loanInfo);
        }
        else
            throw new LoanInfoNotFoundException("Could not update LoanInfo - [ID = "+loanInfo.getLoanId()+"  ]");*/

        return Mono.just(loanInfo.getLoanId())
                .flatMap(loanInfoRepository::findByLoanId)
                .flatMap(loanInfoRepository::save)
                .switchIfEmpty(Mono.error(new LoanInfoNotFoundException("Could not update LoanInfo - [ID = "+loanInfo.getLoanId()+"  ]")));
    }

    @Override
    public Mono<String> deleteLoanInfo(String id) throws Exception {
        try {
            loanInfoRepository.deleteById(id);
            return Mono.just("success");
        }catch(Exception e){
            return Mono.just("failed");
        }
    }
}
