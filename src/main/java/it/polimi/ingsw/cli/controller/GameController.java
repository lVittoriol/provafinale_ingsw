package it.polimi.ingsw.cli.controller;

import it.polimi.ingsw.cli.view.GameView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.commongoalcard.CommonGoalCard;
import it.polimi.ingsw.model.exceptions.FullColumnException;
import it.polimi.ingsw.model.exceptions.NotValidPickException;
import it.polimi.ingsw.model.exceptions.TrappedCardException;
import it.polimi.ingsw.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The `GameController` class is responsible for controlling the interactions between the `Game` model
 * and the `GameView` view in the Command Line Interface (CLI).
 *
 * @author Vittorio La Rosa
 * @author Francesco Guarnello
 */
public class GameController {
    private final GameView view;
    private Game model;

    /**
     * Constructs a new `GameController` object with the given model and view.
     *
     * @param model The `Game` model.
     * @param view  The `GameView` view.
     */
    public GameController(Game model, GameView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Returns the current model of the game.
     *
     * @return The current `Game` model.
     */
    public Game getModel() {
        return model;
    }

    /**
     * Sets the model of the game.
     *
     * @param model The `Game` model to set.
     */
    public void setModel(Game model) {
        this.model = model;
    }

    /**
     * Checks if the game has ended.
     *
     * @return `true` if the game has ended, `false` otherwise.
     */
    public boolean hasEnded() {
        return model.hasEnded();
    }

    /**
     * Places the picked tiles in the specified column.
     *
     * @param pickedTiles The list of `ItemTile` to be placed.
     * @param column      The column index where the tiles will be placed.
     * @throws FullColumnException If the column is already full.
     */
    public void tilesInColumn(List<ItemTile> pickedTiles, int column) throws FullColumnException {
        model.tilesInColumn(pickedTiles, column);
    }

    /**
     * Checks the personal goal card of the specified player.
     *
     * @param player The `Player` for whom to check the personal goal card.
     */
    public void checkPersonalGoalCard(Player player) {
        model.checkPersonalGoalCard(player);
    }

    /**
     * Returns the points scored by the common goal cards.
     *
     * @return The list of points scored by the common goal cards.
     */
    public List<Integer> pointsCommonGoalCards() {
        return model.pointsCommonGoalCards();
    }

    /**
     * Checks the living room and performs the corresponding actions (Checks the living room to see if it needs a reset).
     */
    public void checkLivingRoom() {
        model.checkLivingRoom();
    }

    /**
     * Checks the winners of the game.
     *
     * @return The list of winning players.
     */
    public List<Player> checkWinners() {
        return model.checkWinners();
    }

    /**
     * Picks the tiles from the specified start position to the end position.
     *
     * @param start The starting position for tile picking.
     * @param end   The ending position for tile picking.
     * @return The list of picked `ItemTile`.
     * @throws TrappedCardException  If a trapped card is encountered during tile picking.
     * @throws NotValidPickException If the picked tiles do not form a valid set.
     */
    public ArrayList<ItemTile> pickTiles(Position start, Position end) throws TrappedCardException, NotValidPickException {
        return model.pickTiles(start, end);
    }

    /**
     * Returns the current player in the game.
     *
     * @return The current `Player`.
     */
    public Player getCurrentPlayer() {
        return model.getCurrentPlayer();
    }

    /**
     * Returns the list of players in the game.
     *
     * @return The list of `Player`.
     */
    public List<Player> getPlayers() {
        return model.getPlayers();
    }

    /**
     * Updates the view by printing the current state of the game model.
     */
    public void updateView() {
        view.printGame(model);
    }

    /**
     * Checks for adjacent item tiles for the specified player.
     *
     * @param player The `Player` for whom to check adjacent item tiles.
     */
    public void checkAdjacentItemTiles(Player player) {
        Game.checkAdjacentItemTiles(player);
    }

    /**
     * Returns the mapping of common goal cards to the list of winners.
     *
     * @return The mapping of `CommonGoalCard` to the list of winning players.
     */
    public Map<CommonGoalCard, List<Player>> getCommonGoalCardsToWinners() {
        return model.getCommonGoalCardToWinners();
    }

    /**
     * Assigns additional points to the specified player.
     *
     * @param player The `Player` to whom additional points will be assigned.
     */
    public void assignAdditionalPoint(Player player) {
        model.assignAdditionalPoint(player);
    }

    /**
     * Orders the picked tiles according to the specified ordering.
     *
     * @param pickedTiles   The list of picked `ItemTile`.
     * @param orderingTiles The list of integers specifying the desired ordering.
     * @return The ordered list of `ItemTile`.
     */
    public List<ItemTile> orderTiles(List<ItemTile> pickedTiles, List<Integer> orderingTiles) {
        return model.orderTiles(pickedTiles, orderingTiles);
    }

    /**
     * Passes the turn to the next player in the game.
     */
    public void passTurn() {
        model.passTurn();
    }


}
