package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.exceptions.MFINotFoundException;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurRepository;
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
class MicroEntrepreneurServiceImplTest {

    @Configuration
    static class TestConfiguration {
        @Bean
        public MFIService getMFIService() {
            return new MFIServiceImpl();
        }
    }

    MicroEntrepreneur microEntrepreneur;

    @Autowired
    MicroEntrepreneurService microEntrepreneurService;

    @MockBean
    MicroEntrepreneurRepository repository;

    @Before
    public void setUp() throws Exception {
        microEntrepreneur = new MicroEntrepreneur();
        microEntrepreneur.setId("123");
        Mockito.when(repository.save(microEntrepreneur)).thenReturn(microEntrepreneur);
        Mockito.when(repository.findById("123")).thenReturn(Optional.of(microEntrepreneur));

    }

    @Test
    public void givenNewMFIRegistration_thenReturnMFIId() throws Exception {
        //MFI mfi = new MFI("Grameen Bank", "Grameen Bank - Bangladesh");
        microEntrepreneurService.registerMicroEntrepreneur(microEntrepreneur);
        Assert.assertEquals("123", microEntrepreneur.getId());
    }

    @Test
    public void whenValidMFIId_thenMFIShouldBeFound() throws Exception {
        String uEntprId = "123";
        MicroEntrepreneur microEntrepreneur = microEntrepreneurService.getMicroEntrepreneurById(uEntprId);
        Assert.assertEquals(uEntprId, microEntrepreneur.getId());
    }

    @Test(expected = MFINotFoundException.class)
    public void whenInValidMFIId_thenShouldThrowException() throws Exception {
        String uEntprId = "InvalidMFIID";
        MicroEntrepreneur microEntrepreneur = microEntrepreneurService.getMicroEntrepreneurById(uEntprId);
    }

    @Test
    public void deleteMFIFailed() throws Exception{
        Mockito.doThrow(new IllegalArgumentException()).when(repository).deleteById("123");
        String s = microEntrepreneurService.deleteMicroEntrepreneur("123");
        Assert.assertEquals(s,"failed");
    }

    @Test
    public void deleteMFISuccess() throws Exception{
        String s = microEntrepreneurService.deleteMicroEntrepreneur("123");
        Assert.assertEquals(s,"success");
    }

}