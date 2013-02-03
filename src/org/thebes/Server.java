/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thebes;

import java.io.FileInputStream; 
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thebes.net.SessionManager;
import org.thebes.net.rqt.RequestDispatcher;
import org.thebes.net.sql.Database;
import org.thebes.util.Constants;

/**
 *
 * @author smokey
 */
public class Server implements Runnable {
     
    public static Database database = new Database();
    public static RequestDispatcher dispatcher = new RequestDispatcher();
    public static ExecutorService threadPool = Executors.newCachedThreadPool();
    
    private static final Logger logger = Logger.getLogger("Server");
    
    public static boolean running = true;  
     
    public void bind() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); 
        InetSocketAddress isa = new InetSocketAddress(
                InetAddress.getLocalHost(), Constants.LISTENER_PORT);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(isa);
        logger.log(Level.INFO, "Server started on 0.0.0.0:" + Constants.LISTENER_PORT);
    }
    
    public void connectToDatabase() throws Exception {
        Properties p = new Properties();
        p.load(new FileInputStream("mysql.properties")); 
        database = new Database(); 
        String username = p.getProperty("username");
        String password = p.getProperty("password");
        String database_ = p.getProperty("database");
        String port = p.getProperty("port");
        String host = p.getProperty("host");
        database.connect(host, port, database_, username, password);  
    }
    
    @Override
    public void run() {
        try { 
            Selector selector = SelectorProvider.provider().openSelector();
            while(selector.isOpen() && isRunning()) {
                Set readyKeys = selector.selectedKeys(); 
                for(Iterator<SelectionKey> i = readyKeys.iterator(); i.hasNext();) {
                    SelectionKey key = i.next(); 
                    ServerSocketChannel channel = 
                        (ServerSocketChannel)key.channel();  
                    SessionManager.open(channel.accept());
                    System.out.println("accepted");
                    i.remove();
                } 
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    } 
        
    public static ExecutorService getThreadPool() {
        return threadPool;
    }
    
    public static Database getDatabase() {
        return database;
    }
    
    public static boolean isRunning() {
        return running;
    }  
}
