package org.thebes.model;

/**
 * Represents a map region.
 *
 * TODO: This needs serious work
 *
 * @author smokey
 */
public class Region { 

    /**
     *
     */
    public int x, y;

    /**
     *
     * @param location
     */
    public Region(Location location) {

        this.x = location.x / 8;
        this.y = location.y / 8;
    }

    /**
     *
     * @param x
     * @param y
     */
    public Region(int x, int y) {

        this.x = x;
        this.y = y;
    }

    /**
     * Get the regions x coordinate
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Get the regions y coordinate
     *
     * @return
     */
    public int getY() {
        return y;
    }
}