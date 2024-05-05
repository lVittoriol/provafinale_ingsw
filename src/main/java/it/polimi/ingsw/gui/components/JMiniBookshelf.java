package it.polimi.ingsw.gui.components;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.utils.Position;

import javax.swing.*;
import java.awt.*;

/**
 * A graphical component that displays a mini version of the bookshelf.
 *
 * @author Marcelo S. Hernandez
 */
public class JMiniBookshelf extends JPanel {
    private final JItemTile[][] itemTiles = new JItemTile[6][5];
    private final Image image;
    private Bookshelf bookshelf;

    /**
     * Constructs a JMiniBookshelf component.
     *
     * @param bookshelf the bookshelf to display
     */
    public JMiniBookshelf(Bookshelf bookshelf) {
        setPreferredSize(new Dimension(150, 150));
        setOpaque(false);
        setLayout(null);

        image = new ImageIcon("assets/boards/bookshelf_orth.png").getImage();

        JPanel grid = new JPanel(new GridLayout(6, 5, 6, 3));
        grid.setOpaque(false);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                itemTiles[i][j] = new JItemTile(bookshelf.getItemTile(i, j), new Position(i, j));
                grid.add(itemTiles[i][j]);
            }
        }

        grid.setBounds(18, 10, 114, 123);
        add(grid);

        this.bookshelf = new Bookshelf(bookshelf);
    }

    /**
     * Returns the bookshelf associated with this component.
     *
     * @return the bookshelf
     */
    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    /**
     * Sets the bookshelf to be displayed and updates the item tiles accordingly.
     *
     * @param bookshelf the bookshelf to set
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
