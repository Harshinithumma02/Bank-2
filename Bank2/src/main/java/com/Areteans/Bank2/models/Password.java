package com.Areteans.Bank2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Password {
    private String username;
    private String oldPaswd;
    private String newPaswd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPaswd() {
        return oldPaswd;
    }

    public void setOldPaswd(String oldPaswd) {
        this.oldPaswd = oldPaswd;
    }

    public String getNewPaswd() {
        return newPaswd;
    }

    public void setNewPaswd(String newPaswd) {
        this.newPaswd = newPaswd;
    }
}
