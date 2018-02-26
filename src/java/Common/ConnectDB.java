/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tien
 */
public class ConnectDB {

    Connection connection = null;

    public Connection getConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            return null;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://"
                    + Config.IP + ":3306/" + Config.DATABASE_NAME + "?useUnicode=yes&characterEncoding=UTF-8", Config.USE_NAME, Config.PASSWORD);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            return null;
        }
        if (connection != null) {
            return connection;
        } else {
            return null;
        }
    }
}
