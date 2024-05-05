package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonGoalCard1Test {
    Bookshelf bookshelf;
    CommonGoalCard commonGoalCard;

    @Before
    public void setUp() throws Exception {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard1();
    }

    @Test
    public void checkBookshelf_empty_returns_false() {assertFalse(commonGoalCard.checkBookshelf(bookshelf));}

    @Test
    public void checkBookshelf_correct_pattern1_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4,4);



        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_correct_pattern2_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1,4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4,0);


        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_correct_pattern3_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1,4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4,0);


        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_correct_pattern4_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5,4);


        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_incorrect_pattern1_returns_false() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5,4);


        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_incorrect_pattern2_returns_false() {
        for(int r=0; r<Bookshelf.ROW_COUNT; r++){
            for(int c=0; c<Bookshelf.COLUMN_COUNT; c++){
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), r, c);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_incorrect_pattern3_returns_false() {

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 0,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2,3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1,4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 0,3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3,3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5,4);

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_incorrect_pattern4_returns_false() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1,2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5,2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5,4);

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }
}