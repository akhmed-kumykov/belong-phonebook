package org.myown.belong.phonebook;

import org.myown.belong.phonebook.swagger.SpringFoxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringFoxConfig.class)
public class PhoneBookWebAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhoneBookWebAppApplication.class, args);
    }
}
