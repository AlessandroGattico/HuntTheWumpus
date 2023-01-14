package model;

import java.util.Random;


/**
 * Class that represents the survivor.
 *
 * @author Gattico Alessandro
 * @see Player
 * @see Random
 */
public class Survivor extends Player {
    static final int DEFAULTARROWS = 2;
    static final int DEFAULTGOLD = 10;


    /**
     * Call to parent constructor and sets its backpack with default arrows (2) and gold (10).
     */
    public Survivor() {
        super();
        this.backpack.setItems(DEFAULTARROWS, DEFAULTGOLD);
    }


    /**
     * Returns randomly an int representing gold or arrows.
     *
     * @return int that represents the item gained item.
     */
    private int gain() {
        Random random = new Random();

        if (random.nextInt(10) < 8) {
            return DEFAULTARROWS;
        } else {
            return DEFAULTGOLD;
        }
    }


    @Override
    public void killed() {
        this.room.deleteItem(Items.SURVIVOR);

        if (this.gain() == DEFAULTARROWS) {
            this.room.setArrows(DEFAULTARROWS);
            this.room.addItem(Items.ARROW);
        } else {
            this.room.setGold(DEFAULTGOLD);
            this.room.addItem(Items.GOLD);
        }

        this.isPlaying = false;
    }


    @Override
    public int react() {
        this.isPlaying = false;
        this.room.deleteItem(Items.SURVIVOR);

        return this.gain();
    }
}
