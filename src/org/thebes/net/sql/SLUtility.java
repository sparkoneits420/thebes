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
   
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.thebes.Main;
import org.thebes.Server;
import org.thebes.model.Player;
import org.thebes.model.PlayerCredentials;
import org.thebes.net.Session;

/**
 * Just a quick utility I wrote to save and load users to the database.
 * Probably no other details need to be uploaded so for the moment this will be 
 * fine. If I ever make forums and shit I'll merge the databases and create 
 * web site registration.
 *
 * @author smokey
 */
public class SLUtility {  
    
    /**
     * Load a user from the MySQL database
     * @param session the connection attached to the user
     * @param username the user's name
     * @param password the user's password
     * @param id the user's id
     * @return the user instance
     */
    public static Player loadUser(Session session,
            String username, String password, Integer id) { 
        Player player = null;
        try {
            if (Server.getDatabase().exists(username)) { 
                ResultSet rsl = Server.getDatabase()
                        .query("SELECT * FROM `users` WHERE " 
                        + "`username`='" + username + "' ");
                if(rsl.first()) { 
                    username = rsl.getString("username");
                    password = rsl.getString("password");
                    id = rsl.getInt("id"); 
                    player = new Player(session,
                            new PlayerCredentials(username, password, id));
                }
                rsl.getStatement().close();
            } 
            if (player == null) { 
                player = new Player(session, new PlayerCredentials(username, password, id));
                if(!saveUser(player)) {
                    System.out.println("Could not save or load " + player.credentials.username);
                    return null;
                } else {
                    System.out.println("Saved for: " + player.credentials.username);
                }
            }
            System.out.println("loaded for " + player.credentials.username); 
            return player;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Save a user to the database
     * @param player the user to be saved
     * @return <boolean> whether or not the user was saved.
     */
    public static boolean saveUser(Player player) {
        if (player == null) {
            return false;
        }
        
        String query = "`users` "
                + "SET `username`='" + player.credentials.username
                + "', `password`='" + player.credentials.password
                + "', `id`='" + player.credentials.id + "';";
         
        try {
            Statement stmt = Server.getDatabase().next();
            if (Server.getDatabase().exists(player.credentials.username)) {  
                stmt.executeUpdate("UPDATE " + query);
            } else {
                stmt.executeUpdate("INSERT INTO " + query); 
            } 
            stmt.close(); 
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }
        return false;
    }
    
}
