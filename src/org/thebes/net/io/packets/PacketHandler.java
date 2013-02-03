/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thebes.net.io.packets;

import org.thebes.net.Session;
import org.thebes.net.io.Packet;

/** 
 * Wrapper for OP_READ packets 
 * @author smokey
 */
public interface PacketHandler {
    
    public void handle(Session session, Packet packet);
}
