package org.myown.belong.phonebook.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myown.belong.phonebook.dao.service.CustomerPhoneRepository;
import org.myown.belong.phonebook.dao.service.CustomerRepository;
import org.myown.belong.phonebook.dao.vo.CustomerEntity;
import org.myown.belong.phonebook.dao.vo.CustomerPhonesEntity;
import org.myown.belong.phonebook.dao.vo.PhoneEntity;
import org.myown.belong.phonebook.dto.Customer;
import org.myown.belong.phonebook.dto.Phone;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CustomerPhoneServiceImpl implements CustomerPhoneService {
    private static final Logger LOGGER = LogManager.getLogger(PhoneServiceImpl.class);

    private final Function<CustomerEntity, Customer> customerEntityToDTO;
    private final Function<PhoneEntity, Phone> phoneEntityToDTO;

    private final CustomerPhoneRepository customerPhoneRepository;
    private final CustomerRepository customerRepository;

    public CustomerPhoneServiceImpl(CustomerPhoneRepository customerPhoneRepository, CustomerRepository customerRepository, Function<CustomerEntity, Customer> customerEntityToDTO, Function<PhoneEntity, Phone> phoneEntityToDTO) {
        this.customerPhoneRepository = customerPhoneRepository;
        this.customerRepository = customerRepository;
        this.customerEntityToDTO = customerEntityToDTO;
        this.phoneEntityToDTO = phoneEntityToDTO;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<CustomerEntity> customersEnt = customerRepository.findAll();
        List<Customer> customers = customersEnt.stream()
                .map(customerEntityToDTO)
                .collect(Collectors.toList());

        return customers;
    }

    @Override
    public List<Phone> getCustomerPhones(Long customerId) {
        List<CustomerPhonesEntity> customerPhonesOption = customerPhoneRepository.findByCustomerId(customerId);
        if (!CollectionUtils.isEmpty(customerPhonesOption)) {
            List<PhoneEntity> phonesEnt = customerPhonesOption.stream().map(p -> p.getPhone()).collect(Collectors.toList());
            List<Phone> phones = phonesEnt.stream()
                    .map(phoneEntityToDTO)
                    .collect(Collectors.toList());
            return phones;
        } else {
            LOGGER.debug("No phones found for customer id: {}", customerId);
        }
        return Collections.emptyList();
    }
}
