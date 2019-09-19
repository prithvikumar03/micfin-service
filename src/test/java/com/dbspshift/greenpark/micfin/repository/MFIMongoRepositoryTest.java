package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.MFI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gayathrig on 01/08/2019.
 */

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Disabled
class MFIMongoRepositoryTest {

    @Autowired
    private MFIRepository repository;

    @BeforeAll
    static void setUp() {

    }

    @Test
    @Disabled
    void saveMFI() {
        MFI mfi = new MFI("Grameen Bank", "Grameen Bank - Bangladesh");
        repository.save(mfi);
        assertEquals(true, repository.findByCompanyName("Grameen Bank"));
    }

    @Test
    @Disabled
    void findByNameStartingWith() {
        List<MFI> result = repository.findByNameStartingWith("Saadhana");
        assertNull(result);
    }


}
