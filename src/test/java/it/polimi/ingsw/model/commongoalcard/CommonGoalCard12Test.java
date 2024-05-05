package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoalCard12Test {
    Bookshelf bookshelf;
    CommonGoalCard12 commonGoalCard;

    @Before
    public void setUp() {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard12();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkBookshelf_empty_returnsFalse() {
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_WithBigLeftStair_returnsTrue(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 4);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_WithLittleLeftStair_returnsTrue(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 4);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_WithBigRightStair_returnsTrue(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }
    @Test
    public void checkBookshelf_WithLittleRightStair_returnsTrue(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_WithLittleRightStairPlusOneRandomTile_returnsFalse(){
        //LittleRightStair
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);
        //OneRandomTile
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 0);


        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_WithBigLeftStairWithoutOneOrMoreRandomTile_returnsFalse(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 3, 0);
       // bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 1);
       // bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 3);
    //    bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 4);

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_FullOfRandomTilesTypeExcludingEmpty_returnsFalse() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                ItemTile.Type randomType = ItemTile.Type.values()[rand.nextInt(ItemTile.Type.values().length - 1) + 1];
                bookshelf.setItemTile(new ItemTile(randomType), j, i);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_FullOfRandomTilesTypeIncludingEmpty_returnsFalse() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                ItemTile.Type randomType = ItemTile.Type.values()[rand.nextInt(ItemTile.Type.values().length - 1)];
                bookshelf.setItemTile(new ItemTile(randomType), j, i);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }
}