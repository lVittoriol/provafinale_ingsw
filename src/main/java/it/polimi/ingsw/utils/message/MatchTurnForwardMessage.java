package it.polimi.ingsw.utils.message;

import it.polimi.ingsw.model.Game;

/**
 * The MatchTurnForwardMessage class represents a message that forwards the current game state to the client.
 *
 * @author Vittorio La Rosa
 * @author Emanuele Fossati
 */

public class MatchTurnForwardMessage extends Message {
    private Game game;

    /**
     * Initializes a new instance of the MatchTurnForwardMessage class with the specified game and content.
     *
     * @param game    the current game state
     * @param content the content of the message
     */
    public MatchTurnForwardMessage(Game game, String content) {
        super(content);
        this.game = game;
    }

    /**
     * Returns the current game state.
     *
     * @return the current game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the current game state.
     *
     * @param game the current game
     */
    public void setGame(Game game) {
        this.game = game;
    }


}
