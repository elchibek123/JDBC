package java15;

import java15.models.Customer;
import java15.services.CustomerService;
import java15.services.impl.CustomerServiceImpl;

import java.util.List;

public class App {
    public static void main( String[] args ) {
        CustomerService customerService = new CustomerServiceImpl();

//        System.out.println(customerService.createCustomerTable());

        Customer customer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .age(35)
                .phoneNumber("123-456-7890")
                .build();

        Customer customer2 = Customer.builder()
                .id(1L)
                .firstName("David")
                .lastName("Smith")
                .age(28)
                .phoneNumber("321-456-7890")
                .build();

//        System.out.println(customerService.createCustomer(customer));
//        System.out.println(customerService.createCustomer(customer2));

//        List<Customer> allCustomers = customerService.getAllCustomers();
//        allCustomers.forEach(System.out::println);

//        System.out.println(customerService.getCustomerById(1L));

//        System.out.println(customerService.updateCustomer(1L, customer2));

//        System.out.println(customerService.deleteCustomer(1L));

//        List<Customer> customers = customerService.sortCustomersByAge("asc");
//        customers.forEach(System.out::println);

//        customerService.searchCustomersByName("david").forEach(System.out::println);
    }
}
