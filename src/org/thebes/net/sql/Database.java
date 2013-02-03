/*
 * Copyright (C) 2012 smokey
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.thebes.net.sql;  

import java.sql.*;

/**
 *
 * @author smokey
 */
public class Database {
    
    public Connection connection;

    public ResultSet query(String s) throws SQLException {  
        return next().executeQuery(s);
    }
    
    public Statement next() throws SQLException {
        if(connection == null) {
            return null;
        }
        return connection.createStatement();
    }

    public boolean exists(String user) { 
        try { 
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `users` WHERE `username`='" + user + "' "); 
            while (rs.next()) {
                if (rs.getString("username").equalsIgnoreCase(user)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    } 
 
    public Database connect(String host, String port,
            String database, String username, String password) {  
        try { 
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://" 
                    + host + "/" + database, username, password);
            
            Statement statement = next(); 
            if(statement.executeQuery("SELECT * FROM `users`") == null) {
                statement.executeUpdate(
                    "CREATE TABLE `users` ("
                    + "`username` varchar(32) PRIMARY KEY,"
                    + "`password` varchar(32),"
                    + "`id` int(0)"
                        + ");");
            }
            return this;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    } 
}