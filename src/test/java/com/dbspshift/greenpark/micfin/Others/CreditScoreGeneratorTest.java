package com.dbspshift.greenpark.micfin.Others;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditScoreGeneratorTest {

    CreditScoreGenerator creditScoreGenerator;

    @Test
    void sendPost() throws Exception {
        creditScoreGenerator.getCreditScore();
    }

    @BeforeEach
    void setUp() {
        creditScoreGenerator = new CreditScoreGenerator();
    }

}