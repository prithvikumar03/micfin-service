package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.Address;
import com.dbspshift.greenpark.micfin.beans.MFI;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataMongoTest
@RunWith(SpringRunner.class)
public class CustomMFIRepositoryImplTest {
/*

    @Autowired
    MFIRepository mfiRepository;

    List<MFI> listOfMFIs = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        loadMFIsToList();
    }

    private void loadMFIsToList(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            listOfMFIs.add(new MFI("MFI1",
                    "OmGanesh Pte Ltd","Aditya Mohan",
                    new Address("1","78","Ashwathama Link","Indraprastha","Bharat","560001"),
                    "100000",
                    "45678",
                    sdf.parse("05/08/2015"),
                    true,
                    false,
                    false,
                    "Bring Intellectual prowess to the masses"));


            listOfMFIs.add(new MFI("MFI2",
                    "WikiViki","Vikram Belur",
                    new Address("2","87","Drona Drive","Indraprastha","Bharat","560002"),
                    "100000",
                    "45678",
                    sdf.parse("05/08/2015"),
                    true,
                    false,
                    false,
                    "Spread Wiki Knowledge"));

            listOfMFIs.add(new MFI("MFIAB",
                    "ThinVoice Limited","Siri Lakshmi",
                    new Address("3","47","Arjuna Road","Pataliputra","Bharat","580003"),
                    "4500000",
                    "234123",
                    sdf.parse("01/03/2016"),
                    true,
                    true,
                    false,
                    "My Voice My Choice"));

            listOfMFIs.add(new MFI("MFIC",
                    "Nordic Wonders","Spatika Chandra",
                    new Address("4","18","Bhima Lane","Bendakaluru","Bharat","560004"),
                    "1800000",
                    "14234",
                    sdf.parse("27/06/2016"),
                    true,
                    true,
                    false,
                    "Be Awesome"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMFIsToRepo(){
        for(MFI mfi : listOfMFIs) {
            mfiRepository.save(mfi);
        }
    }


    @Test
    public void getMaxMfiId() {
        loadMFIsToRepo();
        List<MFI> all = mfiRepository.findAll();
        Optional<MFI> mfi1 = mfiRepository.findByMfiId("MFI1");

        Optional<Integer> maxMfiId = mfiRepository.getMaxMfiId();
        System.out.println(maxMfiId.get());
    }
*/

}