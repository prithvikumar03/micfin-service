package com.dbspshift.greenpark.micfin.exceptions;

public class LoanInfoNotFoundException extends Exception{
    public LoanInfoNotFoundException() {
        super();
    }

    public LoanInfoNotFoundException(String message) {
        super(message);
    }
}
