package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.exceptions.MFINotFoundException;
import com.dbspshift.greenpark.micfin.repository.MFIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.dbspshift.greenpark.micfin.Others.MicFinPropererties.MFI_ID_BEG;

@Service
public class MFIServiceImpl implements MFIService {

    @Autowired
    MFIRepository repository;

    /*@Override
    public MFI registerMFI(MFI mfi) throws Exception {
        Optional<MFI> byMfiId = repository.findByMfiId(mfi.getMfiId());
        if(byMfiId.isPresent()){
            throw new MFINotFoundException("MFI is already registered - [ID = "+mfi.getMfiId()+"  ]");
        }
        else {
            return (repository.save(mfi));
        }
    }*/

    @Override
    public MFI registerMFI(MFI mfi) throws Exception {
        //Optional<MFI> byMfiId = repository.findByCompanyName(mfi.getCompanyName());
        if(isDuplicateMfi(mfi)){
            throw new MFINotFoundException("MFI is already registered - [ID = "+mfi.getMfiId()+"  ]");
        }
        else {
            Optional<Integer> maxMfiId = repository.getMaxMfiId();
            String newMfiId = "";
            if(maxMfiId.isPresent()){
                Integer integer = maxMfiId.get();
                newMfiId = MFI_ID_BEG + String.valueOf(integer + 1);
            }
            else{
                newMfiId = MFI_ID_BEG + "1";
            }
            mfi.setMfiId(newMfiId);
            return (repository.save(mfi));
        }
    }

    public boolean isDuplicateMfi(MFI mfi) throws Exception{
        Optional<MFI> byMfiId = repository.findByCompanyName(mfi.getCompanyName());
        return byMfiId.isPresent();

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
