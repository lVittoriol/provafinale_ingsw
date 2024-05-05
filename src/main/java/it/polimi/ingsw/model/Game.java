package it.polimi.ingsw.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.polimi.ingsw.model.commongoalcard.*;
import it.polimi.ingsw.model.exceptions.FullColumnException;
import it.polimi.ingsw.model.exceptions.NotValidPickException;
import it.polimi.ingsw.model.exceptions.TrappedCardException;
import it.polimi.ingsw.utils.Position;

import java.io.Serializable;
import java.util.*;
import java.util.stream.IntStream;

/**
 * The Game class represents a game session of the board game.
 * It manages the game flow, player turns, scoring, and game ending conditions.
 *
 * @author Francesco Guarnello
 * @author Vittorio La Rosa
 * @author Emanuele Fossati
 * @author Marcelo S. Hernandez
 */

public class Game implements Serializable {
    private static final int MAX_COMMON_GOAL_CARD_COUNT = 12;
    private static final int MAX_PERSONAL_GOAL_CARD_COUNT = 12;
    private static final int COMMON_GOAL_CARD_COUNT = 2;
    private static final int MAX_PLAYER_COUNT = 4;
    private final Map<CommonGoalCard, List<Player>> commonGoalCardToWinners;
    private final LivingRoom livingRoom;
    private List<PersonalGoalCard> personalGoalCards;
    private final List<Player> players;
    private Player currentPlayer;
    private boolean isAdditionalPointAssigned = false;

    /**
     * Constructs and initializes a new game with the specified player names.
     *
     * @param playerNames an array of player names
     * @throws IllegalArgumentException if the number of players exceeds the maximum allowed
     * @author Francesco Guarnello
     * @author Vittorio La Rosa
     * @author Emanuele Fossati
     * @author Marcelo S. Hernandez
     */
    public Game(String[] playerNames) {
        if (playerNames.length > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("the number of players must be less than or equal to " + MAX_PLAYER_COUNT);
        }

        this.commonGoalCardToWinners = new HashMap<>();
        this.players = new ArrayList<>(playerNames.length);

        this.initializePersonalGoalCards();
        drawCommonGoalCards();

        Arrays.stream(playerNames).forEach(playerName -> this.players.add(new Player(playerName, new Bookshelf(), this.drawPersonalGoalCard())));

        this.livingRoom = new LivingRoom(players.size());

        this.currentPlayer = players.get(0);
    }

    public Game(JsonObject jsonObject) {
        Gson gson = new Gson();
        commonGoalCardToWinners = new HashMap<>();

        for (String key : jsonObject.get("commonGoalCardToWinners").getAsJsonObject().keySet()) {
            CommonGoalCard commonGoalCard = switch (key) {
                case "1" -> new CommonGoalCard1();
                case "2" -> new CommonGoalCard2();
                case "3" -> new CommonGoalCard3();
                case "4" -> new CommonGoalCard4();
                case "5" -> new CommonGoalCard5();
                case "6" -> new CommonGoalCard6();
                case "7" -> new CommonGoalCard7();
                case "8" -> new CommonGoalCard8();
                case "9" -> new CommonGoalCard9();
                case "10" -> new CommonGoalCard10();
                case "11" -> new CommonGoalCard11();
                case "12" -> new CommonGoalCard12();
                default -> throw new IllegalStateException("Couldn't parse CommonGoalCard from JSON");
            };

            commonGoalCardToWinners.put(commonGoalCard, gson.fromJson(jsonObject.get("commonGoalCardToWinners").getAsJsonObject().get(key), List.class));
        }

        livingRoom = gson.fromJson(jsonObject.get("livingRoom"), LivingRoom.class);
        players = new ArrayList<>();

        for (JsonElement jsonPlayer : jsonObject.get("players").getAsJsonArray().asList()) {
            Player player = gson.fromJson(jsonPlayer, Player.class);
            players.add(player);
        }

        currentPlayer = gson.fromJson(jsonObject.get("currentPlayer"), Player.class);
        isAdditionalPointAssigned = gson.fromJson(jsonObject.get("isAdditionalPointAssigned"), Boolean.class);
    }

    /**
     * Checks the adjacent item tiles on the player's shelf and calculates the points earned based on the groups formed.
     * The calculated points are added to the player's score.
     *
     * @param player The player whose adjacent item tiles need to be checked.
     */
    public static void checkAdjacentItemTiles(Player player) {

        int totalPoints = 0;

        List<List<Position>> groups = player.getShelf().getGroupsOfAdjacentItemTiles();
        for (List<Position> group : groups) {
            int groupSize = group.size();

            if (groupSize == 3 || groupSize == 4) {
                totalPoints += groupSize - 1;
            } else if (groupSize == 5) {
                totalPoints += 5;
            } else if (groupSize >= 6) {
                totalPoints += 8;
            }
        }

        player.setScore(player.getScore() + totalPoints);



        /*
        int totalPoints = 0;
        int points;
        List<List<ItemTile>> groups = player.getShelf().GroupsAdjacentItemTile();

        for (List<ItemTile> group : groups) {
            int groupSize = group.size();
            if (groupSize == 3 || groupSize == 4) {
                points = groupSize - 1;
                totalPoints += points;
            }
            else if(groupSize == 5) totalPoints+=5;
            else if(groupSize >= 6) totalPoints+=6;
        }
        currentPlayer.setScore(currentPlayer.getScore() + totalPoints);
        groups.clear();*/
    }

    /**
     * Draws the common goal cards for the game.
     */
    private void drawCommonGoalCards() {
        List<Integer> indexes = new ArrayList<>(IntStream.range(1, MAX_COMMON_GOAL_CARD_COUNT + 1).boxed().toList());
        Collections.shuffle(indexes);

        for (int i = 0; i < COMMON_GOAL_CARD_COUNT; i++) {
            commonGoalCardToWinners.put(CommonGoalCardFactory.createCommonGoalCard(indexes.get(i)), Collections.emptyList());
        }
    }

    /**
     * Draws a personal goal card for a player.
     *
     * @return the drawn personal goal card
     */

    private PersonalGoalCard drawPersonalGoalCard() {
        Random random = new Random();
        int cardIndex = random.nextInt((this.personalGoalCards.size()));

        PersonalGoalCard card = this.personalGoalCards.get(cardIndex);
        this.personalGoalCards.remove(cardIndex);

        return card;
    }

    /**
     * Checks the living room to see if it needs a reset.
     */
    public void checkLivingRoom() {
        livingRoom.checkLivingRoom();
    }

    /**
     * Returns the living room object.
     *
     * @return the living room object
     */
    public LivingRoom getLivingRoom() {
        return this.livingRoom;
    }

    /*
     * Checks if a specific column on the shelf can accommodate the picked tiles.
     *
     * @param pickedTiles the list of picked tiles
     * @param column      the column index on the shelf
     * @return true if the column can accommodate the tiles, false otherwise

    private boolean checkColumn(List<ItemTile> pickedTiles, int column){
        int firstAvailableRow = -1;


        for(int row = Bookshelf.ROW_COUNT-1; row >= 0; row ++){
            if(currentPlayer.getShelf().getItemTile(row, column) == null){
                firstAvailableRow = row;
                break;
            }

        }
        if(firstAvailableRow == 0) return false;

        if(firstAvailableRow + 1 < pickedTiles.size()) return false;

        return true;
    }
    */

    /**
     * Returns the list of common goal cards in the game.
     *
     * @return the list of common goal cards
     */
    public List<CommonGoalCard> getCommonGoalCards() {
        return new ArrayList<>(commonGoalCardToWinners.keySet());
    }

    /**
     * Picks item tiles from the living room within the specified range.
     *
     * @param start the starting position of the range
     * @param end   the ending position of the range
     * @return the list of picked item tiles
     * @throws TrappedCardException  if a trapped card is encountered while picking tiles
     * @throws NotValidPickException if the specified range is invalid
     */
    public ArrayList<ItemTile> pickTiles(Position start, Position end) throws TrappedCardException, NotValidPickException {
        return livingRoom.pickTiles(start, end);
    }

    /**
     * Places the picked tiles in a specific column on the player's bookshelf.
     *
     * @param pickedTiles the list of picked tiles
     * @param column      the column index on the shelf
     * @throws FullColumnException if the placement is invalid
     */
    public void tilesInColumn(List<ItemTile> pickedTiles, int column) throws FullColumnException {
        currentPlayer.placeTiles(pickedTiles, column);
    }

    /**
     * Orders the picked tiles based on the given ordering.
     *
     * @param pickedTiles   the list of picked tiles
     * @param orderingTiles the list of ordering indices
     */
    public List<ItemTile> orderTiles(List<ItemTile> pickedTiles, List<Integer> orderingTiles) {
        List<ItemTile> orderedTiles = new ArrayList<>(pickedTiles.size());

        for (int index : orderingTiles) {
            orderedTiles.add(pickedTiles.get(index));
        }

        return orderedTiles;
    }

    /**
     * Checks a player's personal goal card and updates their score accordingly.
     *
     * @param player the player whose personal goal card needs to be checked
     */
    public void checkPersonalGoalCard(Player player) {
        player.setScore(player.getScore() + player.getPersonalGoalCard().checkBookshelf(player.getShelf()));
    }

    /**
     * Calculates the bonus points obtained by players for common goal cards.
     * Returns a list containing the bonus points obtained by each player.
     *
     * @return A list of integers representing the bonus points obtained by the players.
     */
    public List<Integer> pointsCommonGoalCards() {
        List<Integer> bonusList = new ArrayList<>();

        for (CommonGoalCard commonGoalCard : commonGoalCardToWinners.keySet()) {
            List<Player> winners = new ArrayList<>(commonGoalCardToWinners.get(commonGoalCard));

            if (commonGoalCard.checkBookshelf(currentPlayer.getShelf()) && winners.size() < players.size() && !winners.contains(currentPlayer)) {


                winners.add(currentPlayer);

                int bonus = 0;

                switch (players.size()) {
                    case 2:
                        bonus = switch (winners.size()) {
                            case 1 -> 8;
                            case 2 -> 4;
                            default -> bonus;
                        };
                        break;
                    case 3:
                        bonus = switch (winners.size()) {
                            case 1 -> 8;
                            case 2 -> 6;
                            case 3 -> 4;
                            default -> bonus;
                        };
                        break;
                    case 4:
                        bonus = switch (winners.size()) {
                            case 1 -> 8;
                            case 2 -> 6;
                            case 3 -> 4;
                            case 4 -> 2;
                            default -> bonus;
                        };
                        break;
                }

                currentPlayer.setScore(currentPlayer.getScore() + bonus);
                bonusList.add(bonus);

            } else {
                bonusList.add(0);
            }
            commonGoalCardToWinners.put(commonGoalCard, winners);

        }
        return bonusList;
    }

    /**
     * Checks the scores of all players and determines the winners based on the highest score.
     *
     * @return A list of players who have the highest score and are considered winners.
     */
    public List<Player> checkWinners() {
        int maxPoints = -1;
        List<Player> winners = new ArrayList<>();

        for (Player player : players) {
            this.checkPersonalGoalCard(player);
            if (player.getScore() > maxPoints) {
                maxPoints = player.getScore();
                winners.clear();
                winners.add(player);
            } else if (player.getScore() == maxPoints) {
                winners.add(player);
            }
        }

        return winners;
    }

    /**
     * Returns the next player in the turn order.
     *
     * @return The next player in the turn order.
     */
    private Player nextPlayer() {
        int nextPlayerIndex = (this.players.indexOf(currentPlayer) + 1) % this.players.size();
        return players.get(nextPlayerIndex);
    }

    /**
     * Passes the turn to the next player.
     */
    public void passTurn() {
        this.currentPlayer = nextPlayer();
    }

    /**
     * Checks if the game has ended.
     *
     * @return {@code true} if at least one player's shelf is full and the current player is the first player, {@code false} otherwise.
     */
    public boolean hasEnded() {
        return players.stream().anyMatch(x -> x.getShelf().isFull()) && currentPlayer.equals(players.get(0));
    }

    /**
     * Assigns an additional point to the player if their shelf is full and the additional point has not been assigned yet.
     *
     * @param player The player to assign the additional point to.
     */
    public void assignAdditionalPoint(Player player) {
        if (!isAdditionalPointAssigned && player.getShelf().isFull()) {
            player.addPoints(1);
            isAdditionalPointAssigned = true;
        }
    }


    /**
     * Initializes the personal goal cards.
     */
    private void initializePersonalGoalCards() {
        this.personalGoalCards = new ArrayList<>();

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 0, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 0, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 1, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 2, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 3, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 5, 2)
                }
                , 1));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 1, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 2, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 2, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 3, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 4, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 5, 4)
                }, 2
        ));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 1, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 1, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 2, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 3, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 3, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 5, 0)
                }, 3
        ));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 0, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 2, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 2, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 3, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 4, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 4, 2)
                }, 4
        ));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 1, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 3, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 3, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 4, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 5, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 5, 3)
                }, 5
        ));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 0, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 0, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 2, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 4, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 4, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 5, 0)
                }, 6
        ));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 3, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 0, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 5, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 4, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 1, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 2, 1)
                }, 7
        ));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 2, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 1, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 4, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 5, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 0, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 3, 0)
                }, 8
        ));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 0, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 4, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 5, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 4, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 3, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 2, 2)
                }, 9
        ));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 5, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 4, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 3, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 2, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 1, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 0, 4)
                }, 10
        ));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 0, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 4, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 2, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 1, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 5, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 3, 2)
                }, 11
        ));

        this.personalGoalCards.add(new PersonalGoalCard(
                new PersonalGoalCard.Task[]{
                        new PersonalGoalCard.Task(ItemTile.Type.FRAME, 2, 2),
                        new PersonalGoalCard.Task(ItemTile.Type.GAME, 4, 4),
                        new PersonalGoalCard.Task(ItemTile.Type.PLANT, 1, 1),
                        new PersonalGoalCard.Task(ItemTile.Type.CAT, 5, 0),
                        new PersonalGoalCard.Task(ItemTile.Type.TROPHY, 3, 3),
                        new PersonalGoalCard.Task(ItemTile.Type.BOOK, 0, 2)
                }, 12
        ));
    }

    /**
     * Returns the current player.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the list of players.
     *
     * @return The list of players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the mapping of common goal cards to winners.
     *
     * @return The mapping of common goal cards to winners.
     */
    public Map<CommonGoalCard, List<Player>> getCommonGoalCardToWinners() {
        return commonGoalCardToWinners;
    }

}
