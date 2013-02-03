package org.thebes.model;

/**
 * Represents a location in-game.
 *
 * @author smokey
 */
public class Location { 

    /**
     *
     */
    public int x, currentX, y, currentY, height;

    /**
     *
     * @param x
     * @param y
     * @param height
     */
    public Location(int x, int y, int height) { 
        this.x = x;
        this.y = y;
        this.height = height;
    }
    
    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @param l
     * @return
     */
    public int getLocalX() {
        return x - 8 * getRegionX();
    }

    /**
     *
     * @param l
     * @return
     */
    public int getLocalY() {
        return y - 8 * getRegionY();
    }

    /**
     *
     * @return
     */
    public int getRegionX() {
        return (x >> 3) - 6;
    }

    /**
     *
     * @return
     */
    public int getRegionY() {
        return (y >> 3) - 6;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @param diffX
     * @param diffY
     * @param diffZ
     * @return
     */
    public Location transform(int diffX, int diffY, int diffZ) {
        return new Location(x + diffX, y + diffY, height + diffZ);
    }
    
    /**
     * Lol, simple and quick way of getting surrounding regions, TODO: make use 
     * of cached regions so that we don't make 5000 instances per cycle
     */
    public Region[] getSurroundingRegions() {

        int regionX = x / 8;
        int regionY = y / 8;

        Region[] surrounding = new Region[9];
        surrounding[0] = new Region(regionX, regionY);
        surrounding[1] = new Region(regionX - 1, regionY - 1);
        surrounding[2] = new Region(regionX + 1, regionY + 1);
        surrounding[3] = new Region(regionX - 1, regionY);
        surrounding[4] = new Region(regionX, regionY - 1);
        surrounding[5] = new Region(regionX + 1, regionY);
        surrounding[6] = new Region(regionX, regionY + 1);
        surrounding[7] = new Region(regionX - 1, regionY + 1);
        surrounding[8] = new Region(regionX + 1, regionY - 1);

        return surrounding;
    }
    
    
    /**
     *
     * @param location
     * @return
     */
    public boolean withinDistance(Location location) {
        if (height != location.height) {
            return false;
        }
        int distanceX = x - location.x, distanceY = y - location.y;
        return distanceX <= 15 && distanceX >= -16 && distanceY <= 15
                && distanceY >= -16;
    }

}
