package it.polimi.ingsw.model;

import it.polimi.ingsw.cli.view.BookshelfView;
import it.polimi.ingsw.utils.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class BookshelfTest {
    private Bookshelf bookshelf;

    @Before
    public void setUp(){
        this.bookshelf = new Bookshelf();
    }
    @After
    public void tearDown(){
    }

    @Test
    public void test_checkAdjacentItemTiles1_return_true(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 3, 3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 4);

        BookshelfView.printBookshelf(bookshelf);
        int i = 0;
        for (List<Position> group : bookshelf.getGroupsOfAdjacentItemTiles()){
            System.out.println("Gruppo: " + i);
            for (Position p : group){
                System.out.println(p.toString());
            }

            i++;
        }

        Player player = new Player("abc", bookshelf, null);
        Game.checkAdjacentItemTiles(player);
        assertEquals(6, bookshelf.getGroupsOfAdjacentItemTiles().size());
    }
    @Test
    public void test_checkAdjacentItemTiles2_return_true(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 4);

        BookshelfView.printBookshelf(bookshelf);
        int i = 0;
        for (List<Position> group : bookshelf.getGroupsOfAdjacentItemTiles()){
            System.out.println("Gruppo: " + i);
            for (Position p : group){
                System.out.println(p.toString());
            }

            i++;
        }

        Player player = new Player("abc", bookshelf, null);
        Game.checkAdjacentItemTiles(player);
        assertEquals(5, bookshelf.getGroupsOfAdjacentItemTiles().size());
    }

    @Test
    public void test_checkIsFull_fullBookshelf_return_true(){

        for (int i = 0; i < Bookshelf.ROW_COUNT; i++){
            for (int j = 0; j< Bookshelf.COLUMN_COUNT; j++){
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i, j);
            }
        }

        assertTrue(bookshelf.isFull());
    }

    @Test
    public void test_checkIsFull_almostFullBookshelf_return_false(){

        for (int i = 0; i < Bookshelf.ROW_COUNT; i++){
            for (int j = 0; j< Bookshelf.COLUMN_COUNT; j++){
                bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), i, j);
            }
        }
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.EMPTY), 0, 0);
        assertFalse(bookshelf.isFull());
    }

    @Test
    public void test_checkAdjacentItemTiles3_return_true(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 3, 4);

        BookshelfView.printBookshelf(bookshelf);
        int i = 0;
        for (List<Position> group : bookshelf.getGroupsOfAdjacentItemTiles()){
            System.out.println("Gruppo: " + i);
            for (Position p : group){
                System.out.println(p.toString());
            }

            i++;
        }

        Player player = new Player("abc", bookshelf, null);
        Game.checkAdjacentItemTiles(player);
        assertEquals(6, bookshelf.getGroupsOfAdjacentItemTiles().size());
    }

    @Test
    public void test_checkAdjacentItemTiles_pattern4_return_true(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 4, 0);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 2);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 2, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 5, 3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 5, 4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 1, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 0);


        BookshelfView.printBookshelf(bookshelf);
        int i = 0;
        for (List<Position> group : bookshelf.getGroupsOfAdjacentItemTiles()){
            System.out.println("Gruppo: " + i);
            for (Position p : group){
                System.out.println(p.toString());
            }

            i++;
        }

        Player player = new Player("abc", bookshelf, null);
        Game.checkAdjacentItemTiles(player);
        assertEquals(10, player.getScore());
    }

    @Test
    public void test_checkAdjacentItemTiles_pattern5_return_true(){
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 2, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), 3, 3);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 2, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 3, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 4, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.FRAME), 5, 4);


        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.TROPHY), 4, 3);


        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 0, 4);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 0);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.CAT), 1, 4);

        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), 5, 3);


        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 2);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 1, 3);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 1);
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.GAME), 2, 3);




        BookshelfView.printBookshelf(bookshelf);
        int i = 0;
        for (List<Position> group : bookshelf.getGroupsOfAdjacentItemTiles()){
            System.out.println("Gruppo: " + i);
            for (Position p : group){
                System.out.println(p.toString());
            }

            i++;
        }

        Player player = new Player("abc", bookshelf, null);
        Game.checkAdjacentItemTiles(player);
        assertEquals(26, player.getScore());
    }
}
