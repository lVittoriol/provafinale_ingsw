package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoalCard9Test {
    Bookshelf bookshelf;
    CommonGoalCard9 commonGoalCard;

    @Before
    public void setUp() {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard9();
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
    public void checkBookshelf_3Columns_return_true(){
        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT),i,0);
        }
        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT),i,2);
        }
        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME),i,4);
        }
        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_2Columns_return_false(){
        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT),i,1);
        }
        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT),i,4);
        }

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_1Columns_return_false(){
        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT),i,3);
        }

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }
    @Test
    public void checkBookshelf_3ColumnsLessThan3DifferentTypes_return_true(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 4);


        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_3ColumnsMoreThan3DifferentTypes_return_false(){
        //more than 3 in this column
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 4);


        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

}
