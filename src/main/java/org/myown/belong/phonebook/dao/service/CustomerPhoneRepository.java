package org.myown.belong.phonebook.dao.service;

import org.myown.belong.phonebook.dao.vo.CustomerPhonesEntity;
import org.myown.belong.phonebook.dao.vo.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerPhoneRepository extends JpaRepository<CustomerPhonesEntity, Long> {
    List<CustomerPhonesEntity> findByCustomerId(Long customerId);
}
