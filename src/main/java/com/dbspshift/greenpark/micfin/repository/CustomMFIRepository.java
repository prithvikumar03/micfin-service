package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MFI;

import java.util.List;
import java.util.Optional;

public interface CustomMFIRepository<T,Id> {

     List<MFI> findByNameStartingWith(String regexp);

     public Optional<MFI> findByMfiId(String mfiId);

     public Optional<Integer> getMaxMfiId();

     public Optional<MFI> findByCompanyName(String companyName);

     //public MFI findByMicroEntrepreneurId(String loanId);
     //Enable this when reactive is enabled.
     //Flux<MFI> findByNameStartingWith(String regexp);
}
