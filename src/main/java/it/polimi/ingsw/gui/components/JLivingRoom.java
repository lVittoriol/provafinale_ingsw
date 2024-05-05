package it.polimi.ingsw.gui.components;

import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.LivingRoom.Board;
import it.polimi.ingsw.utils.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A graphical component that displays the living room board.
 *
 * @author Marcelo S. Hernandez
 * @author Francesco Guarnello
 */
public class JLivingRoom extends JPanel {
    private final JItemTile[][] itemTiles = new JItemTile[9][9];
    private final ArrayList<JItemTile> selectedItemTiles = new ArrayList<>();
    private final Image image;
    private JItemTile middleItemTile;
    private Board board;

    /**
     * Constructs a JLivingRoom component.
     *
     * @param board the board to display
     */
    public JLivingRoom(Board board) {
        setPreferredSize(new Dimension(476, 476));
        setOpaque(false);
        setLayout(null);

        image = new ImageIcon("assets/boards/livingroom.png").getImage();

        JPanel grid = new JPanel(new GridLayout(9, 9, 3, 3));
        grid.setOpaque(false);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JItemTile itemTile = new JItemTile(board.getTile(i, j), new Position(i, j));

                itemTile.addActionListener(e -> {
                    if (itemTile.getItemTile().getType() != ItemTile.Type.EMPTY && itemTile.getItemTile().getType() != ItemTile.Type.BLOCKED) {
                        if (selectedItemTiles.size() == 0) {
                            selectedItemTiles.add(itemTile);
                            itemTile.setSelected(true);
                            itemTile.setIndex(0);
                        } else if (selectedItemTiles.size() == 1) {
                            if (selectedItemTiles.contains(itemTile)) {
                                selectedItemTiles.remove(itemTile);
                                itemTile.setSelected(false);
                                itemTile.removeIndex();
                            } else {
                                JItemTile startItemTile = selectedItemTiles.get(0);
                                JItemTile endItemTile = itemTile;
                                Position start = selectedItemTiles.get(0).getPosition();
                                Position end = itemTile.getPosition();
                                int distance = 999;

                                if (start.getRow() == end.getRow()) {
                                    distance = start.getColumn() - end.getColumn();

                                    if (distance == -2) {
                                        middleItemTile = itemTiles[start.getRow()][start.getColumn() + 1];
                                    } else if (distance == 2) {
                                        middleItemTile = itemTiles[start.getRow()][start.getColumn() - 1];
                                    }
                                } else if (start.getColumn() == end.getColumn()) {
                                    distance = start.getRow() - end.getRow();

                                    if (distance == -2) {
                                        middleItemTile = itemTiles[start.getRow() + 1][start.getColumn()];
                                    } else if (distance == 2) {
                                        middleItemTile = itemTiles[start.getRow() - 1][start.getColumn()];
                                    }
                                }

                                if (Math.abs(distance) <= 2) {
                                    if (Math.abs(distance) == 1) {
                                        if (distance < 0) {
                                            startItemTile.setIndex(0);
                                            endItemTile.setIndex(1);
                                        } else {
                                            startItemTile.setIndex(1);
                                            endItemTile.setIndex(0);
                                        }
                                    } else {
                                        if (distance < 0) {
                                            startItemTile.setIndex(0);
                                            middleItemTile.setIndex(1);
                                            endItemTile.setIndex(2);
                                        } else {
                                            startItemTile.setIndex(2);
                                            middleItemTile.setIndex(1);
                                            endItemTile.setIndex(0);
                                        }
                                    }

                                    selectedItemTiles.add(itemTile);
                                    itemTile.setSelected(true);
                                } else {
                                    clearSelectedItems();
                                    selectedItemTiles.add(itemTile);
                                    itemTile.setSelected(true);
                                    itemTile.setIndex(0);
                                }
                            }
                        } else {
                            if (selectedItemTiles.contains(itemTile)) {
                                selectedItemTiles.remove(itemTile);
                                itemTile.setSelected(false);
                                itemTile.removeIndex();

                                if (middleItemTile != null) {
                                    middleItemTile.removeIndex();
                                    middleItemTile = null;
                                }

                                selectedItemTiles.get(0).setIndex(0);
                            } else {
                                clearSelectedItems();
                                selectedItemTiles.add(itemTile);
                                itemTile.setSelected(true);
                                itemTile.setIndex(0);
                            }
                        }
                    }
                });

                itemTiles[i][j] = itemTile;
                grid.add(itemTiles[i][j]);
            }

            grid.setBounds(21, 23, 435, 433);
            add(grid);
        }

        this.board = board;
    }

    /**
     * Returns the board associated with this component.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets the board to be displayed and updates the item tiles accordingly.
     *
     * @param board the board to set
     */
    public void setBoard(Board board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                itemTiles[i][j].setItemTile(board.getTile(i, j));
            }
        }

        this.board = board;
        repaint();
    }

    /**
     * Returns the list of selected item tiles.
     *
     * @return the list of selected item tiles
     */
    public ArrayList<JItemTile> getSelectedItemTiles() {
        return selectedItemTiles;
    }

    /**
     * Clears the list of selected item tiles.
     */
    public void clearSelectedItems() {
        for (JItemTile itemTile : selectedItemTiles) {
            itemTile.removeIndex();
            itemTile.setSelected(false);
        }

        selectedItemTiles.clear();

        if (middleItemTile != null) {
            middleItemTile.removeIndex();
            middleItemTile = null;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
