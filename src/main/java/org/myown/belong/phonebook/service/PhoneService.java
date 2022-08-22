package org.myown.belong.phonebook.service;

import org.myown.belong.phonebook.dao.vo.PhoneEntity;
import org.myown.belong.phonebook.dto.Phone;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PhoneService {
    List<Phone> getAllPhones();
    Boolean activatePhone(Long phoneId);
    Boolean activatePhone(String phoneNumber);
}
