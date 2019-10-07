package com.dbspshift.greenpark.micfin.bootstrap;

import com.dbspshift.greenpark.micfin.beans.*;
import com.dbspshift.greenpark.micfin.reactiveservices.LoanInfoReactiveService;
import com.dbspshift.greenpark.micfin.reactiveservices.MFIReactiveService;
import com.dbspshift.greenpark.micfin.reactiveservices.MicroEntrepreneurReactiveService;
import com.dbspshift.greenpark.micfin.reactiveservices.RepaymentInfoReactiveService;
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

    @Autowired
    private MFIReactiveService mfiService;
    @Autowired
    private MicroEntrepreneurReactiveService microEntrepreneurService;
    @Autowired
    private LoanInfoReactiveService loanInfoService;
    @Autowired
    private RepaymentInfoReactiveService repaymentInfoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*loadMFIs();
        loadMicroEntrepreneurs();
        loadLoanInfos();
        loadRepayments();*/
    }

    private void loadMFIs(){
        List<MFI> listOfMFIs = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            listOfMFIs.add(new MFI(
                    "OmGanesh Pte Ltd","Aditya Mohan",
                    new Address("1","78","Ashwathama Link","Indraprastha","Bharat","560001"),
                    "100000",
                    "45678",
                    sdf.parse("05/08/2015"),
                    true,
                    false,
                    false,
                    "Bring Intellectual prowess to the masses"));


            listOfMFIs.add(new MFI(
                    "WikiViki","Vikram Belur",
                    new Address("2","87","Drona Drive","Indraprastha","Bharat","560002"),
                    "100000",
                    "45678",
                    sdf.parse("05/08/2015"),
                    true,
                    false,
                    false,
                    "Spread Wiki Knowledge"));

            listOfMFIs.add(new MFI(
                    "ThinVoice Limited","Siri Lakshmi",
                    new Address("3","47","Arjuna Road","Pataliputra","Bharat","580003"),
                    "4500000",
                    "234123",
                    sdf.parse("01/03/2016"),
                    true,
                    true,
                    false,
                    "My Voice My Choice"));

            listOfMFIs.add(new MFI(
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

                    mfiService.registerMFI(mfi);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMicroEntrepreneurs() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
            List<MicroEntrepreneur> listOfMicroEntrepreneurs = new ArrayList<>();
            listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME100", "Bharat", "A12378J", "married", "graduate", 40000, 12000, false, "MFI3", "male", simpleDateFormat.parse("1974-09-07 08:46:25")));
            listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME101", "Chandrika", "C649087L", "married", "university", 37000, 21000, false, "MFI1", "female", simpleDateFormat.parse("1982-09-07 08:46:25")));
            listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME102", "Gaurav", "A19978H", "single", "highschool", 35000, 15000, true, "MFI4", "male", simpleDateFormat.parse("1985-09-07 08:46:25")));
            listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME103", "Krishna", "K44893R", "married", "graduate", 40000, 12000, false, "MFI3", "male", simpleDateFormat.parse("1990-09-07 08:46:25")));
            listOfMicroEntrepreneurs.add(new MicroEntrepreneur("ME104", "Seema", "S66757G", "single", "highschool", 35000, 15000, true, "MFI2", "female", simpleDateFormat.parse("1998-09-07 08:46:25")));

            for (MicroEntrepreneur microEntrepreneur : listOfMicroEntrepreneurs) {

                microEntrepreneurService.registerMicroEntrepreneur(microEntrepreneur);

            }
        }
        catch (Exception e){

        }
    }

    private void loadLoanInfos() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
        List<LoanInfo> listOfLoans = new ArrayList<>();
        listOfLoans.add(new LoanInfo("L200","MFI1","OmGanesh Pte Ltd","ME101",20000,"P400","QuickCash",12,10, simpleDateFormat.parse("2019-09-07 08:46:25")));
        listOfLoans.add(new LoanInfo("L201","MFI2","WikiViki","ME104",45000,"P400","QuickCash",24,12, simpleDateFormat.parse("2019-07-07 08:46:25")));
        listOfLoans.add(new LoanInfo("L202","MFI1","OmGanesh Pte Ltd","ME101",28000,"P400","QuickCash",20,15, simpleDateFormat.parse("2019-08-09 08:46:25")));
        listOfLoans.add(new LoanInfo("L203","MFI4","Nordic Wonders","ME102",80000,"P400","QuickCash",36,18, simpleDateFormat.parse("2019-06-13 08:46:25")));
        listOfLoans.add(new LoanInfo("L204","MFI3","ThinVoice Limited","ME103",25000,"P400","QuickCash",12,5, simpleDateFormat.parse("2019-05-28 08:46:25")));

        for(LoanInfo loanInfo : listOfLoans) {
            try {
                loanInfoService.registerLoanInfo(loanInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadRepayments(){
        List<RepaymentInfo> repaymentInfoList = new ArrayList<>();
        //String microEntrepreneurId, String mfiId, String loanId, String loanAmount, Integer payment, Integer paymentDelayedInMonths, String productId, Integer tenure, Integer interestRate
        repaymentInfoList.add(new RepaymentInfo("ME101","MFI1","L200",20000,2000,1,"P400",12,10));
        repaymentInfoList.add(new RepaymentInfo("ME104","MFI2","L201",45000,1890,2,"P400",24,12));
        repaymentInfoList.add(new RepaymentInfo("ME101","MFI1","L200",20000,1000,0,"P400",12,10));
        repaymentInfoList.add(new RepaymentInfo("ME104","MFI2","L201",45000,1500,0,"P400",24,12));
        repaymentInfoList.add(new RepaymentInfo("ME101","MFI1","L200",20000,1100,1,"P400",12,10));
        repaymentInfoList.add(new RepaymentInfo("ME104","MFI2","L201",45000,1700,0,"P400",24,12));
        repaymentInfoList.add(new RepaymentInfo("ME101","MFI1","L200",20000,1000,1,"P400",12,10));
        repaymentInfoList.add(new RepaymentInfo("ME104","MFI2","L201",45000,1500,1,"P400",24,12));
        repaymentInfoList.add(new RepaymentInfo("ME101","MFI1","L200",20000,1600,0,"P400",12,10));
        repaymentInfoList.add(new RepaymentInfo("ME104","MFI2","L201",45000,1700,1,"P400",24,12));
        repaymentInfoList.add(new RepaymentInfo("ME101","MFI1","L200",20000,1000,3,"P400",12,10));
        repaymentInfoList.add(new RepaymentInfo("ME104","MFI2","L201",45000,1500,1,"P400",24,12));

        for(RepaymentInfo repaymentInfo : repaymentInfoList) {
            try {
                repaymentInfoService.registerRepaymentInfo(repaymentInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
