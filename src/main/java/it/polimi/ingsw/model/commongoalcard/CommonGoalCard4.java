package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;

import java.util.stream.IntStream;

/**
 * Represents a common goal card that requires the presence of four lines each formed by 5 tiles of maximum three different
 * types.
 *
 * @author Marcelo S. Hernandez
 */
public class CommonGoalCard4 implements CommonGoalCard {
    @Override
    public boolean checkBookshelf(Bookshelf bookshelf) {
        return IntStream.range(0, Bookshelf.ROW_COUNT)
                .mapToObj(i -> IntStream.range(0, Bookshelf.COLUMN_COUNT)
                        .mapToObj(j -> bookshelf.getItemTile(i, j).getType())
                        .toList()
                )
                .filter(x -> !x.contains(ItemTile.Type.EMPTY))
                .map(x -> x.stream().distinct().count())
                .filter(x -> x >= 1 && x <= 3)
                .count() == 4;
    }

    @Override
    public int getNumber() {
        return 4;
    }

    @Override
    public String getDescription() {
        return "4) Four lines each formed by 5 tiles of " +
                "maximum three different types. One " +
                "line can show the same or a different " +
                "combination of another line.";
    }
}
