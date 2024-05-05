package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a goal where there are two columns each formed by 6 different types of tiles.
 *
 * @author Emanuele Fossati
 */
public class CommonGoalCard6 implements CommonGoalCard {
    private static final int COLUMN_COUNT_REQUIRED = 2;

    @Override
    public boolean checkBookshelf(Bookshelf bookshelf) {
        ItemTile[][] tileColumns = new ItemTile[Bookshelf.COLUMN_COUNT][];

        for (int c = 0; c < Bookshelf.COLUMN_COUNT; c++) {

            tileColumns[c] = new ItemTile[Bookshelf.ROW_COUNT];

            for (int r = 0; r < Bookshelf.ROW_COUNT; r++) {
                tileColumns[c][r] = bookshelf.getItemTile(r, c);
            }
        }

        return Arrays
                .stream(tileColumns)
                .filter(d -> Arrays
                        .stream(d)
                        .allMatch(Objects::nonNull))
                .filter(d -> Arrays.stream(d)
                        .distinct()
                        .count() == Bookshelf.ROW_COUNT)
                .count() >= COLUMN_COUNT_REQUIRED;
    }

    @Override
    public int getNumber() {
        return 6;
    }

    @Override
    public String getDescription() {
        return "6) Two columns each formed by 6 " +
                "different types of tiles.";
    }
}