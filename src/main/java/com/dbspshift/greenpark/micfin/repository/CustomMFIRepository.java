package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.MFI;

import java.util.List;

public interface CustomMFIRepository<T,Id> {

     List<MFI> findByNameStartingWith(String regexp);

     //Enable this when reactive is enabled.
//     Flux<MFI> findByNameStartingWith(String regexp);
}
