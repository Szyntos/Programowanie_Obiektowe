package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    MoveDirection[] moves;
    IWorldMap map;
    Vector2d[] initialPositions;
    List<Animal> animals = new ArrayList<>();
    int noMoves;
    int noAnimals;
    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions){
        this.moves = moves;
        this.map = map;
        this.initialPositions = initialPositions;

        this.noMoves = this.moves.length;
        Animal tmp;
        for (Vector2d initialPosition : initialPositions) {
            tmp = new Animal(map, initialPosition);
            tmp.addObserver((IPositionChangeObserver) map);
            if (map.place(tmp)) {
                animals.add(tmp);
            }
        }
        this.noAnimals = this.animals.size();
    }

    @Override
    public void run() {
        System.out.println(map);
        for (int i = 0; i < noMoves; i++){
            animals.get(i%noAnimals).move(moves[i]);
            System.out.println(map);
        }
    }
}
