package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;

import java.util.List;
import java.util.Optional;

public interface CustomeMicroEntrepreneurRepository<T,Id> {

    public Optional<MicroEntrepreneur> findByMicroEntrepreneurId(String microEntrepreneurId);

    public Optional<List<MicroEntrepreneur>> findAllMicroEntrepreneursByMFIId(String microEntrepreneurId);

    public Optional<MicroEntrepreneur> findByMicroEntrepreneurFirstName(String microEntrepreneurFirstName);

    public Optional<MicroEntrepreneur> findByMicroEntrepreneurLastName(String microEntrepreneurLastName);

    public Optional<Integer> getMaxMicroEntrepreneurId();
}
