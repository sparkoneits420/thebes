package org.thebes.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//import org.extinal.net.ActionSender;
import org.thebes.net.Session;
import org.thebes.net.SessionManager;
import org.thebes.net.io.PacketSender;
import org.thebes.util.Constants;

/**
 * Represents a concurrent player session. Note: items that shouldn't be saved
 * need to be transient
 *
 * @author smokey
 */
public class Player extends Entity { 

    /**
     * The players session; this never changes from login to logout
     */
    public final transient Session session;
    /**
     * The players login credentials
     */
    public PlayerCredentials credentials;
    /**
     * The players appearance
     */
    public Appearance appearance;
    /**
     * The players equipment.
     */
    public Equipment equipment; 
  
    /**
     * PacketSender instance, I saw 5
     */
    public transient PacketSender packetSender; 
    
    /**
     * The player's rights, ID and index.
     */
    public int rights = 0, id = 0, index;
    /**
     * The players skills
     */
    public Skills skills;
    /**
     * The players chat queue
     */
    public transient LinkedList<ChatMessage> chatMessages = new LinkedList<>();
    /**
     * The players current message
     */
    public ChatMessage currentMessage;
    /**
     * The current flags appended
     */
    public transient ArrayList<UpdateFlag> flags = new ArrayList<>();

    /**
     * Create the new player. Note: if a player is loaded from a file, this is
     * never called. This is what I would call, single instance serialization
     *
     * @param session
     * @param id
     * @param username
     * @param password
     */
    public Player(Session session, PlayerCredentials credentials) {
        super(Constants.DEFAULT_LOCATION);
        this.session = session;
        this.credentials = credentials;
        this.appearance = new Appearance();
        this.equipment = new Equipment();
        this.packetSender = new PacketSender(this);
        this.skills = new Skills();
        //this.getWalkingQueue().reset();
        //TODO: reset walking queue upon initialization of the Player object, 
        //rather then calling it in this constructor
    }

    /**
     * Get the players rights
     *
     * @return the players rights
     */
    public int getRights() {

        return rights;
    }
 
    /**
     * Get the player's.. player index
     *
     * @return the player's index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Get the session attached to this player
     *
     * @return
     */
    public Session getSession() {
        return session;
    }
 
    /**
     * Get if the size of the flags list is greater then zero
     *
     * @return whether or not an update is required
     */
    public boolean isUpdateRequired() {
        return flags.size() > 0;
    } 

    /**
     * Get the players equipment
     *
     * @return
     */
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * Get the players appearance
     *
     * @return
     */
    public Appearance getAppearance() {
        return appearance;
    }

    /**
     * Get the players skills
     *
     * @return
     */
    public Skills getSkills() {
        return skills;
    } 

    /**
     *
     * @param e
     * @return
     */
    public List<Player> getLocalPlayers() { 
        List<Player> localPlayers = new LinkedList<>();
        for (Player p : SessionManager.players) { 
            if (p != null && location.withinDistance(p.getLocation())) {  
                localPlayers.add(p);
            }
        }
        return localPlayers;
    }

    
    @Override
    public WalkingQueue getWalkingQueue() {
        return walkingQueue;
    }
}
