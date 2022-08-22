package org.myown.belong.phonebook.dao.transform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myown.belong.phonebook.dao.vo.PhoneEntity;
import org.myown.belong.phonebook.dto.ActiveFlag;
import org.myown.belong.phonebook.dto.Phone;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PhoneEntityToDTO implements Function<PhoneEntity, Phone> {
    private static final Logger LOGGER = LogManager.getLogger(PhoneEntityToDTO.class);

    @Override
    public Phone apply(PhoneEntity phoneEntity) {
        Phone phone = new Phone();
        phone.setActiveFlag(ActiveFlag.ACTIVE == ActiveFlag.parse(phoneEntity.getActiveFlag()));
        phone.setPhoneId(phoneEntity.getPhoneId());
        phone.setPhoneNumber(phoneEntity.getPhoneNumber());
        return phone;
    }
}
