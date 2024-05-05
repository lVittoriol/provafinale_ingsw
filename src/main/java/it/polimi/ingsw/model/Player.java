package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exceptions.FullColumnException;
import it.polimi.ingsw.model.exceptions.NotValidPickException;
import it.polimi.ingsw.model.exceptions.TrappedCardException;
import it.polimi.ingsw.utils.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game.
 * Each player has a username, a bookshelf to place tiles, a score, and a personal goal card.
 *
 * @author Vittorio La Rosa
 */
public class Player implements Serializable {
    private final String username;
    private final Bookshelf shelf;
    private final PersonalGoalCard personalGoalCard;
    private int score;

    public Player(String username, Bookshelf shelf, PersonalGoalCard personalGoalCard) {
        this.username = username;
        this.shelf = shelf;
        this.score = 0;
        this.personalGoalCard = personalGoalCard;
    }

    /**
     * Returns the username of the player.
     *
     * @return the username of the player
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the bookshelf of the player.
     *
     * @return the bookshelf of the player
     */
    public Bookshelf getShelf() {
        return shelf;
    }

    /**
     * Returns the score of the player.
     *
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score of the player to the specified value.
     *
     * @param score the new score for the player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns the personal goal card of the player.
     *
     * @return the personal goal card of the player
     */
    public PersonalGoalCard getPersonalGoalCard() {
        return personalGoalCard;
    }

    /**
     * Increases the player's score by the specified number of points.
     *
     * @param points the number of points to add to the player's score
     */
    public void addPoints(int points) {
        this.score = this.score + points;
    }

    /**
     * Places the given list of tiles in the specified column of the player's bookshelf.
     *
     * @param tiles  the list of tiles to place
     * @param column the column in which to place the tiles
     * @throws FullColumnException if the column is already full and cannot accommodate all the tiles
     */
    public void placeTiles(List<ItemTile> tiles, int column) throws FullColumnException {
        int row = 0;
        if (shelf.getItemTile(row, column).getType() == ItemTile.Type.EMPTY) {
            for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
                if (shelf.getItemTile(i, column).getType() == ItemTile.Type.EMPTY)
                    row = i;
            }
            if (tiles.size() > row + 1)
                throw new FullColumnException();

            for (ItemTile t : tiles) {
                shelf.setItemTile(t, new Position(row, column));
                row--;
            }
        } else throw new FullColumnException();
    }

    /**
     * Chooses and returns a list of tiles from the living room based on the specified positions.
     *
     * @param livingRoom the living room from which to pick the tiles
     * @param from       the starting position
     * @param to         the ending position
     * @return a list of chosen tiles
     * @throws TrappedCardException  if a trapped card is encountered while picking the tiles
     * @throws NotValidPickException if the pick is not valid
     */
    public ArrayList<ItemTile> chooseTiles(LivingRoom livingRoom, Position from, Position to) throws TrappedCardException, NotValidPickException {
        return livingRoom.pickTiles(from, to);
    }
}
