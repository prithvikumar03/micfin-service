package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.Product;

import java.util.List;

public interface ProductService {

    public Product registerProduct(Product product) throws Exception;

    public Product getProductById(String id) throws Exception;

    public List<Product> getAllProducts() throws Exception;

    public Product updateProduct(Product product) throws Exception;

    public String deleteProduct(String id) throws Exception;
}
