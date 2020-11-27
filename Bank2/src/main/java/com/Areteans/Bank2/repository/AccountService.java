package com.Areteans.Bank2.repository;

import com.Areteans.Bank2.models.Account;
import com.Areteans.Bank2.models.Password;
import com.Areteans.Bank2.models.PaymentJPA;
import com.Areteans.Bank2.service.AccountRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    private final PaymentRepository paymentRepo;
    public JdbcTemplate jt;

    public AccountService(PaymentRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
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

    public PaymentJPA saveAccount(PaymentJPA xyz) {
        return paymentRepo.save(xyz);
    }
    public List<PaymentJPA> getAll() {
        return paymentRepo.findAll();
    }


public String deleteID(int trans_id )
{
   int records=paymentRepo.deleteId(trans_id);
   return  records+"  rows Deleted";
}
    public String addAmount(int trans_id ,int amount)
    {
        int records=paymentRepo.addAmount(trans_id,amount);
        return  records+"  rows updated";
    }
    public List<PaymentJPA> save(PaymentJPA paymentJPA)
    {
        PaymentJPA paymentJPA1=paymentRepo.save(paymentJPA);
        List<PaymentJPA> paymentJPA2=paymentRepo.findAll();
        return paymentJPA2;
    }
    public Map<String,Object> create (Account acc,JdbcTemplate jt){
        System.out.println("Hi I am Sushma");
        Map<String,Object> map= jt.queryForMap("insert into account values (?,?,?,?,?) returning account",
                acc.getAcc_id(),
                acc.getAccType(),
                acc.getBalance(),
                acc.getUsername(),
                acc.getPassword());;
        System.out.println("Hi I am Sushma");

int count1=jt.update("insert into branch(id,address)values (?,?)",
        acc.getBb().getId(),
        acc.getBb().getAddress());
        System.out.println("Hi I am Sushma");

return map;
    }


}