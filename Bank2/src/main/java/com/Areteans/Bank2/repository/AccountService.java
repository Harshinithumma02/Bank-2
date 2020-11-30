package com.Areteans.Bank2.repository;

import com.Areteans.Bank2.models.Account;
import com.Areteans.Bank2.models.Password;
import com.Areteans.Bank2.models.PaymentJPA;
import com.Areteans.Bank2.service.AccountRowMapper;
import com.Areteans.Bank2.util.Thread1;
import com.Areteans.Bank2.util.Thread2;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        Map<String,Object> map= jt.queryForMap("insert into account values (?,?,?,?,?) returning account",
                acc.getAcc_id(),
                acc.getAccType(),
                acc.getBalance(),
                acc.getUsername(),
                acc.getPassword());;


int count1=jt.update("insert into branch(id,address)values (?,?)",
        acc.getBb().getId(),
        acc.getBb().getAddress());


return map;
    }

   public PaymentJPA multi(PaymentJPA paymentJPA) {
        RestTemplate restTemplate = new RestTemplate();
         //Map<String, String> map = new ConcurrentHashMap<>();
        String url="multithreading/http";
        Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {

            for (int a=0;a<10;a++){
                System.out.println("a value is"+a);

            }
        }
    });
        thread1.start();
        System.out.println("after thread1 start");

    Thread1 example = new Thread1();
    Thread t2 = new Thread(example);
        t2.start();

    Thread2 thread3 = new Thread2();
        thread3.start();

    ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(example);

        System.out.println("Calling the 2nd thread process");
    ResponseEntity<PaymentJPA> paymentJPAResponseEntity = restTemplate.postForEntity("http://localhost:8080/account", paymentJPA, PaymentJPA.class);
         return paymentJPAResponseEntity.getBody();
//        int amount=jt.queryForObject("select (amount) from payment where trans_id='1'",Integer.class);
//       return map.put("Amount",amount);
    }

}