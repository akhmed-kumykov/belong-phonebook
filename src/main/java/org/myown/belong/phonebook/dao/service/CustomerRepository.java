package org.myown.belong.phonebook.dao.service;

import org.myown.belong.phonebook.dao.vo.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
