package agh.ics.oop;

import java.util.Arrays;


public class World {
    public static void main(String[] args){
        System.out.println("system wystartowal");
        Direction[] dirs = Arrays.stream(args).map(World::strToDirection).toArray(Direction[]::new);
        run(dirs);
        System.out.println("system zakonczyl dzialanie");
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        MapDirection n = MapDirection.NORTH;
        MapDirection e = MapDirection.EAST;
        MapDirection s = MapDirection.SOUTH;
        MapDirection w = MapDirection.WEST;
        System.out.println(n.previous());

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

