package java15.dao;

import java15.models.Customer;

import java.util.List;

public interface CustomerDao {
    boolean saveTable();

    boolean save(Customer customer);

    List<Customer> findAll();

    Customer findById(Long id);

    boolean update(Long id, Customer newCustomer);

    boolean delete(Long id);

    List<Customer> sortByAge(String ascOrDesc);

    List<Customer> searchByName(String name);
}
