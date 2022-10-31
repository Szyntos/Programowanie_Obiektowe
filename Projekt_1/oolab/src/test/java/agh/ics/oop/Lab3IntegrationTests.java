package agh.ics.oop;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class Lab3IntegrationTests {
    public MapDirection getAnimalDirection(Animal animal){
        String direction = animal.toString().substring(12, animal.toString().length() - 16);
        return switch (direction){
            case "Polnoc" -> MapDirection.NORTH;
            case "Wschod" -> MapDirection.EAST;
            case "Poludnie" -> MapDirection.SOUTH;
            case "Zachod" -> MapDirection.WEST;
            default -> MapDirection.NORTH;
        };
    }
    public Vector2d getAnimalCoordinates(Animal animal){
        String coordinates = animal.toString().substring(animal.toString().length() - 5);
        int coordinates_x = Integer.parseInt(coordinates.substring(1, 2));
        int coordinates_y = Integer.parseInt(coordinates.substring(3, 4));
        return new Vector2d(coordinates_x, coordinates_y);
    }
    @Test
    public void wholeIntegrationTest(){
        MoveDirection[] correctInstructions_1 = new MoveDirection[50];
        String[] args = new String[50];
        int randomNumber;
        int j = 0;
        int x;
        int y;
        Vector2d correctCoordinates = new Vector2d(2, 2);
        MapDirection correctDirection = MapDirection.NORTH;
        for (int i = 0; i < 50; i++){
            randomNumber = (int) (Math.random()*10);
            switch (randomNumber) {
                case 0 -> {
                    args[i] = "f";
                    x = Math.max(Math.min(4, correctCoordinates.add(correctDirection.toUnitVector()).x), 0);
                    y = Math.max(Math.min(4, correctCoordinates.add(correctDirection.toUnitVector()).y), 0);
                    correctCoordinates = new Vector2d(x, y);
                    correctInstructions_1[j] = MoveDirection.FORWARD;
                    j++;
                }
                case 1 -> {
                    args[i] = "forward";
                    x = Math.max(Math.min(4, correctCoordinates.add(correctDirection.toUnitVector()).x), 0);
                    y = Math.max(Math.min(4, correctCoordinates.add(correctDirection.toUnitVector()).y), 0);
                    correctCoordinates = new Vector2d(x, y);
                    correctInstructions_1[j] = MoveDirection.FORWARD;
                    j++;
                }
                case 2 -> {
                    args[i] = "b";
                    x = Math.max(Math.min(4, correctCoordinates.subtract(correctDirection.toUnitVector()).x), 0);
                    y = Math.max(Math.min(4, correctCoordinates.subtract(correctDirection.toUnitVector()).y), 0);
                    correctCoordinates = new Vector2d(x, y);
                    correctInstructions_1[j] = MoveDirection.BACKWARD;
                    j++;
                }
                case 3 -> {
                    args[i] = "backward";
                    x = Math.max(Math.min(4, correctCoordinates.subtract(correctDirection.toUnitVector()).x), 0);
                    y = Math.max(Math.min(4, correctCoordinates.subtract(correctDirection.toUnitVector()).y), 0);
                    correctCoordinates = new Vector2d(x, y);
                    correctInstructions_1[j] = MoveDirection.BACKWARD;
                    j++;
                }
                case 4 -> {
                    args[i] = "r";
                    correctInstructions_1[j] = MoveDirection.RIGHT;
                    correctDirection = correctDirection.next();
                    j++;
                }
                case 5 -> {
                    args[i] = "right";
                    correctInstructions_1[j] = MoveDirection.RIGHT;
                    correctDirection = correctDirection.next();
                    j++;
                }
                case 6 -> {
                    args[i] = "l";
                    correctInstructions_1[j] = MoveDirection.LEFT;
                    correctDirection = correctDirection.previous();
                    j++;
                }
                case 7 -> {
                    args[i] = "left";
                    correctInstructions_1[j] = MoveDirection.LEFT;
                    correctDirection = correctDirection.previous();
                    j++;
                }
                case 8 -> args[i] = "asdkjashja";
                case 9 -> args[i] = "fosan=q0rward";
            }
        }

        MoveDirection[] correctInstructions = new MoveDirection[j];
        System.arraycopy(correctInstructions_1, 0, correctInstructions, 0, j);


        MoveDirection[] instructions = OptionsParser.parse(args);
        Assertions.assertArrayEquals(correctInstructions, instructions);
        Animal animal = new Animal();
        for (MoveDirection instruction : instructions){;
            animal.move(instruction);
//            System.out.println(animal);
        }
        Assertions.assertEquals(correctCoordinates, getAnimalCoordinates(animal));
        Assertions.assertEquals(correctDirection, getAnimalDirection(animal));
        Assertions.assertTrue(getAnimalCoordinates(animal).x <= 4 && getAnimalCoordinates(animal).x >= 0 &&
                getAnimalCoordinates(animal).y <= 4 && getAnimalCoordinates(animal).y >= 0);
    }
}
