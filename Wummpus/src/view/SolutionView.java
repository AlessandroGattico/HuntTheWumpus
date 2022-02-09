package view;

import model.World;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Class that represents the solution view with all the icons displayed.
 *
 * @author Gattico Alessandro
 * @see MapView
 * @see World
 */
public class SolutionView extends MapView {

    /**
     * Call to the parent constructor and sets its visibility to false.
     *
     * @param dimension map's dimension.
     */
    public SolutionView(int dimension) {
        super(dimension);

        this.setVisible(false);
    }

    /**
     * Displays the icons on the map.
     *
     * @param world to get the icons' positions from.
     */
    private void showIcons(World world) {
        if (world.isGameOn()){
            for (int i = 0; i < this.dimension; i++) {
                for (int j = 0; j < this.dimension; j++) {
                    this.mapContent[i][j].setBackground(Color.WHITE);
                    switch (world.getMap()[i][j].getItems()) {
                        case ARROW:
                            this.mapContent[i][j]
                                    .setIcon(new ImageIcon(
                                            Objects.requireNonNull(getClass().getResource("/icons/arrow.png"))));
                            break;
                        case GOLD:
                            this.mapContent[i][j]
                                    .setIcon(new ImageIcon(
                                            Objects.requireNonNull(getClass().getResource("/icons/gold.png"))));
                            break;
                        case BABYWUMPUS:
                            this.mapContent[i][j]
                                    .setIcon(new ImageIcon(
                                            Objects.requireNonNull(getClass().getResource("/icons/babyWumpus.png"))));
                            break;
                        case PIT:
                            this.mapContent[i][j]
                                    .setIcon(new ImageIcon(
                                            Objects.requireNonNull(getClass().getResource("/icons/pit.png"))));
                            break;
                        case SURVIVOR:
                            this.mapContent[i][j]
                                    .setIcon(new ImageIcon(
                                            Objects.requireNonNull(getClass().getResource("/icons/survivor.png"))));
                            break;
                        case WUMPUS:
                            this.mapContent[i][j]
                                    .setIcon(new ImageIcon(
                                            Objects.requireNonNull(getClass().getResource("/icons/wumpus.png"))));
                            break;
                        default:
                            this.mapContent[i][j].setIcon(null);
                            break;
                    }
                    if (world.getMap()[i][j].getPlayer()) {
                        switch (world.getPlayer().getFacing()) {
                            case NORTH:
                                this.mapContent[i][j]
                                        .setIcon(new ImageIcon(
                                                Objects.requireNonNull(getClass().getResource("/icons/playerNorth.png"))));
                                break;
                            case EAST:
                                this.mapContent[i][j]
                                        .setIcon(new ImageIcon(
                                                Objects.requireNonNull(getClass().getResource("/icons/playerEast.png"))));
                                break;
                            case SOUTH:
                                this.mapContent[i][j]
                                        .setIcon(new ImageIcon(
                                                Objects.requireNonNull(getClass().getResource("/icons/playerSouth.png"))));
                                break;
                            case WEST:
                                this.mapContent[i][j]
                                        .setIcon(new ImageIcon(
                                                Objects.requireNonNull(getClass().getResource("/icons/playerWest.png"))));
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Updates the view with the new positions of the icons.
     *
     * @param world to take the positions from.
     */
    public void updateView(World world) {
        this.showIcons(world);
    }

    /**
     * Resets the view.
     */
    public void resetSolution() {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.mapContent[i][j].setIcon(null);
            }
        }
    }
}
