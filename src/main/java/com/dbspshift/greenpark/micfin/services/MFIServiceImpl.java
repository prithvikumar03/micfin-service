package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.exceptions.MFINotFoundException;
import com.dbspshift.greenpark.micfin.repository.MFIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MFIServiceImpl implements MFIService {

    @Autowired
    MFIRepository repository;

    @Override
    public MFI registerMFI(MFI mfi) throws Exception {
        Optional<MFI> byMfiId = repository.findByMfiId(mfi.getMfiId());
        if(byMfiId.isPresent()){
            throw new MFINotFoundException("MFI is already registered - [ID = "+mfi.getMfiId()+"  ]");
        }
        else {
            return (repository.save(mfi));
        }
    }

    @Override
    public MFI getMFIById(String mfiId) throws Exception {
        Optional<MFI> mfi=repository.findByMfiId(mfiId);
        if(mfi.isPresent())
            return mfi.get();
        else
            throw new MFINotFoundException("Could not find details for MFI - [ID = "+mfiId+"  ]");
    }

    @Override
    public List<MFI> getAllMFIs() throws Exception {
        return repository.findAll();
    }

    @Override
    public MFI updateMFI(MFI mfi) throws Exception {
        Optional<MFI> byId = repository.findByMfiId(mfi.getMfiId());
        if(byId.isPresent()){
            repository.save(mfi);
        }
        return mfi;
    }

    @Override
    public String deleteMFI(String mfiId){
        Optional<MFI> byId = repository.findByMfiId(mfiId);
        if(byId.isPresent()){
            repository.deleteById(byId.get().getId());
            return "success";
        }
        else
            return "Mfi not present";
    }


}
