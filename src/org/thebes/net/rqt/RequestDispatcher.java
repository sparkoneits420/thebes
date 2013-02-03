
package org.thebes.net.rqt;
 
import java.util.Iterator;
import org.thebes.Server;
import org.thebes.model.Player;
import org.thebes.net.RequestPipeline;
import org.thebes.net.SessionManager;
import org.thebes.net.io.Packet;
import org.thebes.net.io.PacketBuilder;

/**
 * Asynchronous request system, I figure all requests will be handled
 * as they're added to the dispatcher. This is a make-shift reactor.
 * @author smokey
 */
public class RequestDispatcher implements Runnable { 
    
    public RequestDispatcher() {
        super();
    }

    @Override
    public void run() {
        while(Server.isRunning()) { 
            for(Player player : SessionManager.players) {
                Iterator<Request> iterator = player
                        .getSession().requestQueue.iterator(); 
                while(iterator.hasNext()) { 
                    Request request = iterator.next(); 
                    RequestPipeline pipeline = player
                            .getSession().getPipeline();
                    switch(request.type) {//login handles itself
                        case OP_WRITE://encode
                            if(request.appended instanceof PacketBuilder) { 
                                 PacketBuilder packet = (PacketBuilder) request.appended; 
                                pipeline.getPacketEncoder()
                                       .encode(player, packet);
                            }
                            break;
                        case OP_READ://decode
                            if(request.appended instanceof Packet) { 
                                Packet packet = (Packet) request.appended; 
                                pipeline.getPacketDecoder()
                                       .decode(player.getSession().channel);
                            }
                            break;
                        case OP_UPDATE:
                            //TODO: player updating
                            break;
                    } 
                    iterator.remove();
                }

                try {
                    Thread.sleep(25);
                } catch (Exception ex) { 
                    ex.printStackTrace();
                }
            }
        }
    } 
}
