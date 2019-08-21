package com.visa.prj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.prj.dao.CustomerDao;
import com.visa.prj.dao.ProductDao;
import com.visa.prj.entity.Customer;
import com.visa.prj.entity.Product;

@Service
public class OrderService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CustomerDao customerDao;
    
    public List<Product> getProducts(){
        return productDao.findAll();
    }
    
    public List<Product> getProductByPrice(double price){
        return productDao.getByPriceGreaterThan(price);
    }
    
    public List<Product> getByCategory(String category){
        return productDao.findByCategory(category);
    }
    
    @Transactional
    public int saveProduct(Product p) {
        productDao.save(p);
        return p.getId();
    }
    
    public Product getById(int id) {
        return productDao.findById(id).get();
    }
    
    
    public List<Customer> getCustomers(){
    	return customerDao.findAll();
    }
    
    public Customer getByEmail(String email) {
    	return customerDao.findByEmail(email);
    }
    
    public List<Customer> getByFirstName(String firstName){
        return customerDao.findByFirstName(firstName);
    }
    
    @Transactional
    public String saveCustomer(Customer c) {
        customerDao.save(c);
        return c.getEmail();
    }
    
}
