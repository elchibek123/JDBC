package java15.services;

import java15.models.Customer;

import java.util.List;

public interface CustomerService {
    String createCustomerTable();

    String createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    String updateCustomer(Long id, Customer newCustomer);

    String deleteCustomer(Long id);

    List<Customer> sortCustomersByAge(String ascOrDesc);

    List<Customer> searchCustomersByName(String name);
}
