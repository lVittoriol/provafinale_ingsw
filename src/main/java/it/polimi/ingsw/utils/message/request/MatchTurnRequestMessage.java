package it.polimi.ingsw.utils.message.request;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.utils.message.Message;

/**
 * The MatchTurnRequestMessage class represents a message requesting to make a turn in a match.
 * It contains the game state representing the current turn.
 *
 * @author Vittorio La Rosa
 * @author Emanuele Fossati
 * @author Marcelo S. Hernandez
 */
public class MatchTurnRequestMessage extends Message {
    private Game game;

    /**
     * Initializes a new instance of the MatchTurnRequestMessage class with the specified game state
     * and content.
     *
     * @param game    the game state representing the current turn
     * @param content the content of the message
     */
    public MatchTurnRequestMessage(Game game, String content) {
        super(content);
        this.game = game;
    }

    /**
     * Returns the game state representing the current turn.
     *
     * @return the game state
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the game state representing the current turn.
     *
     * @param game the game state to set
     */
    public void setGame(Game game) {
        this.game = game;
    }
}
