package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Class that represents the player
 *
 * @author Gattico Alessandro
 * @see Character
 * @see PropertyChangeSupport
 * @see PropertyChangeListener
 */
public class Player extends Character {
    private static final Directions DEFAULTFACING = Directions.EAST;
    protected Backpack backpack;
    private Directions facing;
    private int moves;
    private PropertyChangeSupport support;


    /**
     * Call to the parent constructor, creates the backpack and sets the default facing direction to EAST.
     */
    public Player() {
        super();
        this.backpack = new Backpack();
        this.facing = DEFAULTFACING;
        this.moves = 0;
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public int react() {
        return 0;
    }

    @Override
    public void killed() {

    }

    /**
     * Adds the listener to the PropertyChangeSupport.
     *
     * @param listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the player's facing direction.
     *
     * @return player's facing direction.
     */
    public Directions getFacing() {
        return this.facing;
    }

    /**
     * Resets the facing direction to the default value(EAST).
     */
    public void resetFacing() {
        this.facing = DEFAULTFACING;
    }

    /**
     * Returns the player's backpack.
     *
     * @return player's {@link Backpack backpack}.
     */
    public Backpack getBackpack() {
        return this.backpack;
    }

    /**
     * Returns the number of moves that the player has made.
     *
     * @return int that represents the player's moves.
     */
    public int getMoves() {
        return this.moves;
    }

    /**
     * Resets the player's moves to 0.
     */
    public void resetMoves() {
        this.moves = 0;
    }

    /**
     * Turns the player in the direction passed as argument.
     *
     * @param direction direction where the player has to turn.
     */
    public void turn(Directions direction) {
        Directions oldDirection;
        oldDirection = this.facing;

        switch (direction) {
            case TURNLEFT:
                switch (this.facing) {
                    case NORTH:
                        this.facing = Directions.WEST;
                        break;
                    case SOUTH:
                        this.facing = Directions.EAST;
                        break;
                    case WEST:
                        this.facing = Directions.SOUTH;
                        break;
                    case EAST:
                        this.facing = Directions.NORTH;
                        break;
                }
                break;
            case TURNRIGHT:
                switch (this.facing) {
                    case NORTH:
                        this.facing = Directions.EAST;
                        break;
                    case SOUTH:
                        this.facing = Directions.WEST;
                        break;
                    case WEST:
                        this.facing = Directions.NORTH;
                        break;
                    case EAST:
                        this.facing = Directions.SOUTH;
                        break;
                }
                break;
        }

        this.moves++;
        this.support.firePropertyChange("turn", oldDirection, this.facing);
    }

    @Override
    public void move(Directions direction, boolean random) {
        Room oldRoom;
        oldRoom = this.room;

        this.moves++;

        switch (direction) {
            case MOVEFORWARD:
                switch (this.facing) {
                    case NORTH:
                        if (this.room.getNeighbours()[0] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[0]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[2]);
                            }
                        }
                        break;
                    case EAST:
                        if (this.room.getNeighbours()[1] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[1]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[3]);
                            }
                        }
                        break;
                    case SOUTH:
                        if (this.room.getNeighbours()[2] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[2]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[0]);
                            }
                        }
                        break;
                    case WEST:
                        if (this.room.getNeighbours()[3] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[3]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[1]);
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            case MOVEBACKWARDS:
                switch (this.facing) {
                    case NORTH:
                        if (this.room.getNeighbours()[2] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[2]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[0]);
                            }
                        }
                        break;
                    case EAST:
                        if (this.room.getNeighbours()[3] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[3]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[1]);
                            }
                        }
                        break;
                    case SOUTH:
                        if (this.room.getNeighbours()[0] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[0]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[2]);
                            }
                        }
                        break;
                    case WEST:
                        if (this.room.getNeighbours()[1] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[1]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[3]);
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            case MOVELEFT:
                switch (this.facing) {
                    case NORTH:
                        if (this.room.getNeighbours()[3] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[3]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[1]);
                            }
                        }
                        break;
                    case EAST:
                        if (this.room.getNeighbours()[0] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[0]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[2]);
                            }
                        }
                        break;
                    case SOUTH:
                        if (this.room.getNeighbours()[1] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[1]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[3]);
                            }
                        }
                        break;
                    case WEST:
                        if (this.room.getNeighbours()[2] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[2]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[0]);
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            case MOVERIGHT:
                switch (this.facing) {
                    case NORTH:
                        if (this.room.getNeighbours()[1] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[1]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[3]);
                            }
                        }
                        break;
                    case EAST:
                        if (this.room.getNeighbours()[2] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[2]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[0]);
                            }
                        }
                        break;
                    case SOUTH:
                        if (this.room.getNeighbours()[3] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[3]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[1]);
                            }
                        }
                        break;
                    case WEST:
                        if (this.room.getNeighbours()[0] != null) {
                            this.changePlayerRoom(this.room.getNeighbours()[0]);
                        } else {
                            if (random) {
                                this.changePlayerRoom(this.room.getNeighbours()[2]);
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        this.support.firePropertyChange("move", oldRoom, this.room);
    }


    /**
     * If the player has at least an arrows it hoots one in the facing direction.
     */
    public void shoot() {
        if (this.backpack.arrows > 0) {
            switch (this.facing) {
                case NORTH:
                    if (this.room.getNeighbours()[0] != null) {
                        this.backpack.modifyArrows(-1);
                        this.support.firePropertyChange("shoot", this.room, this.room.getNeighbours()[0]);
                    } else {
                        this.backpack.modifyArrows(-1);
                    }
                    break;
                case EAST:
                    if (this.room.getNeighbours()[1] != null) {
                        this.backpack.modifyArrows(-1);
                        this.support.firePropertyChange("shoot", this.room, this.room.getNeighbours()[1]);
                    } else {
                        this.backpack.modifyArrows(-1);
                    }
                    break;
                case SOUTH:
                    if (this.room.getNeighbours()[2] != null) {
                        this.backpack.modifyArrows(-1);
                        this.support.firePropertyChange("shoot", this.room, this.room.getNeighbours()[2]);
                    } else {
                        this.backpack.modifyArrows(-1);
                    }
                    break;
                case WEST:
                    if (this.room.getNeighbours()[3] != null) {
                        this.backpack.modifyArrows(-1);
                        this.support.firePropertyChange("shoot", this.room, this.room.getNeighbours()[3]);
                    } else {
                        this.backpack.modifyArrows(-1);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Changes the player's room with its neighbour passed as argument.
     *
     * @param room new player's room.
     */
    private void changePlayerRoom(Room room) {
        this.room.deleteItem(Items.PLAYER);
        this.room = room;
        this.room.addItem(Items.PLAYER);
    }

    /**
     * Class that represents the player's backpack.
     *
     * @author Borova Dritan 20034245
     * @author Gattico Alessandro 20033987
     */
    public static class Backpack {
        private static final int DEFAULTARROWS = 4;
        private static final int DEFAULTGOLD = 0;
        private int gold;
        private int arrows;

        /**
         * Sets the items to the default values (4 arrows, 0 gold).
         */
        private Backpack() {
            this.arrows = DEFAULTARROWS;
            this.gold = DEFAULTGOLD;
        }

        /**
         * Sets player's arrows and gold.
         *
         * @param arrows to set.
         * @param gold   to set.
         */
        protected void setItems(int arrows, int gold) {
            this.setArrows(arrows);
            this.setGold(gold);
        }

        private void setArrows(int arrows) {
            this.arrows = arrows;
        }

        private void setGold(int gold){
            this.gold = gold;
        }

        /**
         * Returns the player's arrows.
         *
         * @return player's arrows.
         */
        public int getArrows() {
            return this.arrows;
        }

        /**
         * Returns the player's gold.
         *
         * @return player's gold.
         */
        public int getGold() {
            return this.gold;
        }

        /**
         * Increases or decreases player's arrows.
         *
         * @param arrows to add (or subtract) into (from) the backpack.
         */
        public void modifyArrows(int arrows) {
            this.arrows += arrows;

            if (this.arrows < 0) {
                this.arrows = 0;
            }
        }

        /**
         * Increases or decreases player's gold.
         *
         * @param gold to add (or subtract) into (from) the backpack.
         */
        public void modifyGold(int gold) {
            this.gold += gold;

            if (this.gold < 0) {
                this.gold = 0;
            }
        }

        /**
         * Resets the items at the default values.
         */
        public void resetItems() {
            this.setArrows(DEFAULTARROWS);
            this.setGold(DEFAULTGOLD);
        }
    }
}

