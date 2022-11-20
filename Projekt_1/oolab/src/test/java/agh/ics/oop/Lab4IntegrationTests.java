package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Lab4IntegrationTests {
    public MapDirection getAnimalDirection(Animal animal){
        return animal.getCurrDirection();
    }
    public Vector2d getAnimalCoordinates(Animal animal){
        return animal.getPosition();
    }
    @Test
    public void runIntegrationTest(){
        int width = 10;
        int height = 5;

        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,3), new Vector2d(2, 2), new Vector2d(2,3)};
        Vector2d[] correctCoordinates = {new Vector2d(2, 2), new Vector2d(2, 3)};
        MapDirection[] correctDirection = {MapDirection.NORTH, MapDirection.NORTH};
        int n = 50;
        String[] args = new String[n];
        int randomNumber;
        int j = 0;
        int x;
        int y;

        int noAnimals = 2;
        for (int i = 0; i < n; i++){
            randomNumber = (int) (Math.random()*4);
            switch (randomNumber) {
                case 0 -> {
                    args[i] = "f";
                    x = Math.max(Math.min(width-1, correctCoordinates[i%noAnimals].add(correctDirection[i%noAnimals].toUnitVector()).x), 0);
                    y = Math.max(Math.min(height-1, correctCoordinates[i%noAnimals].add(correctDirection[i%noAnimals].toUnitVector()).y), 0);
                    if (!correctCoordinates[(i+1)%noAnimals].equals(new Vector2d(x, y))){
                        correctCoordinates[i%noAnimals] = new Vector2d(x, y);
                    }
                    j++;
                }
                case 1 -> {
                    args[i] = "b";
                    x = Math.max(Math.min(width-1, correctCoordinates[i%noAnimals].subtract(correctDirection[i%noAnimals].toUnitVector()).x), 0);
                    y = Math.max(Math.min(height-1, correctCoordinates[i%noAnimals].subtract(correctDirection[i%noAnimals].toUnitVector()).y), 0);
                    if (!correctCoordinates[(i+1)%noAnimals].equals(new Vector2d(x, y))){
                        correctCoordinates[i%noAnimals] = new Vector2d(x, y);
                    }

                    j++;
                }
                case 2 -> {
                    args[i] = "r";
                    correctDirection[i%noAnimals] = correctDirection[i%noAnimals].next();
                    j++;
                }
                case 3 -> {
                    args[i] = "l";
                    correctDirection[i%noAnimals] = correctDirection[i%noAnimals].previous();
                    j++;
                }
            }
        }
        MoveDirection[] directions = OptionsParser.parse(args);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Assertions.assertTrue(map.isOccupied(correctCoordinates[0]));
        Assertions.assertTrue(map.isOccupied(correctCoordinates[1]));
        Animal[] animals = new Animal[2];
        animals[0] = (Animal) map.objectAt(correctCoordinates[0]);
        animals[1] = (Animal) map.objectAt(correctCoordinates[1]);
        Assertions.assertEquals(animals[0].getCurrDirection(), correctDirection[0]);
        Assertions.assertEquals(animals[1].getCurrDirection(), correctDirection[1]);
    }
}
