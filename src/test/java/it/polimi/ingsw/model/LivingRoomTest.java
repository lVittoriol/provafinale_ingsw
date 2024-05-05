package it.polimi.ingsw.model;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LivingRoomTest {
    @Test
    public void testGetTile() {
        LivingRoom livingRoom = new LivingRoom(4);
        ItemTile tile = livingRoom.getTile(1, 1);
        assertNotEquals(tile.getType(), ItemTile.Type.EMPTY);
        assertEquals(livingRoom.getTile(1,1).getType(), ItemTile.Type.BLOCKED);
    }
}
