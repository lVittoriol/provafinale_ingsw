package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;

/**
 * Represents a common goal card that requires five tiles of the same type forming an X.
 *
 * @author Francesco Guarnello
 */
public class CommonGoalCard10 implements CommonGoalCard {
    @Override
    public boolean checkBookshelf(Bookshelf shelf) {
        for (int i = 1; i < Bookshelf.ROW_COUNT - 1; i++) {
            for (int j = 1; j < Bookshelf.COLUMN_COUNT - 1; j++) {

                if (
                        (shelf.getItemTile(i, j) != null) && (!shelf.getItemTile(i, j).getType().equals(ItemTile.Type.EMPTY)) &&
                                (shelf.getItemTile(i, j + 1) != null) && (shelf.getItemTile(i, j + 1) != null) && (!shelf.getItemTile(i, j + 1).getType().equals(ItemTile.Type.EMPTY)) &&
                                (shelf.getItemTile(i, j - 1) != null) && (!shelf.getItemTile(i, j - 1).getType().equals(ItemTile.Type.EMPTY)) &&
                                (shelf.getItemTile(i + 1, j) != null) && (!shelf.getItemTile(i + 1, j).getType().equals(ItemTile.Type.EMPTY)) &&
                                (shelf.getItemTile(i - 1, j - 1) != null) && (!shelf.getItemTile(i - 1, j - 1).getType().equals(ItemTile.Type.EMPTY)) &&
                                (shelf.getItemTile(i - 1, j + 1) != null) && (!shelf.getItemTile(i - 1, j + 1).getType().equals(ItemTile.Type.EMPTY)) &&
                                (shelf.getItemTile(i + 1, j - 1) != null) && (!shelf.getItemTile(i + 1, j - 1).getType().equals(ItemTile.Type.EMPTY)) &&
                                (shelf.getItemTile(i + 1, j + 1) != null) && (!shelf.getItemTile(i + 1, j + 1).getType().equals(ItemTile.Type.EMPTY)) &&


                                shelf.getItemTile(i, j).getType().equals(shelf.getItemTile(i + 1, j + 1).getType()) &&
                                shelf.getItemTile(i, j).getType().equals(shelf.getItemTile(i - 1, j - 1).getType()) &&
                                shelf.getItemTile(i, j).getType().equals(shelf.getItemTile(i - 1, j + 1).getType()) &&
                                shelf.getItemTile(i, j).getType().equals(shelf.getItemTile(i + 1, j - 1).getType()) &&
                                !shelf.getItemTile(i, j).getType().equals(shelf.getItemTile(i, j - 1).getType()) &&
                                !shelf.getItemTile(i, j).getType().equals(shelf.getItemTile(i, j + 1).getType()) &&
                                (shelf.getItemTile(i - 1, j) == null || shelf.getItemTile(i - 1, j).getType().equals(ItemTile.Type.EMPTY) || !shelf.getItemTile(i, j).getType().equals(shelf.getItemTile(i - 1, j).getType())) &&
                                !shelf.getItemTile(i, j).getType().equals(shelf.getItemTile(i + 1, j).getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getNumber() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "10) Five tiles of the same type forming an X.";
    }
}