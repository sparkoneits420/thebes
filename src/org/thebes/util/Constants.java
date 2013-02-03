package org.thebes.util;

import org.thebes.model.Location;

/**
 * Server consants.
 * 
 * @author smokey
 */
public class Constants { 

	/**
	 * The port to listen for connections on
	 */
	public static final int LISTENER_PORT = 43594;

	/**
	 * The interval at which the main game engine processes
	 */
	public static final long CYCLE_INTERVAL = 600;

	/**
	 * The maximum amount players allowed to connect
	 */
	public static final int MAXIMUM_PLAYERS = 2000;

	/**
	 * The maximum amount of entities allowed to be created, this should be
	 * implemented so that it does not interfere with the player model, yet
	 * allows the server to keep the entity spawns contained to prevent crashes.
	 */
	public static final int MAXIMUM_ENTITIES = 6000;

	/**
	 * The maximum amount of players allowed to connect per host
	 */
	public static final int MAXIMUM_PLAYERS_PER_HOST = 6;

	/**
	 * The servers revision
	 */
	public static final int REVISION = 317;

        /**
	 * Skill count, 
         * @note: don't be confused, the arrays start at 0 and end at 20, that's 21.
	 */
	public static transient final int SKILL_COUNT = 21;

	/**
	 * The default location for all players, this is where they will spawn when
	 * they join and when they die. This will most likely be the home area
	 * coordinates.
	 */
	public static final Location DEFAULT_LOCATION = new Location(3093, 3494, 0);

	/**
	 * Server name
	 */
	public static final String SERVER_NAME = "Extinal";

	/**
	 * Character file-name extension.
	 */
	public static final String CHARACTER_FILE_EXTENSION = ".ext";

	/**
	 * Difference in X coordinates for directions array.
	 */
	public static final byte[] DIRECTION_DELTA_X = new byte[] { -1, 0, 1, -1,
			1, -1, 0, 1 };
	/**
	 * Difference in Y coordinates for directions array.
	 */
	public static final byte[] DIRECTION_DELTA_Y = new byte[] { 1, 1, 1, 0, 0,
			-1, -1, -1 };
        
        	public static byte DIRECTION_XLATE_TABLE[] = new byte[]{ 1, 2, 4, 7, 6, 5, 3, 0 };
        
	/**
	 * Default sidebar interfaces array.
	 */
	public static final int SIDEBAR_INTERFACES[][] = new int[][] {
            {1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 0},
            {3917, 638, 3213, 1644, 5608, 1151, 5065, 5715, 2449, 4445, 147,
                6299, 2423} 
        };
        
	/**
	 * Incoming packet sizes array.
	 */
	public static final int PACKET_LENGTHS[] = { 
			0, 0, 0, 1, -1, 0, 0, 0, 0, 0, // 0
			0, 0, 0, 0, 8, 0, 6, 2, 2, 0, // 10
			0, 2, 0, 6, 0, 12, 0, 0, 0, 0, // 20
			0, 0, 0, 0, 0, 8, 4, 0, 0, 2, // 30
			2, 6, 0, 6, 0, -1, 0, 0, 0, 0, // 40
			0, 0, 0, 12, 0, 0, 0, 0, 8, 0, // 50
			0, 8, 0, 0, 0, 0, 0, 0, 0, 0, // 60
			6, 0, 2, 2, 8, 6, 0, -1, 0, 6, // 70
			0, 0, 0, 0, 0, 1, 4, 6, 0, 0, // 80
			0, 0, 0, 0, 0, 3, 0, 0, -1, 0, // 90
			0, 13, 0, -1, 0, 0, 0, 0, 0, 0,// 100
			0, 0, 0, 0, 0, 0, 0, 6, 0, 0, // 110
			1, 0, 6, 0, 0, 0, -1, 0, 2, 6, // 120
			0, 4, 6, 8, 0, 6, 0, 0, 0, 2, // 130
			0, 0, 0, 0, 0, 6, 0, 0, 0, 0, // 140
			0, 0, 1, 2, 0, 2, 6, 0, 0, 0, // 150
			0, 0, 0, 0, -1, -1, 0, 0, 0, 0,// 160
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
			0, 8, 0, 3, 0, 2, 0, 0, 8, 1, // 180
			0, 0, 12, 0, 0, 0, 0, 0, 0, 0, // 190
			2, 0, 0, 0, 0, 0, 0, 0, 4, 0, // 200
			4, 0, 0, 0, 7, 8, 0, 0, 10, 0, // 210
			0, 0, 0, 0, 0, 0, -1, 0, 6, 0, // 220
			1, 0, 0, 0, 6, 0, 6, 8, 1, 0, // 230
			0, 4, 0, 0, 0, 0, -1, 0, -1, 4,// 240
			0, 0, 6, 6, 0, 0 // 250
	};

	/**
	 * An array of valid characters in a long username.
	 */
	public static final char VALID_CHARS[] = { '_', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '!', '@', '#', '$', '%', '^', '&', '*',
			'(', ')', '-', '+', '=', ':', ';', '.', '>', '<', ',', '"', '[',
			']', '|', '?', '/', '`' };
	/**
	 * Packed text translate table.
	 */
	public static final char XLATE_TABLE[] = { ' ', 'e', 't', 'a', 'o', 'i',
			'h', 'n', 's', 'r', 'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g',
			'p', 'b', 'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', ' ', '!', '?', '.', ',', ':', ';', '(',
			')', '-', '&', '*', '\\', '\'', '@', '#', '+', '=', '\243', '$',
			'%', '"', '[', ']' };

	/**
	 * Skill name constants
	 */
	public static transient final String[] SKILL_NAMES = { "attack", "defense",
			"strength", "hitpoints", "ranged", "prayer", "magic", "cooking",
			"woodcutting", "fletching", "fishing", "firemaking", "crafting",
			"smithing", "mining", "herblore", "agility", "thieving", "slayer",
			"farming", "runecraft" };

	/**
	 * Skill id constants
	 */
	public static transient final int ATTACK = 0, DEFENSE = 1, STRENGTH = 2,
			HITPOINTS = 3, RANGED = 4, PRAYER = 5, MAGIC = 6, COOKING = 7,
			WOODCUTTING = 8, FLETCHING = 9, FISHING = 10, FIREMAKING = 11,
			CRAFTING = 12, SMITHING = 13, MINING = 14, HERBLORE = 15,
			AGILITY = 16, THIEVING = 17, SLAYER = 18, FARMING = 19,
			RUNECRAFT = 20;

	/**
	 * Server administrators
	 */
	public static final String[] ADMINISTATORS = { "Smokey", "Mopar"};

	/**
	 * Server moderators
	 */
	public static final String[] MODERATORS = {};

}
