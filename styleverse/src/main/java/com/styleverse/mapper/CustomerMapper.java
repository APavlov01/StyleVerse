package com.styleverse.mapper;

import com.styleverse.dto.CustomerDTO;
import com.styleverse.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "id", target = "customerId")
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(source = "customerId", target = "id")
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
