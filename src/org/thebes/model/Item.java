package org.thebes.model;

/**
 * Represents an in-game item
 *
 * @author smokey
 *
 */
public class Item { 

    /**
     *
     */
    public int id;

    /**
     *
     * @param id
     */
    public Item(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return id;
    }
}
