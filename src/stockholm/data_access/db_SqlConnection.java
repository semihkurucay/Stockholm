/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author semih
 */
public class db_SqlConnection {

    private String userName = "user";
    private String password = "user123";
    private String url = "jdbc:sqlserver://A\\MSSQLSERVER01:52916;databaseName=db_Stockholm;encrypt=true;trustServerCertificate=true";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
}
