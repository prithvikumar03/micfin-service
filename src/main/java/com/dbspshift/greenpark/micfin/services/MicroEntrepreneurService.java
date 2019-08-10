package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;

import java.util.List;

public interface MicroEntrepreneurService {
    public MFI registerMicroEntrepreneur(MicroEntrepreneur mfi) throws Exception;

    public MFI getMicroEntrepreneurById(String id) throws Exception;

    public List<MicroEntrepreneur> getAllMicroEntrepreneursByMFIId(String id) throws Exception;

    public MFI updateMicroEntrepreneur(MicroEntrepreneur mfi) throws Exception;

    public String deleteMicroEntrepreneur(String id) throws Exception;
}
