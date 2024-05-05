package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CommonGoalCard5Test {
    Bookshelf bookshelf;
    CommonGoalCard commonGoalCard;
    @Before
    public void setUp() throws Exception {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard5();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkBookshelf_empty_returns_false() {assertFalse(commonGoalCard.checkBookshelf(bookshelf));}

    @Test
    public void checkBookshelf_correct_pattern1_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 3);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_correct_pattern2_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 4);


        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_correct_pattern3_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0,2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 3);



        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_correct_pattern4_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 3,4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 1);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_correct_pattern5_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3,2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4,3);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_incorrect_pattern1_returns_false() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0,3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1,4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 1);

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_incorrect_pattern2_returns_false() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3,2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3,3);

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_incorrect_pattern3_returns_false() {
        for(int r=0; r<Bookshelf.ROW_COUNT; r++){
            for(int c=0; c<Bookshelf.COLUMN_COUNT; c++){
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), r, c);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }





    @Test
    public void checkBookshelf() {
    }
}