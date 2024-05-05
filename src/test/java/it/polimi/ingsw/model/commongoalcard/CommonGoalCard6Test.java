package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonGoalCard6Test {
    Bookshelf bookshelf;
    CommonGoalCard commonGoalCard;
    @Before
    public void setUp() throws Exception {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard6();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkBookshelf_empty_returns_false() {assertFalse(commonGoalCard.checkBookshelf(bookshelf));}

    @Test
    public void checkBookshelf_correct_pattern_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5,1);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_incorrect_pattern1_returns_false() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5,1);

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_incorrect_pattern2_returns_false() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5,0);

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_multiple_correct_pattern_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5,0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3,1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5,1);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1,2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3,2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5,2);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }



}