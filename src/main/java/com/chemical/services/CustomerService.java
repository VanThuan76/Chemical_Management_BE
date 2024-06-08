package com.chemical.services;

import com.chemical.dto.request.CustomerCreateRequestDTO;
import com.chemical.dto.request.CustomerUpdateRequestDTO;
import com.chemical.dto.response.CustomerResponseDTO;
import com.chemical.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDTO> getAllCustomers();

    Customer findById(Long customerId);

    CustomerResponseDTO findDetailsById(Long customerId);

    Customer save(CustomerCreateRequestDTO request);

    Customer update(Long customerId, CustomerUpdateRequestDTO request);

    void delete(Long customerId);


}
