package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.integration.repository.MicroEntrepreneurRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.dbspshift.greenpark.micfin.exceptions.MicroEntrepreneurNotFoundException;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;

public class MicroEntrepreneurServiceImpl implements MicroEntrepreneurService {

    private final Logger logger = LogManager.getLogger(MicroEntrepreneurServiceImpl.class);

    @Autowired
    MicroEntrepreneurRepository microEntrepreneurRepository;

    @Override
    public MicroEntrepreneur registerMicroEntrepreneur(MicroEntrepreneur microEntrepreneur) throws Exception {
        return microEntrepreneurRepository.save(microEntrepreneur);
    }

    @Override
    public MicroEntrepreneur getMicroEntrepreneurById(String id) throws Exception {
        Optional<MicroEntrepreneur> byId = microEntrepreneurRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new MicroEntrepreneurNotFoundException("Could not find details for MicroEntrepreneur - [ID = "+id+"  ]");
        //return
    }

    @Override
    public List<MicroEntrepreneur> getAllMicroEntrepreneursByMFIId(String mfiId) throws Exception {
        return microEntrepreneurRepository.findAll();
    }

    @Override
    public MicroEntrepreneur updateMicroEntrepreneur(MicroEntrepreneur microEntrepreneur) throws Exception {
        return null;
    }

    @Override
    public String deleteMicroEntrepreneur(String id){
        try{
            microEntrepreneurRepository.deleteById(id);
            return "success";
        }
        catch(IllegalArgumentException e) {
            return "failed";
        }
    }
}
