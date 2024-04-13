package com.example.bankingapplication.mapper;

import com.example.bankingapplication.dto.AccountDto;
import com.example.bankingapplication.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()



        );
        return account;
    }

    public static AccountDto mapTAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()

        );
        return accountDto;
    }
}
