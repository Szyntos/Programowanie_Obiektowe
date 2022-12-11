package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{
    MoveDirection[] moves;
    IWorldMap map;
    Vector2d[] initialPositions;
    List<Animal> animals = new ArrayList<>();
    int noMoves;
    int noAnimals;
    int moveDelay;
    App app;
    boolean visualisation;
    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions){
        visualisation = false;
        this.moves = moves;
        this.map = map;
        this.initialPositions = initialPositions;

        this.noMoves = this.moves.length;
        Animal tmp;
        for (Vector2d initialPosition : initialPositions) {
            tmp = new Animal(map, initialPosition);
            if (map.place(tmp)) {
                animals.add(tmp);
            }
        }
        this.noAnimals = this.animals.size();
    }
    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions, App app, int moveDelay){
        visualisation = true;
        this.app = app;
        this.moveDelay = moveDelay;
        this.moves = moves;
        this.map = map;
        this.initialPositions = initialPositions;

        this.noMoves = this.moves.length;
        Animal tmp;
        for (Vector2d initialPosition : initialPositions) {
            tmp = new Animal(map, initialPosition);
            if (map.place(tmp)) {
                animals.add(tmp);
            }
        }
        this.noAnimals = this.animals.size();
    }
    public SimulationEngine(IWorldMap map, Vector2d[] initialPositions, App app, int moveDelay){
        visualisation = true;
        this.app = app;
        this.moveDelay = moveDelay;
        this.map = map;
        this.initialPositions = initialPositions;
        Animal tmp;
        for (Vector2d initialPosition : initialPositions) {
            tmp = new Animal(map, initialPosition);
            if (map.place(tmp)) {
                animals.add(tmp);
            }
        }
        this.noAnimals = this.animals.size();
    }

    public void setMoves(MoveDirection[] moves) {
        this.moves = moves;
        this.noMoves = this.moves.length;
    }

    @Override
    public void run() {
        for (int i = -1; i < noMoves; i++){
            try{
                Thread.sleep(this.moveDelay);
            }catch (InterruptedException exception){
                System.out.println("Stosowny komunikat");
            }
            if (i != -1) {
                animals.get(i % noAnimals).move(moves[i]);
            }
            if (visualisation){
                Platform.runLater(() -> app.updateGrid());
            }else{
                System.out.println(map);
            }
        }
    }
}
