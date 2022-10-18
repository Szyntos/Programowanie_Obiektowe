package agh.ics.oop;

import java.util.Arrays;


public class World {
    public static void main(String[] args){
        System.out.println("system wystartował");
        Direction[] dirs = Arrays.stream(args).map(World::strToDirection).toArray(Direction[]::new);
        run(dirs);
        System.out.println("system zakończył działanie");

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
            case b -> "Zwierzak idzie do tyłu";
            case r -> "Zwierzak skręca w prawo";
            case l -> "Zwierzak skręca w lewo";
            default -> "";
        };
    }

}

