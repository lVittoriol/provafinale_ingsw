package it.polimi.ingsw.gui.components;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.utils.Position;

import javax.swing.*;
import java.awt.*;

/**
 * A graphical component that displays a bookshelf on the user interface.
 *
 * @author Marcelo S. Hernandez
 */
public class JBookshelf extends JPanel {
    private final JItemTile[][] itemTiles = new JItemTile[6][5];
    private final Image image;
    private Bookshelf bookshelf;

    /**
     * Constructs a JBookshelf component with the specified bookshelf.
     *
     * @param bookshelf the bookshelf to be displayed
     */
    public JBookshelf(Bookshelf bookshelf) {
        setPreferredSize(new Dimension(256, 256));
        setOpaque(false);
        setLayout(null);

        image = new ImageIcon("assets/boards/bookshelf_orth.png").getImage();

        JPanel grid = new JPanel(new GridLayout(6, 5, 11, 6));
        grid.setOpaque(false);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                itemTiles[i][j] = new JItemTile(bookshelf.getItemTile(i, j), new Position(i, j));
                grid.add(itemTiles[i][j]);
            }
        }

        grid.setBounds(31, 17, 194, 210);
        add(grid);

        this.bookshelf = new Bookshelf(bookshelf);
    }

    /**
     * Returns the bookshelf associated with this JBookshelf component.
     *
     * @return the bookshelf
     */
    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    /**
     * Sets the bookshelf to be displayed by this JBookshelf component.
     *
     * @param bookshelf the bookshelf to be set
     */
    public void setBookshelf(Bookshelf bookshelf) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                itemTiles[i][j].setItemTile(bookshelf.getItemTile(i, j));
            }
        }

        this.bookshelf = new Bookshelf(bookshelf);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}