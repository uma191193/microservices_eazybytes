package com.eazybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice",
                description = "Microservice related to EasyBank accounts",
                contact = @Contact(
                        name = "Uma Maheswar",
                        email = "uma191193@gmail.com",
                        url = "https://www.linkedin.com/umaMaheswar-dev"
                ),
                version = "v1",
                license = @License(
                        name = "Production License",
                        url = "https://www.google.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "For more info",
                url = "https://www.google.com"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {

        SpringApplication.run(AccountsApplication.class, args);
    }

}
