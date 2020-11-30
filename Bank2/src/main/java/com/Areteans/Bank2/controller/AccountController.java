package com.Areteans.Bank2.controller;

import com.Areteans.Bank2.models.Account;
import com.Areteans.Bank2.models.Password;
import com.Areteans.Bank2.models.PaymentJPA;
import com.Areteans.Bank2.repository.AccountService;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    private final JdbcTemplate jt;
    private final AccountService as;
   public AccountController(JdbcTemplate jt, AccountService as) {
        this.jt = jt;
        this.as = as;
    }

    @PostMapping(path = "updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updatePassword(@RequestBody Password ps) {
        System.out.println("in updatePassword endpoint");
        int op = as.password(ps, jt);
        return "New Password Updated for " + ps.getUsername() + "," + op + "rows updated";
    }

    @GetMapping(path = "fromDB/{username}")
    public Account fromDB(@PathVariable("username") String username)

    {
        Account acc=as.readFromDB(username,jt);
        return acc;
    }

    @PutMapping(path = "putAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String putAccount(@RequestBody Account acc) {
        System.out.println("in credit reading requestBody");
        int op = as.putAccount(acc, jt);
        return "Put successful" + op + " rows put";
    }

    @DeleteMapping(path = "delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@RequestBody Password ps) {
        System.out.println("in delete endpoint");
        int count = as.delete(ps, jt);
        if (count > 0)
            return "deleted for" + ps.getUsername();
        else
            return "Nothing to delete";
    }

    @PostMapping(path = "account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PaymentJPA createAccount(@RequestBody PaymentJPA btjpa)
    {
        return as.saveAccount(btjpa);
    }

    @GetMapping(path = "example", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentJPA> example()
    {
        List<PaymentJPA> list=as.getAll();
        return list;
    }
    @PutMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentJPA> example1(@RequestBody PaymentJPA paymentJPA)
    {
        //1st endpoint for multiThreading
      return as.save(paymentJPA);

    }
    @DeleteMapping(path = "delete1", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String example2(@RequestBody PaymentJPA paymentJPA)
    {

        return as.deleteID(paymentJPA.getTrans_id());
        //return op;
    }
    @PutMapping(path = "addAmount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addAmount(@RequestBody PaymentJPA paymentJPA)
    {
        //2nd endpoint for multiThreading
        return as.addAmount(paymentJPA.getTrans_id(),paymentJPA.getAmount());
        //return op;
    }
    @PostMapping(path="nj",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Object> account(@RequestBody Account account)
    {
        return as.create(account,jt);
    }

    @PostMapping(path = "multithreading/http", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PaymentJPA createEmployeeOveRHTTP(@RequestBody PaymentJPA paymentJPA) {
        return as.multi(paymentJPA);
    }

}