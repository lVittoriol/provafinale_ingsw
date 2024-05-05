
package it.polimi.ingsw.model.commongoalcard;


import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonGoalCard10Test {
    Bookshelf bookshelf;
    CommonGoalCard10 commonGoalCard;

    @Before
    public void setUp() {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard10();
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
        for(int i=0; i<5; i++){
            for(int j=0; j<4; j++){
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), i, j);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_BottomLeftCornerXWithFullShelf_returnsTrue() {
        //X-Shape
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 2);
        //FullXsSquare
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 2);
        //FullXsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 4);
        //FullRemainsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 4);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_BottomRightCornerXWithFullShelf_returnsTrue() {
        //X-Shape
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 2);
        //FullXsSquare
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 2);
        //FullXsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 1);
        //FullRemainsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 4);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_TopRightCornerXWithFullShelf_returnsTrue() {
        //X-Shape
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 3); //center
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 2);
        //FullXsSquare
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 2);
        //FullXsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 1);
        //FullRemainsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 4);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_TopLeftCornerXWithFullShelf_returnsTrue() {
        //X-Shape
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 1); //center
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 2);
        //FullXsSquare
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 2);
        //FullXsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 4);
        //FullRemainsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 4);

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_BottomLeftCornerXWithFullXsSquareWithFullShelf_returnsFalse() {
        //X-Shape
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 2);
        //FullXsSquare
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 2);
        //FullXsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 4);
        //FullRemainsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 4);

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_TopLeftCornerXWithOneWrongTileInTheXsSquareWithFullShelf_returnsFalse() {
        //X-Shape
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 1); //center
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 2, 2);
        //FullXsSquare
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 1, 2);
        //FullXsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 4);
        //FullRemainsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 4);

        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_BottomCenterMinimumXWithEmptyRemainingShelf_returnsTrue() {
        //X-Shape
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 2); //center
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 5, 3);
        //FullXsSquare
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 5, 2);
        /*bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 3, 2);
        //FullXsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 5, 4);
        //FullRemainsRows
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 2, 4);*/

        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }
}