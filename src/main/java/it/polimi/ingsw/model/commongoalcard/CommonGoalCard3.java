package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;

import java.util.Arrays;

/**
 * Represents a goal where there are four tiles of the same type in the four corners of the bookshelf.
 *
 * @author Emanuele Fossati
 */
public class CommonGoalCard3 implements CommonGoalCard {
    @Override
    public boolean checkBookshelf(Bookshelf bookshelf) {
        ItemTile[] cornersCards = new ItemTile[4];

        cornersCards[0] = bookshelf.getItemTile(0, 0);
        cornersCards[1] = bookshelf.getItemTile(0, Bookshelf.COLUMN_COUNT - 1);
        cornersCards[2] = bookshelf.getItemTile(Bookshelf.ROW_COUNT - 1, 0);
        cornersCards[3] = bookshelf.getItemTile(Bookshelf.ROW_COUNT - 1, Bookshelf.COLUMN_COUNT - 1);

        if (Arrays.stream(cornersCards).anyMatch(x -> x.getType() == ItemTile.Type.EMPTY)) {
            return false;
        }

        return Arrays.stream(cornersCards).allMatch(cc -> cc.getType() == cornersCards[0].getType());
    }


    @Override
    public int getNumber() {
        return 3;
    }

    @Override
    public String getDescription() {
        return "3) Four tiles of the same type in the four corners of the bookshelf";
    }
}
