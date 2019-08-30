package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import com.dbspshift.greenpark.micfin.repository.RepaymentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RepaymentInfoServiceImpl implements RepaymentInfoService {

    @Autowired
    RepaymentInfoRepository repository;


    @Override
    public RepaymentInfo registerRepaymentInfo(RepaymentInfo repaymentInfo) throws Exception {
        RepaymentInfo save = repository.save(repaymentInfo);
        return save;
    }

    @Override
    public RepaymentInfo getRepaymentInfoById(String id) throws Exception {
        Optional<RepaymentInfo> byId = repository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else
            return null;
    }

    @Override
    public List<RepaymentInfo> getAllRepaymentInfoByMicroEntrepreneurId(String id) throws Exception {
        Predicate<RepaymentInfo> predFilterByMeId = rp -> rp.getMicroEntrepreneurId().equals(id);
        List<RepaymentInfo> collect = repository.findAll().stream().filter(p -> predFilterByMeId.test(p)).collect(Collectors.toList());
        return collect;
    }
}
