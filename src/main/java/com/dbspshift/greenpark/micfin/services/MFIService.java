package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MFI;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

public interface MFIService {
    public MFI registerMFI(MFI mfi) throws Exception;

    public MFI findMFIById(String id) throws Exception;
}
