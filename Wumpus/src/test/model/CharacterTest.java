package test.model;

import model.Character;
import model.Directions;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterTest {

    Character character;

    @BeforeEach
    void setUp() {
        this.character = new Character() {
            @Override
            public void move(Directions direction, boolean random) {

            }

            @Override
            public int react() {
                return 0;
            }

            @Override
            public void killed() {

            }
        };
    }


    @Test
    void setRoom() {
        Room expected = new Room(1, 2);
        this.character.setRoom(expected);

        assertEquals(expected, this.character.getRoom());
    }


    @Test
    void getRoom() {
        this.setRoom();
    }


    @Test
    void setPlaying() {
        this.character.setPlaying(true);

        assertTrue(this.character.isPlaying());
    }


    @Test
    void isPlaying() {
        this.setPlaying();
    }
}