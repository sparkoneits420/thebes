/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thebes.model;

/**
 * Represents a players login credentials
 *
 * @author smokey
 */
public class PlayerCredentials { 
    
    /**
     * Player's username, password, host name and UID
     */
    public String username, password;
    public int id;
    
    /**
     * Create a new "PlayerCredentials" object. Contains login information
     * @param username the player's username
     * @param password the player's password
     * @param host the players connection host name
     * @param id the players unique identification ID
     */
    public PlayerCredentials(String username, String password, int id) {
        this.username = username;
        this.password = password; 
        this.id = id;
    } 
}
