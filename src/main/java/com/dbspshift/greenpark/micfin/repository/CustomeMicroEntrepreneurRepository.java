package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;

public interface CustomeMicroEntrepreneurRepository<T,Id> {

    public MicroEntrepreneur findByMicroEntrepreneurId(String microEntrepreneurId);
}
