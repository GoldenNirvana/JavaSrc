package com.example.springapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// пробую git с jwt

public class Main
{
    public static Connection getNewConnection(String dbURL, String username, String password) throws SQLException
    {
        Connection connection = DriverManager.getConnection(dbURL, username, password);
        if (connection.isValid(1))
        {
            System.out.println("Connection successful");
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);
        String dbURL = "jdbc:postgresql://localhost:5432/autopark";
        String username = "postgres";
        String password = "root";
        Connection connection;

        try
        {
            connection = getNewConnection(dbURL, username, password);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }

        Statement statement = connection.createStatement();
        String request = scanner.nextLine();

        while (!request.equals("exit"))
        {
            statement.execute(request);
            request = scanner.nextLine();
        }
    }
}
