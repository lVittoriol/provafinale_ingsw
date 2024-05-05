
package it.polimi.ingsw.model.commongoalcard;


import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoalCard8Test {
    Bookshelf bookshelf;
    CommonGoalCard8 commonGoalCard;

    @Before
    public void setUp() {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard8();
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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), i, j);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_FirstAndLastRowPlentyOfDifferentTiles_returnsTrue(){
        //1st-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 4);
        //2nd-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 4);
        //3rd-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 4);
        //4th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 4);
        //5th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 4);
        //6th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 4);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_FirstAndSecondRowPlentyOfDifferentTiles_returnsTrue(){
        //1st-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 4);
        //2nd-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 4);
        //3rd-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 4);
        //4th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 4);
        //5th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 4);
        //6th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 4);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_EveryRowPlentyOfDifferentTiles_returnsTrue() {
        //1st-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 4);
        //2nd-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 4);
        //3rd-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 3, 4);
        //4th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 4);
        //5th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 4);
        //6th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 0, 4);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }
    @Test
    public void checkBookshelf_FirstAndSecondRowPlentyOfDifferentTilesEmptyShelf_returnsTrue(){
        //1st-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 4);
        //2nd-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 4);
        //3rd-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 3, 4);
        //4th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 2, 4);
        //5th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 4);
        //6th-Row
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 4);


        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

}