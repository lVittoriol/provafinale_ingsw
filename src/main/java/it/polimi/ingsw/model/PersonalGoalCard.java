package it.polimi.ingsw.model;

import it.polimi.ingsw.utils.Position;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a personal goal card.
 *
 * @author Marcelo S. Hernandez
 */
public class PersonalGoalCard implements Serializable {
    private final Task[] tasks;
    private final int number;

    /**
     * Constructs and initializes a personal goal card with the specified tasks.
     *
     * @param tasks the tasks of the newly constructed personal goal card
     */
    public PersonalGoalCard(Task[] tasks, int number) {
        this.tasks = Arrays.stream(tasks).map(Task::new).toArray(Task[]::new);
        this.number = number;
    }

    /**
     * Returns an integer whose value depends on the number of tasks completed by the specified bookshelf.
     *
     * @param bookshelf a bookshelf
     * @return an integer whose value depends on the number of tasks completed by the specified bookshelf
     */
    public int checkBookshelf(Bookshelf bookshelf) {
        int matchCount = Arrays.stream(tasks)
                .mapToInt(x -> bookshelf.getItemTile(x.position) != null && bookshelf.getItemTile(x.position).getType() == x.itemTileType ? 1 : 0)
                .sum();

        return switch (matchCount) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 6;
            case 5 -> 9;
            case 6 -> 12;
            default -> 0;
        };
    }

    /**
     * Returns a list of tasks associated with this personal goal card.
     *
     * @return a list of tasks associated with this personal goal card
     */
    public List<Task> getTasks() {
        return List.of(tasks);
    }

    /**
     * Returns the number associated with this personal goal card.
     *
     * @return the number associated with this personal goal card
     */
    public int getNumber() {
        return number;
    }

    /**
     * Represents a personal goal card's task.
     *
     * @author Marcelo S. Hernandez
     */
    public static class Task implements Serializable {
        private final ItemTile.Type itemTileType;
        private final Position position;

        /**
         * Constructs and initializes a task with the specified item tile type and row and column pair.
         *
         * @param itemTileType the item tile type of the newly constructed task
         * @param row          the row of the newly constructed task
         * @param column       the column of the newly constructed task
         */
        public Task(ItemTile.Type itemTileType, int row, int column) {
            this.itemTileType = itemTileType;
            this.position = new Position(row, column);
        }

        /**
         * Constructs and initializes a task with the specified item tile type and position.
         *
         * @param itemTileType the item tile type of the newly constructed task
         * @param position     the position of the newly constructed task
         */
        public Task(ItemTile.Type itemTileType, Position position) {
            this.itemTileType = itemTileType;
            this.position = new Position(position);
        }

        /**
         * Constructs and initializes a task with the same item tile type and position as the specified task.
         *
         * @param other a task
         */
        public Task(Task other) {
            this.itemTileType = other.itemTileType;
            this.position = new Position(other.position);
        }

        /**
         * Returns the item tile type associated with this task.
         *
         * @return the item tile type associated with this task
         */
        public ItemTile.Type getItemTileType() {
            return itemTileType;
        }

        /**
         * Returns the position associated with this task.
         *
         * @return the position associated with this task
         */
        public Position getPosition() {
            return position;
        }
    }
}