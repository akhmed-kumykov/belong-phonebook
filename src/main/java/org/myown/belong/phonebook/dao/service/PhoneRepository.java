package org.myown.belong.phonebook.dao.service;

import org.myown.belong.phonebook.dao.vo.CustomerPhonesEntity;
import org.myown.belong.phonebook.dao.vo.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
    Optional<PhoneEntity> findByPhoneNumber(String phoneNumber);
}
