package it.polimi.ingsw.model.commongoalcard;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.ItemTile;
import it.polimi.ingsw.utils.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a goal where there are four groups each containing at least 4 tiles of the same type. The tiles of one group can be different from those of another group.
 *
 * @author Emanuele Fossati
 */
public class CommonGoalCard5 implements CommonGoalCard {

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

        long sum = 0;

        for (int i = 0; i < 6; i++) {
            sum += getNumberOfValidGroups(tilePositions[i]);
        }

        return sum >= 4;
    }

    private int getNumberOfValidGroups(List<Position> positionList) {
        int numGroups = 0;
        List<List<Position>> groups = new ArrayList<>();

        for (Position position : positionList) {
            List<Position> newGroup = new ArrayList<>();
            newGroup.add(position);
            groups.add(newGroup);
        }

        boolean isMerging;
        do {
            isMerging = false;

            List<List<Position>> mergedGroups = new ArrayList<>();

            for (int i = 0; i < groups.size(); i++) {
                List<Position> group1 = groups.get(i);
                List<Position> mergedGroup = new ArrayList<>(group1);

                for (int j = i + 1; j < groups.size(); j++) {
                    List<Position> group2 = groups.get(j);

                    if (canMergeGroups(group1, group2)) {
                        mergedGroup.addAll(group2);
                        groups.remove(j);
                        isMerging = true;
                        j--;
                    }
                }

                mergedGroups.add(mergedGroup);
            }

            groups = mergedGroups;
        } while (isMerging);

        for (List<Position> group : groups) {
            if (group.size() >= 4) {
                numGroups++;
            }
        }

        return numGroups;
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

    @Override
    public int getNumber() {
        return 5;
    }

    @Override
    public String getDescription() {
        return "5) Four groups each containing at least " +
                "4 tiles of the same type (not necessarily " +
                "in the depicted shape). " +
                "The tiles of one group can be different " +
                "from those of another group.";
    }
}