/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thebes.net.io;

import org.thebes.model.Player;
import org.thebes.net.Session;

/** 
 *
 * @author smokey
 */
public class PacketSender {
    
    private Player player;
    private Session session;

    public PacketSender(Player player) { 
        this.player = player;
        this.session = player.session;
    } 
    
}
