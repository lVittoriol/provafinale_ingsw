package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;

/**
 * Represents a goal where there are three columns each formed by 6 tiles
 * of maximum three different types. One column can show the same or a different
 * combination of another column.
 *
 * @author Vittorio La Rosa
 */

public class CommonGoalCard9 implements CommonGoalCard {
    private final boolean[] tilesPresent = {false, false, false, false, false, false};
    private int columns = 0;
    private int typesOfTiles = 0;
    private int tilesInTheColumn = 0;
    private boolean check = false;

    @Override
    public boolean checkBookshelf(Bookshelf bookshelf) {
        for (int i = 0; i < Bookshelf.COLUMN_COUNT; i++) {
            for (int j = 0; j < Bookshelf.ROW_COUNT; j++) {
                if (bookshelf.getItemTile(j, i) != null && bookshelf.getItemTile(j, i).getType() != ItemTile.Type.EMPTY) {

                    switch (bookshelf.getItemTile(j, i).getType()) {
                        case CAT -> tilesPresent[0] = true;
                        case BOOK -> tilesPresent[1] = true;
                        case GAME -> tilesPresent[2] = true;
                        case FRAME -> tilesPresent[3] = true;
                        case TROPHY -> tilesPresent[4] = true;
                        case PLANT -> tilesPresent[5] = true;
                    }
                    tilesInTheColumn++;
                }
            }
            if (tilesInTheColumn == 6) {
                for (int k = 0; k < 6; k++) {
                    if (tilesPresent[k])
                        typesOfTiles++;
                }
                if (typesOfTiles <= 3) {
                    columns++;
                }
            }
            for (int k = 0; k < 6; k++) {
                tilesPresent[k] = false;
            }
            tilesInTheColumn = 0;
            typesOfTiles = 0;
        }
        if (columns >= 3) check = true;
        return check;
    }

    @Override
    public int getNumber() {
        return 9;
    }

    @Override
    public String getDescription() {
        return "9) Three columns each formed by 6 tiles" +
                "of maximum three different types. One " +
                "column can show the same or a different " +
                "combination of another column.";
    }
}



