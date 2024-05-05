package it.polimi.ingsw.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DeckTest {
    LivingRoom.Deck deck;
    @Before
    public void setUp(){
        deck = new LivingRoom.Deck();
    }

    @After
    public void tearDown(){
    }

    @Test
    public void checkDeck_initialization(){
        Assert.assertEquals(deck.size(),132);
        int[] tiles = {0,0,0,0,0,0};
        while (deck.size() > 0){
            switch (deck.getFirstCard().getType()){
                case CAT -> tiles[0]++;
                case PLANT -> tiles[1]++;
                case TROPHY -> tiles[2]++;
                case FRAME -> tiles[3]++;
                case GAME -> tiles[4]++;
                case BOOK -> tiles[5]++;

            }
        }
        for (int i = 0; i < 6; i++) {
            assertEquals(tiles[i],22);

        }
    }


}
