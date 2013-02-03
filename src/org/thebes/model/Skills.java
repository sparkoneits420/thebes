package org.thebes.model;

import java.util.Arrays;
import org.thebes.util.Constants;

/**
 * Represents the players skills
 *
 * @author smokey
 *
 */
public class Skills { 

    /**
     * Players skills
     */
    public int[] level = new int[Constants.SKILL_COUNT];
    /**
     * 
     */
    public int[] experience = new int[Constants.SKILL_COUNT];

    /**
     * Create skills for a new player or used some that are submitted.
     *
     */
    public Skills() {
        Arrays.fill(level, 1);
        Arrays.fill(experience, 0);
        level[Constants.HITPOINTS] = 10;
        experience[Constants.HITPOINTS] = 1154;
    }

    /**
     * Get the players experience in a specific skill
     *
     * @param id the skill to get the experience for
     * @return the players experience in the specified skill
     */
    public int getExperience(int id) {
        return experience[id];
    }

    /**
     * Get the players level in a specific skill
     *
     * @param id the skill to get the level for
     * @return the players level in the specified skill
     */
    public int getLevel(int id) {
        return level[id];
    }

    /**
     * 
     * @return
     */
    public int getTotalLevel() {
        int total = 0;
        for (int i = 0; i < Constants.SKILL_COUNT; i++) {
            total += level[i];
        }
        return total;
    }

    /**
     * lel stolen from winterlove i believe
     * TODO: correct these calculations
     */
    public int getCombatLevel() {
        int combatLevel;
//        int attack = level[Constants.ATTACK];
//        int defence = level[Constants.DEFENSE];
//        int strength = level[Constants.STRENGTH];
//        int hitpoints = level[Constants.HITPOINTS];
//        int prayer = level[Constants.PRAYER];
//        int range = level[Constants.RANGED];
//        int magic = level[Constants.MAGIC];
        combatLevel = (int) (((double) (level[Constants.DEFENSE] + level[Constants.HITPOINTS]) +
                Math.floor(level[Constants.PRAYER] / 2)) * 0.25D) + 1;
        double d = (double) (level[Constants.ATTACK] + level[Constants.STRENGTH]) 
                * 0.32500000000000001D;
        double d1 = Math.floor((double) level[Constants.RANGED] * 1.5D)
                * 0.32500000000000001D;
        double d2 = Math.floor((double) level[Constants.MAGIC] * 1.5D)
                * 0.32500000000000001D;
        if (d >= d1 && d >= d2) {
            combatLevel += d;
        } else if (d1 >= d && d1 >= d2) {
            combatLevel += d1;
        } else if (d2 >= d && d2 >= d1) {
            combatLevel += d2;
        }
        return combatLevel;
    }
}
