package com.epam.jwd.core_final.sql;

import com.epam.jwd.core_final.util.ApplicationProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Connector {
    public static final Connector CONNECTOR = new Connector();
    private Connector() {}
    // JDBC URL, username and password of MySQL server
    private static final String url = ApplicationProperties.APP_PROPERTIES.getSql();
    private static final String user =  ApplicationProperties.APP_PROPERTIES.getUser();
    private static final String password =  ApplicationProperties.APP_PROPERTIES.getPassword();
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public Collection <? extends Object> sql2array (SQLTable sqlTable, String query) {
        Collection <? extends Object> sqlObjects = new ArrayList<>();
        System.out.println(url + user + password);
        try {
            con = DriverManager.getConnection (url, user, password); // opening database connection to MySQL server
            stmt = con.createStatement();                           // getting Statement object to execute query
            rs = stmt.executeQuery(query);                          // executing SELECT query
            switch (sqlTable) {
                case ROUTES:
                    sqlObjects = ReadObject.READ_SQL.routesFromSQL(rs);
                break;
                case SPACESHIPS:
                    sqlObjects = ReadObject.READ_SQL.spaceshipsFromSQL(rs);
                break;
                case CREWMEMBERS:
                    sqlObjects = ReadObject.READ_SQL.crewMembersFromSQL(rs);
                break;
                default:
                    System.out.println("unknown type");
            }
        } catch (SQLException sqlEx) {sqlEx.printStackTrace();}

        finally {                 //close connection ,stmt and resultset here

                try {con.close();} catch (SQLException se) { /*can't do anything */ }
                try {stmt.close();} catch (SQLException se) { /*can't do anything */ }
                try {rs.close(); } catch (SQLException se) { /*can't do anything */ }
        }
    return sqlObjects;
    }
}