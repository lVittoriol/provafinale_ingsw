package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.utils.Position;


/**
 * Represents a common goal card that requires the presence of five columns of increasing or decreasing height.
 * Starting from the first column on the left or on the right, each next column must be made of exactly one more tile.
 * Tiles can be of any type.
 *
 * @author Francesco Guarnello
 */


public class CommonGoalCard12 implements CommonGoalCard {
    @Override
    public boolean checkBookshelf(Bookshelf bookshelf) {
        int[] heights = new int[5];
        boolean increasing = false;
        boolean decreasing = false;

        for (int i = 0; i < Bookshelf.COLUMN_COUNT; i++) {
            heights[i] = 0;
            for (int j = 0; j < Bookshelf.ROW_COUNT; j++) {
                Position position = new Position(j, i);
                ItemTile tile = bookshelf.getItemTile(position);
                if (tile != null && tile.getType() != ItemTile.Type.EMPTY) {
                    heights[i]++;
                }
            }
            if (i > 0 && heights[i] != heights[i - 1] + 1) {
                increasing = false;
                break;
            }
            increasing = true;
        }
        if (increasing) {
            return true;
        }

        for (int i = 0; i < Bookshelf.COLUMN_COUNT; i++) {
            heights[i] = 0;
            for (int j = 0; j < Bookshelf.ROW_COUNT; j++) {
                Position position = new Position(j, i);
                ItemTile tile = bookshelf.getItemTile(position);
                if (tile != null && tile.getType() != ItemTile.Type.EMPTY) {
                    heights[i]++;
                }
            }
            if (i > 0 && heights[i] != heights[i - 1] - 1) {
                decreasing = false;
                break;
            }
            decreasing = true;
        }
        return decreasing;
    }

    @Override
    public int getNumber() {
        return 12;
    }

    @Override
    public String getDescription() {
        return "12) Five columns of increasing or decreasing " + "height. Starting from the first column on " + "the left or on the right, each next column " + "must be made of exactly one more tile. " + "Tiles can be of any type.";
    }
}
