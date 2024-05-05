package it.polimi.ingsw.utils.message.request;

import it.polimi.ingsw.utils.message.Message;

/**
 * The CheckIfFirstMatchMessage class represents a message requesting to check if it is the first match.
 * It is used to determine whether it is the first match being played on the server.
 *
 * @author Vittorio La Rosa
 */
public class CheckIfFirstMatchMessage extends Message {

    /**
     * Initializes a new instance of the CheckIfFirstMatchMessage class with the specified content.
     *
     * @param content the content of the message
     */
    public CheckIfFirstMatchMessage(String content) {
        super(content);
    }
}
