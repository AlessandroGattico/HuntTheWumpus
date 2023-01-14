package test.model;

import model.Directions;
import model.Player;
import model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        Room[] neighbours;

        this.player = new Player();
        this.player.setRoom(new Room(0, 0));

        neighbours = new Room[4];
        neighbours[0] = null;
        neighbours[1] = new Room(0, 1);
        neighbours[2] = new Room(1, 0);
        neighbours[3] = null;

        this.player.getRoom().setNeighbours(neighbours);
    }


    @Test
    void getFacing() {
        assertEquals(Directions.EAST, this.player.getFacing());
    }


    @Test
    void getBackpack() {
        Assertions.assertNotNull(this.player.getBackpack());
    }


    @Test
    void getMoves() {
        assertEquals(0, this.player.getMoves());
    }


    @Test
    void resetMoves() {
        this.player.move(Directions.MOVEFORWARD, false);
        this.player.resetMoves();
        assertEquals(0, this.player.getMoves());
    }


    @Test
    void resetFacing() {
        this.player.turn(Directions.TURNLEFT);
        this.player.resetFacing();
        assertEquals(Directions.EAST, this.player.getFacing());
    }


    @Test
    void turn() {
        this.player.turn(Directions.TURNLEFT);
        assertEquals(Directions.NORTH, this.player.getFacing());
    }


    @Test
    void move() {
        this.player.move(Directions.MOVEFORWARD, false);
        assertEquals(0, this.player.getRoom().getY());
        assertEquals(1, this.player.getRoom().getX());
        assertEquals(1, this.player.getMoves());
    }


    @Test
    void moveOutOfMap() {
        this.player.move(Directions.MOVEBACKWARDS, false);
        assertEquals(0, this.player.getRoom().getY());
        assertEquals(0, this.player.getRoom().getX());
        assertEquals(1, this.player.getMoves());
    }


    @Test
    void moveRandom() {
        this.player.move(Directions.MOVEBACKWARDS, true);
        assertEquals(0, this.player.getRoom().getY());
        assertEquals(1, this.player.getRoom().getX());
        assertEquals(1, this.player.getMoves());
    }


    @Test
    void shoot() {
        this.player.shoot();
        assertEquals(3, this.player.getBackpack().getArrows());
    }
}