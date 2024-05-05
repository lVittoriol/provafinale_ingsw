package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoalCard7Test {
    Bookshelf bookshelf;
    CommonGoalCard7 commonGoalCard;

    @Before
    public void setUp() {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard7();
    }

    @After
    public void tearDown() {
    }


    @Test
    public void checkBookshelf_empty1_returnsFalse() {
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }
    @Test
    public void checkBookshelf_empty2_returnsFalse() {
        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            for (int j = 0; j < Bookshelf.COLUMN_COUNT; j++) {
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY),i,j);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }
    @Test
    public void checkBookshelf_squaresAtTheRightCorners_returns_true(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 4);
        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_squaresAtTheLeftCorners_returns_true(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 1);
        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_nearbySquares_returns_true(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 3);
        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_horizontalRectangle2by3_returns_false(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 2);
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_verticalRectangle2by3_returns_false(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 1);
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }


}
