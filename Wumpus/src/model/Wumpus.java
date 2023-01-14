package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * Class that represents the wumpus.
 *
 * @author Gatico Alessandro
 * @see Character
 */
public class Wumpus extends Character {

    private final PropertyChangeSupport support;


    /**
     * Calls the parent constructor.
     */
    Wumpus() {
        super();
        this.support = new PropertyChangeSupport(this);
    }


    /**
     * Adds the listener to the PropertyChangeSupport.
     *
     * @param listener to add.
     */
    void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }


    @Override
    public void move(Directions direction, boolean random) {
        /*  IT DOESN'T MOVE */
    }


    @Override
    public int react() {
        this.support.firePropertyChange("gameOverWumpus", true, false);
        return 0;
    }


    @Override
    public void killed() {
        this.support.firePropertyChange("win", 0, 1);
    }
}
