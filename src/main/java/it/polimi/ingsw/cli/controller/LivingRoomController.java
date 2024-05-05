package it.polimi.ingsw.cli.controller;

import it.polimi.ingsw.cli.view.LivingRoomView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.LivingRoom;

/**
 * The `LivingRoomController` class is responsible for controlling the interactions between the `LivingRoom` model
 * and the `LivingRoomView` view in the Command Line Interface (CLI).
 *
 * @author Vittorio La Rosa
 */
public class LivingRoomController {
    private final LivingRoom model;
    private final LivingRoomView view;

    /**
     * Constructs a new `LivingRoomController` object with the given model and view.
     *
     * @param model The `LivingRoom` model.
     * @param view  The `LivingRoomView` view.
     */
    public LivingRoomController(LivingRoom model, LivingRoomView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Updates the view by printing the current state of the living room model.
     */
    public void updateView() {
        view.printLivingRoom(model);
    }

    /**
     * Initializes the living room controller by printing the common goal cards and updating the view.
     *
     * @param game The `Game` model containing the common goal cards.
     */
    public void initialize(Game game) {
        view.printCommonGoalCards(game);
        updateView();
    }


}
