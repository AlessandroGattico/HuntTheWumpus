package test.model;

import model.Directions;
import model.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    private World world;

    @BeforeEach
    void setUp() {
        this.world = new World(10);
    }

    @Test
    void getDimension() {
        assertEquals(10, this.world.getDimension());
    }

    @Test
    void getMap() {
        assertNotNull(this.world.getMap());
    }

    @Test
    void getPlayer() {
        assertNotNull(this.world.getPlayer());
    }

    @Test
    void getMessages() {
        HashSet<String> expected = new HashSet<>();

        assertEquals(expected, this.world.getMessages());
    }

    @Test
    void isGameOn() {
        assertFalse(this.world.isGameOn());
    }

    @Test
    void play() {
        this.world.play();

        assertEquals(this.world.getMap()[0][0], this.world.getPlayer().getRoom());
        assertTrue(this.world.isGameOn());
    }

    @Test
    void resetGame() {
        this.world.play();
        this.world.resetGame();

        assertFalse(this.world.isGameOn());
        assertEquals(Directions.EAST, this.world.getPlayer().getFacing());
        assertEquals(0, this.world.getPlayer().getMoves());

    }
}