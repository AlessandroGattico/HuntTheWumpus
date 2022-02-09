package model;

/**
 * Abstract class used as a model for all characters in the game.
 *
 * @author Gattico Alessandro
 */
public abstract class Character {
    protected Room room;
    protected boolean isPlaying;

    /**
     * Initializes the character's {@link Room room} as null and sets it not playing.
     */
    public Character() {
        this.room = null;
        this.isPlaying = false;
    }

    /**
     * Returns the character's room.
     *
     * @return character's room.
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * Sets the character's room.
     *
     * @param room new character's room.
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Returns the character's playing status.
     *
     * @return boolean that represents the character's playing status.
     */
    public boolean isPlaying() {
        return this.isPlaying;
    }

    /**
     * Sets the character's playing status.
     *
     * @param playing new character's playing status.
     */
    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
    }


    /**
     * Moves the character.
     *
     * @param direction direction where the character has to move.
     * @param random if true the character <strong>must move</strong> in the map.
     */
    public abstract void move(Directions direction, boolean random);

    /**
     * Describes the character reaction when the player enters its room and stops playing.
     *
     * @return int that represents the lost or gained item.
     */
     public abstract int react();

    /**
     * Describes the behavior when killed. It leaves, randomly, arrows or gold in the room and stops playing.
     */
    public abstract void killed();
}
