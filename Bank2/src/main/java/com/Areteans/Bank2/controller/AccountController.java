package com.Areteans.Bank2.controller;

import com.Areteans.Bank2.models.Account;
import com.Areteans.Bank2.models.Password;
import com.Areteans.Bank2.service.AccountService;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
@RestController
public class AccountController {
    private final JdbcTemplate jt;
    private final AccountService as;

    public AccountController(JdbcTemplate jt, AccountService as) {
        this.jt = jt;
        this.as = as;
    }

    @PostMapping (path = "updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updatePassword(@RequestBody Password ps) {
        System.out.println("in updatePassword endpoint");
        int op = as.password(ps,jt);
        return "New Password Updated for " + ps.getUsername()+","+op+"rows updated";
    }
    @GetMapping(path="fromDB/{username}")
    public Account fromDB(@PathVariable("username") String username)
    {
        return as.readFromDB(username,jt);
    }

    @PutMapping(path = "putAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String putAccount(@RequestBody Account acc) {
        System.out.println("in credit reading requestBody");
        int op = as.putAccount(acc,jt);
        return "Put successful"+op+" rows put";
    }
    @DeleteMapping(path = "delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@RequestBody Password ps) {
        System.out.println("in delete endpoint");
        int count = as.delete(ps,jt);
        if (count > 0)
            return "deleted for" + ps.getUsername();
        else
            return "Nothing to delete";
    }
}
