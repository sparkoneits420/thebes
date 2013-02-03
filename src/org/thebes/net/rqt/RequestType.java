/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thebes.net.rqt;

/**
 * Request types
 * @author smokey
 */ 
public enum RequestType {
    
    /**
     * Request type for OP_READ packets
     */
    OP_READ,
    
    /**
     * Request type for OP_WRITE packets
     */
    OP_WRITE,
    
    /**
     * Request type for updating a model structure
     */
    OP_UPDATE;
}
