package com.Areteans.Bank2.models;
import com.Areteans.Bank2.enums.CustomerEnum;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BankEmployee extends Customer{
    private float salary;
    private String designation;

    public BankEmployee(String firstName, CustomerEnum savings) {
        super(firstName, savings);
    }
}
