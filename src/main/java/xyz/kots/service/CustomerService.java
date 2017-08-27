package xyz.kots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kots.dao.ICustomerDAO;
import xyz.kots.entity.Customer;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
	@Autowired
	private ICustomerDAO customerDAO;

	@Override
	public List<Customer> getAllCustomers(){
		return customerDAO.getAllCustomers();
	}

	@Override
	public Customer getCustomerId(long customerId) {
		Customer obj = customerDAO.getCustomerById(customerId);
		return obj;
	}

	@Override
	public List<Customer> getCustomerName(String customerName) {
		List<Customer> obj1 = customerDAO.getCustomerByName(customerName);
		return obj1;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		customerDAO.addCustomer(customer);
		return true;
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDAO.updateCustomer(customer);
	}
}
