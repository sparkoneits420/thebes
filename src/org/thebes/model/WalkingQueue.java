package org.thebes.model;

import java.util.Deque; 
import java.util.LinkedList;
import org.thebes.util.Constants;
import org.thebes.util.Utility;

/**
 * <p>A
 * <code>WalkingQueue</code> stores steps the client needs to walk and allows
 * this queue of steps to be modified.</p>
 *
 * <p>The class will also process these steps when
 * {@link #processNextMovement()} is called. This should be called once per
 * server cycle.</p> 
 *
 * @author Graham Edgecombe
 * 
 */
public class WalkingQueue {

    /**
     * Represents a single point in the queue.
     *
     * @author Graham Edgecombe
     *
     */
    private static class Point {

        /**
         * The x-coordinate.
         */
        private final int x;
        /**
         * The y-coordinate.
         */
        private final int y;
        /**
         * The direction to walk to this point.
         */
        private final int dir;

        /**
         * Creates a point.
         *
         * @param x X coord.
         * @param y Y coord.
         * @param dir Direction to walk to this point.
         */
        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    /**
     * The maximum size of the queue. If there are more points than this size,
     * they are discarded.
     */
    public static final int MAXIMUM_SIZE = 50;
    /**
     * The entity.
     */
    private Entity entity;
    /**
     * The queue of waypoints.
     */
    private Deque<Point> waypoints = new LinkedList<>();
    /**
     * Run toggle (button in client).
     */
    private boolean runToggled = false;
    /**
     * Run for this queue (CTRL-CLICK) toggle.
     */
    private boolean runQueue = false;

    /**
     * Creates the
     * <code>WalkingQueue</code> for the specified
     * <code>Entity</code>.
     *
     * @param entity The entity whose walking queue this is.
     */
    public WalkingQueue(Entity entity) {
        this.entity = entity;
    }

    /**
     * Sets the run toggled flag.
     *
     * @param runToggled The run toggled flag.
     */
    public void setRunningToggled(boolean runToggled) {
        this.runToggled = runToggled;
    }

    /**
     * Sets the run queue flag.
     *
     * @param runQueue The run queue flag.
     */
    public void setRunningQueue(boolean runQueue) {
        this.runQueue = runQueue;
    }

    /**
     * Gets the run toggled flag.
     *
     * @return The run toggled flag.
     */
    public boolean isRunningToggled() {
        return runToggled;
    }

    /**
     * Gets the running queue flag.
     *
     * @return The running queue flag.
     */
    public boolean isRunningQueue() {
        return runQueue;
    }

    /**
     * Checks if any running flag is set.
     *
     * @return
     * <code>true</code. if so, <code>false</code> if not.
     */
    public boolean isRunning() {
        return runToggled || runQueue;
    }

    /**
     * Resets the walking queue so it contains no more steps.
     */
    public void reset() {
        runQueue = false;
        waypoints.clear();
        waypoints.add(new Point(entity.getLocation().getX(), entity.getLocation().getY(), -1));
    }

    /**
     * Checks if the queue is empty.
     *
     * @return
     * <code>true</code> if so,
     * <code>false</code> if not.
     */
    public boolean isEmpty() {
        return waypoints.isEmpty();
    }

    /**
     * Removes the first waypoint which is only used for calculating directions.
     * This means walking begins at the correct time.
     */
    public void finish() {
        waypoints.removeFirst();
    }

    /**
     * Adds a single step to the walking queue, filling in the points to the
     * previous point in the queue if necessary.
     *
     * @param x The local x coordinate.
     * @param y The local y coordinate.
     */
    public void addStep(int x, int y) {
        if (waypoints.size() == 0) {
            reset();
        }
        Point last = waypoints.peekLast();

        int diffX = x - last.x;
        int diffY = y - last.y;

        int max = Math.max(Math.abs(diffX), Math.abs(diffY));
        for (int i = 0; i < max; i++) {
            if (diffX < 0) 
                diffX++;
            else if (diffX > 0) 
                diffX--; 
            if (diffY < 0) 
                diffY++;
            else if (diffY > 0) 
                diffY--; 
            addStepInternal(x - diffX, y - diffY);
        }
    }

    /**
     * Adds a single step to the queue internally without counting gaps. This
     * method is unsafe if used incorrectly so it is private to protect the
     * queue.
     *
     * @param x The x coordinate of the step.
     * @param y The y coordinate of the step.
     */
    private void addStepInternal(int x, int y) {
        if (waypoints.size() >= MAXIMUM_SIZE) {
            return;
        }
        Point last = waypoints.peekLast();
        int dir = Utility.direction(x, y, last.x, last.y);
        if (dir > -1) {
            waypoints.add(new Point(x, y, dir));

        }
    }

    /**
     * Processes the next player's movement.
     */
    public void processNextMovement() {
        Point walkPoint, runPoint = null;
        if(entity instanceof Player) {
            Player player = (Player) entity;
            if (player.teleporting) {
                reset();
                player.teleporting = false;
                player.location = player.teleportTarget;
                player.teleportTarget = null;
            }
        } else {
            walkPoint = getNextPoint();
            if (runToggled || runQueue) {
                runPoint = getNextPoint();
            }
            int walkDir = walkPoint == null ? -1 : walkPoint.dir;
            int runDir = runPoint == null ? -1 : runPoint.dir;
            entity.walkingDirection = walkDir;
            entity.runningDirection = runDir;
        }
        int diffX = entity.getLocation().getX() - entity.getRegion().getX() * 8;
        int diffY = entity.getLocation().getY() - entity.getRegion().getY() * 8;
        boolean changed = false;
        if (diffX < 16 || diffX >= 88 ||diffY < 16 || diffY >= 88) {
            changed = true;
        } 
        entity.regionChanging = changed;
    }

    /**
     * Gets the next point of movement.
     *
     * @return The next point.
     */
    private Point getNextPoint() {
        Point p = waypoints.poll();
        if (p == null || p.dir == -1) {
            return null;
        } else {
            int diffX = Constants.DIRECTION_DELTA_X[p.dir];
            int diffY = Constants.DIRECTION_DELTA_Y[p.dir];
            entity.setLocation(entity.getLocation().transform(diffX, diffY, 0));
            return p;
        }
    }
}
