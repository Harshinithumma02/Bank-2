package com.Areteans.Bank2.models;

public class Bank {
    int principalAmt;
    int interest;
    public Bank calcInterest(int i) {
        Bank bank=new Bank();
        //System.out.println("pamount is.."+i);
        interest=(i*1*5)/100;
        //System.out.println("Interest...."+interest);
        bank.setInterest(interest);
        return bank;
    }
    public Bank calcCredit(int i) {
        Bank bank=new Bank();
        //System.out.println("pamount is.."+i);
        interest=(i*1*1)/100;
        System.out.println("......"+interest);
        bank.setInterest(interest);
        return bank;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public int getPrincipalAmt() {
        return principalAmt;
    }

    public void setPrincipalAmt(int principalAmt) {
        this.principalAmt = principalAmt;
    }
}
