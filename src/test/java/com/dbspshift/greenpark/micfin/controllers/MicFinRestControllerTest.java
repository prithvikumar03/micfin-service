package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.Address;
import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import com.dbspshift.greenpark.micfin.services.MFIService;
import com.dbspshift.greenpark.micfin.services.MicroEntrepreneurService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/*@RunWith(SpringRunner.class)
@WebMvcTest(MicFinRestController.class)*/
public class MicFinRestControllerTest {

    MicFinRestController micFinRestController;

    @Mock
    MFIService mfiService;
    @Mock
    MicroEntrepreneurService microEntrepreneurService;

   @Before
    public void setUp() throws Exception {
       MockitoAnnotations.initMocks(this);
       micFinRestController = new MicFinRestController(mfiService,microEntrepreneurService);
   }

    @Test
    public void testFindMFIById() throws Exception{
        //MockMvc mockMvc = MockMvcBuilders.standaloneSetup(micFinRestController).build();
        MFI mfi = new MFI("1","Aditya","Aditya Mohan",new Address());
    
        when(mfiService.getMFIById("1")).thenReturn(mfi);
        MFI retValue = micFinRestController.findMFIById("1");
        assertThat(retValue.getDirectorName().contains("Aditya"));
        System.out.println(retValue);
    }
    
    @Test
    public void testRegisterMFI() throws Exception {
        MFI mfi = new MFI("1","Aditya","Aditya Mohan",new Address());
        when(mfiService.registerMFI(mfi)).thenReturn(mfi);
        MFI mfi1 = micFinRestController.registerMfi(mfi);
        assertThat(mfi1.getDirectorName().equals(mfi.getDirectorName()));
    }
    
    @Test
    public void getAllMFIs() throws Exception{
       List<MFI> mfiList = new ArrayList<>();
       mfiList.add(new MFI("1","Spatika","Spatika Chandra",new Address()));
       mfiList.add(new MFI("2","Vikram","Vikram Belur",new Address()));
    
       when(mfiService.getAllMFIs()).thenReturn(mfiList);
       final List<MFI> allMFIs = micFinRestController.getAllMFIs();
       assertThat(allMFIs.size()==2);
       assertThat(allMFIs.get(0).getDirectorName().equals("Spatika Chandra"));
       assertThat(allMFIs.get(1).getDirectorName().equals("Vikram Belur"));
    }

    @Test
    public void updateMFI() throws Exception{
        MFI mfi = new MFI("1","Aditya","Aditya Mohan",new Address());
        MFI updatedMFI = new MFI("1","Aditya","Aditya Chandra",new Address());

        when(mfiService.updateMFI(mfi)).thenReturn(updatedMFI);
        MFI mfi1 = micFinRestController.updateMFI(mfi);
        assertThat(mfi1.getDirectorName().equals("Aditya Chandra"));
    }

    @Test
    public void deleteMFIById() throws Exception{
       when(mfiService.deleteMFI("1")).thenReturn("success");
        String s = micFinRestController.deleteMFIById("1");
        assertThat(s.equals("success"));
    }

    @Test
    public void getAllMicroEntrepreneurs() throws Exception {
       List<MicroEntrepreneur> listMicroEntpr = new ArrayList<>();
       MicroEntrepreneur microEntrepreneur = new MicroEntrepreneur();
       microEntrepreneur.setMicroEntrepreneurId("1");
       listMicroEntpr.add(microEntrepreneur);
       microEntrepreneur = new MicroEntrepreneur();
       microEntrepreneur.setMicroEntrepreneurId("2");

       when(microEntrepreneurService.getAllMicroEntrepreneursByMFIId("1"))
       .thenReturn(listMicroEntpr);
       List<MicroEntrepreneur> allMicroEntrepreneursByMFIId = micFinRestController.getAllMicroEntrepreneurs("1");
       assertThat(allMicroEntrepreneursByMFIId.size()==2 && allMicroEntrepreneursByMFIId.get(0).getMicroEntrepreneurId().equals("1"));
    }

    @Test
    public void registerMFI() throws Exception {
        MFI mfi = new MFI();
        mfi.setMfiId("1");
        mfi.setCompanyName("123456");
        mfi.setDirectorName("Aditya");

        when(mfiService.registerMFI(mfi)).thenReturn(mfi);
        MFI mfi1 = micFinRestController.registerMfi(mfi);
        assertThat(mfi.getDirectorName().equals("Aditya"));
    }

    @Test
    public void getMicroEntrepreneur() throws Exception {
        MicroEntrepreneur microEntrepreneur = new MicroEntrepreneur();
        microEntrepreneur.setMicroEntrepreneurId("1");
        microEntrepreneur.setAadhar("123456");
        microEntrepreneur.setFirstName("Varun");

        when(microEntrepreneurService.getMicroEntrepreneurById("1")).thenReturn(microEntrepreneur);
        MicroEntrepreneur microEntrepreneur1 = micFinRestController.getMicroEntrepreneur("1");
        assertThat(microEntrepreneur.getFirstName().equals("Varun"));
    }

    @Test
    public void updateMicroEntrepreneur() throws Exception {
        MicroEntrepreneur microEntrepreneur = new MicroEntrepreneur();
        microEntrepreneur.setMicroEntrepreneurId("1");
        microEntrepreneur.setAadhar("123456");
        microEntrepreneur.setFirstName("Aditya");

        MicroEntrepreneur microEntrepreneur1 = new MicroEntrepreneur();
        microEntrepreneur1.setMicroEntrepreneurId("1");
        microEntrepreneur1.setAadhar("123456");
        microEntrepreneur1.setFirstName("Aalur");

        when(microEntrepreneurService.updateMicroEntrepreneur(microEntrepreneur)).thenReturn(microEntrepreneur1);
        MicroEntrepreneur microEntrepreneur2 = micFinRestController.updateMicroEntrepreneur(microEntrepreneur);
        assertThat(microEntrepreneur2.getFirstName().equals("Aalur"));
    }
}



    /*private MockMvc mockMvc;
    // private JacksonTester<MFI> jsonMFI;

    @InjectMocks
    private MicFinRestController controller = new MicFinRestController();

    @MockBean
    private MFIService mfiService;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this,new ObjectMapper());
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        given(mfiService.getMFIById("1"))
                .willReturn(new MFI("1","Aditya","Aditya Mohan","Pattalama Street"));
    }

    @Test
    public void getMFIById() throws Exception{
       *//* given(mfiService.getMFIById("1"))
             .willReturn(new MFI("1","Aditya","Aditya Mohan","Pattalama Street"));
*//*
        MockHttpServletResponse response = mockMvc.perform(
                get("/mfi/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
       *//* assertThat(response.getContentAsString()).isEqualTo(
                jsonMFI.write(new MFI("1","Aditya","Aditya Mohan","Pattalama Street")).getJson()
        );*//*
    }


            /*mockMvc.perform(get("/micfin/api/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));*/