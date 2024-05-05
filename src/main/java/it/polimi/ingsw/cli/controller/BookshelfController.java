package it.polimi.ingsw.cli.controller;

import it.polimi.ingsw.cli.view.BookshelfView;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;

/**
 * The `BookshelfController` class is responsible for controlling the interactions between the `Bookshelf` model
 * and the `BookshelfView` view in the Command Line Interface (CLI).
 *
 * @author Vittorio La Rosa
 */
public class BookshelfController {
    private final Bookshelf model;
    private final BookshelfView view;

    /**
     * Constructs a new `BookshelfController` object with the given model and view.
     *
     * @param model The `Bookshelf` model.
     * @param view  The `BookshelfView` view.
     */
    public BookshelfController(Bookshelf model, BookshelfView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Sets the given `ItemTile` on the specified row and column of the bookshelf model.
     *
     * @param tile   The `ItemTile` to be placed on the bookshelf.
     * @param row    The row index of the bookshelf where the tile will be placed.
     * @param column The column index of the bookshelf where the tile will be placed.
     */
    public void setItemTile(ItemTile tile, int row, int column) {
        model.setItemTile(tile, row, column);
    }

    /**
     * Updates the view by printing the current state of the bookshelf model.
     */
    public void updateView() {
        BookshelfView.printBookshelf(model);
    }

}
