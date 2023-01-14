package test.model;

import model.Items;
import model.Room;
import model.Survivor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SurvivorTest {
    private Survivor survivor;

    @BeforeEach
    void setUp() {
        this.survivor = new Survivor();
        this.survivor.setRoom(new Room(0, 0));
        this.survivor.getRoom().addItem(Items.SURVIVOR);
    }

    @Test
    void killed() {
        this.survivor.killed();

        if (this.survivor.getRoom().getArrows() != 0) {
            assertEquals(Items.ARROW, this.survivor.getRoom().getItems());
        } else {
            assertEquals(Items.GOLD, this.survivor.getRoom().getItems());
        }
        assertFalse(this.survivor.isPlaying());
    }

    @Test
    void react() {
        this.survivor.react();

        assertFalse(this.survivor.isPlaying());
        assertEquals(Items.EMPTY, this.survivor.getRoom().getItems());
    }
}