package com.Areteans.Bank2.repository;

import com.Areteans.Bank2.models.BankTransactionJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
  public interface BankTransactionRepository extends JpaRepository<BankTransactionJPA,Long> {
}