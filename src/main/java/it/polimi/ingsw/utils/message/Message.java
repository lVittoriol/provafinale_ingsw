package it.polimi.ingsw.utils.message;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The Message class represents a generic message that can be sent between client and server.
 *
 * @author Emanuele Fossati
 * @author Marcelo S. Hernandez
 */
public class Message implements Serializable {
    private final LocalDateTime timestamp;
    private final String content;

    /**
     * Initializes a new instance of the Message class with the specified content.
     *
     * @param content the content of the message
     */
    public Message(String content) {
        this.timestamp = LocalDateTime.now();
        this.content = content;
    }

    /**
     * Returns the timestamp of the message.
     *
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the content of the message.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }
}
