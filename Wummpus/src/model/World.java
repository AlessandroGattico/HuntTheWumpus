package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Random;

/**
 * Class that represents the wumpus world.
 *
 * @author Gattico Alessandro
 * @see HashSet
 * @see PropertyChangeSupport
 * @see PropertyChangeListener
 * @see Random
 */
public class World {

    private Room[][] map;
    private Wumpus wumpus;
    private Player player;
    private BabyWumpus[] babywumpuses;
    private Pit[] pits;
    private Survivor[] survivors;
    private int dimension;
    private Random rand;
    private HashSet<String> messages;
    private boolean gameOn;


    /**
     * Creates a 2D array of {@link Room rooms} and each character of the game.
     *
     * @param dimension world's dimension.
     */
    public World(int dimension) {
        this.dimension = dimension;
        this.map = new Room[this.dimension][this.dimension];
        this.wumpus = new Wumpus();
        this.player = new Player();
        this.pits = new Pit[Math.round(this.dimension / 2)];
        this.survivors = new Survivor[Math.round(this.dimension / 2)];
        this.babywumpuses = new BabyWumpus[Math.round(this.dimension / 2)];
        this.messages = new HashSet<>();
        this.rand = new Random();
        this.gameOn = false;

        this.setupWorld();
    }

    /**
     * Adds the listener to the PropertyChangeSupport.
     *
     * @param listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        for (Pit pit : this.pits) {
            pit.addPropertyChangeListener(listener);
        }
        this.wumpus.addPropertyChangeListener(listener);
    }

    /**
     * Returns the world's dimension.
     *
     * @return world's dimension.
     */
    public int getDimension() {
        return this.dimension;
    }

    /**
     * Returns the game map.
     *
     * @return 2D array of {@link Room rooms}.
     */
    public Room[][] getMap() {
        return this.map;
    }

    /**
     * Returns the player.
     *
     * @return {@link Player player}.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the messages sent at the player.
     *
     * @return Set of messages.
     */
    public HashSet<String> getMessages() {
        return this.messages;
    }

    /**
     * Returns the game status.
     *
     * @return game status.
     */
    public boolean isGameOn() {
        return this.gameOn;
    }

    /**
     * Creates each room of the map.
     */
    private void setupWorld() {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.map[i][j] = new Room(i, j);
            }
        }

        for (int i = 0; i < this.survivors.length; i++) {
            this.survivors[i] = new Survivor();
            this.babywumpuses[i] = new BabyWumpus();
            this.pits[i] = new Pit();
        }
    }

    /**
     * Starts the game, sets each room's neighbours and sets up each character.
     */
    public void play() {
        this.setNeighbours();
        this.setupPlayer();
        this.setupWumpus();
        this.setupSurvivors();
        this.setupBabyWumpuses();
        this.setupPits();
        this.gameOn = true;
    }

    /**
     * Checks if there's another character in the player's room, moves each playing babywumpus and checks the feels
     * in the neighbours of the player's room.
     */
    public void updateWorld() {
        this.messages.clear();
        this.checkRoomItems();
        this.moveBaby();
        this.checkRoomFeels();
    }

    /**
     * Resets the game at the default parameters.
     */
    public void resetGame() {
        this.gameOn = false;
        this.stopPlay();

        for (Room[] row : this.map) {
            for (Room room : row) {
                room.resetRoom();
            }
        }

        this.player.resetFacing();
        this.player.backpack.resetItems();
        this.player.resetMoves();
        this.messages.clear();
    }

    /**
     * Removes the characters and their feels from the map.
     */
    private void stopPlay() {
        this.player.setPlaying(false);
        this.player.getRoom().deleteItem(Items.PLAYER);
        this.wumpus.setPlaying(false);
        this.wumpus.getRoom().deleteItem(Items.WUMPUS);
        this.removeNeighboursFeels(this.wumpus.getRoom(), Feels.STENCH);

        for (int i = 0; i < this.survivors.length; i++) {
            this.survivors[i].setPlaying(false);
            this.survivors[i].getRoom().deleteItem(Items.SURVIVOR);
            this.removeNeighboursFeels(this.survivors[i].getRoom(), Feels.HELP);
            this.pits[i].setPlaying(false);
            this.pits[i].getRoom().deleteItem(Items.PIT);
            this.removeNeighboursFeels(this.pits[i].getRoom(), Feels.BREEZE);
            this.babywumpuses[i].setPlaying(false);
            this.babywumpuses[i].getRoom().deleteItem(Items.BABYWUMPUS);
            this.removeNeighboursFeels(this.babywumpuses[i].getRoom(), Feels.CLOSEBABY);
        }
    }

    /**
     * Sets up the {@link Player player}.
     */
    private void setupPlayer() {
        this.player.setRoom(this.map[0][0]);
        this.player.getRoom().addItem(Items.PLAYER);
        this.player.setPlaying(true);
    }

    /**
     * Sets up the {@link Wumpus wumpus}.
     */
    private void setupWumpus() {
        int[] random;

        random = getRandomCoordinates(5);
        this.wumpus.setRoom(this.map[random[0]][random[1]]);
        this.wumpus.getRoom().addItem(Items.WUMPUS);
        this.addNeighbourFeels(this.wumpus.getRoom(), Feels.STENCH);
        this.wumpus.setPlaying(true);
    }

    /**
     * Sets up each {@link Survivor survivor}.
     */
    private void setupSurvivors() {
        int[] random;

        for (int i = 0; i < this.survivors.length; i++) {

            do {
                random = getRandomCoordinates(3);
            } while (!this.neighboursEmpty(this.map[random[0]][random[1]]));

            this.survivors[i].setRoom(this.map[random[0]][random[1]]);
            this.survivors[i].getRoom().addItem(Items.SURVIVOR);
            this.addNeighbourFeels(this.survivors[i].getRoom(), Feels.HELP);
            this.survivors[i].setPlaying(true);
        }
    }

    /**
     * Sets up each {@link BabyWumpus baby wumpus}.
     */
    private void setupBabyWumpuses() {
        int[] random;

        for (int i = 0; i < this.babywumpuses.length; i++) {

            do {
                random = getRandomCoordinates(3);
            } while (!this.neighboursEmpty(this.map[random[0]][random[1]]));

            this.babywumpuses[i].setRoom(this.map[random[0]][random[1]]);
            this.babywumpuses[i].getRoom().addItem(Items.BABYWUMPUS);
            this.addNeighbourFeels(this.babywumpuses[i].getRoom(), Feels.CLOSEBABY);
            this.babywumpuses[i].setPlaying(true);
        }
    }

    /**
     * Sets up each {@link Pit pit}.
     */
    private void setupPits() {
        int[] random;

        for (int i = 0; i < this.pits.length; i++) {

            do {
                random = getRandomCoordinates(3);
            } while (!this.neighboursEmpty(this.map[random[0]][random[1]]));

            this.pits[i].setRoom(this.map[random[0]][random[1]]);
            this.pits[i].getRoom().addItem(Items.PIT);
            this.addNeighbourFeels(this.pits[i].getRoom(), Feels.BREEZE);
            this.pits[i].setPlaying(true);
        }
    }

    /**
     * Generates random coordinates: x -> randomC[0], y -> randomC[1].
     *
     * @param away minimum rooms that the character <strong>must be </strong>far from the player at setup.
     * @return array of int that represents the random coordinates.
     */
    private int[] getRandomCoordinates(int away) {
        int[] randomC = new int[2];

        do {
            do {
                randomC[0] = this.rand.nextInt(this.dimension);
                randomC[1] = this.rand.nextInt(this.dimension);
            } while (this.map[randomC[0]][randomC[1]].getItems() != Items.EMPTY);
        } while (randomC[0] < away && randomC[1] < away);

        return randomC;
    }

    /**
     * Sets each room's neighbours.
     * <p>
     *       N
     *      [0]
     * W[3]     [1]E
     *      [2]
     *       S
     *
     */
    private void setNeighbours() {
        //north = 0, east = 1, south = 2, west = 3
        Room[] neighbours;

        for (Room[] row : this.map) {
            for (Room room : row) {
                neighbours = new Room[4];
                if (room.getY() - 1 >= 0) {
                    neighbours[0] = this.map[room.getY() - 1][room.getX()];    //north
                } else {
                    neighbours[0] = null;
                }
                if (room.getX() + 1 < this.dimension) {
                    neighbours[1] = this.map[room.getY()][room.getX() + 1];    //east
                } else {
                    neighbours[1] = null;
                }
                if (room.getY() + 1 < this.dimension) {
                    neighbours[2] = this.map[room.getY() + 1][room.getX()];    //south
                } else {
                    neighbours[2] = null;
                }
                if (room.getX() - 1 >= 0) {
                    neighbours[3] = this.map[room.getY()][room.getX() - 1];    //west
                } else {
                    neighbours[3] = null;
                }
                room.setNeighbours(neighbours);
            }
        }
    }

    /**
     * Adds the feel passed as argument to each room's neighbour.
     *
     * @param feel feel to add.
     * @param room room to get the neighbours from.
     */
    private void addNeighbourFeels(Room room, Feels feel) {
        for (Room r : room.getNeighbours()) {
            if (r != null) {
                r.addFeels(feel);
            }
        }
    }

    /**
     * Removes the feel passed as argument from each room's neighbour.
     *
     * @param feel feel to remove.
     * @param room room to get the neighbours from.
     */
    private void removeNeighboursFeels(Room room, Feels feel) {
        for (Room r : room.getNeighbours()) {
            if (r != null) {
                r.deleteFeels(feel);
            }
        }
    }


    /**
     * Checks if each room's neighbour is empty.
     *
     * @param room to get the neighbours from.
     * @return true if each neighbour is empty, false otherwise.
     */
    private boolean neighboursEmpty(Room room) {
        for (Room r : room.getNeighbours()) {
            if (r != null) {
                if (!r.getItems().equals(Items.EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks the item in the room where the player shot.
     *
     * @param room room to check.
     */
    public void checkShoot(Room room) {
        if (room != null) {
            switch (room.getItems()) {
                case SURVIVOR:
                    for (Survivor survivor : this.survivors) {
                        if (survivor.getRoom().equals(room)) {
                            this.removeNeighboursFeels(survivor.getRoom(), Feels.HELP);
                            survivor.killed();
                            if (survivor.getRoom().getItems().equals(Items.ARROW)) {
                                this.addNeighbourFeels(survivor.getRoom(), Feels.SHARPTHING);
                            } else {
                                this.addNeighbourFeels(survivor.getRoom(), Feels.SHINYTHING);
                            }
                        }
                    }
                    break;
                case BABYWUMPUS:
                    for (BabyWumpus baby : this.babywumpuses) {
                        if (baby.getRoom().equals(room)) {
                            this.removeNeighboursFeels(baby.getRoom(), Feels.CLOSEBABY);
                            baby.killed();
                            if (baby.getRoom().getItems().equals(Items.ARROW)) {
                                this.addNeighbourFeels(baby.getRoom(), Feels.SHARPTHING);
                            } else {
                                this.addNeighbourFeels(baby.getRoom(), Feels.SHINYTHING);
                            }
                        }
                    }
                    break;
                case WUMPUS:
                    this.player.setPlaying(false);
                    this.gameOn = false;
                    this.wumpus.killed();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Checks the feels of the player's room and adds them to the messages.
     */
    private void checkRoomFeels() {
        if (this.player.getRoom().getFeels().contains(Feels.BREEZE)) {
            this.messages.add("There's a strange breeze\n");
        }
        if (this.player.getRoom().getFeels().contains(Feels.CLOSEBABY)) {
            this.messages.add("Pay attention to your backpack!!\n");
        }
        if (this.player.getRoom().getFeels().contains(Feels.HELP)) {
            this.messages.add("Someone is asking for help!!\n");
        }
        if (this.player.getRoom().getFeels().contains(Feels.STENCH)) {
            this.messages.add("Something around here smells so bad\n");
        }
        if (this.player.getRoom().getFeels().contains(Feels.SHINYTHING)) {
            this.messages.add("There's something shiny around here\n");
        }
        if (this.player.getRoom().getFeels().contains(Feels.SHARPTHING)) {
            this.messages.add("There's something sharp around here\n");
        }
    }

    /**
     * Moves randomly each baby wumpus that is still playing.
     */
    private void moveBaby() {
        for (BabyWumpus baby : this.babywumpuses) {
            if (baby.isPlaying()) {
                this.removeNeighboursFeels(baby.getRoom(), Feels.CLOSEBABY);
                baby.move(Directions.values()[rand.nextInt(4)], false);
                this.addNeighbourFeels(baby.getRoom(), Feels.CLOSEBABY);
            }
        }
    }

    /**
     * Checks the items in the player's room.
     */
    private void checkRoomItems() {
        int app;

        switch (this.player.getRoom().getItems()) {
            case ARROW:
                if (this.player.getRoom().getArrows() == 1) {
                    this.messages.add("Nice, you found an arrow\n");
                } else {
                    this.messages.add("Nice, you found " + this.player.getRoom().getArrows() + " arrows\n");
                }
                this.player.getBackpack().modifyArrows(this.player.getRoom().getArrows());
                this.player.getRoom().setArrows(0);
                this.player.getRoom().deleteItem(Items.ARROW);
                this.removeNeighboursFeels(this.player.getRoom(), Feels.SHARPTHING);
                break;
            case GOLD:
                this.messages.add("Nice, you found " + this.player.getRoom().getGold() + " gold\n");
                this.player.getBackpack().modifyGold(this.player.getRoom().getGold());
                this.player.getRoom().setGold(0);
                this.player.getRoom().deleteItem(Items.GOLD);
                this.removeNeighboursFeels(this.player.getRoom(), Feels.SHINYTHING);
                break;
            case BABYWUMPUS:
                for (BabyWumpus baby : this.babywumpuses) {
                    if (baby.getRoom().equals(this.player.getRoom())) {
                        app = baby.react();
                        this.removeNeighboursFeels(this.player.getRoom(), Feels.CLOSEBABY);
                        if (app == BabyWumpus.DEFAULTLOOSEGOLD) {
                            this.player.getBackpack().modifyGold(app);
                            this.messages.add("Oh no! A baby wumpus stole you some gold.\n");
                        } else {
                            this.player.getBackpack().modifyArrows(app);
                            this.messages.add("Oh no! A baby wumpus stole you an arrow.\n");
                        }
                    }
                }
                break;
            case PIT:
                for (Pit pit : this.pits) {
                    if (pit.getRoom().equals(this.player.getRoom())) {
                        this.player.setPlaying(false);
                        this.gameOn = false;
                        pit.react();
                    }
                }
                break;
            case SURVIVOR:
                for (Survivor survivor : this.survivors) {
                    if (survivor.getRoom().equals(this.player.getRoom())) {
                        app = survivor.react();
                        this.removeNeighboursFeels(survivor.getRoom(), Feels.HELP);
                        if (app == Survivor.DEFAULTARROWS) {
                            this.player.getBackpack().modifyArrows(app);
                            this.messages.add("Nice! A survivor gave you 2 arrows\n");
                        } else {
                            this.player.getBackpack().modifyGold(app);
                            this.messages.add("Nice! A survivor gave you some gold\n");
                        }
                    }
                }
                break;
            case WUMPUS:
                this.player.setPlaying(false);
                this.gameOn = false;
                this.wumpus.react();
                break;
            default:
                break;
        }
    }
}
