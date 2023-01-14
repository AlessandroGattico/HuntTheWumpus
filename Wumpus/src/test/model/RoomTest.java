package test.model;

import model.Feels;
import model.Items;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room room;

    @BeforeEach
    void setUp() {
        this.room = new Room(0, 0);
    }

    @Test
    void setNeighbours() {
        Room[] neighbours;

        neighbours = new Room[4];
        neighbours[0] = null;
        neighbours[1] = new Room(0, 1);
        neighbours[2] = new Room(1, 0);
        neighbours[3] = null;
        this.room.setNeighbours(neighbours);

        assertEquals(neighbours, this.room.getNeighbours());
    }

    @Test
    void getNeighbours() {
        this.setNeighbours();
    }

    @Test
    void setGold() {
        this.room.setGold(10);

        assertEquals(10, this.room.getGold());
    }

    @Test
    void getGold() {
        this.setGold();
    }

    @Test
    void setArrows() {
        this.room.setArrows(2);

        assertEquals(2, this.room.getArrows());
    }

    @Test
    void getArrows() {
        this.setGold();
    }

    @Test
    void addItem() {
        this.room.addItem(Items.PLAYER);
        this.room.addItem(Items.SURVIVOR);

        assertTrue(this.room.getPlayer());
        assertEquals(Items.SURVIVOR, this.room.getItems());
    }

    @Test
    void deleteItem() {
        this.room.addItem(Items.PLAYER);
        this.room.addItem(Items.SURVIVOR);

        this.room.deleteItem(Items.PLAYER);
        this.room.deleteItem(Items.SURVIVOR);

        assertFalse(this.room.getPlayer());
        assertEquals(Items.EMPTY, this.room.getItems());
    }

    @Test
    void testGetPlayer() {
        this.room.addItem(Items.PLAYER);

        assertTrue(this.room.getPlayer());
    }

    @Test
    void getItems() {
        this.room.addItem(Items.SURVIVOR);

        assertEquals(Items.SURVIVOR, this.room.getItems());
    }

    @Test
    void addFeels() {
        HashSet<Feels> expected = new HashSet<>();

        expected.add(Feels.HELP);
        expected.add(Feels.CLOSEBABY);
        expected.add(Feels.SHARPTHING);
        expected.add(Feels.STENCH);

        this.room.addFeels(Feels.HELP);
        this.room.addFeels(Feels.CLOSEBABY);
        this.room.addFeels(Feels.SHARPTHING);
        this.room.addFeels(Feels.STENCH);

        assertEquals(expected, this.room.getFeels());
    }

    @Test
    void deleteFeels() {
        HashSet<Feels> expected = new HashSet<>();

        expected.add(Feels.CLOSEBABY);
        expected.add(Feels.SHARPTHING);

        this.room.addFeels(Feels.HELP);
        this.room.addFeels(Feels.CLOSEBABY);
        this.room.addFeels(Feels.SHARPTHING);
        this.room.deleteFeels(Feels.HELP);

        assertEquals(expected, this.room.getFeels());
    }

    @Test
    void getFeels() {
        this.addFeels();
    }

    @Test
    void resetRoom() {
        HashSet<Feels> expected = new HashSet<>();

        this.room.addFeels(Feels.HELP);
        this.room.addFeels(Feels.CLOSEBABY);
        this.room.addFeels(Feels.SHARPTHING);
        this.room.addItem(Items.PLAYER);
        this.room.setArrows(1);
        this.room.setGold(10);

        this.room.resetRoom();

        assertEquals(expected, this.room.getFeels());
        assertEquals(0, this.room.getArrows());
        assertEquals(0, this.room.getGold());
        assertEquals(Items.EMPTY, this.room.getItems());
        assertFalse(this.room.getPlayer());
    }

    @Test
    void getX() {
        assertEquals(0, this.room.getX());
    }

    @Test
    void getY() {
        assertEquals(0, this.room.getY());
    }
}