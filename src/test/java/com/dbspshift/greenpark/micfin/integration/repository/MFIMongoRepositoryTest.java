package com.dbspshift.greenpark.micfin.integration.repository;

import com.dbspshift.greenpark.micfin.beans.MFI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gayathrig on 01/08/2019.
 */

/*@DataMongoTest
@ExtendWith(SpringExtension.class)*/
@DataMongoTest
@RunWith(SpringRunner.class)
@Disabled
class MFIMongoRepositoryTest {

    @Autowired
    private MFIRepository repository;

    @BeforeAll
    static void setUp() {

    }

    @Test
//    @Disabled
    void saveMFI() throws Exception {
        MFI mfi = new MFI("Grameen Bank", "Grameen Bank - Bangladesh");
        Example<MFI> mfiExample = Example.of(mfi);
        repository.save(mfi);
        assertEquals(mfi, repository.findOne(mfiExample).thenReturn(mfi));
    }

    @Test
    @Disabled
    void findByNameStartingWith() {
        Flux<MFI> result = repository.findByNameStartingWith("Saadhana");
        assertNull(result);
    }


}
