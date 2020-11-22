package com.Areteans.Bank2.service;

import com.Areteans.Bank2.models.Account;
import com.Areteans.Bank2.models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
    public class DebitService {
//        public Account debit(Transaction tx, JdbcTemplate jt) {
//            Connection connection = PostgresManager.getConnection();
//            Account account=new Account();
//            try {
//                PreparedStatement ps = connection.prepareStatement("select * from account where \"acc_id\"=?");
//                ps.setInt(1, tx.getAcc_id());
//                ResultSet resultSet = ps.executeQuery();
//                while (resultSet.next()) {
//                    account.setBalance(resultSet.getDouble("balance"));
//                }
//
//                ps = connection.prepareStatement("update account set balance=? where acc_id=?");
//                ps.setDouble(1,account.getBalance()- tx.getAmt());
//                ps.setInt(2, tx.getAcc_id());
//                System.out.println("prepared statement is created" + ps);
//                ps.execute();
//
//                ps = connection.prepareStatement("select * from account where \"acc_id\"=?");
//                ps.setInt(1, tx.getAcc_id());
//                resultSet = ps.executeQuery();
//                while (resultSet.next()) {
//                    account.setBalance(resultSet.getDouble("balance"));
//                    account.setAcc_id(resultSet.getInt("acc_id"));
//                    account.setAccType(resultSet.getString("acc_type"));}
//
//            } catch (SQLException throwable) {
//                throwable.printStackTrace();
//            }
//            return account;
//        }
public Account postDebit(Transaction tx, JdbcTemplate jt) {
    Integer oldBal=jt.queryForObject("select (balance) from account where acc_id=?",new Object[]{tx.getAmt()},Integer.class);
    int count=jt.update("update account set balance=? where acc_id=?",tx.getAmt()+(oldBal.intValue()),tx.getAcc_id());
    Account account=jt.queryForObject("select * from account where acc_id=?",new Object[]{tx.getAcc_id()},Account.class);
    return account;
}
    public Account getDebit( int acc_id,JdbcTemplate jt) {
        Account account=jt.queryForObject("select * from account where acc_id=?",
                new Object[] {acc_id},new AccountRowMapper());
        return account;
    }
    public Account putDebit(Account acc, JdbcTemplate jt) {
        Account account=jt.queryForObject("insert into account values (?,?,?,?,?)",
                Account.class,
                acc.getAccType(),
                acc.getAcc_id(),
                acc.getBalance(),
                acc.getUsername(),
                acc.getPassword());
        return account;
    }
    public Account deleteDebit(Account acc, JdbcTemplate jt) {
        Account account=jt.queryForObject("delete from account where acc_id=?",
                Account.class, acc.getAcc_id());
        return account;
    }
    }

