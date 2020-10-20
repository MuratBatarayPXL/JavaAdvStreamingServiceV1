package be.pxl.ja.streamingservice.repository;

import be.pxl.ja.streamingservice.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private ArrayList<Account> accounts;

    public void addAccount(Account account){
        accounts = new ArrayList<Account>();
        accounts.add(account);
    }
    public Account findAccount(String email){
        for (Account a: accounts) {
            if (a.getEmail().equals(email)){
                return a;
            }
        }
        return null;
    }
}
