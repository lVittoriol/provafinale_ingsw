package it.polimi.ingsw.utils.message.request;

import it.polimi.ingsw.utils.message.Message;

/**
 * The NewUsernameMessage class represents a message containing a new username.
 *
 * @author Vittorio La Rosa
 */
public class NewUsernameMessage extends Message {
    String username;

    /**
     * Initializes a new instance of the NewUsernameMessage class with the specified username and content.
     *
     * @param username the new username
     * @param content  the content of the message
     */
    public NewUsernameMessage(String username, String content) {
        super(content);
        this.username = username;
    }

    /**
     * Returns the new username.
     *
     * @return the new username
     */
    public String getUsername() {
        return username;
    }
}
