package it.polimi.ingsw.model;

import it.polimi.ingsw.utils.Position;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a bookshelf.
 *
 * @author Marcelo S. Hernandez
 */
public class Bookshelf implements Serializable {
    public static final int ROW_COUNT = 6;
    public static final int COLUMN_COUNT = 5;
    private final ItemTile[][] itemTiles;

    /**
     * Constructs an empty bookshelf.
     */
    public Bookshelf() {
        itemTiles = new ItemTile[ROW_COUNT][COLUMN_COUNT];
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                itemTiles[i][j] = new ItemTile(ItemTile.Type.EMPTY);
            }
        }
    }

    /**
     * Constructs and initializes a bookshelf with the same item tiles as the specified bookshelf.
     *
     * @param other a bookshelf
     */
    public Bookshelf(Bookshelf other) {
        itemTiles = new ItemTile[ROW_COUNT][COLUMN_COUNT];

        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                itemTiles[i][j] = other.getItemTile(i, j);
            }
        }
    }

    /**
     * Ensures that the specified row and column pair is a valid position in this bookshelf.
     *
     * @param row    a row
     * @param column a column
     * @throws IndexOutOfBoundsException if the specified row and column pair is out of bounds
     */
    private static void validatePosition(int row, int column) throws IndexOutOfBoundsException {
        if (row < 0 || row >= ROW_COUNT) {
            throw new IndexOutOfBoundsException("`row` must be greater than or equal to zero and less than " + ROW_COUNT);
        }

        if (column < 0 || column >= COLUMN_COUNT) {
            throw new IndexOutOfBoundsException("`column` must be greater than or equal to zero and less than " + COLUMN_COUNT);
        }
    }

    /**
     * Inserts the specified item tile at the specified row and column pair in this bookshelf.
     *
     * @param itemTile the item tile to be inserted
     * @param row      the row at which the specified item tile is to be inserted
     * @param column   the column at which the specified item tile is to be inserted
     * @throws IndexOutOfBoundsException if the specified row and column pair is out of bounds
     */
    public void setItemTile(ItemTile itemTile, int row, int column) throws IndexOutOfBoundsException {
        validatePosition(row, column);
        itemTiles[row][column].setType(itemTile.getType());
    }

    /**
     * Inserts the specified item tile at the specified position in this bookshelf.
     *
     * @param itemTile the item tile to be inserted
     * @param position the position at which the specified item tile is to be inserted
     * @throws IndexOutOfBoundsException if the specified position is out of bounds
     */
    public void setItemTile(ItemTile itemTile, Position position) throws IndexOutOfBoundsException {
        setItemTile(itemTile, position.getRow(), position.getColumn());
    }

    /**
     * Removes the item tile at the specified row and column pair from this bookshelf.
     *
     * @param row    the row of the item tile to be removed
     * @param column the column of the item tile to be removed
     * @throws IndexOutOfBoundsException if the specified row and column pair is out of bounds
     */
    public void removeItemTile(int row, int column) throws IndexOutOfBoundsException {
        validatePosition(row, column);
        itemTiles[row][column].setType(ItemTile.Type.EMPTY);
    }

    /**
     * Removes the item tile at the specified position from this bookshelf.
     *
     * @param position the position of the item tile to be removed
     * @throws IndexOutOfBoundsException if the specified position is out of bounds
     */
    public void removeItemTile(Position position) throws IndexOutOfBoundsException {
        removeItemTile(position.getRow(), position.getColumn());
    }

    /**
     * Returns the item tile at the specified row and column pair in this bookshelf.
     *
     * @param row    the row of the item tile to be returned
     * @param column the column of the item tile to be returned
     * @return the item tile at the specified row and column pair in this bookshelf
     * @throws IndexOutOfBoundsException if the specified row and column pair is out of bounds
     */
    public ItemTile getItemTile(int row, int column) throws IndexOutOfBoundsException {
        validatePosition(row, column);
        return itemTiles[row][column];
    }

    /**
     * Returns the item tile at the specified position in this bookshelf.
     *
     * @param position the position of the item tile to be returned
     * @return the item tile at the specified position in this bookshelf
     * @throws IndexOutOfBoundsException if the specified position is out of bounds
     */
    public ItemTile getItemTile(Position position) throws IndexOutOfBoundsException {
        return getItemTile(position.getRow(), position.getColumn());
    }

    /**
     * Returns the position of the first occurrence of the specified item tile in this bookshelf.
     *
     * @param itemTile the item tile to search for
     * @return the position of the first occurrence of the specified item tile in this bookshelf
     * @throws NoSuchElementException if the specified item tile doesn't exist in this bookshelf
     */
    public Position positionOf(ItemTile itemTile) throws NoSuchElementException {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                if (itemTile.equals(itemTiles[i][j])) {
                    return new Position(i, j);
                }
            }
        }

        throw new NoSuchElementException();
    }

    /**
     * Returns <code>true</code> if this bookshelf is full and no new item tiles can be added.
     *
     * @return <code>true</code> if this bookshelf is full and no new item tiles can be added
     */
    public boolean isFull() {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                if (itemTiles[i][j].getType() == ItemTile.Type.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns <code>true</code> if this bookshelf is empty and no item tiles can be removed.
     *
     * @return <code>true</code> if this bookshelf is empty and no item tiles can be removed
     */
    public boolean isEmpty() {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                if (itemTiles[i][j].getType() != ItemTile.Type.EMPTY && itemTiles[i][j].getType() != ItemTile.Type.BLOCKED) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                stringBuilder.append(itemTiles[i][j]);

                if (j != COLUMN_COUNT - 1) {
                    stringBuilder.append(",\t");
                }
            }

            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }


    public List<List<Position>> getGroupsOfAdjacentItemTiles() {
        List<Position>[] tilePositions = new List[6];
        for (int i = 0; i < 6; i++) {
            tilePositions[i] = new ArrayList<>();
        }

        for (int r = 0; r < Bookshelf.ROW_COUNT; r++) {
            for (int c = 0; c < Bookshelf.COLUMN_COUNT; c++) {
                ItemTile currentTile = this.getItemTile(r, c);

                if (currentTile != null && currentTile.getType() != ItemTile.Type.EMPTY) {
                    int typeIndex = currentTile.getType().ordinal();
                    tilePositions[typeIndex].add(new Position(r, c));
                }
            }
        }

        List<List<Position>> groups = new ArrayList<>();

        for (int i = 0; i < tilePositions.length; i++) {
            groups.addAll(detectGroupsOfAdjacentTiles(tilePositions[i]));
        }

        return groups;
    }

    private List<List<Position>> detectGroupsOfAdjacentTiles(List<Position> positionList) {

        List<List<Position>> groupsOfTheSameType = new ArrayList<>();

        for (Position position : positionList) {
            List<Position> newGroup = new ArrayList<>();
            newGroup.add(position);
            groupsOfTheSameType.add(newGroup);
        }

        boolean isMerging;
        do {
            isMerging = false;

            List<List<Position>> mergedGroups = new ArrayList<>();

            for (int i = 0; i < groupsOfTheSameType.size(); i++) {
                List<Position> group1 = groupsOfTheSameType.get(i);
                List<Position> mergedGroup = new ArrayList<>(group1);

                for (int j = i + 1; j < groupsOfTheSameType.size(); j++) {
                    List<Position> group2 = groupsOfTheSameType.get(j);

                    if (canMergeGroups(group1, group2)) {
                        mergedGroup.addAll(group2);
                        groupsOfTheSameType.remove(j);
                        isMerging = true;
                        j--;
                    }
                }

                mergedGroups.add(mergedGroup);
            }

            groupsOfTheSameType = mergedGroups;
        } while (isMerging);

        return groupsOfTheSameType;
    }

    private boolean canMergeGroups(List<Position> group1, List<Position> group2) {
        for (Position position1 : group1) {
            for (Position position2 : group2) {
                if (arePositionsAdjacent(position1, position2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean arePositionsAdjacent(Position position1, Position position2) {
        int dr = Math.abs(position1.getRow() - position2.getRow());
        int dc = Math.abs(position1.getColumn() - position2.getColumn());
        return (dr == 1 && dc == 0) || (dr == 0 && dc == 1);
    }


    /*public List<List<ItemTile>> GroupsAdjacentItemTile() {
        List<List<ItemTile>> groups = new ArrayList<>();
        Set<ItemTile> visited = new HashSet<>();

        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                ItemTile tile = getItemTile(i, j);
                if (tile != null && !visited.contains(tile)) {
                    List<ItemTile> group = new ArrayList<>();
                    addAdjacentTilesToGroup(i, j, tile, visited, group);
                    groups.add(group);
                }
            }
        }

        return groups;
    }

    private void addAdjacentTilesToGroup(int row, int col, ItemTile tile, Set<ItemTile> visited, List<ItemTile> group) {
        if (!visited.contains(tile)) {
            visited.add(tile);
            group.add(tile);

            // Explore adjacent tiles
            int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (isValidTile(newRow, newCol)) {
                    ItemTile adjacentTile = getItemTile(newRow, newCol);
                    if (adjacentTile != null && !visited.contains(adjacentTile) && adjacentTile.getType() == tile.getType()) {
                        addAdjacentTilesToGroup(newRow, newCol, adjacentTile, visited, group);
                    }
                }
            }
        }
    }

*/

    public List<List<ItemTile>> GroupsAdjacentItemTile() {

        List<List<ItemTile>> groups = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        groups.clear();
        visited.clear();

        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                ItemTile tile = getItemTile(i, j);
                String coord = i + "," + j;
                if (tile != null && !visited.contains(coord)) {
                    List<ItemTile> group = new ArrayList<>();
                    addAdjacentTilesToGroup(i, j, tile, visited, group);
                    groups.add(group);
                }
            }
        }
        return groups;
    }
    //da controllare

    private void addAdjacentTilesToGroup(int row, int col, ItemTile tile, Set<String> visited, List<ItemTile> group) {
        String coord = row + "," + col;
        if (!visited.contains(coord)) {
            visited.add(coord);
            group.add(tile);

            // Explore adjacent tiles
            int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (isValidTile(newRow, newCol)) {
                    ItemTile adjacentTile = getItemTile(newRow, newCol);
                    String adjCoord = newRow + "," + newCol;
                    if (adjacentTile != null && !visited.contains(adjCoord) && adjacentTile.getType() == tile.getType()) {
                        addAdjacentTilesToGroup(newRow, newCol, adjacentTile, visited, group);
                    }
                }
            }
        }
    }

    //da controllare


    private boolean isValidTile(int row, int col) {
        return row >= 0 && row < ROW_COUNT && col >= 0 && col < COLUMN_COUNT;
    }

    public int maxTilesInBookshelf() {
        int maxTilesInBookshelf = 0;
        for (int j = 0; j < COLUMN_COUNT; j++) {
            int maxTilesInColumn = 0;
            for (int i = 0; i < ROW_COUNT; i++) {
                if (getItemTile(i, j).getType().equals(ItemTile.Type.EMPTY)) {
                    maxTilesInColumn++;
                }
            }
            if (maxTilesInColumn > maxTilesInBookshelf) {
                maxTilesInBookshelf = maxTilesInColumn;
            }
            if (maxTilesInColumn == ROW_COUNT) {
                break;
            }
        }
        return maxTilesInBookshelf;
    }

    public void fillBookshelf() {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT - 1; j++) {
                setItemTile(new ItemTile(ItemTile.Type.BOOK), i, j);
            }
        }
    }

    //per test


}
