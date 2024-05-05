package it.polimi.ingsw.utils.message.response;

import it.polimi.ingsw.utils.message.Message;

public class FirstMatchMessage extends Message {
    private final boolean isFirstMatch;

    public FirstMatchMessage(String content, boolean isFirstMatch) {
        super(content);
        this.isFirstMatch = isFirstMatch;
    }

    public boolean isFirstMatch() {
        return isFirstMatch;
    }
}
