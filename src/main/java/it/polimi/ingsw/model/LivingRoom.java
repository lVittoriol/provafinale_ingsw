package it.polimi.ingsw.model;


import it.polimi.ingsw.model.exceptions.NotValidPickException;
import it.polimi.ingsw.model.exceptions.TrappedCardException;
import it.polimi.ingsw.utils.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * The LivingRoom class represents the model of a living room in a board game.
 * It contains a deck of cards and a board where the cards are placed.
 * The living room is responsible for managing the state of the board and the deck,
 * as well as performing operations such as picking tiles and resetting the living room.
 *
 * @author Vittorio La Rosa
 * @author Francesco Guarnello
 * @author Marcelo S. Hernandez
 **/

public class LivingRoom implements Serializable {
    private static final int MAX_PICKABLE_TILES = 3;
    private final Deck deck;
    private final Board board;

    /**
     * Constructs a new LivingRoom instance with the specified number of players.
     * Initializes the board and deck, and checks the state of the living room.
     *
     * @param playersNumber the number of players in the game
     */
    public LivingRoom(int playersNumber) {
        board = new Board();
        deck = new Deck();
        board.initBoard(playersNumber);
        checkLivingRoom();
    }

    /**
     * Retrieves the board of the living room.
     *
     * @return the board of the living room
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Checks the state of the living room and performs any necessary actions.
     * If it's time to reset the living room, the method calls resetLivingRoom().
     */
    public void checkLivingRoom() {
        if (board.timeToReset())
            resetLivingRoom();
    }

    /**
     * Resets the living room by clearing the board, shuffling the deck, and dealing new cards onto the board.
     */
    public void resetLivingRoom() {
        board.clearBoard(deck);
        deck.shuffle();
        board.dealCardsOnTheBoard(deck);
    }

    /**
     * Picks a range of tiles from the board based on the specified start and end positions.
     * The range can be a row or a column, but not both at the same time.
     * Throws NotValidPickException if the pick is not valid, and TrappedCardException if any card in the range is trapped.
     *
     * @param start the start position of the range
     * @param end   the end position of the range
     * @return an ArrayList containing the picked tiles
     * @throws NotValidPickException     if the pick is not valid
     * @throws TrappedCardException      if any card in the range is trapped
     * @throws IndexOutOfBoundsException if the range exceeds the maximum number or is less than the minimum number of selectable tiles
     */
    public ArrayList<ItemTile> pickTiles(Position start, Position end) throws NotValidPickException, TrappedCardException {
        return board.pickTiles(start, end);
    }

    /**
     * Retrieves the tile at the specified row and column on the board.
     *
     * @param row    the row index of the tile
     * @param column the column index of the tile
     * @return the ItemTile at the specified position
     */
    public ItemTile getTile(int row, int column) {
        return board.getTile(row, column);
    }

    /**
     * The Deck class represents a deck of item tiles.
     */
    public static class Deck implements Serializable {
        private final LinkedList<ItemTile> deck;

        /**
         * Constructs a new Deck instance and initializes it with item tiles of different types.
         */
        public Deck() {
            this.deck = new LinkedList<>();
            for (ItemTile.Type t : ItemTile.Type.values()) {
                if (t != ItemTile.Type.EMPTY && t != ItemTile.Type.BLOCKED)
                    for (int i = 0; i < 22; i++) {
                        deck.add(new ItemTile(t));

                    }
            }
            Collections.shuffle(deck);

        }

        /**
         * Retrieves and removes the first item tile from the deck.
         *
         * @return the first item tile in the deck
         */
        public ItemTile getFirstCard() {
            return deck.removeFirst();

        }

        /**
         * Adds the specified item tile to the deck.
         *
         * @param card the item tile to be added to the deck
         */
        public void add(ItemTile card) {
            deck.add(card);
        }

        /**
         * Shuffles the item tiles in the deck.
         */
        public void shuffle() {
            Collections.shuffle(deck);
        }

        /**
         * Returns the number of item tiles in the deck.
         *
         * @return the size of the deck
         */
        public int size() {
            return deck.size();
        }

    }

    public static class Board implements Serializable {
        public static final int BOARD_ROWS = 9;
        public static final int BOARD_COLUMNS = 9;
        private final ItemTile[][] board;

        /**
         * Constructs a new Board instance with blocked item tiles.
         */
        public Board() {
            this.board = new ItemTile[BOARD_ROWS][BOARD_COLUMNS];
            for (int i = 0; i < BOARD_ROWS; i++) {
                for (int j = 0; j < BOARD_COLUMNS; j++) {
                    board[i][j] = new ItemTile(ItemTile.Type.BLOCKED);
                }
            }
        }

        /**
         * Validates the specified row and column indices.
         *
         * @param row    the row index to validate
         * @param column the column index to validate
         * @throws IndexOutOfBoundsException if either the row or column index is out of bounds
         */
        private static void validatePosition(int row, int column) throws IndexOutOfBoundsException {
            if (row < 0 || row >= BOARD_ROWS) {
                throw new IndexOutOfBoundsException("`row` must be greater than or equal to zero and less than " + BOARD_ROWS);
            }

            if (column < 0 || column >= BOARD_COLUMNS) {
                throw new IndexOutOfBoundsException("`column` must be greater than or equal to zero and less than " + BOARD_COLUMNS);
            }
        }

        /**
         * Validates the specified position.
         *
         * @param position the position to validate
         * @throws IndexOutOfBoundsException if either the row or column index of the position is out of bounds
         */
        private static void validatePosition(Position position) throws IndexOutOfBoundsException {
            validatePosition(position.getRow(), position.getColumn());
        }

        /**
         * Initializes the board based on the specified number of players.
         *
         * @param playersNumber the number of players in the game
         */
        public void initBoard(int playersNumber) {
            for (int i = 0; i < BOARD_ROWS; i++) {

                if (i == 1) {
                    for (int j = 3; j < 5; j++) {
                        board[i][j].setType(ItemTile.Type.EMPTY);

                    }
                }
                if (i == 2) {
                    for (int j = 3; j < 6; j++) {
                        board[i][j].setType(ItemTile.Type.EMPTY);

                    }
                }
                if (i == 3) {
                    for (int j = 2; j < 8; j++) {
                        board[i][j].setType(ItemTile.Type.EMPTY);

                    }
                }
                if (i == 4) {
                    for (int j = 1; j < 8; j++) {
                        board[i][j].setType(ItemTile.Type.EMPTY);

                    }
                }
                if (i == 5) {
                    for (int j = 1; j < 7; j++) {
                        board[i][j].setType(ItemTile.Type.EMPTY);

                    }
                }
                if (i == 6) {
                    for (int j = 3; j < 6; j++) {
                        board[i][j].setType(ItemTile.Type.EMPTY);

                    }
                }
                if (i == 7) {
                    for (int j = 4; j < 6; j++) {
                        board[i][j].setType(ItemTile.Type.EMPTY);

                    }
                }

            }

            if (playersNumber >= 3) {
                board[0][3].setType(ItemTile.Type.EMPTY);
                board[2][2].setType(ItemTile.Type.EMPTY);
                board[2][6].setType(ItemTile.Type.EMPTY);
                board[3][8].setType(ItemTile.Type.EMPTY);
                board[5][0].setType(ItemTile.Type.EMPTY);
                board[6][2].setType(ItemTile.Type.EMPTY);
                board[6][6].setType(ItemTile.Type.EMPTY);
                board[8][5].setType(ItemTile.Type.EMPTY);
                if (playersNumber == 4) {
                    board[0][4].setType(ItemTile.Type.EMPTY);
                    board[1][5].setType(ItemTile.Type.EMPTY);
                    board[3][1].setType(ItemTile.Type.EMPTY);
                    board[4][0].setType(ItemTile.Type.EMPTY);
                    board[4][8].setType(ItemTile.Type.EMPTY);
                    board[5][7].setType(ItemTile.Type.EMPTY);
                    board[7][3].setType(ItemTile.Type.EMPTY);
                    board[8][4].setType(ItemTile.Type.EMPTY);

                }
            }
        }

        /**
         * Checks if a card at the specified position has no adjacent cards.
         *
         * @param p the position of the card to check
         * @return true if the card is alone, false otherwise
         */
        private boolean aloneCard(Position p) {
            boolean rowBefore;
            boolean rowAfter;
            boolean columnBefore;
            boolean columnAfter;

            if (p.getRow() == 0) {
                rowBefore = true;
                rowAfter = (board[p.getRow() + 1][p.getColumn()].getType() == ItemTile.Type.BLOCKED || board[p.getRow() + 1][p.getColumn()].getType() == ItemTile.Type.EMPTY);
            } else if (p.getRow() == 8) {
                rowBefore = (board[p.getRow() - 1][p.getColumn()].getType() == ItemTile.Type.BLOCKED || board[p.getRow() - 1][p.getColumn()].getType() == ItemTile.Type.EMPTY);
                rowAfter = true;
            } else {
                rowBefore = (board[p.getRow() - 1][p.getColumn()].getType() == ItemTile.Type.BLOCKED || board[p.getRow() - 1][p.getColumn()].getType() == ItemTile.Type.EMPTY);
                rowAfter = (board[p.getRow() + 1][p.getColumn()].getType() == ItemTile.Type.BLOCKED || board[p.getRow() + 1][p.getColumn()].getType() == ItemTile.Type.EMPTY);
            }
            if (p.getColumn() == 0) {
                columnBefore = true;
                columnAfter = (board[p.getRow()][p.getColumn() + 1].getType() == ItemTile.Type.BLOCKED || board[p.getRow()][p.getColumn() + 1].getType() == ItemTile.Type.EMPTY);
            } else if (p.getColumn() == 8) {
                columnBefore = (board[p.getRow()][p.getColumn() - 1].getType() == ItemTile.Type.BLOCKED || board[p.getRow()][p.getColumn() - 1].getType() == ItemTile.Type.EMPTY);
                columnAfter = true;
            } else {
                columnBefore = (board[p.getRow()][p.getColumn() - 1].getType() == ItemTile.Type.BLOCKED || board[p.getRow()][p.getColumn() - 1].getType() == ItemTile.Type.EMPTY);
                columnAfter = (board[p.getRow()][p.getColumn() + 1].getType() == ItemTile.Type.BLOCKED || board[p.getRow()][p.getColumn() + 1].getType() == ItemTile.Type.EMPTY);
            }
            return rowBefore && rowAfter && columnBefore && columnAfter;
        }

        /**
         * Checks if a card at the specified position is trapped, i.e., surrounded by blocked or non-empty cards.
         *
         * @param p the position of the card to check
         * @return true if the card is trapped, false otherwise
         */
        public boolean trappedCard(Position p) {
            boolean rowBefore;
            boolean rowAfter;
            boolean columnBefore;
            boolean columnAfter;

            if (p.getRow() == 0) {
                rowBefore = true;
                rowAfter = (board[p.getRow() + 1][p.getColumn()].getType() != ItemTile.Type.BLOCKED && board[p.getRow() + 1][p.getColumn()].getType() != ItemTile.Type.EMPTY);

            } else if (p.getRow() == 8) {
                rowBefore = (board[p.getRow() - 1][p.getColumn()].getType() != ItemTile.Type.BLOCKED && board[p.getRow() - 1][p.getColumn()].getType() != ItemTile.Type.EMPTY);
                rowAfter = true;
            } else {
                rowBefore = (board[p.getRow() - 1][p.getColumn()].getType() != ItemTile.Type.BLOCKED && board[p.getRow() - 1][p.getColumn()].getType() != ItemTile.Type.EMPTY);
                rowAfter = (board[p.getRow() + 1][p.getColumn()].getType() != ItemTile.Type.BLOCKED && board[p.getRow() + 1][p.getColumn()].getType() != ItemTile.Type.EMPTY);
            }
            if (p.getColumn() == 0) {
                columnBefore = true;
                columnAfter = (board[p.getRow()][p.getColumn() + 1].getType() != ItemTile.Type.BLOCKED && board[p.getRow()][p.getColumn() + 1].getType() != ItemTile.Type.EMPTY);

            } else if (p.getColumn() == 8) {
                columnBefore = (board[p.getRow()][p.getColumn() - 1].getType() != ItemTile.Type.BLOCKED && board[p.getRow()][p.getColumn() - 1].getType() != ItemTile.Type.EMPTY);
                columnAfter = true;
            } else {
                columnBefore = (board[p.getRow()][p.getColumn() - 1].getType() != ItemTile.Type.BLOCKED && board[p.getRow()][p.getColumn() - 1].getType() != ItemTile.Type.EMPTY);
                columnAfter = (board[p.getRow()][p.getColumn() + 1].getType() != ItemTile.Type.BLOCKED && board[p.getRow()][p.getColumn() + 1].getType() != ItemTile.Type.EMPTY);
            }
            return rowBefore && rowAfter && columnBefore && columnAfter;
        }

        /**
         * Checks if it is time to reset the board, i.e., all non-blocked and non-empty cards are alone.
         *
         * @return true if it is time to reset the board, false otherwise
         */
        public boolean timeToReset() {
            for (int i = 0; i < BOARD_ROWS; i++) {
                for (int j = 0; j < BOARD_COLUMNS; j++) {
                    if (board[i][j].getType() != ItemTile.Type.BLOCKED && board[i][j].getType() != ItemTile.Type.EMPTY)
                        if (!aloneCard(new Position(i, j)))
                            return false;


                }
            }
            return true;
        }

        /**
         * Clears the board by removing all non-blocked and non-empty cards and adding them back to the specified deck.
         *
         * @param d the deck to add the cleared cards to
         */
        public void clearBoard(Deck d) {

            for (int i = 0; i < BOARD_ROWS; i++) {
                for (int j = 0; j < BOARD_COLUMNS; j++) {
                    if (board[i][j].getType() == ItemTile.Type.BLOCKED)
                        break;
                    if (board[i][j].getType() != ItemTile.Type.EMPTY) {
                        d.add(board[i][j]);
                        board[i][j].setType(ItemTile.Type.EMPTY);
                    }
                }

            }

        }

        /**
         * Deals cards from the specified deck onto the board, filling empty spaces.
         *
         * @param d the deck to deal cards from
         */
        public void dealCardsOnTheBoard(Deck d) {
            for (int i = 0; i < BOARD_ROWS && d.size() > 0; i++) {
                for (int j = 0; j < BOARD_COLUMNS && d.size() > 0; j++) {
                    if (board[i][j].getType() != ItemTile.Type.BLOCKED) {
                        if (board[i][j].getType() == ItemTile.Type.EMPTY)
                            board[i][j] = d.getFirstCard();
                    }
                }

            }
        }

        /**
         * Picks the tile at the specified position on the board and sets it as empty.
         *
         * @param p the position of the tile to pick
         * @return the picked item tile
         * @throws NotValidPickException if the tile at the specified position is blocked or empty
         */
        private ItemTile pickTile(Position p) throws NotValidPickException { //ritorna la carta in posizione p e la setta empty
            if (board[p.getRow()][p.getColumn()].getType() == ItemTile.Type.BLOCKED || board[p.getRow()][p.getColumn()].getType() == ItemTile.Type.EMPTY)
                throw new NotValidPickException();
            ItemTile o = new ItemTile(board[p.getRow()][p.getColumn()]);
            board[p.getRow()][p.getColumn()].setType(ItemTile.Type.EMPTY);
            return o;
        }

        /**
         * Picks the cards within the specified range on the board.
         *
         * @param start the starting position of the range
         * @param end   the ending position of the range
         * @return the list of picked cards
         * @throws NotValidPickException     if the pick is not valid
         * @throws TrappedCardException      if a trapped card is encountered during the pick
         * @throws IndexOutOfBoundsException if the range exceeds the maximum pickable tiles
         */
        public ArrayList<ItemTile> pickTiles(Position start, Position end) throws NotValidPickException, TrappedCardException, IndexOutOfBoundsException {
            validatePosition(start);
            validatePosition(end);
            if (start.getRow() != end.getRow() && start.getColumn() != end.getColumn())
                throw new NotValidPickException();
            if (start.getRow() == end.getRow())
                return pickRow(start.getRow(), start.getColumn(), end.getColumn());
            else {
                return pickColumn(start.getColumn(), start.getRow(), end.getRow());
            }

        }

        /**
         * Picks a range of tiles from a specified row on the board.
         *
         * @param row         the row from which to pick the tiles
         * @param startColumn the starting column of the range
         * @param endColumn   the ending column of the range
         * @return the list of picked item tiles
         * @throws TrappedCardException      if a trapped card is encountered in the picked range
         * @throws NotValidPickException     if any of the picked tiles are blocked or empty
         * @throws IndexOutOfBoundsException if the range exceeds the maximum number of pickable tiles
         */
        private ArrayList<ItemTile> pickRow(int row, int startColumn, int endColumn) throws TrappedCardException, NotValidPickException {
            ArrayList<ItemTile> pickedTilesList = new ArrayList<>();

            if (endColumn < startColumn) {
                int temp = startColumn;
                startColumn = endColumn;
                endColumn = temp;
            }
            if (endColumn - startColumn >= MAX_PICKABLE_TILES) {
                throw new IndexOutOfBoundsException();
            }

            for (int i = startColumn; i <= endColumn; i++) {
                if (trappedCard(new Position(row, i)))
                    throw new TrappedCardException();
            }

            for (int i = startColumn; i <= endColumn; i++) {
                pickedTilesList.add(pickTile(new Position(row, i)));
            }


            return pickedTilesList;

        }

        /**
         * Picks a range of tiles from a specified column on the board.
         *
         * @param column   the column from which to pick the tiles
         * @param startRow the starting row of the range
         * @param endRow   the ending row of the range
         * @return the list of picked item tiles
         * @throws TrappedCardException      if a trapped card is encountered in the picked range
         * @throws NotValidPickException     if any of the picked tiles are blocked or empty
         * @throws IndexOutOfBoundsException if the range exceeds the maximum number of pickable tiles
         */
        private ArrayList<ItemTile> pickColumn(int column, int startRow, int endRow) throws TrappedCardException, NotValidPickException {
            ArrayList<ItemTile> pickedTilesList = new ArrayList<>();

            if (endRow < startRow) {
                int temp = startRow;
                startRow = endRow;
                endRow = temp;
            }
            if (endRow - startRow >= MAX_PICKABLE_TILES) {
                throw new IndexOutOfBoundsException();
            }

            for (int i = startRow; i <= endRow; i++) {
                if (trappedCard(new Position(i, column)))
                    throw new TrappedCardException();
            }

            for (int i = startRow; i <= endRow; i++) {
                pickedTilesList.add(pickTile(new Position(i, column)));
            }

            return pickedTilesList;
        }

        /**
         * Sets the tile at the specified position on the board to the specified item tile.
         *
         * @param t the item tile to set
         * @param p the position to set the item tile at
         */
        public void setTile(ItemTile t, Position p) {
            if (board[p.getRow()][p.getColumn()].getType() != ItemTile.Type.BLOCKED)
                board[p.getRow()][p.getColumn()].setType(t.getType());
        }

        /**
         * Retrieves the item tile at the specified row and column on the board.
         *
         * @param row    the row index of the tile
         * @param column the column index of the tile
         * @return the item tile at the specified position
         * @throws IllegalArgumentException if the specified row or column is not within the valid range
         */
        public ItemTile getTile(int row, int column) {
            validatePosition(row, column);
            return board[row][column];

        }
    }
}
