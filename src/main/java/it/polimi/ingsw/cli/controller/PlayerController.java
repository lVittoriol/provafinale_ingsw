package it.polimi.ingsw.cli.controller;

import it.polimi.ingsw.cli.view.PlayerView;
import it.polimi.ingsw.model.Player;

/**
 * The `PlayerController` class is responsible for controlling the interactions between the `Player` model
 * and the `PlayerView` view in the Command Line Interface (CLI).
 *
 * @author Vittorio La Rosa
 * @author Francesco Guarnello
 */
public class PlayerController {
    private final Player model;
    private final PlayerView view;

    /**
     * Constructs a new `PlayerController` object with the given model and view.
     *
     * @param model The `Player` model.
     * @param view  The `PlayerView` view.
     */
    public PlayerController(Player model, PlayerView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Updates the view by printing the current state of the player model.
     */
    public void updateView() {
        view.printPlayer(model);
    }

    /**
     * Prints the personal goal of the player with the provided username and updates the view.
     *
     * @param player     The player whose personal goal is to be printed.
     * @param myUsername The username of the current player.
     */
    public void printPersonaGoal(Player player, String myUsername) {
        view.printPersonalGoal(player, myUsername);
        updateView();
    }
}
