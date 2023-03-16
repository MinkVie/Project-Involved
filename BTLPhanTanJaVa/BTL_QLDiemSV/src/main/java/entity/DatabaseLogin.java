/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Yahiko
 */
public class DatabaseLogin {
    public static Connection openConnection(DataSQL data) throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:"+data.getPort()+";database=DoAn_SV";
            Connection connect=DriverManager.getConnection(connectionUrl,data.getUsername(),data.getPassword());
            return connect;
        } catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
}
}
