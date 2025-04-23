package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Accounts information")
public class CustomerDto {

    @Schema(name = "name", description = "Represents name of the customer", example = "Uma Maheswar")
    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 5, max = 30, message = "Name size must be between 5 and 30")
    private String name;

    @Schema(name = "email", description = "Holds the email id of the customer", example = "uma@example.com")
    @NotEmpty(message = "Email can't be null or empty")
    @Email(message = "Email must be specified in a correct way")
    private String email;

    @Schema(name = "mobileNumber", description = "Hold the mobile Number", example = "9876543210")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;

}
