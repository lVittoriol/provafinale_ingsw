package it.polimi.ingsw.utils;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the position of an element in a matrix with a row and column pair.
 *
 * @author Marcelo S. Hernandez
 */
public class Position implements Serializable {
    private int row;
    private int column;

    /**
     * Constructs and initializes a position with the specified row and column pair.
     *
     * @param row    the row of the newly constructed position
     * @param column the column of the newly constructed position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Constructs and initializes a position with the same row and column pair as the specified position.
     *
     * @param other a position
     */
    public Position(Position other) {
        this.row = other.row;
        this.column = other.column;
    }

    /**
     * Returns the row of this position.
     *
     * @return the row of this position
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row of this position to the specified row
     *
     * @param row the new row for this position
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Returns the column of this position.
     *
     * @return the column of this position
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets the column of this position to the specified column
     *
     * @param column the new column for this position
     */
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position other = (Position) object;
        return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "(" + (row + 1) + ", " + (column + 1) + ")";
    }
}
