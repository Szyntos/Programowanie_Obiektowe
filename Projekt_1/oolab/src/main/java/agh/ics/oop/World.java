package agh.ics.oop;

import java.util.Arrays;


public class World {
    public static void main(String[] args){
//        RectangularMap map = new RectangularMap(4, 4);
//        MoveDirection[] instructions = OptionsParser.parse(args);
//        Animal animal = new Animal();
//        for (MoveDirection instruction : instructions){
//            animal.move(instruction);
//            System.out.println(animal);
//        }
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();

    }


    public static void run(Direction[] tab){
        System.out.println("Start");
        Arrays.stream(tab).filter(w -> w != Direction.ignore).map(World::dirToTranslatedString)
                .forEach((k) -> {System.out.print(k + "\n");});
        System.out.println("Stop");
    }
    public static Direction strToDirection(String s){
        return switch (s){
            case "f" -> Direction.f;
            case "b" -> Direction.b;
            case "r" -> Direction.r;
            case "l" -> Direction.l;
            default -> Direction.ignore;
        };
    }
    public static String dirToTranslatedString(Direction dir){
        return switch (dir) {
            case f -> "Zwierzak idzie do przodu";
            case b -> "Zwierzak idzie do tylu";
            case r -> "Zwierzak skreca w prawo";
            case l -> "Zwierzak skreca w lewo";
            default -> "";
        };
    }

}

