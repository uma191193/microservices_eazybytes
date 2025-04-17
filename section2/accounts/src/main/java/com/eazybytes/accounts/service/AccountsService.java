package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountsService {

    /**
     * @param customerDto is input for creating account
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber is input for fetching account details
     */
    CustomerDto fetchAccountDetails(String mobileNumber);
}
