package org.myown.belong.phonebook.service;

import org.myown.belong.phonebook.dao.vo.CustomerEntity;
import org.myown.belong.phonebook.dao.vo.PhoneEntity;
import org.myown.belong.phonebook.dto.Customer;
import org.myown.belong.phonebook.dto.Phone;

import java.util.List;

public interface CustomerPhoneService {
    List<Customer> getAllCustomers();
    List<Phone> getCustomerPhones(Long customerId);
}
