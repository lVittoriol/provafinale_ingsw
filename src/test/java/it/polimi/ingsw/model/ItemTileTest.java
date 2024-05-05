package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ItemTileTest {

    @Test
    public void testGetType() {

        ItemTile itemTile = new ItemTile(ItemTile.Type.BOOK);
        assertEquals(ItemTile.Type.BOOK, itemTile.getType());
    }

    @Test
    public void testSetType() {

        ItemTile itemTile = new ItemTile(ItemTile.Type.BOOK);
        assertEquals(ItemTile.Type.BOOK, itemTile.getType());

        itemTile.setType(ItemTile.Type.PLANT);
        assertEquals(ItemTile.Type.PLANT, itemTile.getType());
    }

    @Test
    public void testEquals() {

        ItemTile itemTile1 = new ItemTile(ItemTile.Type.BOOK);
        ItemTile itemTile2 = new ItemTile(ItemTile.Type.BOOK);
        ItemTile itemTile3 = new ItemTile(ItemTile.Type.PLANT);

        assertEquals(itemTile1, itemTile1);

        assertEquals(itemTile1, itemTile2);

        assertNotEquals(itemTile1, itemTile3);
    }

    @Test
    public void testHashCode() {

        ItemTile itemTile1 = new ItemTile(ItemTile.Type.BOOK);
        ItemTile itemTile2 = new ItemTile(ItemTile.Type.BOOK);

        assertEquals(itemTile1.hashCode(), itemTile2.hashCode());
    }

    @Test
    public void testToString() {

        ItemTile itemTile = new ItemTile(ItemTile.Type.BOOK);
        assertEquals("BOOK", itemTile.toString());
    }

}
