package com.Areteans.Bank2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankTransactionJPA {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long trans_id;
    private int amount;

}