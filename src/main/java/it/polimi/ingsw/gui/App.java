package it.polimi.ingsw.gui;

import it.polimi.ingsw.network.client.Client;

import javax.swing.*;
import java.io.IOException;

/**
 * The main application class for the GUI of the "My Shelfie" game.
 *
 * @author Marcelo S. Hernandez
 */
public class App extends JFrame {
    private final SplashPage splashPage;
    private final HomePage homePage;
    private final CreatePage createPage;
    private final JoinPage joinPage;
    private final GamePage gamePage;
    private Client client;

    /**
     * Constructs an instance of the App class.
     */
    public App() {
        splashPage = new SplashPage();
        homePage = new HomePage(this);
        createPage = new CreatePage(this);
        joinPage = new JoinPage(this);
        gamePage = new GamePage(this);

        showSplashPage();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * The entry point of the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        App app = new App();

        try {
            Client client = new Client();
            app.setClient(client);

            if (client.isFirstMatch) {
                app.showCreatePage();
            } else {
                app.showHomePage();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Si è verificato un errore durante la creazione del client.");
        }
    }

    /**
     * Returns the client instance used by the application.
     *
     * @return the client instance
     */
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void showSplashPage() {
        setTitle("My Shelfie");
        setContentPane(splashPage);
        pack();
    }

    /**
     * Displays the home page.
     */
    public void showHomePage() {
        setTitle("My Shelfie");
        setContentPane(homePage);
        pack();
    }

    /**
     * Displays the create page for creating a new game.
     */
    public void showCreatePage() {
        setTitle("My Shelfie – Crea partita");
        setContentPane(createPage);
        pack();
    }

    /**
     * Displays the join page for joining an existing game.
     */
    public void showJoinPage() {
        setTitle("My Shelfie – Unisciti a una partita");
        setContentPane(joinPage);
        pack();
    }

    /**
     * Displays the game page.
     */
    public void showGamePage() {
        setTitle("My Shelfie – Il gioco inizierà a breve...");
        setContentPane(gamePage);
        pack();
        gamePage.start(this);
    }

    /**
     * Displays the results page.
     *
     * @param personalGoalPoints the points earned from personal goals
     * @param commonGoalsPoints  the points earned from common goals
     * @param adjacencyPoints    the points earned from adjacency goals
     * @param bonusPoints        the points earned from filling the bookshelf first
     * @param hasWon             `true` if the player has won the game, `false` otherwise
     */
    public void showResultsPage(int personalGoalPoints, int commonGoalsPoints, int adjacencyPoints, int bonusPoints, boolean hasWon) {
        setTitle("My Shelfie – Risultati");
        ResultsPage resultsPage = new ResultsPage(this, personalGoalPoints, commonGoalsPoints, adjacencyPoints, bonusPoints, hasWon);
        setContentPane(resultsPage);
        pack();
    }
}
