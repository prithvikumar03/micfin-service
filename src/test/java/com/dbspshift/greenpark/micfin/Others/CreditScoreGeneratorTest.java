package com.dbspshift.greenpark.micfin.Others;

import com.dbspshift.greenpark.micfin.beans.RepaymentInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditScoreGeneratorTest {

    CreditScoreGenerator creditScoreGenerator;

    @Test
    void sendPost() throws Exception {
        creditScoreGenerator.getCreditScore(new RepaymentInfo());
    }

    @BeforeEach
    void setUp() {
        creditScoreGenerator = new CreditScoreGenerator();
    }

}