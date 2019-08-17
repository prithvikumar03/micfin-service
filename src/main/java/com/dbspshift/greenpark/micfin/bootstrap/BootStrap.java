package com.dbspshift.greenpark.micfin.bootstrap;

import com.dbspshift.greenpark.micfin.beans.Address;
import com.dbspshift.greenpark.micfin.beans.MFI;
import com.dbspshift.greenpark.micfin.repository.MFIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BootStrap implements ApplicationRunner {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private MFIRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadMFIs();
    }

    private void loadMFIs() throws ParseException {
        List<MFI> listOfMFIs = new ArrayList<>();
        listOfMFIs.add(new MFI("1",
                "OmGanesh Pte Ltd","Aditya Mohan",
                new Address("1","78","Ashwathama Link","Indraprastha","Bharat","560001"),
                "100000",
                "45678",
                sdf.parse("05/08/2015"),
                true,
                false,
                false,
                "Bring Intellectual prowess to the masses"));


        listOfMFIs.add(new MFI("2",
                "WikiViki","Vikram Belur",
                new Address("2","87","Drona Drive","Indraprastha","Bharat","560002"),
                "100000",
                "45678",
                sdf.parse("05/08/2015"),
                true,
                false,
                false,
                "Spread Wiki Knowledge"));

        listOfMFIs.add(new MFI("3",
                "ThinVoice Limited","Siri Lakshmi",
                new Address("3","47","Arjuna Road","Pataliputra","Bharat","580003"),
                "4500000",
                "234123",
                sdf.parse("01/03/2016"),
                true,
                true,
                false,
                "My Voice My Choice"));

        listOfMFIs.add(new MFI("4",
                "Nordic Wonders","Spatika Chandra",
                new Address("4","18","Bhima Lane","Bendakaluru","Bharat","560004"),
                "1800000",
                "14234",
                sdf.parse("27/06/2016"),
                true,
                true,
                false,
                "Be Awesome"));

        for(MFI mfi : listOfMFIs) {
            repository.save(mfi);
        }
    }
}
