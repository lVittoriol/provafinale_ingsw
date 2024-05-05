package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoalCard11Test {
    Bookshelf bookshelf;
    CommonGoalCard11 commonGoalCard;

    @Before
    public void setUp() {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard11();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkBookshelf_empty_returns_false() {assertFalse(commonGoalCard.checkBookshelf(bookshelf));}

    @Test
    public void checkBookshelf_8Tiles_returns_true(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 3);
        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_7Tiles_returns_false(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 3);
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }



}
