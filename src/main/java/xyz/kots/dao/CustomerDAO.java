package xyz.kots.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.kots.entity.Customer;

@Transactional
@Repository
public class CustomerDAO implements ICustomerDAO {

	@PersistenceContext
	private EntityManager entityManager;	

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers() {
		return (List<Customer>) entityManager.createQuery("FROM Customer").getResultList();
	}

	@Override
	public Customer getCustomerById(long customerId) {
		return entityManager.find(Customer.class, customerId);
	}

	@Override
	public List<Customer> getCustomerByName(String customerName) {
		return (List<Customer>) entityManager.createQuery("FROM Customer WHERE name = :name", Customer.class).setParameter("name", customerName).getResultList();
	}

	@Override
	public void addCustomer(Customer customer) {
		entityManager.persist(customer);
}

	@Override
	public void updateCustomer(Customer customer) {
		Customer cust = getCustomerById(customer.getId());
		if (customer.getName()!=null)
			cust.setName(customer.getName());
		if (customer.getPhone()!=null)
			cust.setPhone(customer.getPhone());
		if (customer.getAddress()!=null)
			cust.setAddress(customer.getAddress());
		entityManager.flush();
	}
}
