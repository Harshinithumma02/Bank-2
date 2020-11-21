package com.Areteans.Bank2.service;

import com.Areteans.Bank2.models.Account;
import com.Areteans.Bank2.models.Password;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public int password(Password ps,JdbcTemplate jt) {
        int count = jt.update("update account set password=? where username=?"
                , ps.getNewPaswd()
                , ps.getUsername());
        return count;
    }

    public int delete(Password ps,JdbcTemplate jt) {
        int count = jt.update("delete from account where username=?"
                , ps.getUsername());
        return count;
    }

    public Account readFromDB(String username,JdbcTemplate jt) {
        Account account = jt.queryForObject("select * from account where username=?",
                new AccountRowMapper(), username);
        return account;
    }
    public int putAccount(Account acc,JdbcTemplate jt) {
        int count=jt.update("insert into account values (?,?,?,?,?)",
                acc.getAcc_id(),
                acc.getAccType(),
                acc.getBalance(),
                acc.getUsername(),
                acc.getPassword());
        return count;
    }
}