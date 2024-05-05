package it.polimi.ingsw.gui;

import it.polimi.ingsw.gui.components.JWoodButton;
import it.polimi.ingsw.gui.components.JWoodPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The `ResultsPage` class represents the GUI page displayed when the game ends.
 *
 * @author Marcelo S. Hernandez
 */
public class ResultsPage extends JWoodPanel {

    /**
     * Constructs a new `ResultsPage` object with the given parameters.
     *
     * @param app                The `App` instance representing the application.
     * @param personalGoalPoints The number of points earned from personal goals
     * @param commonGoalsPoints  The number of points earned from common goals
     * @param adjacencyPoints    The number of points earned from adjacent tiles
     * @param bonusPoints        the points earned from filling the bookshelf first
     * @param hasWon             `true` if the player has won the game, `false` otherwise
     */
    public ResultsPage(final App app, int personalGoalPoints, int commonGoalsPoints, int adjacencyPoints, int bonusPoints, boolean hasWon) {
        setLayout(null);

        JLabel titleLabel = new JLabel(hasWon ? "Congratulazioni, hai vinto!" : "Mi dispiace, hai perso!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        addChild(this, titleLabel, 307, 223);

        JLabel bodyLabel = new JLabel("<html>Punti obiettivo personale: " + personalGoalPoints + "<br>Punti obiettivi comuni: " + commonGoalsPoints + "<br>Punti tessere adiacenti: " + adjacencyPoints + "<br>Punti bonus: " + bonusPoints + "<br>Punti totali: " + (personalGoalPoints + commonGoalsPoints + adjacencyPoints + bonusPoints) + "</html>");
        bodyLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        addChild(this, bodyLabel, 307, 294);

        JWoodButton exitButton = new JWoodButton("Esci");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        exitButton.setPreferredSize(new Dimension(128, 41));
        exitButton.addActionListener(e -> {
            app.dispose();
            System.exit(0);
        });
        addChild(this, exitButton, 449, 503);
    }
}
