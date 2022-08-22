package org.myown.belong.phonebook.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myown.belong.phonebook.dao.service.PhoneRepository;
import org.myown.belong.phonebook.dao.vo.PhoneEntity;
import org.myown.belong.phonebook.dto.ActiveFlag;
import org.myown.belong.phonebook.dto.Phone;
import org.myown.belong.phonebook.exceptions.AlreadyActivatedException;
import org.myown.belong.phonebook.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PhoneServiceImpl implements PhoneService {
    private static final Logger LOGGER = LogManager.getLogger(PhoneServiceImpl.class);

    public static final String ALREADY_ACTIVE_MSG = "Phone %s is already activated";
    public static final String NO_RECORD_FOUND_MSG = "Phone with id %d does not exists";
    public static final String NO_PHONE_NUMBER_FOUND_MSG = "Phone with number %s does not exists";

    private final PhoneRepository phoneRepository;
    private final Function<PhoneEntity, Phone> phoneEntityToDTO;

    public PhoneServiceImpl(PhoneRepository phoneRepository, Function<PhoneEntity, Phone> phoneEntityToDTO) {
        super();
        this.phoneRepository = phoneRepository;
        this.phoneEntityToDTO = phoneEntityToDTO;
    }

    @Override
    public List<Phone> getAllPhones() {
        List<PhoneEntity> phonesEnt = phoneRepository.findAll();
        List<Phone> phones = phonesEnt.stream()
                .map(phoneEntityToDTO)
                .collect(Collectors.toList());

        return phones;
    }

    @Override
    public Boolean activatePhone(Long phoneId) {
        Optional<PhoneEntity> phoneOptional = phoneRepository.findById(phoneId);
        return checkAndActivatePhone(phoneOptional, String.format(NO_RECORD_FOUND_MSG, phoneId));
    }

    @Override
    public Boolean activatePhone(String phoneNumber) {
        Optional<PhoneEntity> phoneOptional = phoneRepository.findByPhoneNumber(phoneNumber);
        return checkAndActivatePhone(phoneOptional, String.format(NO_PHONE_NUMBER_FOUND_MSG, phoneNumber));
    }

    private Boolean checkAndActivatePhone(Optional<PhoneEntity> phoneOptional, String format) {
        if (phoneOptional.isPresent()) {
            PhoneEntity phone = phoneOptional.get();
            LOGGER.info("Found phone {}, it's current status {}!", phone.getPhoneNumber(), phone.getActiveFlag());
            if (ActiveFlag.INACTIVE == ActiveFlag.parse(phone.getActiveFlag())) {
                phone.setActiveFlag(ActiveFlag.ACTIVE.code);
                PhoneEntity savedPhone = phoneRepository.save(phone);
                LOGGER.info("Phone {} activated, current status {}!", savedPhone.getPhoneNumber(), savedPhone.getActiveFlag());
                return true;
            } else {
                throw new AlreadyActivatedException(String.format(ALREADY_ACTIVE_MSG, phone.getPhoneNumber()));
            }
        } else {
            throw new RecordNotFoundException(format);
        }
    }
}
