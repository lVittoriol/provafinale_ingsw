package it.polimi.ingsw.cli.view;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;

/**
 * The `BookshelfView` class is responsible for displaying the bookshelf in the Command Line Interface (CLI).
 *
 * @author Vittorio La Rosa
 */
public class BookshelfView {

    /**
     * Prints the bookshelf, including the type of item tiles in each position.
     *
     * @param bookshelf The bookshelf model to be displayed.
     */
    public static void printBookshelf(Bookshelf bookshelf) {
        System.out.println("Bookshelf: ");
        System.out.print("   ");
        for (int i = 1; i <= Bookshelf.COLUMN_COUNT; i++) {
            System.out.print("   " + i + "   ");
        }

        System.out.println("\n   ------------------------------------");


        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            System.out.print(" " + (i + 1) + " ");
            for (int j = 0; j < Bookshelf.COLUMN_COUNT; j++) {
                System.out.print("|");
                if (bookshelf.getItemTile(i, j).getType() == ItemTile.Type.EMPTY) {
                    System.out.print("      ");
                } else {
                    System.out.print(bookshelf.getItemTile(i, j).getType());
                    for (int k = 0; k < 6 - bookshelf.getItemTile(i, j).getType().toString().length(); k++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.print("|\n");
        }
        System.out.println("   ------------------------------------");
    }
}

