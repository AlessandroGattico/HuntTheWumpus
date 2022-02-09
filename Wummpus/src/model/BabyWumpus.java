package model;

import java.util.Random;

/**
 * Class that represents the baby wumpus.
 *
 * @author Gattico Alessandro
 * @see Character
 * @see Random
 */
public class BabyWumpus extends Character {

    static final int DEFAULTLOOSEARROWS = -1;
    static final int DEFAULTLOOSEGOLD = -10;
    private Random random;

    /**
     * Call to the parent constructor and creates the random object.
     */
    public BabyWumpus() {
        super();
        this.random = new Random();
    }

    /**
     * Returns randomly an int representing gold or arrows.
     *
     * @return int that represents the lost item.
     */
    private int loose() {
        int r;

        r = random.nextInt(10);

        if (r < 8) {
            return DEFAULTLOOSEARROWS;
        } else {
            return DEFAULTLOOSEGOLD;
        }
    }

    @Override
    public void killed() {
        int item;

        item = this.loose();
        this.room.deleteItem(Items.BABYWUMPUS);

        if (item == DEFAULTLOOSEARROWS) {
            this.room.setArrows(Math.abs(DEFAULTLOOSEARROWS));
            this.room.addItem(Items.ARROW);
        } else {
            this.room.setGold(Math.abs(DEFAULTLOOSEGOLD));
            this.room.addItem(Items.GOLD);
        }

        this.isPlaying = false;
    }

    @Override
    public void move(Directions direction, boolean random) {
        switch (direction) {
            case MOVEFORWARD:
                if (this.room.getNeighbours()[0] != null && this.room.getNeighbours()[0].getItems().equals(Items.EMPTY) &&
                        !this.room.getNeighbours()[0].getPlayer()) {
                    this.changeRoom(this.room.getNeighbours()[0]);
                }
                break;
            case MOVERIGHT:
                if (this.room.getNeighbours()[1] != null && this.room.getNeighbours()[1].getItems().equals(Items.EMPTY) &&
                        !this.room.getNeighbours()[1].getPlayer()) {
                    this.changeRoom(this.room.getNeighbours()[1]);
                }
                break;
            case MOVEBACKWARDS:
                if (this.room.getNeighbours()[2] != null && this.room.getNeighbours()[2].getItems().equals(Items.EMPTY) &&
                        !this.room.getNeighbours()[2].getPlayer()) {
                    this.changeRoom(this.room.getNeighbours()[2]);
                }
                break;
            case MOVELEFT:
                if (this.room.getNeighbours()[3] != null && this.room.getNeighbours()[3].getItems().equals(Items.EMPTY) &&
                        !this.room.getNeighbours()[3].getPlayer()) {
                    this.changeRoom(this.room.getNeighbours()[3]);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Removes the baby wumpus from its room, sets the new room, adds the baby wumpus into its new room.
     *
     * @param r new room.
     */
    private void changeRoom(Room r) {
        this.room.deleteItem(Items.BABYWUMPUS);
        this.setRoom(r);
        this.room.addItem(Items.BABYWUMPUS);
    }

    @Override
    public int react() {
        this.isPlaying = false;
        this.room.deleteItem(Items.BABYWUMPUS);

        return this.loose();
    }
}
