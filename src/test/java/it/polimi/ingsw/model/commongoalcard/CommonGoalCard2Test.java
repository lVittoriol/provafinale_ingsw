package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonGoalCard2Test {
    Bookshelf bookshelf;
    CommonGoalCard commonGoalCard;
    @Before
    public void setUp() throws Exception {
        bookshelf = new Bookshelf();
        commonGoalCard = new CommonGoalCard2();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkBookshelf_empty_returns_false() {assertFalse(commonGoalCard.checkBookshelf(bookshelf));}
    @Test
    public void checkBookshelf_first_diagonal_returns_true() {
        for (int i = 0; i < 5; i++) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i, i);
        }
        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }
    @Test
    public void checkBookshelf_first_diagonal_incomplete_returns_false() {
        for (int i = 0; i < 5 ; i++) {
            if(i!= 4){
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i, i);
            }
            else{
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), i, i);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_second_diagonal_returns_true() {
        for (int i = 0; i < 5; i++) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i+1, i);
        }
        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }
    @Test
    public void checkBookshelf_second_diagonal_incomplete_returns_false() {
        for (int i = 0; i < 5 ; i++) {
            if(i!=4 ){
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i+1, i);
            }
            else{
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), i+1, i);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_third_diagonal_returns_true() {
        for (int i = 4; i>=0 ; i--) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i, 4-i);
        }
        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }
    @Test
    public void checkBookshelf_third_diagonal_incomplete_returns_false() {
        for (int i = 4; i>=0 ; i--) {
            if(i!=4 ){
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i, 4-i);
            }
            else{
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), i, 4-i);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_fourth_diagonal_returns_true() {
        for (int i = 5; i>=1 ; i--) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i, 5-i);
        }
        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }
    @Test
    public void checkBookshelf_fourth_diagonal_incomplete_returns_false() {
        for (int i = 5; i>=1 ; i--) {
            if(i!=1 ){
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i, 5-i);
            }
            else{
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), i, 5-i);
            }
        }
        assertFalse(commonGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void checkBookshelf_two_diagonals_return_true() {
        for (int i = 0; i < 5; i++) {
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i, i);
            bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), i+1, i);
        }
        assertTrue(commonGoalCard.checkBookshelf(bookshelf));
    }

}