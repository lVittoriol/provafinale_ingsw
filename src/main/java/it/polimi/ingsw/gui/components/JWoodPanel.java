package it.polimi.ingsw.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * A customized panel component with a wooden texture background.
 *
 * @author Marcelo S. Hernandez
 * @author Francesco Guarnello
 */
public class JWoodPanel extends JPanel {

    /**
     * Constructs a JWoodPanel with a preferred size of 1024x768 pixels.
     */
    public JWoodPanel() {
        super();
        setPreferredSize(new Dimension(1024, 768));
    }

    /**
     * Adds a child component to the parent panel at the specified coordinates.
     *
     * @param parent the parent panel to which the child component will be added
     * @param child  the child component to be added
     * @param x      the x-coordinate of the child component's position
     * @param y      the y-coordinate of the child component's position
     */
    public static void addChild(JPanel parent, JComponent child, int x, int y) {
        child.setBounds(x, y, child.getPreferredSize().width, child.getPreferredSize().height);
        parent.add(child);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon("assets/misc/sfondo parquet.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
