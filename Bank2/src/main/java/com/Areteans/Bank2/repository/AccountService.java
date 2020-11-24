package com.Areteans.Bank2.repository;

import com.Areteans.Bank2.models.Account;
import com.Areteans.Bank2.models.BankTransactionJPA;
import com.Areteans.Bank2.models.Password;
import com.Areteans.Bank2.service.AccountRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final BankTransactionRepository bankRepo;

    public AccountService(BankTransactionRepository bankRepo) {
        this.bankRepo = bankRepo;
    }


    public int password(Password ps, JdbcTemplate jt) {
        int count = jt.update("update account set password=? where username=?"
                , ps.getNewPaswd()
                , ps.getUsername());
        return count;
    }

    public int delete(Password ps, JdbcTemplate jt) {
        return jt.update("delete from account where username=?"
                , ps.getUsername());
    }

    public Account readFromDB(String username, JdbcTemplate jt) {
        Account account = jt.queryForObject("select * from account where username=?",
                new AccountRowMapper(), username);
        return account;
    }

    public int putAccount(Account acc, JdbcTemplate jt) {
        int count = jt.update("insert into account values (?,?,?,?,?)",
                acc.getAcc_id(),
                acc.getAccType(),
                acc.getBalance(),
                acc.getUsername(),
                acc.getPassword());
        return count;
    }

    public BankTransactionJPA saveAccount(BankTransactionJPA xyz) {
        return bankRepo.save(xyz);
    }
}