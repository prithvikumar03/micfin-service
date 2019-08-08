package com.dbspshift.greenpark.micfin.integration.repository;

import com.dbspshift.greenpark.micfin.beans.MFI;

import java.util.List;

public interface CustomMFIRepository<T,Id> {

     List<MFI> findByNameStartingWith(String regexp);
}
