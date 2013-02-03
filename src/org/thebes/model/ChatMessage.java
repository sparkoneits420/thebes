package org.thebes.model;

/**
 * Represents a chat message
 *
 * @author smokey
 *
 */
public class ChatMessage { 

    /**
     * The text color
     */
    private int color;
    /**
     * The message effects
     */
    private int effects;
    /**
     * The packed message text
     */
    private byte[] text;

    /**
     * Creates a new chat message
     *
     * @param color the text color
     * @param effects the message effects
     * @param text the packed message text
     */
    public ChatMessage(int color, int effects, byte[] text) {
        this.color = color;
        this.effects = effects;
        this.text = text;
    }

    /**
     * Get the message color
     *
     * @return
     */
    public int getColor() {
        return color;
    }

    /**
     * Get the message effects
     *
     * @return
     */
    public int getEffects() {
        return effects;
    }

    /**
     * Gets the packed message text
     *
     * @return
     */
    public byte[] getText() {
        return text;
    }
}
