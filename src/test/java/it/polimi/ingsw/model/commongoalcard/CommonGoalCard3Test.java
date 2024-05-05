package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonGoalCard3Test {
    Bookshelf bookshelf;
    CommonGoalCard commonGoalCard;
    @Before
    public void setUp() throws Exception {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard3();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkBookshelf_empty_returns_false() {assertFalse(commonGoalCard.checkBookshelf(bookshelf));}

    @Test
    public void checkBookshelf_correct_pattern_returns_true() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,Bookshelf.COLUMN_COUNT-1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), Bookshelf.ROW_COUNT-1,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), Bookshelf.ROW_COUNT-1,Bookshelf.COLUMN_COUNT-1);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_incorrect_pattern_returns_false() {
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0,Bookshelf.COLUMN_COUNT-1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), Bookshelf.ROW_COUNT-1,0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), Bookshelf.ROW_COUNT-1,Bookshelf.COLUMN_COUNT-1);

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

}