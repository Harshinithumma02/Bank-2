package com.Areteans.Bank2.service;

import com.Areteans.Bank2.config.PostgresManager;
import com.Areteans.Bank2.models.Account;
import com.Areteans.Bank2.models.Customer;
import com.Areteans.Bank2.models.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    public Customer xyz(Customer customer) {
        Connection connection = PostgresManager.getConnection();
        //ResultSet resultSet=null;
        try {
            PreparedStatement ps = connection.prepareStatement("insert into customer values (?,?,?,?)");
            ps.setString(1, customer.getName());
            ps.setInt(2, 0);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            System.out.println("prepared statement is created" + ps);
            ps.execute();


            //select query to get Data
            ps = connection.prepareStatement("select * from customer where \"name\"=?");
            ps.setString(1, customer.getName());
            System.out.println("prepared select query statement is created, to see if data is inserted" + ps);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customer .setName(resultSet.getString("name"));
                customer .setAge(resultSet.getInt("age"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }
    public List<Map<String, String>> getDataFromDB( ) {
        Connection connection = PostgresManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer");
            List<Map<String, String>> resultList = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, String> result = new HashMap<>();
                String name = result.put("name", String.valueOf(resultSet.getObject("name")));
                String age= result.put("age",String.valueOf(resultSet.getObject("age")));
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            System.err.println("Error getting data from table");
            return null;
        }
    }
    public Account credit(Transaction tx) {
        Connection connection = PostgresManager.getConnection();
        Account account=new Account();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from account where \"acc_id\"=?");
            ps.setInt(1, tx.getAcc_id());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                account.setBalance(resultSet.getDouble("balance"));
            }

            ps = connection.prepareStatement("update account set balance=? where acc_id=?");
            ps.setDouble(1,account.getBalance()+ tx.getAmt());
            ps.setInt(2, tx.getAcc_id());
            System.out.println("prepared statement is created" + ps);
            ps.execute();

            ps = connection.prepareStatement("select * from account where \"acc_id\"=?");
            ps.setInt(1, tx.getAcc_id());
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                account.setBalance(resultSet.getDouble("balance"));
                account.setAcc_id(resultSet.getInt("acc_id"));
                account.setAccType(resultSet.getString("acc_type"));}

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }
    public Account credit1(Transaction tx,JdbcTemplate jt) {
      Integer oldBal=jt.queryForObject("select (balance) from account where acc_id=?",new Object[]{tx.getAmt()},Integer.class);
      int count=jt.update("update account set balance=? where acc_id=?",tx.getAmt()+(oldBal.intValue()),tx.getAcc_id());
      Account account=jt.queryForObject("select * from account where acc_id=?",new Object[]{tx.getAcc_id()},Account.class);
      return account;
    }

    }







