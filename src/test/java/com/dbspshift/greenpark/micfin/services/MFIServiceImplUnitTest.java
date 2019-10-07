package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.exceptions.MFINotFoundException;
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

import java.util.Optional;

@RunWith(SpringRunner.class)
public class MFIServiceImplUnitTest {

    /*@Configuration
    static class TestConfiguration {
        @Bean
        public MFIService getMFIService() {
            return new MFIServiceImpl();
        }
    }*/

    /*MFI mfi;

    @Autowired
    MFIService mfiService;

    @MockBean
    MFIRepository repository;

    @Before
    public void setUp() throws Exception {
        mfi = new MFI("Grameen Bank", "Grameen Bank - Bangladesh");
        //mfi.setMfiId("123");
        Mockito.when(repository.save(mfi)).thenReturn(mfi);
        Mockito.when(repository.findByMfiId("MFI1")).thenReturn(Optional.of(mfi));
    }

    @Test
    public void givenNewMFIRegistration_thenReturnMFIId() throws Exception {
        //MFI mfi = new MFI("Grameen Bank", "Grameen Bank - Bangladesh");
        mfiService.registerMFI(mfi);
        Assert.assertEquals("MFI1", mfi.getMfiId());
    }

    @Test
    public void whenValidMFIId_thenMFIShouldBeFound() throws Exception {
        String mfiID = "MFI1";
        MFI mfi = mfiService.getMFIById(mfiID);
        Assert.assertEquals(mfiID, mfi.getMfiId());
    }

    @Test(expected = MFINotFoundException.class)
    public void whenInValidMFIId_thenShouldThrowException() throws Exception {
        String mfiID = "InvalidMFIID";
        MFI mfi = mfiService.getMFIById(mfiID);
    }

    @Test
    public void deleteMFIFailed() throws Exception{
        Mockito.doThrow(new IllegalArgumentException()).when(repository).deleteById("123");
        String s = mfiService.deleteMFI("123");
        Assert.assertEquals(s,"failed");
    }

    @Test
    public void deleteMFISuccess() throws Exception{
        String s = mfiService.deleteMFI("MFI1");
        Assert.assertEquals(s,"success");
    }*/
}
