/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thebes.net.codec;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger; 
import org.thebes.net.io.Packet;
import org.thebes.net.io.PacketBuilder;

/**
 * 
 * @author smokey
 */
public class LoginDecoder {

    
    private long serverKey, clientKey;
    private final byte[] INITIAL_RESPONSE = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private final byte OPCODE = 0, HEADER = 1, BLOCK = 2; 
    private byte state = OPCODE;
    
    public boolean decode(SocketChannel channel) { 
        try {
            ByteBuffer buf = ByteBuffer.allocate(127);
            channel.read(buf); 
            PacketBuilder pb = new PacketBuilder(buf); 
            switch(state) {
                case OPCODE:
                    int opcode = pb.getUnsignedByte(); 
                    int hash = pb.getUnsignedByte();//(l >> 16 & 31L);
                    pb.addBytes(INITIAL_RESPONSE);
                    pb.addLong(serverKey);
                    state = HEADER; 
                case HEADER:
                    int loginOpcode = pb.getUnsignedByte();
                    if(loginOpcode != 10) { 
                        return false;
                    }
                case BLOCK: 
                    return true;
            } 
        } catch (IOException ex) {
            
        }
        return false;
    } 
}
