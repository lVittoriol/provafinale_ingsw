package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;

/**
 * Represents a goal where there are eight tiles of the same type
 * on the Bookshelf. There are no restrictions on the position of these tiles.
 *
 * @author Vittorio La Rosa
 */
public class CommonGoalCard11 implements CommonGoalCard {
    private final int[] tiles = {0, 0, 0, 0, 0, 0};
    private boolean check = false;

    @Override
    public boolean checkBookshelf(Bookshelf bookshelf) {
        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            for (int j = 0; j < Bookshelf.COLUMN_COUNT; j++) {

                if (bookshelf.getItemTile(i, j) != null) {
                    switch (bookshelf.getItemTile(i, j).getType()) {
                        case CAT -> tiles[0]++;
                        case BOOK -> tiles[1]++;
                        case GAME -> tiles[2]++;
                        case FRAME -> tiles[3]++;
                        case TROPHY -> tiles[4]++;
                        case PLANT -> tiles[5]++;

                    }
                }

            }


        }
        for (int i = 0; i < 6; i++) {
            if (tiles[i] >= 8) {
                check = true;
                break;
            }
        }
        return check;
    }

    @Override
    public int getNumber() {
        return 11;
    }

    @Override
    public String getDescription() {
        return "11) Eight tiles of the same type. There’s no " +
                "restriction about the position of these " +
                "tiles.";
    }
}
