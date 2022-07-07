package br.com.mabs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/todoapp?useLegacyDatetimeCode=false&serverTimezone=America/Sao_Paulo";
    private static final String USER = "root";
    private static final String PASSWORD = "Mysql@2022";

    //start connection with data base.
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);            
            return DriverManager.getConnection(URL, USER, PASSWORD);          
        } catch (Exception e) {
            throw new RuntimeException("Error to connection with data base. " + e.getMessage(), e);
        }
       
    }

    //close connection.
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro to close connection with date base. " + e.getMessage(), e);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement preparedStatement) {
        try {
            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro to close connection with date base. " + e.getMessage(), e);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro to close connection with date base. " + e.getMessage(), e);
        }
    }

}
