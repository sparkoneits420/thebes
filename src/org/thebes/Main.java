
package org.thebes;

/**
 *
 * @author smokey
 */
public class Main {
     
    public static Server server; 

    /**
     * Is pretty much useless atm, I hate this method and that's why 
     * it's in here, by preference I do this with most everything I write
     * @param args the command line arguments
     */
    public static void main(String args[]) { 
        try { 
            
            server = new Server();
            //server.connectToDatabase();
            server.bind();  
            Server.getThreadPool().submit(server);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
}
