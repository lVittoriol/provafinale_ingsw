package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;

import java.util.Arrays;

/**
 * Represents a goal where there are two groups each containing 4 tiles of the same type
 * arranged in a 2x2 square. The tiles of one square can be different from those of the other square.
 *
 * @author Vittorio La Rosa
 */

public class CommonGoalCard7 implements CommonGoalCard {
    private final boolean[][] shelfMask = new boolean[Bookshelf.ROW_COUNT][Bookshelf.COLUMN_COUNT];
    private int squares2by2 = 0;
    private boolean check = false;

    @Override
    public boolean checkBookshelf(Bookshelf bookshelf) {
        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            Arrays.fill(shelfMask[i], true);
        }

        for (int i = 0; i < Bookshelf.ROW_COUNT - 1; i++) {
            for (int j = 0; j < Bookshelf.COLUMN_COUNT - 1; j++) {

                if (bookshelf.getItemTile(i, j) == null || bookshelf.getItemTile(i, j).getType() == ItemTile.Type.EMPTY)
                    shelfMask[i][j] = false;
                if (bookshelf.getItemTile(i, j + 1) == null || bookshelf.getItemTile(i, j + 1).getType() == ItemTile.Type.EMPTY)
                    shelfMask[i][j + 1] = false;
                if (bookshelf.getItemTile(i + 1, j) == null || bookshelf.getItemTile(i + 1, j).getType() == ItemTile.Type.EMPTY)
                    shelfMask[i + 1][j] = false;
                if (bookshelf.getItemTile(i + 1, j + 1) == null || bookshelf.getItemTile(i + 1, j + 1).getType() == ItemTile.Type.EMPTY)
                    shelfMask[i + 1][j + 1] = false;

                if (shelfMask[i][j] && shelfMask[i][j + 1] && shelfMask[i + 1][j] && shelfMask[i + 1][j + 1]) {
                    shelfMask[i][j] = false;
                    if ((bookshelf.getItemTile(i, j).getType() == bookshelf.getItemTile(i, j + 1).getType()) &&
                            (bookshelf.getItemTile(i, j).getType() == bookshelf.getItemTile(i + 1, j).getType()) &&
                            (bookshelf.getItemTile(i, j).getType() == bookshelf.getItemTile(i + 1, j + 1).getType())) {
                        squares2by2++;
                        shelfMask[i][j + 1] = false;
                        shelfMask[i + 1][j] = false;
                        shelfMask[i + 1][j + 1] = false;
                    }

                }

            }
        }
        if (squares2by2 >= 2) {
            check = true;
        }
        return check;
    }

    @Override
    public int getNumber() {
        return 7;
    }

    @Override
    public String getDescription() {
        return "7) Two groups each containing 4 tiles of " +
                "the same type in a 2x2 square. The tiles " +
                "of one square can be different from " +
                "those of the other square.";
    }
}
