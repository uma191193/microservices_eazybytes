package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema to hold Accounts Information")
public class AccountsDto {

    @Schema(name = "accountNumber", description = "Holds account number", example = "1234567890")
    @NotEmpty(message = "Account number can't be null or empty")
    private Long accountNumber;

    @Schema(name = "accountType", description = "Specifies the type of account", example = "SAVINGS")
    @NotEmpty(message = "Account type can't be null or empty")
    private String accountType;

    @Schema(name = "", description = "Provides the branch address of the bank", example = "123,New york")
    @NotEmpty(message = "Branch address can't be null or empty")
    private String branchAddress;
}
