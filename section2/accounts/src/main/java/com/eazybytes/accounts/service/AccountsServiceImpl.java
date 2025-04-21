package com.eazybytes.accounts.service;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private Random random = new Random();

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(new Customer(), customerDto);
        Optional<Customer> customerByMobileNumber = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (customerByMobileNumber.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with the mobile number is already exists : " + customer.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Uma");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    /**
     * @param mobileNumber is input for fetching account details
     * @return CustomerDto
     */
    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customer id", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(new CustomerDto(), customer);
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());

        customerDto.setAccountsDto(accountsDto);
        return customerDto;

    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (Objects.nonNull(accountsDto)) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Accounts", "AccountNumber", accountsDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accountsRepository.save(accounts);

            Customer customer = customerRepository.findById(accounts.getCustomerId()).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", accounts.getCustomerId().toString())
            );

            CustomerMapper.mapToCustomer(customer, customerDto);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber is input for deleting account details
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );
        if (Objects.nonNull(customer)) {
            customerRepository.deleteByCustomerId(customer.getCustomerId());
            accountsRepository.deleteById(customer.getCustomerId());
            return true;
        }

        return false;
    }

    private Accounts createNewAccount(Customer savedCustomer) {
        Accounts newAccounts = new Accounts();
        long randomAccountNumber = 1000000000L + this.random.nextInt(900000000);

        newAccounts.setCustomerId(savedCustomer.getCustomerId());
        newAccounts.setAccountType(AccountsConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
        newAccounts.setAccountNumber(randomAccountNumber);
        newAccounts.setCreatedAt(LocalDateTime.now());
        newAccounts.setCreatedBy("Uma");
        return newAccounts;
    }

}
