package java15.services.impl;

import java15.dao.CustomerDao;
import java15.dao.impl.CustomerDaoImpl;
import java15.models.Customer;
import java15.services.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public String createCustomerTable() {
        return customerDao.saveTable() ? "Table successfully saved" : "Failed to save table";
    }

    @Override
    public String createCustomer(Customer customer) {
        return customerDao.save(customer) ? "Customer successfully saved" : "Failed to save customer";
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public String updateCustomer(Long id, Customer newCustomer) {
        return customerDao.update(id, newCustomer) ? "Customer successfully updated" : "Failed to update customer";
    }

    @Override
    public String deleteCustomer(Long id) {
        return customerDao.delete(id) ? "Customer successfully deleted" : "Failed to delete customer";
    }

    @Override
    public List<Customer> sortCustomersByAge(String ascOrDesc) {
        return customerDao.sortByAge(ascOrDesc);
    }

    @Override
    public List<Customer> searchCustomersByName(String name) {
        return customerDao.searchByName(name);
    }
}
