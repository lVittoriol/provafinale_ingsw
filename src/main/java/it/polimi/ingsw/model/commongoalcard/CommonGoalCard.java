package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;

import java.io.Serializable;

/**
 * Represents a common goal card.
 *
 * @author Emanuele Fossati
 */

public interface CommonGoalCard extends Serializable {
    /**
     * Returns <code>true</code> if the specified bookshelf completed this common goal card.
     *
     * @param bookshelf a bookshelf
     * @return <code>true</code> if the specified bookshelf completed this common goal card.
     */
    boolean checkBookshelf(Bookshelf bookshelf);

    int getNumber();

    String getDescription();
}
