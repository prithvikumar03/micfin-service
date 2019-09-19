package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.MicroEntrepreneur;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataMongoTest
@RunWith(SpringRunner.class)
public class CustomeMicroEntrepreneurRepositoryImplTest {

    @Autowired
    MicroEntrepreneurRepository microEntrepreneurRepository;

    List<MicroEntrepreneur> listOfMicroEntrepreneurs = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        loadMEsToRepo();
    }

    private void loadMEsToRepo(){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
            listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME100", "Bharat", "A12378J", "married", "graduate", 40000, 12000, false, "MFI3", "male", simpleDateFormat.parse("1974-09-07 08:46:25")));
            listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME101", "Chandrika", "C649087L", "married", "university", 37000, 21000, false, "MFI1", "female", simpleDateFormat.parse("1982-09-07 08:46:25")));
            listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME102", "Gaurav", "A19978H", "single", "highschool", 35000, 15000, true, "MFI4", "male", simpleDateFormat.parse("1985-09-07 08:46:25")));
            listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME103", "Krishna", "K44893R", "married", "graduate", 40000, 12000, false, "MFI3", "male", simpleDateFormat.parse("1990-09-07 08:46:25")));
            listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME104", "Seema", "S66757G", "single", "highschool", 35000, 15000, true, "MFI2", "female", simpleDateFormat.parse("1998-09-07 08:46:25")));

            for (MicroEntrepreneur microEntrepreneur : listOfMicroEntrepreneurs) {
                microEntrepreneurRepository.save(microEntrepreneur);
            }
        }
        catch (Exception e){

        }
    }

    @Test
    public void getMaxMicroEntrepreneurId() {
        Optional<Integer> maxMicroEntrepreneurId = microEntrepreneurRepository.getMaxMicroEntrepreneurId();
        System.out.println(maxMicroEntrepreneurId.get());
    }

}