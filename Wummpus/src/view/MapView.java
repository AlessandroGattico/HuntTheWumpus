package view;

import model.Player;
import model.Room;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Class that represents the view of the game map.
 *
 * @author Gattico Alessandro
 * @see JPanel
 * @see Player
 * @see Room
 */
public class MapView extends JPanel {
    protected JLabel[][] mapContent;
    protected int dimension;

    /**
     * Creates the view and sets all its cells black.
     *
     * @param dimension map's dimension.
     */
    public MapView(int dimension) {
        super(new GridLayout(dimension, dimension));
        this.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.WHITE));
        this.dimension = dimension;
        this.mapContent = new JLabel[this.dimension][this.dimension];

        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.mapContent[i][j] = new JLabel(null, null, JLabel.CENTER);
                this.mapContent[i][j].setBackground(Color.BLACK);
                this.mapContent[i][j].setOpaque(true);
                this.mapContent[i][j].setBorder(BorderFactory.createLineBorder(Color.red, 1));
                this.add(mapContent[i][j]);
            }
        }

        this.setVisible(true);
    }

    /**
     * Updates the view with the new player's room and sets the steps icon in the old one.
     *
     * @param player player.
     * @param oldRoom old player's room.
     */
    public void updateView(Player player, Room oldRoom) {
        if (player.isPlaying()){
            this.mapContent[oldRoom.getY()][oldRoom.getX()].setBackground(Color.WHITE);
            this.mapContent[oldRoom.getY()][oldRoom.getX()].setIcon(new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/icons/steps.png"))));
            this.mapContent[player.getRoom().getY()][player.getRoom().getX()].setBackground(Color.WHITE);

            switch (player.getFacing()) {
                case NORTH:
                    this.mapContent[player.getRoom().getY()][player.getRoom().getX()]
                            .setIcon(new ImageIcon(
                                    Objects.requireNonNull(getClass().getResource("/icons/playerNorth.png"))));
                    break;
                case EAST:
                    this.mapContent[player.getRoom().getY()][player.getRoom().getX()]
                            .setIcon(
                                    new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/playerEast.png"))));
                    break;
                case SOUTH:
                    this.mapContent[player.getRoom().getY()][player.getRoom().getX()]
                            .setIcon(new ImageIcon(
                                    Objects.requireNonNull(getClass().getResource("/icons/playerSouth.png"))));
                    break;
                case WEST:
                    this.mapContent[player.getRoom().getY()][player.getRoom().getX()]
                            .setIcon(
                                    new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/playerWest.png"))));
                    break;
                default:
                    break;
            }

        }
    }


    /**
     * Updates the view with the new player's facing direction.
     *
     * @param player player.
     */
    public void updateView(Player player) {
        if (player.isPlaying()) {
            this.mapContent[player.getRoom().getY()][player.getRoom().getX()].setBackground(Color.WHITE);

            switch (player.getFacing()) {
                case NORTH:
                    this.mapContent[player.getRoom().getY()][player.getRoom().getX()]
                            .setIcon(new ImageIcon(
                                    Objects.requireNonNull(getClass().getResource("/icons/playerNorth.png"))));
                    break;
                case EAST:
                    this.mapContent[player.getRoom().getY()][player.getRoom().getX()]
                            .setIcon(
                                    new ImageIcon(
                                            Objects.requireNonNull(getClass().getResource("/icons/playerEast.png"))));
                    break;
                case SOUTH:
                    this.mapContent[player.getRoom().getY()][player.getRoom().getX()]
                            .setIcon(new ImageIcon(
                                    Objects.requireNonNull(getClass().getResource("/icons/playerSouth.png"))));
                    break;
                case WEST:
                    this.mapContent[player.getRoom().getY()][player.getRoom().getX()]
                            .setIcon(
                                    new ImageIcon(
                                            Objects.requireNonNull(getClass().getResource("/icons/playerWest.png"))));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Resets the view.
     */
    protected void resetView() {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.mapContent[i][j].setBackground(Color.BLACK);
                this.mapContent[i][j].setIcon(null);
            }
        }
    }
}
