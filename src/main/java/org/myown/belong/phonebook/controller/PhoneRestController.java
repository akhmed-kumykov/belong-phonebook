package org.myown.belong.phonebook.controller;

import io.swagger.annotations.ApiOperation;
import org.myown.belong.phonebook.dto.ErrorInfo;
import org.myown.belong.phonebook.dto.Phone;
import org.myown.belong.phonebook.exceptions.AlreadyActivatedException;
import org.myown.belong.phonebook.exceptions.RecordNotFoundException;
import org.myown.belong.phonebook.service.PhoneService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/phones")
public class PhoneRestController {

    @Resource
    private PhoneService phoneService;

    @ApiOperation(value="Retrieve all phones", httpMethod="GET")
    @GetMapping
    @ResponseBody
    public List<Phone> getAllPhones() {
        return phoneService.getAllPhones();
    }

    @ApiOperation(value="Activate phone by phone ID", httpMethod="POST")
    @PostMapping
    @RequestMapping(value = "/activateById/{phoneId}")
    @ResponseBody
    public Boolean activatePhoneById(@PathVariable(name = "phoneId") long phoneId) {
        return phoneService.activatePhone(phoneId);
    }

    @ApiOperation(value="Activate phone by phone number", httpMethod="POST")
    @PostMapping
    @RequestMapping(value = "/activate/{phoneNo}")
    @ResponseBody
    public Boolean activatePhone(@PathVariable(name = "phoneNo") String phoneNumber) {
        return phoneService.activatePhone(phoneNumber);
    }

    @ExceptionHandler(AlreadyActivatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    ErrorInfo AlreadyActivatedHandler(HttpServletRequest request, Exception ex) {

        return new ErrorInfo(request.getRequestURL().toString(), ex);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorInfo recordNotFoundHandler(HttpServletRequest request, Exception ex) {
        return new ErrorInfo(request.getRequestURL().toString(), ex);
    }
}
