package org.thebes.model;

/**
 * Represents the players appearance
 *
 * @author smokey
 *
 */
public class Appearance { 

    /**
     * Player appearance constants.
     */
    public static final transient int HEAD = 0, TORSO = 1, ARMS = 2, HANDS = 3,
            LEGS = 4, FEET = 5, BEARD = 6, GENDER = 7;
    /**
     * The players actual appearance
     */
    public int[] appearance = new int[8];
    /**
     * Appearance colors
     */
    public int hairColor;
    /**
     *
     */
    public int torsoColor;
    /**
     *
     */
    public int legsColor;
    /**
     *
     */
    public int feetColor;
    /**
     *
     */
    public int skinColor;

    /**
     * Create new apprearance. @todo make use of cached player appearances
     */
    public Appearance() { 
        this.appearance[HEAD] = 3;
        this.appearance[TORSO] = 19;
        this.appearance[ARMS] = 29;
        this.appearance[HANDS] = 35;
        this.appearance[LEGS] = 39;
        this.appearance[FEET] = 44;
        this.appearance[BEARD] = 10;
        this.appearance[GENDER] = 0;
        this.hairColor = 7;
        this.torsoColor = 8;
        this.legsColor = 9;
        this.feetColor = 5;
        this.skinColor = 0;
    }

    /**
     * Get the players head appearance
     *
     * @return
     */
    public int getHead() {
        return appearance[HEAD];
    }

    /**
     * Set the players head appearance
     *
     * @param head
     */
    public void setHead(int head) {
        this.appearance[HEAD] = head;
    }

    /**
     * Get the players torso appearance
     *
     * @return
     */
    public int getTorso() {
        return appearance[TORSO];
    }

    /**
     * Set the players torso appearance
     *
     * @param torso
     */
    public void setTorso(int torso) {
        this.appearance[TORSO] = torso;
    }

    /**
     * Get the players arms appearance
     *
     * @return
     */
    public int getArms() {
        return appearance[ARMS];
    }

    /**
     * Set the players arms appearance
     *
     * @param arms
     */
    public void setArms(int arms) {
        this.appearance[ARMS] = arms;
    }

    /**
     * Get the players hands appearance
     *
     * @return
     */
    public int getHands() {
        return appearance[HANDS];
    }

    /**
     * Set the players hands appearance
     *
     * @param hands
     */
    public void setHands(int hands) {
        this.appearance[HANDS] = hands;
    }

    /**
     * Get the players legs appearance
     *
     * @return
     */
    public int getLegs() {
        return appearance[LEGS];
    }

    /**
     * Set the players legs appearance
     *
     * @param legs
     */
    public void setLegs(int legs) {
        this.appearance[LEGS] = legs;
    }

    /**
     * Get the players feet appearance
     *
     * @return
     */
    public int getFeet() {
        return appearance[FEET];
    }

    /**
     * Set the players feet appearance
     *
     * @param feet
     */
    public void setFeet(int feet) {
        this.appearance[FEET] = feet;
    }

    /**
     * Get the players beard appearance
     *
     * @return
     */
    public int getBeard() {
        return appearance[BEARD];
    }

    /**
     * Set the players beard appearance
     *
     * @param beard
     */
    public void setBeard(int beard) {
        this.appearance[BEARD] = beard;
    }

    /**
     * The players gender
     *
     * @return
     */
    public int getGender() {
        return appearance[GENDER];
    }

    /**
     * Set the players gender
     *
     * @param gender
     */
    public void setGender(int gender) {
        this.appearance[GENDER] = gender;
    }

    /**
     * Get the players hair color
     *
     * @return
     */
    public int getHairColor() {
        return hairColor;
    }

    /**
     * Set the players hair color
     *
     * @param hairColor
     */
    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Get the players torso color
     *
     * @return
     */
    public int getTorsoColor() {
        return torsoColor;
    }

    /**
     * Set the players torso color
     *
     * @param torsoColor
     */
    public void setTorsoColor(int torsoColor) {
        this.torsoColor = torsoColor;
    }

    /**
     * Get the players legs color
     *
     * @return
     */
    public int getLegsColor() {
        return legsColor;
    }

    /**
     * Set the players legs color
     *
     * @param legsColor
     */
    public void setLegsColor(int legsColor) {
        this.legsColor = legsColor;
    }

    /**
     * Get the players feet color
     *
     * @return
     */
    public int getFeetColor() {
        return feetColor;
    }

    /**
     * Set the players feet color
     *
     * @param feetColor
     */
    public void setFeetColor(int feetColor) {
        this.feetColor = feetColor;
    }

    /**
     * Get the players skin color
     *
     * @return
     */
    public int getSkinColor() {
        return skinColor;
    }

    /**
     * Set the players skin color
     *
     * @param skinColor
     */
    public void setSkinColor(int skinColor) {
        this.skinColor = skinColor;
    }
}
