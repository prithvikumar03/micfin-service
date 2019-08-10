package com.dbspshift.greenpark.micfin.controllers;

import com.dbspshift.greenpark.micfin.beans.Address;
import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.services.MFIService;
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

   @Before
    public void setUp() throws Exception {
       MockitoAnnotations.initMocks(this);
       micFinRestController = new MicFinRestController(mfiService);
   }

    @Test
    public void testFindMFIById() throws Exception{
        //MockMvc mockMvc = MockMvcBuilders.standaloneSetup(micFinRestController).build();
        MFI mfi = new MFI("1","Aditya","Aditya Mohan",new Address());
    
        when(mfiService.getMFIById("1")).thenReturn(mfi);
        String retValue = micFinRestController.findMFIById("1");
        assertThat(retValue.contains("Aditya"));
        System.out.println(retValue);
    }
    
    @Test
    public void testRegisterMFI() throws Exception {
        MFI mfi = new MFI("1","Aditya","Aditya Mohan",new Address());
        when(mfiService.registerMFI(mfi)).thenReturn(mfi);
        MFI mfi1 = micFinRestController.registerMFI(mfi);
        assertThat(mfi1.getFullName().equals(mfi.getFullName()));
    }
    
    @Test
    public void getAllMFIs() throws Exception{
       List<MFI> mfiList = new ArrayList<>();
       mfiList.add(new MFI("1","Spatika","Spatika Chandra",new Address()));
       mfiList.add(new MFI("2","Vikram","Vikram Belur",new Address()));
    
       when(mfiService.getAllMFIs()).thenReturn(mfiList);
       final List<MFI> allMFIs = micFinRestController.getAllMFIs();
       assertThat(allMFIs.size()==2);
       assertThat(allMFIs.get(0).getFullName().equals("Spatika Chandra"));
       assertThat(allMFIs.get(1).getFullName().equals("Vikram Belur"));
    }

    @Test
    public void updateMFI() throws Exception{
        MFI mfi = new MFI("1","Aditya","Aditya Mohan",new Address());
        MFI updatedMFI = new MFI("1","Aditya","Aditya Chandra",new Address());

        when(mfiService.updateMFI(mfi)).thenReturn(updatedMFI);
        MFI mfi1 = micFinRestController.updateMFI(mfi);
        assertThat(mfi1.getFullName().equals("Aditya Chandra"));
    }

    @Test
    void deleteMFIById() throws Exception{
       when(mfiService.deleteMFI("1")).thenReturn("success");
        String s = micFinRestController.deleteMFIById("1");
        assertThat(s.equals("success"));
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