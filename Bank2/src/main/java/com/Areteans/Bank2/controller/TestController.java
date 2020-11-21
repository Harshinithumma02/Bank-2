package com.Areteans.Bank2.controller;

import com.Areteans.Bank2.models.*;
import com.Areteans.Bank2.service.AccountService;
import com.Areteans.Bank2.service.CreditService;
import com.Areteans.Bank2.service.CustomerService;
import com.Areteans.Bank2.service.DebitService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "test")
public class TestController {
    CustomerService customerService=new CustomerService();
    DebitService debitService = new DebitService();
    CreditService creditService = new CreditService();
    AccountService accountService=new AccountService();

    @GetMapping(path = "/data")
    public String getDummyData(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "age", required = false) Integer age) {
        //return "Hello World";
        return name != null && age != null ? name.toUpperCase() + ": " + age : "Empty Input";
    }

    @GetMapping(path = "data/{age}")
    public String getfromPV(@PathVariable("age") Integer age) {
        return String.valueOf(age);
    }

    @PostMapping(path = "data", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getDummyData(@RequestBody Map<String, String> input) {
        String name = input.get("name");
        Integer age = Integer.parseInt(input.get("age"));
        return name != null && age != null ? name.toUpperCase() + " : " + age : "Empty Input";
    }
    @PutMapping(path = "data", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getDummyData1(@RequestBody Map<String, String> input) {
        String name = input.get("name");
        Integer age = Integer.parseInt(input.get("age"));
        return name != null && age != null ? name.toUpperCase() + " : " + age : "Empty Input";
    }

    @PostMapping(path = "interest", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Bank calcInterest(@RequestBody Bank bank) {
        int pAmt = bank.getPrincipalAmt();
        bank.calcInterest(pAmt);
        return bank;
    }

    @PostMapping(path = "insertCustomer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer insertCustomer(@RequestBody Customer customer) {
        System.out.println("in postendpoint reading requestBody");
        Customer op = customerService.xyz(customer);
        return op;
    }

    @PostMapping(path = "custType", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, String>> getListOfDb() {
        return customerService.getDataFromDB();
    }

//    @PostMapping(path = "credit", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Account credit(@RequestBody Transaction transaction) {
//        System.out.println("in credit reading requestBody");
//        Account op = creditService.credit(transaction);
//        return op;
//    }
//
//    @PostMapping(path = "debit", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Account debit(@RequestBody Transaction transaction) {
//        System.out.println("in debit reading requestBody");
//        Account op = debitService.debit(transaction);
//        return op;
//    }
//
//    @PostMapping(path = "updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public String updatePassword(@RequestBody Password ps) {
//        System.out.println("in updatePassword endpoint");
//        int op = accountService.password(ps);
//        return "New Password Updated for " + ps.getUsername();
//    }
//
//    //    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
////    public Map<String, Object> save (@RequestBody Account account) {
////        System.out.println("in create endpoint");
////        Map<String, Object> op = abc.save(account);
////        return op;
////    }
//    @DeleteMapping(path = "delete", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public String delete(@RequestBody Password ps) {
//        System.out.println("in delete endpoint");
//        int count = accountService.delete(ps);
//        if (count > 0)
//            return "deleted for" + ps.getUsername();
//        else
//            return "Nothing to delete";
//    }
//
//    @GetMapping(path="fromDB/{username}")
//    public Account fromDB(@PathVariable("username") String username) {
//        return accountService.readFromDB(username);
//    }
//    @PostMapping(path = "credit1", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Account credit1(@RequestBody Transaction transaction) {
//        System.out.println("in credit reading requestBody");
//        Account op = customerService.credit1(transaction);
//        return op;
//    }
}