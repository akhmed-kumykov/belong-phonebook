package org.myown.belong.phonebook.dao.transform;

import org.myown.belong.phonebook.dao.vo.CustomerEntity;
import org.myown.belong.phonebook.dto.Customer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerEntityToDTO implements Function<CustomerEntity, Customer>{
    @Override
    public Customer apply(CustomerEntity customerEntity) {
        Customer customer = new Customer();
        customer.setCustomerId(customerEntity.getCustomerId());
        customer.setFirstName(customerEntity.getFirstName());
        customer.setLastName(customerEntity.getLastName());
        return customer;
    }
}
