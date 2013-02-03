
package org.thebes;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.thebes.net.SessionManager;
import org.thebes.net.rqt.RequestDispatcher;
import org.thebes.net.sql.Database;
import org.thebes.util.Constants;
import org.thebes.util.logging.Level;
import org.thebes.util.logging.Logger;

/**
 *
 * @author smokey
 */
public class Main {
    
    public static final Logger logger = Logger.getLogger("Main"); 
    public static Server server; 

    public static void main(String args[]) { 
        try {
            server = new Server();
            server.bind();
            server.connectToDatabase();  
            Server.getThreadPool().submit(server);
            logger.log(Level.INFO, "Server started on 0.0.0.0:" + Constants.LISTENER_PORT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
}
