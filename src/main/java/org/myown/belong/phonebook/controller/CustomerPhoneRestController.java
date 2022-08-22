package org.myown.belong.phonebook.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.myown.belong.phonebook.dto.Customer;
import org.myown.belong.phonebook.dto.Phone;
import org.myown.belong.phonebook.service.CustomerPhoneService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerPhoneRestController {

    @Resource
    private CustomerPhoneService customerPhoneService;

    @ApiOperation(value="Retrieve all customers", httpMethod="GET")
    @GetMapping
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return customerPhoneService.getAllCustomers();
    }

    @ApiOperation(value="Retrieve all phones of a customer", httpMethod="GET")
    @GetMapping
    @RequestMapping(value = "/phones/{customerId}")
    @ResponseBody
    public List<Phone> getCustomerPhones(@PathVariable(name = "customerId") long customerId) {
        return customerPhoneService.getCustomerPhones(customerId);
    }
}
