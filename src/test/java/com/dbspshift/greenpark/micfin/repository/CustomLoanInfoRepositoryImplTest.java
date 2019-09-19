package com.dbspshift.greenpark.micfin.repository;

import com.dbspshift.greenpark.micfin.beans.LoanInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@DataMongoTest
@RunWith(SpringRunner.class)
public class CustomLoanInfoRepositoryImplTest {

    List<LoanInfo> listOfLoans = new ArrayList<>();
    @Autowired
    LoanInfoRepository loanInfoRepository;

    @Before
    public void setUp() {
        loadLoanInfosToRepo();
    }

    private void loadLoanInfosToRepo(){
        listOfLoans.add(new LoanInfo("LN00","MFI1","OmGanesh Pte Ltd","ME101",20000,"P400","QuickCash",12,10));
        listOfLoans.add(new LoanInfo("LN01","MFI2","WikiViki","ME104",45000,"P400","QuickCash",24,12));
        listOfLoans.add(new LoanInfo("LN02","MFI1","OmGanesh Pte Ltd","ME101",28000,"P400","QuickCash",20,15));
        listOfLoans.add(new LoanInfo("L203","MFI4","Nordic Wonders","ME102",80000,"P400","QuickCash",36,18));
        listOfLoans.add(new LoanInfo("L204","MFI3","ThinVoice Limited","ME103",25000,"P400","QuickCash",12,5));

        for(LoanInfo loanInfo : listOfLoans) {
            try {
                loanInfoRepository.save(loanInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void findByLoanId() {
    }

    @Test
    public void getMaxLoanId() throws Exception {
        Optional<Integer> maxLoanId = loanInfoRepository.getMaxLoanId();
        System.out.println(maxLoanId.isPresent()?maxLoanId.get():"");
        Integer expectedVal = 2;
        assertEquals(expectedVal,maxLoanId.get());
    }
}