package com.Areteans.Bank2.models;

import com.Areteans.Bank2.enums.CustomerEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Customer extends CustomerType {
    private String name;
    private Integer age;
    private String  address;

    public Customer(String firstName, CustomerEnum savings){
        super(firstName, CustomerEnum.SAVINGS);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
