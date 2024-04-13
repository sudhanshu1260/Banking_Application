package com.example.bankingapplication.service.impl;

import com.example.bankingapplication.dto.AccountDto;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.mapper.AccountMapper;
import com.example.bankingapplication.repository.AccountRepository;
import com.example.bankingapplication.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
      Account savedAccount =   accountRepository.save(account);

        return AccountMapper.mapTAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account = accountRepository
               .findById(id)
               .orElseThrow(()-> new RuntimeException("Account doesn't exist"));
        return AccountMapper.mapTAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account doesn't exist"));

//     double total = account.getBalance() + amount;
        int balance =  (int) account.getBalance();
        double total = balance + amount;

        account.setBalance(total);
     Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapTAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account doesn't exist"));

        // Assuming getBalance() returns a double
        if (((Double)account.getBalance()) < amount) {
            throw new RuntimeException("Insufficient amount");
        }


        double balance = (double) account.getBalance();
        double total = balance - amount;

        account.setBalance(total);
       Account savedAccount =  accountRepository.save(account);


        return AccountMapper.mapTAccountDto(savedAccount);
    }


    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(account -> AccountMapper.mapTAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account doesn't exist"));

        accountRepository.deleteAllById(Collections.singleton(id));
    }

}
