package it.polimi.ingsw.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * A graphical component that displays a personal goal card.
 *
 * @author Marcelo S. Hernandez
 */

public class JPersonalGoalCard extends JPanel {
    private int number;
    private boolean isFaceUp;
    private Image image;

    /**
     * Constructs a JPersonalGoalCard component.
     */
    public JPersonalGoalCard() {
        setPreferredSize(new Dimension(151, 228));
        setOpaque(false);

        isFaceUp = false;
    }

    /**
     * Returns the number of the personal goal card.
     *
     * @return the card number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the number of the personal goal card and updates the image accordingly.
     *
     * @param number the card number to set
     */
    public void setNumber(int number) {
        String filename = "assets/personal goal cards/Personal_Goals";
        filename += (number != 1) ? number : "";
        filename += ".png";

        this.image = new ImageIcon(filename).getImage();
        this.number = number;

        repaint();
    }

    /**
     * Returns whether the personal goal card is face up or not.
     *
     * @return true if the card is face up, false otherwise
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Sets whether the personal goal card is face up or not and repaints the component.
     *
     * @param faceUp true to set the card face up, false to set it face down
     */
    public void setFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isFaceUp && image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.drawImage(new ImageIcon("assets/personal goal cards/back.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    }
}
