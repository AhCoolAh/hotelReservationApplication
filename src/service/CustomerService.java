package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    //Collection<Customer> customers = new ArrayList<>();
    static Map<String, Customer> mapOfCustomers = new HashMap<String, Customer>();
    private static CustomerService instance;
    //private final Map<String, Customer> mapOfCustomers;

    private CustomerService() {}

    public static CustomerService getInstance() {
        if(instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        mapOfCustomers.put(customer.getEmail(), customer);
    }

    public Customer getCustomer(String customerEmail) {
        return mapOfCustomers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return mapOfCustomers.values();
    }

}
