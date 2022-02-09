package view;

import model.Room;
import model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Class that represents the game's view.
 *
 * @author Gattico Alessandro
 * @see Frame
 * @see FocusListener
 * @see GridBagLayout
 * @see Room
 * @see World
 */
public class HuntTheWumpusView extends JFrame {
    private MapView mapView;
    private SolutionView solutionView;
    private ControlView controlView;
    private ImageIcon icon;
    private World world;

    /**
     * Creates the elements of the view.
     *
     * @param world to get the elements from.
     */
    public HuntTheWumpusView(World world) {
        super("Hunt the wumpus");
        this.world = world;
        this.initializeComponents();
        this.setFocusable(true);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                e.getComponent().requestFocus();
            }
        });
        this.setVisible(true);
    }

    /**
     * Sets in place the components of the view.
     */
    private void initializeComponents() {
        this.setSize(1200, 800);
        this.setMinimumSize(new Dimension(1000, 650));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.mapView = new MapView(this.world.getDimension());
        this.solutionView = new SolutionView(this.world.getDimension());
        this.controlView = new ControlView();

        GridBagConstraints gridBag = new GridBagConstraints();

        gridBag.weightx = 1;
        gridBag.weighty = 5;

        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.fill = GridBagConstraints.BOTH;
        this.add(this.mapView, gridBag);

        gridBag.gridx = 1;
        gridBag.gridy = 0;
        gridBag.fill = GridBagConstraints.BOTH;
        this.add(this.solutionView, gridBag);

        gridBag.weightx = 0.5;
        gridBag.weighty = 0.5;
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 2;
        gridBag.fill = GridBagConstraints.BOTH;
        this.add(this.controlView, gridBag);
    }

    /**
     * Returns the control view.
     *
     * @return {@link ControlView control view}.
     */
    public ControlView getControlView() {
        return this.controlView;
    }

    /**
     * Returns the solution view.
     *
     * @return {@link SolutionView solution vew}.
     */
    public SolutionView getSolutionView() {
        return this.solutionView;
    }

    /**
     * Updates the views after the player's move.
     *
     * @param oldRoom previous player's room.
     */
    public void updateViews(Room oldRoom) {
        this.mapView.updateView(this.world.getPlayer(), oldRoom);
        this.solutionView.updateView(this.world);
        this.controlView.updateView(this.world);
    }

    /**
     * Updates the views.
     */
    public void updateViews() {
        this.mapView.updateView(this.world.getPlayer());
        this.solutionView.updateView(this.world);
        this.controlView.updateView(this.world);
    }

    /**
     * Resets the views.
     */
    public void resetViews() {
        this.controlView.resetView();
        this.mapView.resetView();
        this.solutionView.resetSolution();
    }
}
