package it.polimi.ingsw.model.commongoalcard;

/**
 * A factory to create common goal cards.
 *
 * @author Marcelo S. Hernandez
 */
public class CommonGoalCardFactory {
    public static CommonGoalCard createCommonGoalCard(int number) {
        return switch (number) {
            case 1 -> new CommonGoalCard1();
            case 2 -> new CommonGoalCard2();
            case 3 -> new CommonGoalCard3();
            case 4 -> new CommonGoalCard4();
            case 5 -> new CommonGoalCard5();
            case 6 -> new CommonGoalCard6();
            case 7 -> new CommonGoalCard7();
            case 8 -> new CommonGoalCard8();
            case 9 -> new CommonGoalCard9();
            case 10 -> new CommonGoalCard10();
            case 11 -> new CommonGoalCard11();
            case 12 -> new CommonGoalCard12();
            default ->
                    throw new IllegalArgumentException("`number` must be greater than or equal to 1 and less than or equal to 12");
        };
    }
}
