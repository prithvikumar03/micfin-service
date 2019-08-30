package com.dbspshift.greenpark.micfin.services;

import com.dbspshift.greenpark.micfin.beans.Product;
import com.dbspshift.greenpark.micfin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product registerProduct(Product product) throws Exception {
        Product insert = productRepository.insert(product);
        return insert;
    }

    @Override
    public Product getProductById(String id) throws Exception {
        Optional<Product> byId = productRepository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else
            return null;
    }

    @Override
    public List<Product> getAllProducts() throws Exception {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) throws Exception {
        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(String id) throws Exception {
        try {
            productRepository.deleteById(id);
            return "success";
        }catch(Exception e){
            return "failed";
        }
    }
}
