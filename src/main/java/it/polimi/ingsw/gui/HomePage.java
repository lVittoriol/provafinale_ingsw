package it.polimi.ingsw.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.gui.components.JWoodButton;
import it.polimi.ingsw.gui.components.JWoodPanel;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.utils.GameTypeAdapter;
import it.polimi.ingsw.utils.message.LoadGameMessage;
import it.polimi.ingsw.utils.message.Message;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * The `HomePage` class represents the GUI page for the home screen. It displays the title, subtitle, and buttons for creating and joining a game.
 *
 * @author Marcelo S. Hernandez
 * @author Francesco Guarnello
 */
public class HomePage extends JWoodPanel {

    /**
     * Constructs a new `HomePage` object with the given `App` instance.
     *
     * @param app The `App` instance representing the application.
     */
    public HomePage(final App app) {
        setLayout(null);

        JLabel titleLabel = new JLabel("My Shelfie");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        addChild(this, titleLabel, 429, 318);

        JLabel subtitleLabel = new JLabel("Realizzato da Marcelo, Vittorio, Francesco ed Emanuele");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        addChild(this, subtitleLabel, 302, 365);

        JWoodButton createButton = new JWoodButton("Crea");
        createButton.setPreferredSize(new Dimension(128, 41));
        createButton.addActionListener(e -> app.showCreatePage());
        addChild(this, createButton, 376, 408);

        JWoodButton joinButton = new JWoodButton("Unisciti");
        joinButton.setPreferredSize(new Dimension(128, 41));
        joinButton.addActionListener(e -> app.showJoinPage());
        addChild(this, joinButton, 520, 408);

        JWoodButton loadButton = new JWoodButton("Carica");
        loadButton.setPreferredSize(new Dimension(128, 41));
        loadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(app);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.registerTypeAdapter(Game.class, new GameTypeAdapter());
                    Gson gson = gsonBuilder.create();
                    Game game = gson.fromJson(Files.readString(fileChooser.getSelectedFile().toPath(), StandardCharsets.UTF_8), Game.class);
                    String[] options = game.getPlayers().stream().map(Player::getUsername).toArray(String[]::new);

                    String selectedOption = (String) JOptionPane.showInputDialog(
                            app,
                            "Choose a username:",
                            "Load Game",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            options[0]);

                    try {
                        app.getClient().username = selectedOption;
                        Message message = app.getClient().loadGame(new LoadGameMessage("", game, fileChooser.getSelectedFile().getName().replace(".txt", ""), selectedOption));

                        if (message.getContent().equals("match already exists")) {
                            JOptionPane.showMessageDialog(app, "A match with the same ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        app.showGamePage();
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(app, "An error occurred while loading the game.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addChild(this, loadButton, 448, 679);
    }
}
