package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] options) throws IllegalArgumentException{
        int newLength = 0;
        for (String option : options) {
            switch (option) {
                case "f", "forward", "l", "left", "r", "right", "b", "backward" -> newLength++;
                default -> throw new IllegalArgumentException(option + " is not legal move specification");
            }
        }
        MoveDirection[] parsedMoveDirection = new MoveDirection[newLength];
        int j = 0;
        for (String option : options) {
            switch (option) {
                case "f", "forward" -> {
                    parsedMoveDirection[j] = MoveDirection.FORWARD;
                    j++;
                }
                case "b", "backward" -> {
                    parsedMoveDirection[j] = MoveDirection.BACKWARD;
                    j++;
                }
                case "r", "right" -> {
                    parsedMoveDirection[j] = MoveDirection.RIGHT;
                    j++;
                }
                case "l", "left" -> {
                    parsedMoveDirection[j] = MoveDirection.LEFT;
                    j++;
                }
            }
        }
        return parsedMoveDirection;
    }
}
