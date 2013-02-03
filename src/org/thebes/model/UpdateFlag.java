package org.thebes.model;

/**
 * Player update flags
 * 
 * @author smokey
 * 
 */
public enum UpdateFlag {  
    
	/**
	 * Flag for appearance updates
	 */
	APPEARANCE,
	
	/**
	 * Flag for graphical updates
	 */
	GRAPHIC, 
	
	/**
	 * Flags for chat updates
	 */
	FORCED_CHAT, 
        CHAT, 
	
	/**
	 * Flags for entity facing
	 */
	FACE_ENTITY, 
        /**
         * 
         */
        FACE_COORDINATE, 
	
	/**
	 * Flags for hit updates
	 */
	HIT,  
        HIT_2, 
	
	/**
	 * Location transformation update flag
	 */
	TRANSFORM, 
	
	/**
	 * Flag for animation updates
	 */
	ANIMATION, 
}
