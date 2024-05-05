package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an item tile.
 *
 * @author Marcelo S. Hernandez
 */
public class ItemTile implements Serializable {
    private Type type;

    /**
     * Constructs and initializes an item tile with the specified type.
     *
     * @param type the type of the newly constructed item tile
     */
    public ItemTile(Type type) {
        this.type = type;
    }

    /**
     * Constructs and initializes an item tile with the same type as the specified item tile.
     *
     * @param other an item tile
     */
    public ItemTile(ItemTile other) {
        this.type = other.type;
    }

    /**
     * Returns the type of this item tile.
     *
     * @return the type of this item tile
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type of this item tile to the specified type.
     *
     * @param type the new type for this item tile
     */
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ItemTile other = (ItemTile) object;
        return this.type == other.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return type.toString();
    }

    /**
     * Represents an item tile's type.
     *
     * @author Marcelo S. Hernandez
     */
    public enum Type {
        CAT, BOOK, GAME, FRAME, TROPHY, PLANT, BLOCKED, EMPTY
    }
}
