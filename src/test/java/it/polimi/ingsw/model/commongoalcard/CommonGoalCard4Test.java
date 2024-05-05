package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoalCard4Test {
    Bookshelf bookshelf;
    CommonGoalCard4 commonGoalCard;

    @Before
    public void setUp() {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard4();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkBookshelf_empty_returnsFalse() {
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_threeRowsOfFiveTilesOfOneType_returnsFalse() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 0);
            }
        }

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_fourRowsOfFourTilesOfOneType_returnsFalse() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 0);
            }
        }

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_fourRowsOfFiveTilesOfOneType_returnsTrue() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), i, j);
            }
        }

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_fourRowsOfFiveTilesOfTwoTypes_returnsTrue() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                bookshelf.setItemTile(new ItemTile(j % 2 == 0 ? ItemTile.Type.BOOK : ItemTile.Type.CAT), i, j);
            }
        }

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_fourRowsOfFiveTilesOfThreeTypes_returnsTrue() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), i, j);
                } else {
                    bookshelf.setItemTile(new ItemTile(j % 2 == 0 ? ItemTile.Type.BOOK : ItemTile.Type.CAT), i, j);
                }
            }
        }

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_fourRowsOfFiveTilesOfFourTypes_returnsFalse() {
        for (int i = 0; i < 4; i++) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), i, 0);
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), i, 1);
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), i, 1);
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), i, 1);
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), i, 1);
        }

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }
}