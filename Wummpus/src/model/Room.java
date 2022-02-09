package model;

import java.util.HashSet;

/**
 * Class that represents each world's room.
 *
 * @author Gattico Alessandro
 * @see HashSet
 */
public class Room {

    private final int x;
    private final int y;
    private int gold;
    private int arrows;
    private HashSet<Feels> feels;
    private Items item;
    private boolean player;
    private Room[] neighbours;

    /**
     * Sets up the room setting it empty and with no feels in it.
     *
     * @param x x coordinate of the room.
     * @param y y coordinate if the room.
     */
    public Room(int y, int x) {
        this.x = x;
        this.y = y;
        this.item = Items.EMPTY;
        this.feels = new HashSet<>();
        this.arrows = 0;
        this.gold = 0;
        this.player = false;
    }

    /**
     * Resets the room with the default values.
     */
    public void resetRoom() {
        this.deleteItem(Items.PLAYER);
        this.addItem(Items.EMPTY);
        this.arrows = 0;
        this.gold = 0;
        this.clearFeels();
    }

    /**
     * Sets the room's neighbours.
     *
     * @param neighbours array of room that contains the room's neighbours.
     */
    public void setNeighbours(Room[] neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * Returns an array of rooms that represents the room's neighbours.
     *
     * @return array of rooms that represents the room's neighbours.
     */
    public Room[] getNeighbours() {
        return this.neighbours;
    }

    /**
     * Sets the room's gold.
     *
     * @param gold new room's gold.
     */
    public void setGold(int gold) {
        this.gold += gold;
    }

    /**
     * Returns the room's gold.
     *
     * @return int that represents the room's gold.
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * Sets the room's arrows.
     *
     * @param arrows new room's arrows
     */
    public void setArrows(int arrows) {
        this.arrows += arrows;
    }

    /**
     * Returns the room's arrows.
     *
     * @return int that represents the room's arrows.
     */
    public int getArrows() {
        return this.arrows;
    }

    /**
     * If the item passed as argument is PLAYER sets the playerIn indicator to true, otherwise sets the room's item
     * with the one passed as argument.
     *
     * @param item new room's item.
     * @see Items
     */
    public void addItem(Items item) {
        if (item == Items.PLAYER) {
            this.player = true;
        } else {
            this.item = item;
        }
    }

    /**
     * If the item passed as argument is PLAYER sets the playerIn indicator to false, otherwise removes the items in
     * the room and sets it empty.
     *
     * @param item item to remove from the room.
     * @see Items
     */
    public void deleteItem(Items item) {
        if (item == Items.PLAYER) {
            this.player = false;
        } else {
            this.item = Items.EMPTY;
        }
    }

    /**
     * Returns the playerIn indicator.
     *
     * @return boolean that represents if the player is in the room.
     */
    public boolean getPlayer() {
        return this.player;
    }

    /**
     * Returns the item in the room.
     *
     * @return item in the room.
     */
    public Items getItems() {
        return this.item;
    }

    /**
     * Adds the feel passed as argument to the room's feels.
     *
     * @param feel feel to add.
     * @see Feels
     */
    public void addFeels(Feels feel) {
        this.feels.add(feel);
    }

    /**
     * Removes the feel passed as argument to the room's feels.
     *
     * @param feel feel to remove from the room.
     * @see Feels
     */
    public void deleteFeels(Feels feel) {
        this.feels.remove(feel);
    }

    /**
     * Returns the room's feels.
     *
     * @return HashSet of feels that represents the room's feels.
     */
    public HashSet<Feels> getFeels() {
        return this.feels;
    }

    /**
     * Removes all the feels in the room.
     */
    private void clearFeels() {
        this.feels.clear();
    }

    /**
     * Returns the room's X coordinate.
     *
     * @return X coordinate of the room.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the room's Y coordinate.
     *
     * @return Y coordinate of the room.
     */
    public int getY() {
        return this.y;
    }
}
