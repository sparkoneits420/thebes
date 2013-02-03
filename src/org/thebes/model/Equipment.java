package org.thebes.model;

import java.util.LinkedList;

/**
 * Represents a players equipment. (a very basic impl)
 *
 * @author smokey
 */
public class Equipment extends LinkedList<Item> { 

    private static final long serialVersionUID = 1L;
    /**
     * Equipment slot ID constants..
     */
    public static final transient int HEAD = 0, CLOAK = 1, AMULET = 2, WEAPON = 3,
            TORSO = 4, SHIELD = 5, LEGS = 7, HANDS = 9, FEET = 10, RING = 12,
            ARROWS = 13;
    /**
     * The amount of arrow we're holding
     */
    public int arrows = 0;

    /**
     *
     */
    public Equipment() {
        super();
    }

    /**
     *
     * @param slot
     * @return
     */
    public int getIDForSlot(int slot) {
        return get(slot).getID();
    }

    /**
     *
     * @param slot
     * @return
     */
    public boolean isSlotUsed(int slot) {
        return get(slot) != null;
    }
}
