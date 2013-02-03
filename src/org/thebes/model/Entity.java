package org.thebes.model;

/**
 * Represents a physical entity in-game. This could be anything from an npc or a
 * player to an object, or a ground item. Absolutely anything that is contained
 * in a tile.
 *
 * @author smokey
 */
public abstract class Entity { 

    /**
     * This entities current location.
     */
    public Location location;
    /**
     * This entities current map region.
     */
    public Region region;
    /**
     * If the entity is active or not
     */
    public transient boolean active = false;
    /**
     * If the region is changing
     */
    public transient boolean regionChanging = false;
    /**
     * If the entity is teleporting
     */
    public transient boolean teleporting = false;
    /**
     * Target to teleport to
     */
    public transient Location teleportTarget;
    /**
     * Interacting entity
     */
    public transient Entity interacting;
    /**
     * The entities walking queue
     */
    public transient WalkingQueue walkingQueue;
    /**
     * The entities running direction
     */
    public transient int runningDirection;
    /**
     * The entities walking direction
     */
    public transient int walkingDirection;

    /**
     * Create a new entity relative to the map region.
     *
     * @param location the location in which this entity is standing on
     */
    public Entity(Location location) {
        this.location = location;
        this.region = new Region(location);
        this.walkingQueue = new WalkingQueue(this);
    }

    /**
     * Set this entities location; not relative to the map region.
     *
     * @param location
     */
    public void setLocation(final Location location) {
        this.location = location;
    }

    /**
     * @return this entites current location; not relative to the map region
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set this entities region; relative to the entities current location
     *
     * @param region
     */
    public void setRegion(Region region) {
        this.region = region;
    } 
    
    /**
     * Get the current region
     *
     * @return
     */
    public Region getRegion() {
        return region;
    } 

    /**
     * Get the walking queue
     *
     * @return
     */
    public WalkingQueue getWalkingQueue() {
        return walkingQueue;
    }

    /**
     * Set the entity that this entity is interacting with currently
     *
     * @param entity the entity that this entity is interacting with
     */
    public void setInteractingEntity(Entity entity) {
        this.interacting = entity;
    }

    /**
     * Get the entity this entity is interacting with
     *
     * @return the entity this entity is interacting with
     */
    public Entity getInteractingEntity() {
        return interacting;
    }
 
}