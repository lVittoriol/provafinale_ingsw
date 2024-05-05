package it.polimi.ingsw.cli.view;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;

/**
 * The `GameView` class is responsible for displaying the game state in the Command Line Interface (CLI).
 *
 * @author Vittorio La Rosa
 * @author Francesco Guarnello
 */
public class GameView {
    private final String myUsername;

    /**
     * Constructs a `GameView` object with the specified username.
     *
     * @param myUsername The username of the current player.
     */
    public GameView(String myUsername) {
        this.myUsername = myUsername;
    }

    /**
     * Prints the current game state, including the living room, common goal cards, and player information.
     *
     * @param game The game model to be displayed.
     */
    public void printGame(Game game) {
        System.out.println("\nTocca a: " + game.getCurrentPlayer().getUsername() + "\n\n");
        LivingRoomView livingRoomView = new LivingRoomView();
        livingRoomView.printLivingRoom(game.getLivingRoom());
        livingRoomView.printCommonGoalCards(game);
        PlayerView playerView = new PlayerView();
        for (Player p : game.getPlayers()) {
            playerView.printPlayer(p);
            playerView.printPersonalGoal(p, myUsername);
        }
    }
}
