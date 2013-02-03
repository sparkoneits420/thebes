/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thebes.net;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import org.thebes.model.Player;

/** 
 *
 * @author smokey
 */
public class SessionManager {
    
    public static final short MAXIMUM_PLAYERS = 2000; 
    
    public static ArrayList<Player> players = new ArrayList<>(MAXIMUM_PLAYERS);

    public static void open(SocketChannel socketChannel) throws IOException { 
        
    }
    
    public void register(Player player, Session session) {
        
    }
}
