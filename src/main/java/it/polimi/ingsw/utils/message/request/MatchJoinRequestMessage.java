package it.polimi.ingsw.utils.message.request;

import it.polimi.ingsw.utils.message.Message;

/**
 * The MatchJoinRequestMessage class represents a message requesting to join an existing match.
 * It contains information about the username of the player and the match ID.
 *
 * @author Emanuele Fossati
 * @author Marcelo S. Hernandez
 */
public class MatchJoinRequestMessage extends Message {
    private String username;
    private String matchId;

    /**
     * Initializes a new instance of the MatchJoinRequestMessage class with the specified username,
     * match ID, and content.
     *
     * @param username the username of the player joining the match
     * @param matchId  the ID of the match to join
     * @param content  the content of the message
     */
    public MatchJoinRequestMessage(String username, String matchId, String content) {
        super(content);
        this.username = username;
        this.matchId = matchId;
    }

    /**
     * Returns the username of the player joining the match.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the player joining the match.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the ID of the match to join.
     *
     * @return the match ID
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * Sets the ID of the match to join.
     *
     * @param matchId the match ID to set
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
