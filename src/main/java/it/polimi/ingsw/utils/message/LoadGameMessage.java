package it.polimi.ingsw.utils.message;

import it.polimi.ingsw.model.Game;

public class LoadGameMessage extends Message {
    /**
     * Initializes a new instance of the Message class with the specified content.
     *
     * @param content the content of the message
     */
    private final Game game;
    private final String username;
    private final String matchId;

    public LoadGameMessage(String content, Game game, String matchId, String username) {
        super(content);
        this.game = game;
        this.matchId = matchId;
        this.username = username;

    }

    public Game getGame() {
        return this.game;
    }

    public String getMatchId() {
        return this.matchId;
    }

    public String getUsername() {
        return this.username;
    }


}
