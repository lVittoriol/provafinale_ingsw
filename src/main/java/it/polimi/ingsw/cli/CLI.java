package it.polimi.ingsw.cli;

import it.polimi.ingsw.cli.controller.GameController;
import it.polimi.ingsw.cli.view.GameView;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.commongoalcard.CommonGoalCard;
import it.polimi.ingsw.model.exceptions.FullColumnException;
import it.polimi.ingsw.model.exceptions.NotValidPickException;
import it.polimi.ingsw.model.exceptions.TrappedCardException;
import it.polimi.ingsw.network.client.Client;
import it.polimi.ingsw.utils.Position;
import it.polimi.ingsw.utils.message.MatchEndMessage;
import it.polimi.ingsw.utils.message.MatchTurnForwardMessage;
import it.polimi.ingsw.utils.message.Message;
import it.polimi.ingsw.utils.message.request.MatchCreateRequestMessage;
import it.polimi.ingsw.utils.message.request.MatchJoinRequestMessage;
import it.polimi.ingsw.utils.message.request.MatchTurnRequestMessage;
import it.polimi.ingsw.utils.message.request.NewUsernameMessage;
import it.polimi.ingsw.utils.message.response.UsernameNotAvailableMessage;

import java.io.IOException;
import java.util.*;

/**
 * The CLI class represents the command-line interface for the game client.
 * It allows players to create or join a match and interact with the game through the command line.
 *
 * @author Vittorio La Rosa
 */
public class CLI {
    private Scanner scanner;
    private Client client;
    private String command;

    /**
     * Constructs a new CLI object.
     */
    public CLI() {
        try {
            this.client = new Client();
            this.scanner = new Scanner(System.in);
            this.command = "";

            if (client.isFirstMatch) {
                command = "C";
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method is the entry point of the CLI application.
     *
     * @param args the command-line arguments
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {

        CLI cli = new CLI();
        try {

            while (!cli.command.equals("C") && !cli.command.equals("J")) {
                System.out.println("Do you want to create a match or join one? [C/J]");
                cli.command = cli.scanner.nextLine().toUpperCase();

            }

            if (cli.command.equals("C")) { //creo una partita

                cli.createMatch();
                System.out.println("Codice partita: " + cli.client.matchId);

            } else { //mi unisco ad una partita
                cli.joinMatch();

            }

            GameController gameController = new GameController(new Game(new String[]{"", ""}), new GameView(cli.client.username));
            List<Player> winners;

            Message message = cli.client.nextMessage();

            while (!(message instanceof MatchTurnForwardMessage)) {
                message = cli.client.nextMessage();
            }

            while (!(message instanceof MatchEndMessage)) {

                if (message instanceof MatchTurnForwardMessage) {
                    gameController.setModel(((MatchTurnForwardMessage) message).getGame());
                    if (((MatchTurnForwardMessage) message).getGame().getCurrentPlayer().getUsername().equals(cli.client.username)) {
                        playPlayerTurn(cli.scanner, gameController);
                        checkCommonGoalCards(gameController);
                        checkShelf(gameController);
                        gameController.passTurn();
                        message = cli.client.turnForward(new MatchTurnRequestMessage(gameController.getModel(), ""));
                    } else {
                        gameController.updateView();
                        message = cli.client.nextMessage();
                    }
                }

            }

            calculateScores(gameController);

            winners = gameController.checkWinners();

            if (winners.stream().anyMatch(player -> player.getUsername().equals(cli.client.username))) {
                System.out.println(winners.size() == 1 ? "Vincitore: " : "Vincitori: ");

                for (Player winner : winners) {
                    System.out.println(winner.getUsername() +
                            " con " + winner.getScore() + " punti.");
                }
                System.out.println("Congratulazioni!");


            } else {
                System.out.println("Purtroppo hai perso.");
            }

        } catch (Exception e) {
            System.out.println("La partita è crashata");
        }
    }

    /**
     * Prompts the user for the number of players in the match.
     *
     * @param scanner the scanner object used for user input
     * @return the number of players
     */
    private static int getPlayerNumber(Scanner scanner) {
        int playerNum = 0;

        while (playerNum != 2 && playerNum != 3 && playerNum != 4) {
            try {
                System.out.println("Quanti giocatori?");
                playerNum = Integer.parseInt(scanner.nextLine());
                if (playerNum < 2 || playerNum > 4) {
                    System.out.println("Inserire un numero da 2 a 4");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inserire un numero");
            }
        }

        return playerNum;
    }

    /**
     * Prompts the user for the username.
     *
     * @param scanner the scanner object used for user input
     * @return the username
     */
    private static String getUsername(Scanner scanner) {


        String username;
        while (true) {
            System.out.println("Inserire username del player ");
            username = scanner.nextLine();

            if (username.length() < 4 || username.length() > 32) {
                System.out.println("Il nome del giocatore deve avere almeno 4 caratteri e al massimo 32 caratteri.");
                continue;
            }
            break;
        }


        return username;
    }

    /**
     * Prompts the user for the match ID.
     *
     * @param scanner the scanner object used for user input
     * @return the match ID
     */
    private static String getMatchId(Scanner scanner) {

        String matchId;
        while (true) {
            System.out.print("Inserire matchId: ");
            matchId = scanner.nextLine();

            if (matchId.length() != 36) {
                System.out.println("Il matchId è una stringa di 36 caratteri, inclusi '-'");
                continue;
            }
            break;
        }

        return matchId;
    }

    /**
     * Plays a turn for the current player.
     *
     * @param scanner        the scanner object used for user input
     * @param gameController the game controller object
     */
    public static void playPlayerTurn(Scanner scanner, GameController gameController) {

        Player currentPlayer = gameController.getCurrentPlayer();
        System.out.println("\n\nOra hai " + currentPlayer.getScore() + " punti!\n");
        Position start = new Position(-1, -1);
        Position end = new Position(-1, -1);
        int column;
        List<ItemTile> pickedTiles;
        gameController.updateView();
        while (true) {

            try {
                System.out.println("Inserire le coordinate della prima tessera che si vuole pescare: ");
                int startingRow = Integer.parseInt(scanner.nextLine()) - 1;
                start.setRow(startingRow);
                int startingColumn = Integer.parseInt(scanner.nextLine()) - 1;
                start.setColumn(startingColumn);

                System.out.println("Inserire le coordinate dell' ultima tessera che si vuole pescare: ");
                int endingRow = Integer.parseInt(scanner.nextLine()) - 1;
                end.setRow(endingRow);
                int endingColumn = Integer.parseInt(scanner.nextLine()) - 1;
                end.setColumn(endingColumn);

                int numberOfTilesChosen;

                if (startingRow == endingRow) {
                    if (endingColumn > startingColumn) {
                        numberOfTilesChosen = 1 + (endingColumn - startingColumn);
                    } else {
                        numberOfTilesChosen = 1 + (startingColumn - endingColumn);
                    }
                } else if (startingColumn == endingColumn) {
                    if (endingRow > startingRow) {
                        numberOfTilesChosen = 1 + (endingRow - startingRow);
                    } else {
                        numberOfTilesChosen = 1 + (startingRow - endingRow);
                    }
                } else throw new IndexOutOfBoundsException();


                if (numberOfTilesChosen <= gameController.getCurrentPlayer().getShelf().maxTilesInBookshelf()) {
                    pickedTiles = gameController.pickTiles(start, end);
                    System.out.println("\n\nTessere scelte:");
                    for (int i = 0; i < pickedTiles.size(); i++) {
                        ItemTile tile = pickedTiles.get(i);
                        System.out.println((i + 1) + ") " + tile);
                    }


                    break;
                } else {
                    System.out.println("Hai scelto troppe tessere da inserire. Scegline massimo " +
                            gameController.getCurrentPlayer().getShelf().maxTilesInBookshelf());
                }

            } catch (NumberFormatException e) {
                System.out.println("Inserire un numero");
            } catch (TrappedCardException e) {
                System.out.println("Una delle carte selezionate è bloccata");
            } catch (NotValidPickException e) {
                System.out.println("Selezionare solo tessere disposte orizzontalmente o verticalmente");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Selezione non valida");
            }

        }
        gameController.updateView();

        System.out.println("\nIn che ordine vuoi inserire le tessere?\n");
        List<ItemTile> orderedTiles;


        System.out.println("PickedTiles: " + pickedTiles + "        [BASSO][MEZZO][ALTO]        [0][1][2]");

        List<Integer> orderingTiles = new ArrayList<>();
        Set<Integer> indexSet = new HashSet<>();
        for (ItemTile tile : pickedTiles) {
            int index;
            do {
                System.out.println("Inserisci l'indice per la Tile " + tile + " (valori validi da 0 a " + (pickedTiles.size() - 1) + "): ");
                try {
                    index = Integer.parseInt(scanner.nextLine());
                    if (index < 0 || index >= pickedTiles.size()) {
                        System.out.println("L'indice inserito non è valido. Riprova.");
                    } else if (indexSet.contains(index)) {
                        System.out.println("L'indice " + index + " è già stato inserito. Riprova.");
                    } else {
                        indexSet.add(index);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Inserisci un numero. Riprova.");
                }
            } while (true);
            orderingTiles.add(index);
        }
        orderedTiles = gameController.orderTiles(pickedTiles, orderingTiles);
        indexSet.clear();

        System.out.println("OrderedTiles: " + orderedTiles);

        boolean confirmed = false;
        while (!confirmed) {
            System.out.println("Vuoi confermare la scelta? (S/N): ");
            String response = scanner.next();
            scanner.nextLine();
            if (response.equalsIgnoreCase("s")) {
                confirmed = true;
            } else if (response.equalsIgnoreCase("n")) {
                orderingTiles.clear();
                for (ItemTile tile : pickedTiles) {
                    System.out.println("Inserisci l'indice per la Tile " + tile + " (valori validi da 0 a " + (pickedTiles.size() - 1) + "): ");
                    int index = Integer.parseInt(scanner.nextLine());
                    orderingTiles.add(index);
                }
                orderedTiles = gameController.orderTiles(pickedTiles, orderingTiles);
                System.out.println("OrderedTiles: " + orderedTiles);
            } else {
                System.out.println("Risposta non valida. Riprova.");
            }
        }


        while (true) {
            try {
                while (true) {
                    System.out.println("Inserire la colonna in cui si vogliono piazzare le tessere");
                    column = Integer.parseInt(scanner.nextLine()) - 1;
                    System.out.println("Vuoi confermare la scelta della colonna " + (column + 1) + "? [S/N]");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("S")) {
                        gameController.tilesInColumn(orderedTiles, column);
                        break;
                    } else if (confirm.equalsIgnoreCase("N")) {
                    } else {
                        System.out.println("Risposta non valida");
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Inserire un numero");
            } catch (FullColumnException e) {
                System.out.println("Non c'è abbastanza spazio nella colonna");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Colonna non valida");
            }
        }
        gameController.checkLivingRoom();

    }

    /**
     * Checks the common goal cards and awards points to the players.
     *
     * @param gameController the game controller object
     */
    private static void checkCommonGoalCards(GameController gameController) {
        System.out.println("\n\nEffettuando il controllo delle CommonGoalCards...");

        System.out.println("\n\nEffettuando il controllo delle CommonGoalCards...");
        Map<CommonGoalCard, List<Player>> tempMap = new HashMap<>(gameController.getCommonGoalCardsToWinners());
        List<Integer> pointsGained = gameController.pointsCommonGoalCards();
        int points = 0;

        if (pointsGained.get(0) != 0 && pointsGained.get(1) != 0) {
            System.out.println("Complimenti " + gameController.getCurrentPlayer().getUsername() + ", hai completato entrambe le CommonGoalCard!");

            for (CommonGoalCard commonGoalCard : gameController.getCommonGoalCardsToWinners().keySet()) {
                int i = 0;
                if (pointsGained.get(i) != 0) {
                    points = pointsGained.get(i);
                    List<Player> currentWinners = gameController.getCommonGoalCardsToWinners().get(commonGoalCard);
                    List<Player> oldWinners = tempMap.get(commonGoalCard);

                    if (oldWinners == null || currentWinners.size() > oldWinners.size()) {

                        int bonus = 0;

                        switch (gameController.getPlayers().size()) {
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

                        int currentValueOfCommonGoalCard = bonus;

                        System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                                "\nComplimenti " + gameController.getCurrentPlayer().getUsername() + " hai vinto " +
                                points + " punti poiché hai completato la CommonGoalCard:\n" + commonGoalCard.getDescription() +
                                "\nOra se completata da qualcun altro la CommonGoalCard assegnerà " + currentValueOfCommonGoalCard + " punti.\n");
                    }
                }
                i++;
            }

            System.out.println("Ora hai " + gameController.getCurrentPlayer().getScore() + " punti in totale!" +
                    "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
        } else if (pointsGained.get(0) != 0 || pointsGained.get(1) != 0) {

            if (pointsGained.get(0) != 0) {
                points = pointsGained.get(0);
            } else if (pointsGained.get(1) != 0) {
                points = pointsGained.get(1);
            }
            for (CommonGoalCard commonGoalCard : gameController.getCommonGoalCardsToWinners().keySet()) {
                List<Player> currentWinners = gameController.getCommonGoalCardsToWinners().get(commonGoalCard);
                List<Player> oldWinners = tempMap.get(commonGoalCard);
                if (oldWinners == null || currentWinners.size() > oldWinners.size()) {

                    int bonus = 0;

                    switch (gameController.getPlayers().size()) {
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

                    int currentValueOfCommonGoalCard = bonus;

                    System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                            "\nComplimenti " + gameController.getCurrentPlayer().getUsername() + " hai vinto " +
                            points + " punti poiché hai completato la CommonGoalCard:\n" + commonGoalCard.getDescription() +
                            "\nOra se completata da qualcun altro la CommonGoalCard assegnerà " + currentValueOfCommonGoalCard + " punti.\n" +
                            "Ora hai " + gameController.getCurrentPlayer().getScore() + " punti in totale!" +
                            "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
                }
            }
        } else {
            System.out.println("Nessuno ha completato alcuna CommonGoalCard");
        }

        System.out.println("...Controllo effettuato");
    }

    /**
     * Checks the status of the players' shelves and assigns additional points if a player's shelf is full.
     *
     * @param gameController the game controller object
     */
    private static void checkShelf(GameController gameController) {

        int playerNum = gameController.getPlayers().size();

        System.out.println("\n\nEffettuando il controllo delle Shelf...");

        System.out.println("\n\nEffettuando il controllo delle Shelf...");
        if (gameController.getCurrentPlayer().getShelf().isFull()) {
            int pointss = gameController.getCurrentPlayer().getScore();
            gameController.assignAdditionalPoint(gameController.getCurrentPlayer());
            if (pointss != gameController.getCurrentPlayer().getScore()) {
                System.out.println("\n\nComplimenti " + gameController.getCurrentPlayer().getUsername() + " hai vinto 1 punto per aver" +
                        " completato per primo la Shelf!\nOra si giocherà fino al giocatore " + gameController.getPlayers().get(playerNum - 1) +
                        " per poi terminare la partita!\n\n"
                );
                if (gameController.getPlayers().get(playerNum - 1).getUsername().equals(gameController.getCurrentPlayer().getUsername())) {
                    System.out.println("Dato che sei tu il giocatore " + gameController.getPlayers().get(playerNum - 1) + " la partita termina ora!" +
                            "\nProseguiamo con il calcolo dei punteggi per scoprire il vincitore!!\n\n");
                }
            }
        } else {
            System.out.println("Nessuno ha completato la Shelf");
        }

        System.out.println("...Controllo effettuato\n\n");

    }

    /**
     * Calculates the scores for each player based on their personal goal cards and adjacent item tiles.
     *
     * @param gameController the game controller object
     */
    private static void calculateScores(GameController gameController) {
        for (Player player : gameController.getPlayers()) {
            System.out.println("\n\nEffettuando il controllo degli Obiettivi Personali per il giocatore " + player.getUsername() + "...");
            player.addPoints(player.getPersonalGoalCard().checkBookshelf(player.getShelf()));
            System.out.println("\n\nComplimenti " + player.getUsername() + " hai ricevuto " + player.getPersonalGoalCard().checkBookshelf(player.getShelf()) + " punti dagli obiettivi Personali\n" +
                    "Ora il tuo totale è " + player.getScore() + " punti!\n\n");
            System.out.println("...Controllo Obiettivi Personali per il giocatore " + player.getUsername() + " effettuato\n\n");


            System.out.println("\n\nEffettuando il controllo dei Gruppi di uguali Tiles adiacenti per il giocatore " + player.getUsername() + "...");
            int prePoints = player.getScore();
            gameController.checkAdjacentItemTiles(player);
            int receivedPoints = player.getScore() - prePoints;
            System.out.println("\n\nComplimenti " + player.getUsername() + " hai ricevuto " + receivedPoints + " punti dai gruppi di uguali Tiles" +
                    " adiacenti!\nOra il tuo totale è " + player.getScore() + " punti!\n\n");
            System.out.println("...Controllo dei Gruppi di uguali Tiles adiacenti per il giocatore " + player.getUsername() + " effettuato\n\n");

        }
    }

    /**
     * Creates a new match by prompting the user for the number of players and username.
     */
    public void createMatch() {
        int playerNum = getPlayerNumber(scanner);
        String username = getUsername(scanner);
        try {
            Message message = client.createMatch(new MatchCreateRequestMessage(playerNum, username, ""));

            while (message instanceof UsernameNotAvailableMessage) {
                username = getUsername(scanner);
                message = client.newUsername(new NewUsernameMessage(username, "new username"));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Joins an existing match by prompting the user for the username and match ID.
     */
    public void joinMatch() {
        String username = getUsername(scanner);
        String matchId = getMatchId(scanner);
        try {
            Message message = client.joinMatch(new MatchJoinRequestMessage(username, matchId, ""));

            while (message instanceof UsernameNotAvailableMessage) {
                username = getUsername(scanner);
                message = client.newUsername(new NewUsernameMessage(username, "new username"));
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
