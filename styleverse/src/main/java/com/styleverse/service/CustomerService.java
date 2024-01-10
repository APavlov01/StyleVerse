package com.styleverse.service;

import com.styleverse.dto.CustomerDTO;
import com.styleverse.entity.Customer;
import com.styleverse.mapper.CustomerMapper;
import com.styleverse.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(CustomerMapper.INSTANCE::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper.INSTANCE::customerToCustomerDTO)
                .orElse(null);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.customerToCustomerDTO(savedCustomer);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        if (customerRepository.existsById(id)) {
            Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
            customer.setId(id);
            Customer updatedCustomer = customerRepository.save(customer);
            return CustomerMapper.INSTANCE.customerToCustomerDTO(updatedCustomer);
        } else {
            return null; //customer with the given id does not exist
        }
    }

    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        } else {
            return false; // customer with the given id not exist
        }
    }
}
