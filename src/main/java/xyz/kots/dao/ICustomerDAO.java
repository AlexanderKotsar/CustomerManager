package xyz.kots.dao;
import xyz.kots.entity.Customer;

import java.util.List;

public interface ICustomerDAO {
    List<Customer> getAllCustomers();
    Customer getCustomerById(long customerId);
    List<Customer> getCustomerByName(String customerName);
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
}
 