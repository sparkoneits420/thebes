
package org.thebes.net.rqt;
 
/**
 * Struct for I/O requests
 * @author smokey
 */
public class Request {
    
    public RequestType type;
    public Object appended;
    public int length;
 
    public Request(RequestType type, int length) {
        this.type = type; 
        this.length = length;
    }  
    
    public void append(Object appended) {
        this.appended = appended;
    }
}
