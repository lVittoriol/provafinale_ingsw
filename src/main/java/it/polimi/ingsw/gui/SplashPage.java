package it.polimi.ingsw.gui;

import it.polimi.ingsw.gui.components.JWoodPanel;

import javax.swing.*;
import java.awt.*;

public class SplashPage extends JWoodPanel {
    public SplashPage() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Connessione al server in corso...", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        add(label, BorderLayout.CENTER);
    }
}
