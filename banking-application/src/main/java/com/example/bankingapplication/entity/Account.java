package com.example.bankingapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;

    public Account(Long id, String accountHolderName, Object balance) {
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = (Long) id;
    }

    public Object getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(Object accountHolderName) {
        this.accountHolderName = (String) accountHolderName;
    }

    public Object getBalance() {
        return balance;
    }

    public void setBalance(Object balance) {
        this.balance = (double) balance;
    }
}
