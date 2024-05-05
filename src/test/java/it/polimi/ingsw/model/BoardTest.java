package it.polimi.ingsw.model;

import it.polimi.ingsw.utils.Position;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    LivingRoom.Board board;
    LivingRoom.Deck deck;
    @Before
    public void setUp(){
        board = new LivingRoom.Board();
        deck = new LivingRoom.Deck();
    }

    @After
    public void tearDown(){
    }
    @Test
    public void checkBoard_2PlayersEmptyReset_return_true(){
        board.initBoard(2);
        Assert.assertTrue(board.timeToReset());
    }
    @Test
    public void checkBoard_3PlayersEmptyReset_return_true(){
        board.initBoard(3);
        Assert.assertTrue(board.timeToReset());
    }
    @Test
    public void checkBoard_4PlayersEmptyReset_return_true(){
        board.initBoard(4);
        Assert.assertTrue(board.timeToReset());
    }

    @Test
    public void checkBoard_2PlayersFullReset_return_false(){
        board.initBoard(2);
        board.dealCardsOnTheBoard(deck);
        Assert.assertFalse(board.timeToReset());
    }
    @Test
    public void checkBoard_3PlayersFullReset_return_false(){
        board.initBoard(3);
        board.dealCardsOnTheBoard(deck);
        Assert.assertFalse(board.timeToReset());
    }
    @Test
    public void checkBoard_4PlayersFullReset_return_false(){
        board.initBoard(4);
        board.dealCardsOnTheBoard(deck);
        Assert.assertFalse(board.timeToReset());
    }

    @Test
    public void checkBoard_diagonalAloneTilesReset_return_true(){
        board.initBoard(2);
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(3,3));
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(4,4));
        Assert.assertTrue(board.timeToReset());
    }
    @Test
    public void checkBoard_2nearbyTilesReset_return_false(){
        board.initBoard(2);
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(3,3));
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(3,4));
        Assert.assertFalse(board.timeToReset());
    }

    @Test
    public void checkBoard_trappedTile_return_true(){
        board.initBoard(2);
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(3,3));
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(4,4));
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(2,4));
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(3,4));
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(3,5));
        Assert.assertTrue(board.trappedCard(new Position(3,4)));
    }
    @Test
    public void checkBoard_notTrappedTile_return_False(){
        board.initBoard(2);
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(3,3));
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(4,4));
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(2,4));
        board.setTile(new ItemTile(ItemTile.Type.CAT), new Position(3,4));
        Assert.assertFalse(board.trappedCard(new Position(3,4)));
    }


}
