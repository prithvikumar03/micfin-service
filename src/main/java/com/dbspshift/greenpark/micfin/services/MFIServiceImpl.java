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
        return(repository.save(mfi));
    }

    @Override
    public MFI getMFIById(String id) throws Exception {
        Optional<MFI> mfi=repository.findById(id);
        if(mfi.isPresent())
            return mfi.get();
        else
            throw new MFINotFoundException("Could not find details for MFI - [ID = "+id+"  ]");
    }

    @Override
    public List<MFI> getAllMFIs() throws Exception {
        return repository.findAll();
    }

    @Override
    public MFI updateMFI(MFI mfi) throws Exception {
        Optional<MFI> byId = repository.findById(mfi.getId());
        if(byId.isPresent()){
            repository.save(mfi);
        }
        return mfi;
    }

    @Override
    public String deleteMFI(String id){
        try{
            repository.deleteById(id);
            return "success";
        }
        catch(IllegalArgumentException e) {
            return "failed";
        }
    }


}
