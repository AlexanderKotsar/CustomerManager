package xyz.kots.service;

import xyz.kots.entity.Customer;

import java.util.List;


public interface ICustomerService {
     List<Customer> getAllCustomers();
     Customer getCustomerId(long customerId);
     List<Customer> getCustomerName(String customerName);
     boolean addCustomer(Customer customer);
     void updateCustomer(Customer customer);
}
