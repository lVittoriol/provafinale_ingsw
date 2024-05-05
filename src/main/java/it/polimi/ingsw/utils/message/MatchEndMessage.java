package it.polimi.ingsw.utils.message;

import it.polimi.ingsw.model.Player;

import java.util.List;

/**
 * The MatchEndMessage class represents a message indicating the end of a match.
 *
 * @author Emanuele Fossati
 * @author Marcelo S. Hernandez
 */
public class MatchEndMessage extends Message {
    private List<Player> players;

    /**
     * Initializes a new instance of the MatchEndMessage class with the specified list of players and content.
     *
     * @param players the list of players in the match
     * @param content the content of the message
     */
    public MatchEndMessage(List<Player> players, String content) {

        super(content);
        this.players = players;
    }

    /**
     * Returns the list of players in the match.
     *
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the list of players in the match.
     *
     * @param players the list of players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
