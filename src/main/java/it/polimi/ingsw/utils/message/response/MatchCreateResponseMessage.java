package it.polimi.ingsw.utils.message.response;

import it.polimi.ingsw.utils.message.Message;

public class MatchCreateResponseMessage extends Message {
    private String matchId;
    private String userId;

    public MatchCreateResponseMessage(String matchId, String content) {

        super(content);
        this.matchId = matchId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }


}
