package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.repository.MFIRepository;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.dbspshift.greenpark.micfin.exceptions.MicroEntrepreneurNotFoundException;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

@Service
public class MicroEntrepreneurServiceImpl implements MicroEntrepreneurService {

    private final Logger logger = LogManager.getLogger(MicroEntrepreneurServiceImpl.class);

    @Autowired
    MicroEntrepreneurRepository microEntrepreneurRepository;

    @Override
    public MicroEntrepreneur registerMicroEntrepreneur(MicroEntrepreneur microEntrepreneur) throws Exception {
        Optional<MicroEntrepreneur> byId = microEntrepreneurRepository.findByMicroEntrepreneurId(microEntrepreneur.getMicroEntrepreneurId());
        if(byId.isPresent()){
            throw new MicroEntrepreneurNotFoundException("MicroEntrepreneur is already registered - [ID = "+microEntrepreneur.getMicroEntrepreneurId()+"  ]");
        }
        else {
            microEntrepreneur.setCreditScore("5.0");
            return microEntrepreneurRepository.save(microEntrepreneur);
        }
    }

    @Override
    public MicroEntrepreneur getMicroEntrepreneurById(String microEntrepreneurId) throws Exception {
        Optional<MicroEntrepreneur> byId = microEntrepreneurRepository.findByMicroEntrepreneurId(microEntrepreneurId);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new MicroEntrepreneurNotFoundException("Could not find details for MicroEntrepreneur - [ID = "+microEntrepreneurId+"  ]");
        //return
    }

    @Override
    public List<MicroEntrepreneur> getAllMicroEntrepreneursByMFIId(String mfiId) throws Exception {
        Optional<List<MicroEntrepreneur>> allMicroEntrepreneursByMFIId = microEntrepreneurRepository.findAllMicroEntrepreneursByMFIId(mfiId);
        if(allMicroEntrepreneursByMFIId.isPresent()){
            return allMicroEntrepreneursByMFIId.get();
        }
        throw new MicroEntrepreneurNotFoundException("Could not find any MicroEntrepreneur for this Mfi - [ID = "+mfiId+"  ]");
        //return
    }

    @Override
    public MicroEntrepreneur updateMicroEntrepreneur(MicroEntrepreneur microEntrepreneur) throws Exception {
        Optional<MicroEntrepreneur> byId = microEntrepreneurRepository.findByMicroEntrepreneurId(microEntrepreneur.getMicroEntrepreneurId());
        if(byId.isPresent()){
            microEntrepreneurRepository.save(microEntrepreneur);
        }
        return microEntrepreneur;
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
