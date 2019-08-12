package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;

import java.util.List;

public interface MicroEntrepreneurService {
    public MicroEntrepreneur registerMicroEntrepreneur(MicroEntrepreneur microEntrepreneur) throws Exception;

    public MicroEntrepreneur getMicroEntrepreneurById(String id) throws Exception;

    public List<MicroEntrepreneur> getAllMicroEntrepreneursByMFIId(String id) throws Exception;

    public MicroEntrepreneur updateMicroEntrepreneur(MicroEntrepreneur microEntrepreneur) throws Exception;

    public String deleteMicroEntrepreneur(String id) throws Exception;
}
