package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;

import java.util.Optional;

public interface CustomeMicroEntrepreneurRepository<T,Id> {

    public Optional<MicroEntrepreneur> findByMicroEntrepreneurId(String microEntrepreneurId);
}
