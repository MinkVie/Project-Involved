/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Yahiko
 */
public class DataSQL implements Serializable{
    String username;
    String password;
    String Port;

    public DataSQL() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return Port;
    }

    public void setPort(String Port) {
        this.Port = Port;
    }

    public DataSQL(String username, String password, String Port) {
        this.username = username;
        this.password = password;
        this.Port = Port;
    }
    
}
