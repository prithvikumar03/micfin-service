package com.dbspshift.greenpark.micfin.exceptions;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
