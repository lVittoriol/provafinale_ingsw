package it.polimi.ingsw.gui.components;

import it.polimi.ingsw.model.commongoalcard.CommonGoalCard;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * A graphical component that displays a common goal card.
 *
 * @author Marcelo S. Hernandez
 */
public class JCommonGoalCard extends JButton {
    private CommonGoalCard commonGoalCard;
    private boolean isFaceUp;
    private Image image;

    /**
     * Constructs a JCommonGoalCard component.
     */
    public JCommonGoalCard() {
        setPreferredSize(new Dimension(157, 106));
        setOpaque(false);
        setBorder(null);
        setBackground(new Color(0, 0, 0, 0));
        setBorderPainted(false);
        setFocusPainted(false);
        setUI(new BasicButtonUI());

        addActionListener(e -> {
            if (commonGoalCard != null) {
                JOptionPane.showMessageDialog(this, commonGoalCard.getDescription(), "Common Goal Card #" + commonGoalCard.getNumber(), JOptionPane.INFORMATION_MESSAGE);
            }
        });

        this.isFaceUp = false;
    }

    /**
     * Returns the common goal card associated with this component.
     *
     * @return the common goal card
     */
    public CommonGoalCard getCommonGoalCard() {
        return commonGoalCard;
    }

    /**
     * Sets the common goal card to be displayed and updates the image accordingly.
     *
     * @param commonGoalCard the common goal card to set
     */
    public void setCommonGoalCard(CommonGoalCard commonGoalCard) {
        String filename = "assets/common goal cards/";

        switch (commonGoalCard.getNumber()) {
            case 1 -> filename += "4";
            case 2 -> filename += "11";
            case 3 -> filename += "8";
            case 4 -> filename += "7";
            case 5 -> filename += "3";
            case 6 -> filename += "2";
            case 7 -> filename += "1";
            case 8 -> filename += "6";
            case 9 -> filename += "5";
            case 10 -> filename += "10";
            case 11 -> filename += "9";
            case 12 -> filename += "12";
        }

        filename += ".jpg";

        this.image = new ImageIcon(filename).getImage();
        this.commonGoalCard = commonGoalCard;

        repaint();
    }

    /**
     * Checks if the common goal card is face up.
     *
     * @return true if the common goal card is face up, false otherwise
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Sets the face-up state of the common goal card.
     *
     * @param faceUp true to set the common goal card face up, false to set it face down
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
            g.drawImage(new ImageIcon("assets/common goal cards/back.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    }
}
