package it.polimi.ingsw.network.server;

import it.polimi.ingsw.cli.controller.GameController;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.utils.message.LoadGameMessage;
import it.polimi.ingsw.utils.message.MatchEndMessage;
import it.polimi.ingsw.utils.message.MatchTurnForwardMessage;
import it.polimi.ingsw.utils.message.Message;
import it.polimi.ingsw.utils.message.request.*;
import it.polimi.ingsw.utils.message.response.FirstMatchMessage;
import it.polimi.ingsw.utils.message.response.MatchCreateResponseMessage;
import it.polimi.ingsw.utils.message.response.MatchJoinResponseMessage;
import it.polimi.ingsw.utils.message.response.UsernameNotAvailableMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

/**
 * The ClientHandler class represents a handler for client connections on the server side.
 * It manages the communication with a single client and coordinates the match creation and joining process.
 *
 * @author Vittorio La Rosa
 * @author Emanuele Fossati
 */
public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Map<String, Match> idToMatch;

    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    private Match match;
    private String username;
    private boolean isOwner;

    /**
     * Initializes a new instance of the ClientHandler class.
     *
     * @param clientSocket the client socket representing the connection
     * @param idToMatch    the map of match IDs to Match instances
     * @throws IOException if an I/O error occurs when creating the object streams
     */
    public ClientHandler(Socket clientSocket, Map<String, Match> idToMatch) throws IOException {
        this.clientSocket = clientSocket;
        this.idToMatch = idToMatch;

        this.objectOutputStream = new ObjectOutputStream((clientSocket.getOutputStream()));
        this.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    /**
     * Sets up the match based on the client's request.
     *
     * @throws IOException            if an I/O error occurs during the communication
     * @throws ClassNotFoundException if the received object cannot be cast to the expected type
     */
    private void setupMatch() throws IOException, ClassNotFoundException {
        synchronized (idToMatch) {
            Message message = (Message) this.objectInputStream.readObject();
            if (message instanceof CheckIfFirstMatchMessage) {
                this.objectOutputStream.writeObject(new FirstMatchMessage("first match", isFirstMatch()));
            }

            while (match == null) {
                message = (Message) this.objectInputStream.readObject();

                System.out.println("Message received: " + message.getContent());
                this.isOwner = false;

                if (message instanceof MatchCreateRequestMessage) {
                    this.createMatch((MatchCreateRequestMessage) message);
                    this.isOwner = true;
                } else if (message instanceof MatchJoinRequestMessage) {
                    this.joinMatch((MatchJoinRequestMessage) message);
                } else if (message instanceof LoadGameMessage) {
                    this.loadMatch((LoadGameMessage) message);
                }
            }
        }
    }

    /**
     * Creates a new match based on the received match creation request message.
     *
     * @param message the match creation request message
     * @throws IOException            if an I/O error occurs during the communication
     * @throws ClassNotFoundException if the received object cannot be cast to the expected type
     */
    private void createMatch(MatchCreateRequestMessage message) throws IOException, ClassNotFoundException {
        username = message.getUsername();
        while (usernameIsTaken(username)) {
            this.objectOutputStream.writeObject(new UsernameNotAvailableMessage("Username already taken"));
            Message newUsernameMessage = (Message) this.objectInputStream.readObject();
            if (newUsernameMessage instanceof NewUsernameMessage)
                username = ((NewUsernameMessage) newUsernameMessage).getUsername();
        }


        this.match = new Match((message).getPlayCount());
        this.idToMatch.put(match.getId(), match);

        this.match.getUserList().add(message.getUsername());
        this.objectOutputStream.writeObject(new MatchCreateResponseMessage(match.getId(), "new match created!"));

    }

    private void loadMatch(LoadGameMessage message) throws IOException, ClassNotFoundException {
        Game game = message.getGame();
        String username = message.getUsername();
        String matchId = message.getMatchId();

        if (idToMatch.get(matchId) != null) {
            objectOutputStream.writeObject(new Message("match already exists"));
            return;
        }

        this.match = new Match(game, matchId);
        this.match.setLoadedMatch(true);
        this.match.addActiveUser(username);
        this.idToMatch.put(matchId, match);
        this.username = username;
        this.isOwner = true;

        this.objectOutputStream.writeObject(new MatchCreateResponseMessage(match.getId(), "match loaded!"));
    }

    /**
     * Checks if the given username is already taken in any existing matches.
     *
     * @param username the username to check
     * @return true if the username is already taken, false otherwise
     */
    private boolean usernameIsTaken(String username) {

        for (Match m : idToMatch.values()) {
            for (String u : m.getUserList()) {
                if (u.equals(username))
                    return true;
            }
        }
        return false;

    }

    /**
     * Checks if this is the first match being created.
     *
     * @return true if it is the first match, false otherwise
     */
    private boolean isFirstMatch() {
        return idToMatch.keySet().size() == 0;
    }

    /**
     * Joins an existing match based on the received match join request message.
     *
     * @param message the match join request message
     * @throws IOException            if an I/O error occurs during the communication
     * @throws ClassNotFoundException if the received object cannot be cast to the expected type
     */
    private void joinMatch(MatchJoinRequestMessage message) throws IOException, ClassNotFoundException {
        String matchId = message.getMatchId();
        username = message.getUsername();


        this.match = idToMatch.get(matchId);

        if (this.match == null) {
            objectOutputStream.writeObject(new Message("match  not existing!"));
            return;
        }

        if (match.getLoadedMatch()) {
            if (match.getActiveUsers().size() == match.getPlayerCount()) {
                match = null;
                objectOutputStream.writeObject(new Message("the match has already started!"));
                return;
            }

            while (!match.getUserList().stream().anyMatch(u -> u.equals(username)) || match.getActiveUsers().stream().anyMatch(u -> u.equals(username))) {
                this.objectOutputStream.writeObject(new UsernameNotAvailableMessage("Username not available"));

                Message newUsernameMessage = (Message) this.objectInputStream.readObject();

                if (newUsernameMessage instanceof NewUsernameMessage)
                    username = ((NewUsernameMessage) newUsernameMessage).getUsername();
            }
            this.match.addActiveUser(username);
            objectOutputStream.writeObject(new MatchJoinResponseMessage("match joined!"));
            return;
        }

        while (usernameIsTaken(username)) {
            this.objectOutputStream.writeObject(new UsernameNotAvailableMessage("Username already taken"));
            Message newUsernameMessage = (Message) this.objectInputStream.readObject();
            if (newUsernameMessage instanceof NewUsernameMessage)
                username = ((NewUsernameMessage) newUsernameMessage).getUsername();
        }

        if (match.getUserList().size() == match.getPlayerCount()) {
            match = null;
            objectOutputStream.writeObject(new Message("the match has already started!"));
            return;
        }

        match.getUserList().add(username);
        objectOutputStream.writeObject(new MatchJoinResponseMessage("match joined!"));
    }

    /*
     * Deletes a match from the map of matches.
     *
     * @param matchToDelete the match to delete
     * @param idToMatch     the map of match IDs to Match instances
     */
    private void deleteMatch(Match matchToDelete, Map<String, Match> idToMatch) {
        if (idToMatch.get(matchToDelete.getId()) == null) {
            return;
        }

        idToMatch.remove(matchToDelete.getId());
    }

    @Override
    public void run() {
        try {
            System.out.println("Client connected: " + clientSocket);

            this.setupMatch();

            System.out.println("Waiting for players...");
            while (this.match.getUserList().size() < match.getPlayerCount()
                    || (this.match.getLoadedMatch() && this.match.getActiveUsers().size() < match.getPlayerCount())) {
                //ping
                objectOutputStream.writeObject(new Message("waiting for other players..."));
            }

            synchronized (this.match) {

                if (isOwner && !match.getLoadedMatch()) {
                    match.setGameController(new GameController(new Game(this.match.getUserList().toArray(new String[0])), null));
                    match.notifyAll();
                }

                if (match.getGameController() == null)
                    match.wait();
            }

            Message matchMessage;
            synchronized (this.match) {
                while (!match.getGameController().hasEnded()) {
                    if (this.match.matchCrashed) {
                        System.out.println("un giocatore è crashato");
                        clientSocket.close();
                        return;
                    }
                    MatchTurnForwardMessage turnForwardMessage = new MatchTurnForwardMessage(match.getGameController().getModel(), "current game state");

                    objectOutputStream.writeObject(turnForwardMessage);
                    objectOutputStream.flush();
                    System.out.println("mandato messaggio di update sul match " + match.getId() + " a " + this.username);


                    if (this.username.equals(match.getGameController().getCurrentPlayer().getUsername())) {
                        if ((matchMessage = (Message) objectInputStream.readObject()) instanceof MatchTurnRequestMessage) {
                            try {
                                System.out.println("Ricevuta richiesta da " + this.username);
                                match.setGame(((MatchTurnRequestMessage) matchMessage).getGame());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        //this.match.notifyAll();
                    } else this.match.wait(1000);
                }
            }
            MatchEndMessage endMessage = new MatchEndMessage(match.getGameController().getPlayers(), "End game!");
            System.out.println("mandato messaggio di fine del match " + match.getId() + " a " + this.username);
            synchronized (idToMatch) {
                idToMatch.remove(match.getId());
            }
            objectOutputStream.writeObject(endMessage);
            objectOutputStream.flush();

        } catch (SocketException e) {
            synchronized (this.match) {
                this.match.matchCrashed = true;
                idToMatch.remove(match.getId());
            }
            System.out.printf(username + " è crashato");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

