package com.Areteans.Bank2.controller;

import com.Areteans.Bank2.models.Account;
import com.Areteans.Bank2.models.Transaction;
import com.Areteans.Bank2.service.CreditService;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreditController {
    private final JdbcTemplate jt;
    private final CreditService cs;

    public CreditController(JdbcTemplate jt, CreditService cs) {
        this.jt = jt;
        this.cs = cs;
    }

    @PostMapping(path = "postCredit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account credit(@RequestBody Transaction transaction) {
        System.out.println("in credit reading requestBody");
        Account op = cs.postCredit(transaction,jt);
        return op;
    }
    @GetMapping(path="getCredit/{acc_id}")
    public Account getCredit(@PathVariable int acc_id) {
        System.out.println("in credit reading requestBody");
        Account op = cs.getCredit(acc_id,jt);
        return op;
    }
//    @PutMapping(path = "putCredit"consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Account getCredit(@RequestBody Account account) {
//        System.out.println("in putMapping of credit controller");
//        int op = cs.putCredit(account),jt);
//        return "Put successful";
    //}
    @DeleteMapping(path="deleteCredit")
    public Account deleteCredit(@RequestBody Account account) {
        System.out.println("in delete mapping");
        Account op = cs.deleteCredit(account,jt);
        return op;
    }
}
