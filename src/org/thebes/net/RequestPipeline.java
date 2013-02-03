/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thebes.net;

import org.thebes.net.codec.LoginDecoder;
import org.thebes.net.codec.PacketDecoder;
import org.thebes.net.codec.PacketEncoder;

/**
 * I just wanted a place to put these, and they're really required but 
 * 
 * @author smokey
 */
public class RequestPipeline {
    
    public LoginDecoder loginDecoder;
    public PacketDecoder packetDecoder;
    public PacketEncoder packetEncoder;
     
    public static RequestPipeline pipeline() {
        RequestPipeline pipeline = new RequestPipeline(); 
        pipeline.loginDecoder = new LoginDecoder();
        pipeline.packetDecoder = new PacketDecoder();
        pipeline.packetEncoder = new PacketEncoder();
        return pipeline;
    } 
     
    public LoginDecoder getLoginDecoder() {
        return loginDecoder;
    }
    
    public PacketDecoder getPacketDecoder() {
        return packetDecoder;
    }
    
    public PacketEncoder getPacketEncoder() {
        return packetEncoder;
    }
} 