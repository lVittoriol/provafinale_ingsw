package it.polimi.ingsw.gui;

import it.polimi.ingsw.gui.components.JWoodButton;
import it.polimi.ingsw.gui.components.JWoodPanel;
import it.polimi.ingsw.utils.message.Message;
import it.polimi.ingsw.utils.message.request.MatchJoinRequestMessage;
import it.polimi.ingsw.utils.message.request.NewUsernameMessage;
import it.polimi.ingsw.utils.message.response.UsernameNotAvailableMessage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The `JoinPage` class represents the GUI page for joining a game. It allows the user to enter a username and match ID to join a specific game.
 *
 * @author Marcelo S. Hernandez
 * @author Francesco Guarnello
 */
public class JoinPage extends JWoodPanel {
    private final JTextField usernameField;
    private final JTextField matchIdField;

    /**
     * Constructs a new `JoinPage` object with the given `App` instance.
     *
     * @param app The `App` instance representing the application.
     */
    public JoinPage(final App app) {
        setLayout(null);

        JLabel usernameLabel = new JLabel("Nome utente");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        addChild(this, usernameLabel, 376, 277);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(272, 41));
        addChild(this, usernameField, 376, 302);

        JLabel matchIdLabel = new JLabel("ID della partita");
        matchIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        addChild(this, matchIdLabel, 376, 359);

        matchIdField = new JTextField();
        matchIdField.setPreferredSize(new Dimension(272, 41));
        addChild(this, matchIdField, 376, 384);

        JWoodButton confirmButton = new JWoodButton("Conferma");
        confirmButton.setPreferredSize(new Dimension(128, 41));
        confirmButton.addActionListener(e -> joinGame(app));
        addChild(this, confirmButton, 520, 449);

        JWoodButton dismissButton = new JWoodButton("Indietro");
        dismissButton.setPreferredSize(new Dimension(128, 41));
        dismissButton.addActionListener(e -> app.showHomePage());
        addChild(this, dismissButton, 376, 449);
    }

    /**
     * Attempts to join the game using the entered username and match ID.
     *
     * @param app The `App` instance representing the application.
     */
    public void joinGame(final App app) {
        if (!isValidUsername()) {
            JOptionPane.showMessageDialog(this, "Please insert a valid username.", "Invalid username", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String username = usernameField.getText();
        String matchId = matchIdField.getText();

        try {
            Message message = app.getClient().joinMatch(new MatchJoinRequestMessage(username, matchId, ""));

            if (message.getContent().equals("match  not existing!")) {
                JOptionPane.showMessageDialog(this, "The match does not exist!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (message.getContent().equals("the match has already started!")) {
                JOptionPane.showMessageDialog(this, "The match has already started!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            while (message instanceof UsernameNotAvailableMessage) {
                username = JOptionPane.showInputDialog(this, "Username not available, please insert a new one.", "Username not available", JOptionPane.WARNING_MESSAGE);
                message = app.getClient().newUsername(new NewUsernameMessage(username, "new username"));
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while joining the match. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        app.showGamePage();
    }

    /**
     * Checks if the entered username is valid (not empty).
     *
     * @return `true` if the username is valid, `false` otherwise.
     */
    public boolean isValidUsername() {
        return !usernameField.getText().isEmpty();
    }
}
