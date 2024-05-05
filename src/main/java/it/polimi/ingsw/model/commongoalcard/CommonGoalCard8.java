package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.utils.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a common goal card that requires two lines each formed by 5 different types of tiles.
 * One line can show the same or a different combination of the other line.
 *
 * @author Francesco Guarnello
 */

public class CommonGoalCard8 implements CommonGoalCard {
    private static final int ROWS_TO_CHECK = 2;
    private static final int TILES_PER_ROW = 5;

    @Override
    public boolean checkBookshelf(Bookshelf bookshelf) {
        int rowsFound = 0;

        for (int row = 0; row < Bookshelf.ROW_COUNT; row++) {

            Set<ItemTile.Type> tileTypes = new HashSet<>();
            for (int col = 0; col < Bookshelf.COLUMN_COUNT; col++) {

                Position pos = new Position(row, col);
                ItemTile tile = bookshelf.getItemTile(pos);
                if (tile != null && tile.getType() != ItemTile.Type.EMPTY && tileTypes.add(tile.getType())) {

                    if (tileTypes.size() == TILES_PER_ROW) {

                        rowsFound++;
                        if (rowsFound == ROWS_TO_CHECK) {

                            return true;
                        }
                        break;
                    }
                }
            }
        }

        return false;

    }

    @Override
    public int getNumber() {
        return 8;
    }

    @Override
    public String getDescription() {
        return "8) Two lines each formed by 5 different " +
                "types of tiles. One line can show the " +
                "same or a different combination of the " +
                "other line.";
    }
}
