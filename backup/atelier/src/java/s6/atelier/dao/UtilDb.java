/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s6.atelier.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class UtilDb {
    public static Connection getConnPostgre() throws Exception
	{
            Class.forName("org.postgresql.Driver");
            Connection conn =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/atelier","postgres", "itu");
            conn.setAutoCommit(false);
            return conn;
	}
}
