package org.myown.belong.phonebook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.myown.belong.phonebook.dto.Customer;
import org.myown.belong.phonebook.dto.ErrorInfo;
import org.myown.belong.phonebook.dto.Phone;
import org.myown.belong.phonebook.service.PhoneServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/clean_data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PhoneBookWebAppApplicationTest {
    private static final Logger LOGGER = LogManager.getLogger(PhoneServiceImpl.class);

    @LocalServerPort
    private int port;


    @Resource
    private TestRestTemplate restTemplate;
    private String serviceUrl;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        serviceUrl = "http://localhost:" + port;
    }

    @Test
    public void verifyGetAllPhonesApi() throws Exception {
        List<Phone> phones = getAllPhones("/api/phones");
        assertThat(phones).hasSize(10);
        List<Phone> activePhones = phones.stream().filter(p -> p.isActiveFlag()).collect(Collectors.toList());
        assertThat(activePhones).hasSize(1);
    }

    @Test
    public void verifyActivatePhoneByIdApi() throws Exception {
        String url = serviceUrl + "/api/phones/activateById/2";
        LOGGER.info("Calling {}", url);
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Boolean>() {
        });
        LOGGER.info(response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Boolean activatedFlag = response.getBody();
        assertThat(activatedFlag).isTrue();
        List<Phone> phones = getAllPhones("/api/phones");
        assertThat(phones).hasSize(10);

        List<Phone> activePhones = phones.stream().filter(p -> p.isActiveFlag()).collect(Collectors.toList());
        assertThat(activePhones).hasSize(2);
    }

    @Test
    public void verifyActivateAlreadyActivePhoneApi() throws Exception {
        String url = serviceUrl + "/api/phones/activate/0404000001";
        LOGGER.info("Calling {}", url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        });
        LOGGER.info(response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        ErrorInfo errorInfo = mapper.readValue(response.getBody(), ErrorInfo.class);
        assertThat(errorInfo).isNotNull();
        assertThat(errorInfo.getEx()).isEqualTo(String.format(PhoneServiceImpl.ALREADY_ACTIVE_MSG, "0404000001"));
    }

    @Test
    public void verifyActivateNonExistingPhoneApi() throws Exception {
        String url = serviceUrl + "/api/phones/activate/04040000000";
        LOGGER.info("Calling {}", url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        });
        LOGGER.info(response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        ErrorInfo errorInfo = mapper.readValue(response.getBody(), ErrorInfo.class);
        assertThat(errorInfo).isNotNull();
        assertThat(errorInfo.getEx()).isEqualTo(String.format(PhoneServiceImpl.NO_PHONE_NUMBER_FOUND_MSG, "04040000000"));
    }

    @Test
    public void verifyActivatePhoneApi() throws Exception {
        String url = serviceUrl + "/api/phones/activate/0404000002";
        LOGGER.info("Calling {}", url);
        ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Boolean>() {
        });
        LOGGER.info(response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Boolean activatedFlag = response.getBody();
        assertThat(activatedFlag).isTrue();
        List<Phone> phones = getAllPhones("/api/phones");
        assertThat(phones).hasSize(10);

        List<Phone> activePhones = phones.stream().filter(p -> p.isActiveFlag()).collect(Collectors.toList());
        assertThat(activePhones).hasSize(2);
    }

    @Test
    public void verifyCustomerApi() throws Exception {
        String url = serviceUrl + "/api/customers";
        LOGGER.info("Calling {}", url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        });
        LOGGER.info(response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Customer> customers = mapper.readValue(response.getBody(), new TypeReference<List<Customer>>() {
        });
        assertThat(customers).isNotNull();
        assertThat(customers).hasSize(5);
    }

    @Test
    public void verifyCustomerPhonesApi() throws Exception {
        List<Phone> phones = getAllPhones("/api/customers/phones/1");
        assertThat(phones).hasSize(2);
        List<Phone> activePhones = phones.stream().filter(p -> p.isActiveFlag()).collect(Collectors.toList());
        assertThat(activePhones).hasSize(1);

        phones = getAllPhones("/api/customers/phones/2");
        assertThat(phones).hasSize(1);

        phones = getAllPhones("/api/customers/phones/3");
        assertThat(phones).hasSize(4);
        activePhones = phones.stream().filter(p -> p.isActiveFlag()).collect(Collectors.toList());
        assertThat(activePhones).hasSize(0);
    }

    private List<Phone> getAllPhones(String urlSuffix) throws JsonProcessingException {
        String getPhonesUrl = serviceUrl + urlSuffix;
        LOGGER.info("Calling {}", getPhonesUrl);
        ResponseEntity<String> response = restTemplate.exchange(getPhonesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        });
        LOGGER.info(response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Phone> phones = mapper.readValue(response.getBody(), new TypeReference<List<Phone>>() {
        });
        assertThat(phones).isNotNull();
        return phones;
    }
}
