package com.Areteans.Bank2.service;

import com.Areteans.Bank2.models.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class AccountRowMapper implements RowMapper<Account> {
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account acc = new Account();
        acc.setUsername(rs.getString("username"));
        acc.setAccType(rs.getString("acc_type"));
        acc.setAcc_id(rs.getInt("acc_id"));
        acc.setBalance(rs.getDouble("balance"));
        return acc;
    }
}
