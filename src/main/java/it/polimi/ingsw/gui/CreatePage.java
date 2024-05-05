package it.polimi.ingsw.gui;

import it.polimi.ingsw.gui.components.JWoodButton;
import it.polimi.ingsw.gui.components.JWoodPanel;
import it.polimi.ingsw.utils.message.Message;
import it.polimi.ingsw.utils.message.request.MatchCreateRequestMessage;
import it.polimi.ingsw.utils.message.request.NewUsernameMessage;
import it.polimi.ingsw.utils.message.response.UsernameNotAvailableMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

/**
 * The create game page of the "My Shelfie" game GUI.
 *
 * @author Marcelo S. Hernandez
 * @author Francesco Guarnello
 */
public class CreatePage extends JWoodPanel {
    private final JTextField usernameField;
    private final JTextField playerCountField;

    /**
     * Constructs an instance of the CreatePage class.
     *
     * @param app the main application instance
     */
    public CreatePage(final App app) {
        setLayout(null);

        JLabel usernameLabel = new JLabel("Nome utente");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        addChild(this, usernameLabel, 376, 277);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(272, 41));
        addChild(this, usernameField, 376, 302);

        JLabel playerCountLabel = new JLabel("Numero di giocatori");
        playerCountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        addChild(this, playerCountLabel, 376, 359);

        playerCountField = new JTextField();
        playerCountField.setPreferredSize(new Dimension(272, 41));
        addChild(this, playerCountField, 376, 384);

        JWoodButton confirmButton = new JWoodButton("Conferma");
        confirmButton.setPreferredSize(new Dimension(128, 41));
        confirmButton.addActionListener(e -> createGame(app));
        addChild(this, confirmButton, 520, 449);

        JWoodButton dismissButton = new JWoodButton("Indietro");
        dismissButton.setPreferredSize(new Dimension(128, 41));
        dismissButton.addActionListener(e -> app.showHomePage());
        addChild(this, dismissButton, 376, 449);
    }

    /**
     * Creates a new game when the confirm button is clicked.
     *
     * @param app the main application instance
     */
    public void createGame(final App app) {
        if (!isValidUsername()) {
            JOptionPane.showMessageDialog(this, "Please insert a valid username.", "Invalid username", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!isValidPlayerCount()) {
            JOptionPane.showMessageDialog(this, "Please insert a number between 2 and 4.", "Invalid number of players", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String username = usernameField.getText();
        int playerCount = Integer.parseInt(playerCountField.getText());

        try {
            Message message = app.getClient().createMatch(new MatchCreateRequestMessage(playerCount, username, ""));

            while (message instanceof UsernameNotAvailableMessage) {
                username = JOptionPane.showInputDialog(this, "Username not available, please insert a new one.", "Username not available", JOptionPane.WARNING_MESSAGE);
                message = app.getClient().newUsername(new NewUsernameMessage(username, "new username"));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while creating match. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringSelection selection = new StringSelection(app.getClient().matchId);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        JOptionPane.showMessageDialog(this, "Match ID copied to clipboard.", "Match created successfully", JOptionPane.INFORMATION_MESSAGE);

        app.showGamePage();
    }

    /**
     * Checks if the username is valid (not empty).
     *
     * @return true if the username is valid, false otherwise
     */
    public boolean isValidUsername() {
        return !usernameField.getText().isEmpty();
    }

    /**
     * Checks if the player count is valid (between 2 and 4).
     *
     * @return true if the player count is valid, false otherwise
     */
    public boolean isValidPlayerCount() {
        try {
            int playerCount = Integer.parseInt(playerCountField.getText());
            return playerCount >= 2 && playerCount <= 4;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
