package it.polimi.ingsw.gui.components;

import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.ItemTile.Type;
import it.polimi.ingsw.utils.Position;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.Objects;

/**
 * A graphical component that displays an item tile.
 *
 * @author Marcelo S. Hernandez
 * @author Francesco Guarnello
 */
public class JItemTile extends JButton {
    private ItemTile itemTile;
    private Position position;
    private boolean isSelected;
    private int index;
    private Image image;

    /**
     * Constructs a JItemTile component.
     *
     * @param itemTile the item tile to display
     * @param position the position of the item tile
     */
    public JItemTile(ItemTile itemTile, Position position) {
        setOpaque(false);
        setBorder(null);
        setBackground(new Color(0, 0, 0, 0));
        setBorderPainted(false);
        setFocusPainted(false);
        setUI(new BasicButtonUI());
        setItemTile(itemTile);

        this.position = new Position(position);
        this.isSelected = false;
        this.index = -1;
    }

    /**
     * Returns the item tile associated with this component.
     *
     * @return the item tile
     */
    public ItemTile getItemTile() {
        return itemTile;
    }

    /**
     * Sets the item tile to be displayed and updates the image accordingly.
     *
     * @param itemTile the item tile to set
     */
    public void setItemTile(ItemTile itemTile) {
        boolean isDifferent = this.itemTile != null && !this.itemTile.equals(itemTile);
        boolean hasImage = itemTile.getType() != Type.EMPTY && itemTile.getType() != Type.BLOCKED;

        if ((this.itemTile == null || isDifferent) && hasImage) {
            String filename = "assets/item tiles/";

            switch (itemTile.getType()) {
                case FRAME -> filename += "Cornici";
                case CAT -> filename += "Gatti";
                case GAME -> filename += "Giochi";
                case BOOK -> filename += "Libri";
                case PLANT -> filename += "Piante";
                case TROPHY -> filename += "Trofei";
            }

            filename += "1." + ((int) (Math.random() * 3) + 1) + ".png";
            image = new ImageIcon(filename).getImage();
        } else if (Objects.equals(itemTile.getType(), Type.EMPTY) || Objects.equals(itemTile.getType(), Type.BLOCKED)) {
            image = null;
        }

        this.itemTile = new ItemTile(itemTile);
        repaint();
    }

    /**
     * Returns the position of the item tile.
     *
     * @return the position of the item tile
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position of the item tile.
     *
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Checks if the item tile is selected.
     *
     * @return true if the item tile is selected, false otherwise
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Sets the selected state of the item tile.
     *
     * @param selected true to set the item tile as selected, false to set it as not selected
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
        repaint();
    }

    /**
     * Sets the index of the item tile.
     *
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
        repaint();
    }

    /**
     * Removes the index of the item tile.
     */
    public void removeIndex() {
        this.index = -1;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }

        if (isSelected) {
            g.setColor(new Color(0, 0, 255, 75));
            g.fillRect(0, 0, getWidth(), getHeight());
            setBorderPainted(true);
            setBorder(new LineBorder(Color.BLUE, 2));
        } else if (index != -1) {
            g.setColor(new Color(0, 255, 0, 75));
            g.fillRect(0, 0, getWidth(), getHeight());
            setBorderPainted(true);
            setBorder(new LineBorder(Color.GREEN, 2));
        } else {
            setBorderPainted(false);
            setBorder(null);
        }

        if (index != -1) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString(String.valueOf(index), 10, getHeight() / 2 + g.getFontMetrics().getAscent() / 2);
        }
    }
}
