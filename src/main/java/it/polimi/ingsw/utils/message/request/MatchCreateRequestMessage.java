package it.polimi.ingsw.utils.message.request;

import it.polimi.ingsw.utils.message.Message;

/**
 * The MatchCreateRequestMessage class represents a message requesting to create a new match.
 * It contains information about the desired player count and the username of the creator.
 *
 * @author Emanuele Fossati
 */
public class MatchCreateRequestMessage extends Message {
    String username;
    private Integer playCount;

    /**
     * Initializes a new instance of the MatchCreateRequestMessage class with the specified player count,
     * username, and content.
     *
     * @param playCount the desired player count for the match
     * @param username  the username of the match creator
     * @param content   the content of the message
     */
    public MatchCreateRequestMessage(int playCount, String username, String content) {
        super(content);
        this.playCount = playCount;
        this.username = username;
    }

    /**
     * Returns the desired player count for the match.
     *
     * @return the player count
     */
    public Integer getPlayCount() {
        return this.playCount;
    }

    /**
     * Sets the desired player count for the match.
     *
     * @param playCount the player count to set
     */
    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    /**
     * Returns the username of the match creator.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username of the match creator.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
