package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import com.dbspshift.greenpark.micfin.beans.MFI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class LoanInfoRepositoryTest {

    @Autowired
    private LoanInfoRepository loanInfoRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveLoan() {
        LoanInfo loanInfo = new LoanInfo();
        loanInfo.setLoanId("123");
        loanInfo.setMfiId("456");
        loanInfoRepository.save(loanInfo);
        Optional<LoanInfo> byLoanId = loanInfoRepository.findByLoanId("123");
        assertTrue(byLoanId.isPresent());
    }

}