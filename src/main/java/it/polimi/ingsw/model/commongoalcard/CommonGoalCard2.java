package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;

import java.util.Arrays;


/**
 * Represents a goal where there are five tiles of the same type forming a diagonal.
 *
 * @author Emanuele Fossati
 */
public class CommonGoalCard2 implements CommonGoalCard {
    private static final int DIAGONAL_LENGTH = 5;

    @Override
    public boolean checkBookshelf(Bookshelf bookshelf) {
        final int maxNumberOfDiagonals = 2 * (Bookshelf.ROW_COUNT - DIAGONAL_LENGTH + 1);
        ItemTile[][] diagonals = new ItemTile[maxNumberOfDiagonals][];

        int currentDiagonalIndex = 0;

        for (int r = 0; r <= Bookshelf.ROW_COUNT - DIAGONAL_LENGTH; r++) {
            diagonals[currentDiagonalIndex] = new ItemTile[DIAGONAL_LENGTH];

            for (int c = 0; c <= Bookshelf.COLUMN_COUNT - DIAGONAL_LENGTH; c++) {
                for (int d = 0; d < DIAGONAL_LENGTH; d++) {
                    diagonals[currentDiagonalIndex][d] = bookshelf.getItemTile(r + d, c + d);
                }
            }

            currentDiagonalIndex++;
        }

        for (int r = Bookshelf.ROW_COUNT - 1; r >= DIAGONAL_LENGTH - 1; r--) {
            diagonals[currentDiagonalIndex] = new ItemTile[DIAGONAL_LENGTH];

            for (int c = 0; c <= Bookshelf.COLUMN_COUNT - DIAGONAL_LENGTH; c++) {
                for (int d = 0; d < DIAGONAL_LENGTH; d++) {
                    diagonals[currentDiagonalIndex][d] = bookshelf.getItemTile(r - d, c + d);
                }
            }

            currentDiagonalIndex++;
        }

        return Arrays
                .stream(diagonals)
                .filter(d -> Arrays
                        .stream(d)
                        .allMatch(x -> x.getType() != ItemTile.Type.EMPTY))
                .anyMatch(d -> Arrays
                        .stream(d)
                        .allMatch(c -> c.getType() == d[0].getType()));
    }

    @Override
    public int getNumber() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "2) Five tiles of the same type forming a " +
                "diagonal.";
    }
}
