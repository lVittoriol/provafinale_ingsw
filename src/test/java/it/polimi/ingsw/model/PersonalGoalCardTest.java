package it.polimi.ingsw.model;

import it.polimi.ingsw.utils.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonalGoalCardTest {

    @Test
    public void testCheckBookshelf() {
        PersonalGoalCard.Task task1 = new PersonalGoalCard.Task(ItemTile.Type.BOOK, new Position(0, 0));
        PersonalGoalCard.Task task2 = new PersonalGoalCard.Task(ItemTile.Type.PLANT, new Position(1, 1));
        PersonalGoalCard personalGoalCard = new PersonalGoalCard(new PersonalGoalCard.Task[]{task1, task2}, 1);

        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.BOOK), new Position(0, 0));
        bookshelf.setItemTile(new ItemTile(ItemTile.Type.PLANT), new Position(1, 1));

        assertEquals(2, personalGoalCard.checkBookshelf(bookshelf));
    }

    @Test
    public void testGetNumber() {

        PersonalGoalCard personalGoalCard = new PersonalGoalCard(new PersonalGoalCard.Task[]{}, 3);
        assertEquals(3, personalGoalCard.getNumber());
    }


}
