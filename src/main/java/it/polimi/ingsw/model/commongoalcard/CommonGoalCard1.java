package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.utils.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a goal where there are six groups each containing at least
 * 2 tiles of the same type (not necessarily
 * in the depicted shape).
 * The tiles of one group can be different
 * from those of another group.
 *
 * @author Emanuele Fossati
 */
public class CommonGoalCard1 implements CommonGoalCard {
    List<List<Position>> groups;

    @Override
    public boolean checkBookshelf(Bookshelf bookshelf) {
        List<Position>[] tilePositions = new List[6];
        for (int i = 0; i < 6; i++) {
            tilePositions[i] = new ArrayList<>();
        }

        for (int r = 0; r < Bookshelf.ROW_COUNT; r++) {
            for (int c = 0; c < Bookshelf.COLUMN_COUNT; c++) {
                ItemTile currentTile = bookshelf.getItemTile(r, c);

                if (currentTile != null && currentTile.getType() != ItemTile.Type.EMPTY) {
                    int typeIndex = currentTile.getType().ordinal();
                    tilePositions[typeIndex].add(new Position(r, c));
                }
            }
        }

        int numGroups = 0;

        for (int i = 0; i < 6; i++) {
            numGroups += getNumberOfValidGroups(tilePositions[i]);
        }

        return numGroups >= 6;
    }

    private int getNumberOfValidGroups(List<Position> positionList) {
        this.groups = new ArrayList<>();

        for (Position position : positionList) {
            boolean isNewGroup = true;
            for (List<Position> group : this.groups) {
                if (isAdjacentToGroup(position, group)) {
                    group.add(position);
                    isNewGroup = false;
                    break;
                }
            }
            if (isNewGroup) {
                List<Position> newGroup = new ArrayList<>();
                newGroup.add(position);
                this.groups.add(newGroup);
            }
        }

        int validGroupsCount = 0;
        for (List<Position> group : this.groups) {
            if (group.size() >= 2) {
                validGroupsCount++;
            }
        }

        return validGroupsCount;
    }

    private boolean isAdjacentToGroup(Position position, List<Position> group) {
        for (Position groupPosition : group) {
            if (areAdjacent(position, groupPosition)) {
                return true;
            }
        }
        return false;
    }

    private boolean areAdjacent(Position position1, Position position2) {
        double dr = (position2.getRow() - position1.getRow());
        double dc = (position2.getColumn() - position1.getColumn());
        double distance = Math.sqrt(Math.pow(dr, 2) + Math.pow(dc, 2));

        return distance == 1;
    }

    @Override
    public String getDescription() {
        return "1) Six groups each containing at least " +
                "2 tiles of the same type (not necessarily " +
                "in the depicted shape). " +
                "The tiles of one group can be different " +
                "from those of another group.";
    }

    @Override
    public int getNumber() {
        return 1;
    }
}

