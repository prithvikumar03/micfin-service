package com.dbspshift.greenpark.micfin.bootstrap;

import com.dbspshift.greenpark.micfin.beans.*;
import com.dbspshift.greenpark.micfin.repository.LoanInfoRepository;
import com.dbspshift.greenpark.micfin.repository.MFIRepository;
import com.dbspshift.greenpark.micfin.repository.MicroEntrepreneurRepository;
import com.dbspshift.greenpark.micfin.services.LoanInfoService;
import com.dbspshift.greenpark.micfin.services.RepaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class BootStrap implements ApplicationRunner {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private MFIRepository repository;
    @Autowired
    private MicroEntrepreneurRepository microEntrepreneurRepository;
    @Autowired
    private LoanInfoService loanInfoService;
    @Autowired
    private RepaymentInfoService repaymentInfoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadMFIs();
        loadMicroEntrepreneurs();
        loadLoanInfos();
        loadRepayments();
    }

    private void loadMFIs() throws ParseException {
        List<MFI> listOfMFIs = new ArrayList<>();
        listOfMFIs.add(new MFI("MFI-1",
                "OmGanesh Pte Ltd","Aditya Mohan",
                new Address("1","78","Ashwathama Link","Indraprastha","Bharat","560001"),
                "100000",
                "45678",
                sdf.parse("05/08/2015"),
                true,
                false,
                false,
                "Bring Intellectual prowess to the masses"));


        listOfMFIs.add(new MFI("MFI-2",
                "WikiViki","Vikram Belur",
                new Address("2","87","Drona Drive","Indraprastha","Bharat","560002"),
                "100000",
                "45678",
                sdf.parse("05/08/2015"),
                true,
                false,
                false,
                "Spread Wiki Knowledge"));

        listOfMFIs.add(new MFI("MFI-3",
                "ThinVoice Limited","Siri Lakshmi",
                new Address("3","47","Arjuna Road","Pataliputra","Bharat","580003"),
                "4500000",
                "234123",
                sdf.parse("01/03/2016"),
                true,
                true,
                false,
                "My Voice My Choice"));

        listOfMFIs.add(new MFI("MFI-4",
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

    private void loadMicroEntrepreneurs() throws ParseException {

        List<MicroEntrepreneur> listOfMicroEntrepreneurs = new ArrayList<>();
        listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME-100","Bharat","A12378J","married","graduate",40000,12000,false,"MFI-3","male"));
        listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME-101","Chandrika","C649087L","married","university",37000,21000,false,"MFI-1","female"));
        listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME-102","Gaurav","A19978H","single","highschool",35000,15000,true,"MFI-4","male"));
        listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME-103","Krishna","K44893R","married","graduate",40000,12000,false,"MFI-3","male"));
        listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME-104","Seema","S66757G","single","highschool",35000,15000,true,"MFI-2","female"));

        for(MicroEntrepreneur microEntrepreneur : listOfMicroEntrepreneurs) {
            microEntrepreneurRepository.save(microEntrepreneur);
        }
    }

    private void loadLoanInfos() throws Exception {
        List<LoanInfo> listOfLoans = new ArrayList<>();
        listOfLoans.add(new LoanInfo("L-200","MFI-1","OmGanesh Pte Ltd","ME-100",20000,"P-400","QuickCash",12,10));
        listOfLoans.add(new LoanInfo("L-201","MFI-2","WikiViki","ME-101",45000,"P-400","QuickCash",24,12));
        listOfLoans.add(new LoanInfo("L-202","MFI-1","OmGanesh Pte Ltd","ME-102",28000,"P-400","QuickCash",20,15));
        listOfLoans.add(new LoanInfo("L-203","MFI-4","Nordic Wonders","ME-103",80000,"P-400","QuickCash",36,18));
        listOfLoans.add(new LoanInfo("L-204","MFI-3","ThinVoice Limited","ME-104",25000,"P-400","QuickCash",12,5));

        for(LoanInfo loanInfo : listOfLoans) {
            loanInfoService.registerLoanInfo(loanInfo);
        }
    }

    private void loadRepayments() throws Exception {
        List<RepaymentInfo> repaymentInfoList = new ArrayList<>();
        //String microEntrepreneurId, String mfiId, String loanId, String loanAmount, Integer payment, Integer paymentDelayedInMonths, String productId, Integer tenure, Integer interestRate
        repaymentInfoList.add(new RepaymentInfo("ME-100","MFI-1","L-200",20000,2000,1,"P-400",12,10));
        repaymentInfoList.add(new RepaymentInfo("ME-101","MFI-2","L-201",45000,1890,2,"P-400",24,12));

        for(RepaymentInfo repaymentInfo : repaymentInfoList) {
            repaymentInfoService.registerRepaymentInfo(repaymentInfo);
        }
    }
}
