package it.polimi.ingsw.cli.view;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.LivingRoom;
import it.polimi.ingsw.model.commongoalcard.CommonGoalCard;

/**
 * The `LivingRoomView` class is responsible for displaying the living room and common goal cards in the Command Line Interface (CLI).
 *
 * @author Vittorio La Rosa
 * @author Francesco Guarnello
 */
public class LivingRoomView {

    /**
     * Prints the living room board, including the tiles and their types.
     *
     * @param livingRoom The living room model to be displayed.
     */
    public void printLivingRoom(LivingRoom livingRoom) {
        System.out.println("Board: ");
        System.out.println("-------------------------------------------------------------------");
        System.out.print("   ");
        for (int i = 1; i <= LivingRoom.Board.BOARD_COLUMNS; i++) {
            System.out.print("   " + i + "   ");


        }
        System.out.print("\n");
        for (int i = 0; i < LivingRoom.Board.BOARD_ROWS; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < LivingRoom.Board.BOARD_COLUMNS; j++) {
                System.out.print("|");
                if (livingRoom.getTile(i, j).getType() == ItemTile.Type.BLOCKED || livingRoom.getTile(i, j).getType() == ItemTile.Type.EMPTY) {
                    System.out.print("      ");
                } else {
                    System.out.print(livingRoom.getTile(i, j).getType());
                    for (int k = 0; k < 6 - livingRoom.getTile(i, j).getType().toString().length(); k++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.print("|\n");
        }
        System.out.println("-------------------------------------------------------------------");
    }

    /**
     * Prints the common goal cards available in the game.
     *
     * @param game The game model containing the common goal cards.
     */
    public void printCommonGoalCards(Game game) {
        System.out.println("\nCommon Goal Cards:\n");
        for (CommonGoalCard commonGoalCard : game.getCommonGoalCards()) {
            System.out.println("- " + commonGoalCard.getDescription() + "\n");
        }
    }

}