package it.polimi.ingsw.model;
import it.polimi.ingsw.model.exceptions.FullColumnException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class PlayerTest {
    Bookshelf bookshelf = new Bookshelf();
    PersonalGoalCard personalGoalCard = new PersonalGoalCard(
            new PersonalGoalCard.Task[]{
                    new PersonalGoalCard.Task(ItemTile.Type.FRAME, 2, 2),
                    new PersonalGoalCard.Task(ItemTile.Type.GAME, 4, 4),
                    new PersonalGoalCard.Task(ItemTile.Type.PLANT, 1, 1),
                    new PersonalGoalCard.Task(ItemTile.Type.CAT, 5, 0),
                    new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 3, 3),
                    new PersonalGoalCard.Task(ItemTile.Type.BOOK, 0, 2)
            }, 12
    );

    Player player = new Player("ABC", bookshelf,personalGoalCard);

    @Test
    public void testGetUsername() {
        assertEquals("ABC", player.getUsername());
    }
    @Test
    public void testGetShelf() {
        assertEquals(bookshelf, player.getShelf());
    }
    @Test
    public void testGetScore() {
        assertEquals(0, player.getScore());
    }

    @Test
    public void testGetPersonalGoalCard(){
        assertNotEquals( new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 2, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 4, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 1, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 5, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 3, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 0, 2)
                }, 12), personalGoalCard);
    }

    @Test
    public void testAddPoints() {
        player.addPoints(5);
        assertEquals(5, player.getScore());
    }

    @Test
    public void testSetScore() {
        player.setScore(10);
        assertEquals(10, player.getScore());
    }
    @Test
    public void testPlaceTiles() throws FullColumnException {
        List<ItemTile> tiles = new ArrayList<>();
        tiles.add(new ItemTile(ItemTile.Type.FRAME));
        tiles.add(new ItemTile(ItemTile.Type.FRAME));
        player.placeTiles(tiles, 0);
        assertEquals(tiles.get(0), bookshelf.getItemTile(5, 0));
        assertEquals(tiles.get(1), bookshelf.getItemTile(4, 0));
    }

    @Test(expected = FullColumnException.class)
    public void testPlaceTiles_FullColumnException() throws FullColumnException {
        List<ItemTile> tiles = new ArrayList<>();
        tiles.add(new ItemTile(ItemTile.Type.FRAME));
        tiles.add(new ItemTile(ItemTile.Type.FRAME));
        tiles.add(new ItemTile(ItemTile.Type.FRAME));
        tiles.add(new ItemTile(ItemTile.Type.FRAME));
        tiles.add(new ItemTile(ItemTile.Type.FRAME));
        tiles.add(new ItemTile(ItemTile.Type.FRAME));
        tiles.add(new ItemTile(ItemTile.Type.FRAME));
        player.placeTiles(tiles, 0);
    }


}


