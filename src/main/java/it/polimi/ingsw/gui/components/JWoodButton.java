package it.polimi.ingsw.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * A customized button component with a wooden texture background.
 *
 * @author Marcelo S. Hernandez
 * @author Francesco Guarnello
 */
public class JWoodButton extends JButton {

    /**
     * Constructs a JWoodButton with the specified text.
     *
     * @param text the text to display on the button
     */
    public JWoodButton(String text) {
        super(text);
        setPreferredSize(new Dimension(256, 41));
        setIcon(new ImageIcon("assets/misc/base_pagina2.jpg"));
        setFont(new Font("Arial", Font.BOLD, 14));
        setHorizontalTextPosition(SwingConstants.CENTER);
        setForeground(Color.WHITE);
    }
}
