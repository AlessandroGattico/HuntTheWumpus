package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Class that represents the pit.
 *
 * @author Gattico Alessandro
 * @see Character
 */
public class Pit extends Character {

    private final PropertyChangeSupport support;

    /**
     * Call to the parent constructor.
     */
    Pit() {
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
        /* IT DOESN'T MOVE */
    }

    @Override
    public int react() {
        this.support.firePropertyChange("gameOverPit", true, false);
        return 0;
    }

    @Override
    public void killed() {
    }
}
