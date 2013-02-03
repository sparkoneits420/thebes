/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thebes.net;

import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Deque;
import java.util.LinkedList;
import org.thebes.net.codec.StreamSecurity;
import org.thebes.net.rqt.Request;

/**
 *
 * @author smokey
 */
public class Session {
    
    public StreamSecurity inputSecurity, outputSecurity;
    public SocketChannel channel; 
    public SocketAddress socketAddress;
    public RequestPipeline pipeline;
    
    public final Deque<Request> requestQueue = new LinkedList<>();
    
    public Session(SocketChannel channel, SocketAddress socketAddress) {
        this.channel = channel;
        this.socketAddress = socketAddress; 
    }
     
    public void submitRequest(Request request) {
        if(!requestQueue.contains(request)) {
            synchronized(requestQueue) {
                requestQueue.add(request);
            }
        } 
    }  
    
    public RequestPipeline getPipeline() {
        return pipeline;
    }
}
