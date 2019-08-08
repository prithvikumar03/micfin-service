package com.dbspshift.greenpark.micfin.integration.services;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.exceptions.MFINotFoundException;
import com.dbspshift.greenpark.micfin.integration.repository.MFIRepository;
import com.dbspshift.greenpark.micfin.services.MFIService;
import com.dbspshift.greenpark.micfin.services.MFIServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class MFIServiceImplIntegrationTest {

    @Configuration
    static class TestConfiguration {
        @Bean
        public MFIService getMFIService() {
            return new MFIServiceImpl();
        }
    }

    @Autowired
    MFIService mfiService;

    @MockBean
    MFIRepository repository;

    @Before
    public void setUp() throws Exception {
        MFI mfi = new MFI("Grameen Bank", "Grameen Bank - Bangladesh");
        mfi.setId("123");
        Mockito.when(repository.save(mfi)).thenReturn(mfi);
        Mockito.when(repository.findById("123")).thenReturn(Optional.of(mfi));

    }

    @Test
    public void givenNewMFIRegistration_thenReturnMFIId() throws Exception {
        MFI mfi = new MFI("Grameen Bank", "Grameen Bank - Bangladesh");
        mfiService.registerMFI(mfi);
        Assert.assertEquals("123", mfi.getId());

    }


    @Test
    public void whenValidMFIId_thenMFIShouldBeFound() throws Exception {
        String mfiID = "123";
        MFI mfi = mfiService.findMFIById(mfiID);
        Assert.assertEquals(mfiID, mfi.getId());
    }

    @Test(expected = MFINotFoundException.class)
    public void whenInValidMFIId_thenShouldThrowException() throws Exception {
        String mfiID = "InvalidMFIID";
        MFI mfi = mfiService.findMFIById(mfiID);

    }

}
