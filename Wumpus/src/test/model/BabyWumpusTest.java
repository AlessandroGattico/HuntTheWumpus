package test.model;

import model.BabyWumpus;
import model.Directions;
import model.Items;
import model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BabyWumpusTest {

    private BabyWumpus babyWumpus;

    @BeforeEach
    void setUp() {
        Room[] neighbours;

        this.babyWumpus = new BabyWumpus();
        this.babyWumpus.setRoom(new Room(0, 0));

        neighbours = new Room[4];
        neighbours[0] = null;
        neighbours[1] = new Room(0, 1);
        neighbours[2] = new Room(1, 0);
        neighbours[3] = null;
        this.babyWumpus.getRoom().setNeighbours(neighbours);
    }


    @Test
    void move() {
        this.babyWumpus.move(Directions.MOVERIGHT, false);

        assertEquals(0, this.babyWumpus.getRoom().getY());
        assertEquals(1, this.babyWumpus.getRoom().getX());
    }


    @Test
    void moveOutOfMap() {
        this.babyWumpus.move(Directions.MOVELEFT, false);

        assertEquals(0, this.babyWumpus.getRoom().getY());
        assertEquals(0, this.babyWumpus.getRoom().getX());
    }


    @Test
    void killed() {
        this.babyWumpus.killed();

        if (this.babyWumpus.getRoom().getArrows() != 0) {
            assertEquals(Items.ARROW, this.babyWumpus.getRoom().getItems());
        } else {
            assertEquals(Items.GOLD, this.babyWumpus.getRoom().getItems());
        }

        assertFalse(this.babyWumpus.isPlaying());
    }


    @Test
    void react() {
        this.babyWumpus.react();

        Assertions.assertFalse(this.babyWumpus.isPlaying());
        assertEquals(Items.EMPTY, this.babyWumpus.getRoom().getItems());
    }
}