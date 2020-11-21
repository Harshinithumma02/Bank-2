package com.Areteans.Bank2.controller;

import com.Areteans.Bank2.models.Account;
import com.Areteans.Bank2.models.Transaction;
import com.Areteans.Bank2.service.DebitService;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DebitController {
    private final JdbcTemplate jt;
    private final DebitService ds;

    public DebitController(JdbcTemplate jt,DebitService ds){
        this.jt=jt;
        this.ds=ds;
    }

    @PostMapping(path = "postDebit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account postDebit(@RequestBody Transaction transaction) {
        System.out.println("in debit reading requestBody");
        Account op = ds.postDebit(transaction,jt);
        return op;
    }
    @GetMapping(path = "getDebit/{acc_id}")
    public Account getDebit(@PathVariable int acc_id) {
        System.out.println("in debit reading requestBody");
        Account op = ds.getDebit(acc_id,jt);
        return op;
    }
    @PutMapping(path = "putDebit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account putDebit(@RequestBody Account acc) {
        System.out.println("in debit reading requestBody");
        Account op = ds.putDebit(acc,jt);
        return op;
    }
    @DeleteMapping(path = "deleteDebit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account deleteDebit(@RequestBody Account acc) {
        System.out.println("in debit reading requestBody");
        Account op = ds.deleteDebit(acc,jt);
        return op;
    }
}
