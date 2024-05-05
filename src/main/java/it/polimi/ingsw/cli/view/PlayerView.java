package it.polimi.ingsw.cli.view;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.PersonalGoalCard;
import it.polimi.ingsw.model.Player;

/**
 * The `PlayerView` class is responsible for displaying player information and personal goal cards in the Command Line Interface (CLI).
 *
 * @author Vittorio La Rosa
 * @author Francesco Guarnello
 */
public class PlayerView {

    /**
     * Prints the player's username, score, and bookshelf.
     *
     * @param player The player model to be displayed.
     */
    public void printPlayer(Player player) {
        System.out.println("Username: " + player.getUsername());
        System.out.println("Score: " + player.getScore());
        BookshelfView view = new BookshelfView();
        BookshelfView.printBookshelf(player.getShelf());
    }


//    public void printPlayer(Player player1, Player player2){
//        System.out.print("Username: " + player1.getUsername());
//        System.out.print("                                  ");
//        System.out.println("Score: " + player1.getScore());
//        BookshelfView view = new BookshelfView();
//        view.printBookshelf(player1.getShelf());
//    }

    /*public void printPersonalGoal(Player player) {
        System.out.println("\nPersonal Goal Card:\n");

        int i = 0;
        for (PersonalGoalCard.Task task : player.getPersonalGoalCard().getTasks()) {
            System.out.printf("%d) %s at [%d][%d]\n",
                    i + 1,
                    task.getItemTileType(),
                    task.getPosition().getRow() + 1,
                    task.getPosition().getColumn() + 1);
            i++;
        }
        System.out.print("\n\n\n");
    }*/

    /**
     * Prints the personal goal card of the player.
     *
     * @param player     The player model.
     * @param myUsername The username of the current user.
     */
    public void printPersonalGoal(Player player, String myUsername) {
        if (!(player.getUsername().equals(myUsername)))
            return;
        System.out.println("\nPersonal Goal Card:\n");
        System.out.print("   ");
        for (int i = 1; i <= Bookshelf.COLUMN_COUNT; i++) {
            System.out.print("   " + i + "   ");
        }

        System.out.println("\n   ------------------------------------");


        for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
            System.out.print(" " + (i + 1) + " ");
            for (int j = 0; j < Bookshelf.COLUMN_COUNT; j++) {
                System.out.print("|");
                boolean taskFound = false;
                for (PersonalGoalCard.Task task : player.getPersonalGoalCard().getTasks()) {
                    if (i == task.getPosition().getRow() && j == task.getPosition().getColumn()) {
                        System.out.print(task.getItemTileType());
                        for (int k = 0; k < 6 - task.getItemTileType().toString().length(); k++) {
                            System.out.print(" ");
                        }
                        taskFound = true;
                        break;
                    }
                }
                if (!taskFound) {
                    System.out.print("      ");
                }
            }
            System.out.print("|");
            System.out.print("\n");
        }

        System.out.println("   ------------------------------------");
        System.out.print("\n");
        System.out.print("\n");


    }
}
