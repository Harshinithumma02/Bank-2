package com.Areteans.Bank2.repository;

import com.Areteans.Bank2.models.PaymentJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
  public interface PaymentRepository extends JpaRepository<PaymentJPA,Long> {

// @Query("insert into payment values (amount=:amount ,trans_id=:trans_id)")
// @Modifying
//  int updateName( @Param("amount") int amount,@Param("trans_id") Long trans_id);
//
//  @Override
//  List<PaymentJPA> findAll();
@Modifying
@Query(value = "DELETE FROM payment WHERE trans_id = :id", nativeQuery = true)
int deleteId(@Param("id") int trans_id);

  @Modifying
  @Query(value = "update payment set amount=:amount where trans_id = :id", nativeQuery = true)
  int addAmount(@Param("id") int trans_id,@Param("amount") int amount);
}