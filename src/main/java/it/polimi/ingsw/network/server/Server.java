package it.polimi.ingsw.network.server;

//import com.google.gson.Gson;

import it.polimi.ingsw.cli.controller.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * The Match class represents a game match on the server.
 * It keeps track of the match's ID, player count, and user list.
 *
 * @author Vittorio La Rosa
 * @author Emanuele Fossati
 */
class Match {
    private final String id;
    private final int playerCount;
    private final List<String> userList;
    private final List<String> activeUsers = new ArrayList<>();
    public boolean matchCrashed = false;
    private GameController gameController;
    private boolean loadedMatch = false;

    /**
     * Initializes a new instance of the Match class with the specified player count.
     *
     * @param playerCount the number of players in the match
     */
    Match(int playerCount) {
        this.id = UUID.randomUUID().toString();
        this.playerCount = playerCount;
        this.userList = new ArrayList<>();
    }

    Match(Game game, String matchId) {
        this.id = matchId;
        this.playerCount = game.getPlayers().size();
        this.gameController = new GameController(game, null);
        this.userList = game.getPlayers().stream().map(Player::getUsername).toList();
    }

    public boolean getLoadedMatch() {
        return this.loadedMatch;
    }

    public void setLoadedMatch(boolean b) {
        this.loadedMatch = b;
    }

    public void addActiveUser(String username) {
        activeUsers.add(username);
    }

    public List<String> getActiveUsers() {
        return activeUsers;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setGame(Game game) {
        this.gameController.setModel(game);
    }

    /**
     * Returns the ID of the match.
     *
     * @return the match ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the player count of the match.
     *
     * @return the player count
     */
    public int getPlayerCount() {
        return this.playerCount;
    }

    /**
     * Returns the list of users in the match.
     *
     * @return the list of users
     */
    public List<String> getUserList() {
        return this.userList;
    }


    /**
     * Checks if this match is equal to the specified match.
     * Two matches are considered equal if their IDs are equal.
     *
     * @param match the match to compare
     * @return true if the matches are equal, false otherwise
     */
    public boolean equals(Match match) {
        if (this == match) {
            return true;
        }

        if (match == null) {
            return false;
        }

        return this.id.equals(match.id);
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}

/**
 * The Server class represents the game server.
 * It accepts client connections and creates a new thread for each client.
 */
public class Server {
    /**
     * Starts the game server and listens for client connections.
     *
     * @param args command-line arguments (not used)
     * @throws IOException if an I/O error occurs when creating the server socket
     */

    public static void main(String[] args) throws IOException {
        Map<String, Match> idToMatch = new HashMap<>();

        try (ServerSocket serverSocket = new ServerSocket(59090)) {
            System.out.println("Server is running...");

            // server discovery
            Thread discoveryThread = new Thread(() -> {
                try {
                    InetAddress group = InetAddress.getByName("224.0.0.1"); // Multicast address
                    int port = 8888; // Multicast port

                    try (MulticastSocket discoverySocket = new MulticastSocket()) {
                        String message = "Server discovery response";
                        byte[] sendBuffer = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, group, port);

                        while (true) {
                            discoverySocket.send(sendPacket);
                            System.out.println("Server discovery response sent.");
                            Thread.sleep(5000);
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });

            discoveryThread.start();
            // fine server discovery

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, idToMatch);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

