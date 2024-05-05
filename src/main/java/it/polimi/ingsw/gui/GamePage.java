package it.polimi.ingsw.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.gui.components.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.commongoalcard.CommonGoalCard;
import it.polimi.ingsw.model.exceptions.FullColumnException;
import it.polimi.ingsw.model.exceptions.NotValidPickException;
import it.polimi.ingsw.model.exceptions.TrappedCardException;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.utils.GameTypeAdapter;
import it.polimi.ingsw.utils.Position;
import it.polimi.ingsw.utils.message.MatchEndMessage;
import it.polimi.ingsw.utils.message.MatchTurnForwardMessage;
import it.polimi.ingsw.utils.message.Message;
import it.polimi.ingsw.utils.message.request.MatchTurnRequestMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.*;

/**
 * The `GamePage` class represents the GUI page for the game. It displays the game components and handles user interactions.
 *
 * @author Marcelo S. Hernandez
 * @author Vittorio La Rosa
 * @author Francesco Guarnello
 */
public class GamePage extends JWoodPanel {
    private final JLivingRoom livingRoom;
    private final JPersonalGoalCard personalGoalCard;
    private final JCommonGoalCard[] commonGoalCards;
    private final JBookshelf bookshelf;
    private final JMiniBookshelf[] miniBookshelves;
    private final JTextField tilesOrderField;
    private final JWoodButton confirmButton;
    private final JColumnSelectionForm columnSelectionForm;
    private Game game;

    /**
     * Constructs a new `GamePage` object.
     */

    public GamePage(final App app) {
        setLayout(null);

        livingRoom = new JLivingRoom(new LivingRoom.Board());
        addChild(this, livingRoom, 24, 24);

        personalGoalCard = new JPersonalGoalCard();
        addChild(this, personalGoalCard, 273, 516);

        commonGoalCards = new JCommonGoalCard[2];

        for (int i = 0; i < 2; i++) {
            commonGoalCards[i] = new JCommonGoalCard();
            addChild(this, commonGoalCards[i], 100, 516 + i * 122);
        }

        bookshelf = new JBookshelf(new Bookshelf());
        addChild(this, bookshelf, 635, 254);

        miniBookshelves = new JMiniBookshelf[3];

        for (int i = 0; i < 3; i++) {
            miniBookshelves[i] = new JMiniBookshelf(new Bookshelf());
            addChild(this, miniBookshelves[i], 524 + 164 * i, 24);
        }

        columnSelectionForm = new JColumnSelectionForm();
        addChild(this, columnSelectionForm, 674, 220);

        JLabel tilesOrderLabel = new JLabel("Ordine delle carte (es. \"0 1 2\")");
        tilesOrderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        addChild(this, tilesOrderLabel, 635, 565);

        tilesOrderField = new JTextField(20);
        tilesOrderField.setPreferredSize(new Dimension(256, 41));
        addChild(this, tilesOrderField, 635, 590);

        confirmButton = new JWoodButton("Conferma");
        confirmButton.setEnabled(false);
        confirmButton.setPreferredSize(new Dimension(124, 41));
        confirmButton.addActionListener(this::onConfirm);
        addChild(this, confirmButton, 635, 638);

        JWoodButton discardButton = new JWoodButton("Annulla");
        discardButton.setPreferredSize(new Dimension(124, 41));
        discardButton.addActionListener(this::onDiscard);
        addChild(this, discardButton, 766, 638);

        JWoodButton saveButton = new JWoodButton("Salva partita");
        saveButton.setPreferredSize(new Dimension(256, 41));
        saveButton.addActionListener(e -> onSave(e, app));
        addChild(this, saveButton, 635, 695);
    }

    /**
     * Starts the game page with the given `App` instance.
     *
     * @param app The `App` instance representing the application.
     */
    public void start(App app) {
        new Worker(app).execute();
    }

    /**
     * Event handler for the confirm button. Called when the confirm button is clicked.
     *
     * @param event The `ActionEvent` object representing the event.
     */
    private void onConfirm(ActionEvent event) {
        if (livingRoom.getSelectedItemTiles().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleziona almeno una carta.");
            return;
        }

        Position startPosition = livingRoom.getSelectedItemTiles().get(0).getPosition();
        Position endPosition;

        if (livingRoom.getSelectedItemTiles().size() > 1) {
            endPosition = livingRoom.getSelectedItemTiles().get(1).getPosition();
        } else {
            endPosition = startPosition;
        }

        if (columnSelectionForm.getSelectedColumn() == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona una colonna.");
            return;
        }

        String order = tilesOrderField.getText();
        List<Integer> indexes;

        try {
            indexes = Arrays.stream(order.split("\\s+")).map(Integer::parseInt).toList();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Gli indici devono essere numeri interi.");
            return;
        }

        int selectedCardsCount;

        if (startPosition.getRow() == endPosition.getRow()) {
            selectedCardsCount = Math.abs(endPosition.getColumn() - startPosition.getColumn()) + 1;
        } else {
            selectedCardsCount = Math.abs(endPosition.getRow() - startPosition.getRow()) + 1;
        }

        if (indexes.stream().anyMatch(x -> x < 0 || x > 2)) {
            JOptionPane.showMessageDialog(this, "Gli indici devono essere compresi tra 0 e 2.");
            return;
        } else if (indexes.size() != selectedCardsCount) {
            JOptionPane.showMessageDialog(this, "Il numero di indici deve essere uguale al numero di carte selezionate.");
            return;
        } else if (indexes.stream().distinct().count() != indexes.size()) {
            JOptionPane.showMessageDialog(this, "Gli indici non devono contenere duplicati.");
            return;
        } else {
            List<Integer> sorted = indexes.stream().sorted().toList();

            if (sorted.get(0) != 0 || sorted.get(sorted.size() - 1) != indexes.size() - 1) {
                JOptionPane.showMessageDialog(this, "Gli indici sono sbagliati.");
                return;
            }
        }


        int row = 0;
        int column = columnSelectionForm.getSelectedColumn();
        Bookshelf shelf = game.getCurrentPlayer().getShelf();

        if (shelf.getItemTile(row, column).getType() == ItemTile.Type.EMPTY) {
            for (int i = 0; i < Bookshelf.ROW_COUNT; i++) {
                if (shelf.getItemTile(i, column).getType() == ItemTile.Type.EMPTY) row = i;
            }
            if (livingRoom.getSelectedItemTiles().size() > row + 1) {
                JOptionPane.showMessageDialog(this, "La colonna selezionata è piena.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "La colonna selezionata è piena.");
            return;
        }


        ArrayList<ItemTile> pickedTiles;

        try {
            pickedTiles = game.pickTiles(startPosition, endPosition);
        } catch (TrappedCardException e) {
            JOptionPane.showMessageDialog(this, "Una o più carte selezionate sono bloccate.");
            return;
        } catch (NotValidPickException e) {
            JOptionPane.showMessageDialog(this, "Selezione non valida.");
            return;
        }

        List<ItemTile> orderedTiles = game.orderTiles(pickedTiles, indexes);

        try {
            game.tilesInColumn(orderedTiles, columnSelectionForm.getSelectedColumn());
            game.checkLivingRoom();
        } catch (FullColumnException e) {
            JOptionPane.showMessageDialog(this, "Non c'è abbastanza spazio nella colonna.");
            return;
        }

        onDiscard(null);
        confirmButton.setEnabled(false);
    }

    /**
     * Event handler for the discard button. Called when the discard button is clicked.
     *
     * @param event The `ActionEvent` object representing the event.
     */
    private void onDiscard(ActionEvent event) {
        livingRoom.clearSelectedItems();
        tilesOrderField.setText("");
        columnSelectionForm.clearSelection();
    }

    /**
     * Event handler for the save button. Called when the save button is clicked.
     *
     * @param event The `ActionEvent` object representing the event.
     */
    private void onSave(ActionEvent event, App app) {
        if (game == null) {
            return;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Game.class, new GameTypeAdapter());
        Gson gson = gsonBuilder.create();

        String matchSavingJson = gson.toJson(game);
        File matchSavingFile = new File(app.getClient().matchId + ".txt");

        if (!matchSavingFile.exists()) {
            try {
                matchSavingFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter w = new FileWriter(matchSavingFile)) {
            w.write(matchSavingJson);
            w.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks the common goal cards to determine if any player has completed them.
     */
    private void checkCommonGoalCards() {
        Map<CommonGoalCard, List<Player>> tempMap = new HashMap<>(game.getCommonGoalCardToWinners());
        List<Integer> pointsGained = game.pointsCommonGoalCards();
        int points = 0;

        if (pointsGained.get(0) != 0 && pointsGained.get(1) != 0) {
            for (CommonGoalCard commonGoalCard : game.getCommonGoalCardToWinners().keySet()) {
                int i = 0;
                if (pointsGained.get(i) != 0) {
                    points = pointsGained.get(i);
                    List<Player> currentWinners = game.getCommonGoalCardToWinners().get(commonGoalCard);
                    List<Player> oldWinners = tempMap.get(commonGoalCard);

                    if (oldWinners == null || currentWinners.size() > oldWinners.size()) {

                        int bonus = 0;

                        switch (game.getPlayers().size()) {
                            case 2:
                                bonus = switch (points) {
                                    case 8 -> 4;
                                    case 4 -> 0;
                                    default -> bonus;
                                };
                                break;
                            case 3:
                                bonus = switch (points) {
                                    case 8 -> 6;
                                    case 6 -> 4;
                                    case 4 -> 0;
                                    default -> bonus;
                                };
                                break;
                            case 4:
                                bonus = switch (points) {
                                    case 8 -> 6;
                                    case 6 -> 4;
                                    case 4 -> 3;
                                    case 3 -> 0;
                                    default -> bonus;
                                };
                                break;
                        }
                    }
                }
                i++;
            }
        } else if (pointsGained.get(0) != 0 || pointsGained.get(1) != 0) {
            if (pointsGained.get(0) != 0) {
                points = pointsGained.get(0);
            } else if (pointsGained.get(1) != 0) {
                points = pointsGained.get(1);
            }

            for (CommonGoalCard commonGoalCard : game.getCommonGoalCardToWinners().keySet()) {
                List<Player> currentWinners = game.getCommonGoalCardToWinners().get(commonGoalCard);
                List<Player> oldWinners = tempMap.get(commonGoalCard);
                if (oldWinners == null || currentWinners.size() > oldWinners.size()) {

                    int bonus = 0;

                    switch (game.getPlayers().size()) {
                        case 2:
                            bonus = switch (points) {
                                case 8 -> 4;
                                case 4 -> 0;
                                default -> bonus;
                            };
                        case 3:
                            bonus = switch (points) {
                                case 8 -> 6;
                                case 6 -> 4;
                                case 4 -> 0;
                                default -> bonus;
                            };
                        case 4:
                            bonus = switch (points) {
                                case 8 -> 6;
                                case 6 -> 4;
                                case 4 -> 3;
                                case 3 -> 0;
                                default -> bonus;
                            };
                    }
                }
            }
        }
    }

    /**
     * Checks the shelf to determine if any player has completed it.
     */
    private void checkShelf() {

        int playerNum = game.getPlayers().size();

        //System.out.println("\n\nEffettuando il controllo delle Shelf...");

        //System.out.println("\n\nEffettuando il controllo delle Shelf...");
        if (game.getCurrentPlayer().getShelf().isFull()) {
            int pointss = game.getCurrentPlayer().getScore();
            game.assignAdditionalPoint(game.getCurrentPlayer());
            if (pointss != game.getCurrentPlayer().getScore()) {
//                System.out.println("\n\nComplimenti " + game.getCurrentPlayer().getUsername() + " hai vinto 1 punto per aver" +
//                        " completato per primo la Shelf!\nOra si giocherà fino al giocatore " + game.getPlayers().get(playerNum-1) +
//                        " per poi terminare la partita!\n\n"
//                );
                if (game.getPlayers().get(playerNum - 1).getUsername().equals(game.getCurrentPlayer().getUsername())) {
//                    System.out.println("Dato che sei tu il giocatore " + game.getPlayers().get(playerNum-1) + " la partita termina ora!" +
//                            "\nProseguiamo con il calcolo dei punteggi per scoprire il vincitore!!\n\n");
                }
            }
        } else {
            // System.out.println("Nessuno ha completato la Shelf");
        }

        //System.out.println("...Controllo effettuato\n\n");

    }

    /**
     * The `Worker` class represents a Swing worker that performs background tasks for the game page.
     */
    private class Worker extends SwingWorker<String, Void> {
        private final Client client;
        private final App app;

        /**
         * Constructs a new `Worker` object with the given `App` instance.
         *
         * @param app The `App` instance representing the application.
         */
        public Worker(App app) {
            this.client = app.getClient();
            this.app = app;
        }

        /**
         * Updates the static components of the game page.
         */
        public void updateStaticView() {
            commonGoalCards[0].setCommonGoalCard(game.getCommonGoalCards().get(0));
            commonGoalCards[0].setFaceUp(true);

            commonGoalCards[1].setCommonGoalCard(game.getCommonGoalCards().get(1));
            commonGoalCards[1].setFaceUp(true);

            Player player = game.getPlayers().stream().filter(x -> x.getUsername().equals(client.username)).findFirst().orElse(null);

            if (player != null) {
                personalGoalCard.setNumber(player.getPersonalGoalCard().getNumber());
                personalGoalCard.setFaceUp(true);

                if (!player.getShelf().isEmpty()) {
                    bookshelf.setBookshelf(player.getShelf());
                }
            }
        }

        /**
         * Updates the dynamic components of the game page.
         */
        public void updateDynamicView() {
            livingRoom.setBoard(game.getLivingRoom().getBoard());

            int j = 0;

            for (int i = 0; i < game.getPlayers().size(); i++) {
                if (!Objects.equals(game.getPlayers().get(i).getUsername(), client.username)) {
                    miniBookshelves[j].setBookshelf(game.getPlayers().get(i).getShelf());
                    j++;
                }
            }
        }

        @Override
        protected String doInBackground() {
            Message message;

            try {
                message = client.nextMessage();

                while (!(message instanceof MatchTurnForwardMessage)) {
                    message = client.nextMessage();
                }

                game = ((MatchTurnForwardMessage) message).getGame();
                updateStaticView();

                while (!(message instanceof MatchEndMessage)) {
                    System.out.println("Repeat...");

                    if (message instanceof MatchTurnForwardMessage) {
                        game = ((MatchTurnForwardMessage) message).getGame();
                        updateDynamicView();

                        if (game.getCurrentPlayer().getUsername().equals(client.username)) {
                            app.setTitle("My Shelfie – È il tuo turno");
                            onDiscard(null);
                            confirmButton.setEnabled(true);

                            while (confirmButton.isEnabled()) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            checkCommonGoalCards();
                            checkShelf();

                            bookshelf.setBookshelf(game.getCurrentPlayer().getShelf());
                            game.passTurn();

                            try {
                                System.out.println("Turn forward");
                                message = client.turnForward(new MatchTurnRequestMessage(game, ""));
                                System.out.println("Turn forward sent");
                            } catch (IOException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            app.setTitle("My Shelfie - È il turno di un altro giocatore");
                            System.out.println("Next message");
                            message = client.nextMessage();
                            System.out.println("Next message received");
                        }
                    }
                }

                int personalGoalCardPoints = -1;
                int adjacentItemTilesPoints = -1;
                int commonGoalCardPoints = -1;
                int bonusPoints = -1;

                for (Player player : game.getPlayers()) {
                    int personal = player.getPersonalGoalCard().checkBookshelf(player.getShelf());
                    player.addPoints(personal);

                    int temp = player.getScore();
                    Game.checkAdjacentItemTiles(player);

                    int adjacent = player.getScore() - temp;

                    if (player.getUsername().equals(client.username)) {
                        adjacentItemTilesPoints = adjacent;
                        personalGoalCardPoints = personal;
                        commonGoalCardPoints = player.getScore() - adjacent - personal;

                        if (commonGoalCardPoints % 2 != 0) {
                            commonGoalCardPoints--;
                        }

                        bonusPoints = player.getScore() - adjacentItemTilesPoints - personalGoalCardPoints - commonGoalCardPoints;
                    }
                }

                app.showResultsPage(personalGoalCardPoints, commonGoalCardPoints, adjacentItemTilesPoints, bonusPoints, game.checkWinners().stream().anyMatch(player -> player.getUsername().equals(client.username)));
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            return null;
        }
    }
}
