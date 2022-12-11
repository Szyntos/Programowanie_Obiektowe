package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    Map<Vector2d, Animal> animals = new HashMap<>();
    MapVisualizer visualizer = new MapVisualizer(this);
    MapBoundary boundary = new MapBoundary();
    abstract public Vector2d[] getBorders();
    abstract void addAnimal(Animal animal);
    public boolean place(Animal animal) throws IllegalArgumentException{
        if (canMoveTo(animal.getPosition())){
            animal.addObserver(this);
            animal.addObserver(boundary);
            boundary.addObject(animal.getPosition());
            addAnimal(animal);
            return true;
        }
        else{
            throw new IllegalArgumentException("Na polu " + animal.getPosition().toString() + " nie mozna dodac zwierzecia");
        }
    }
    public String toString() {
        Vector2d lowerLeft = this.getBorders()[0];
        Vector2d upperRight = this.getBorders()[1];
        return visualizer.draw(lowerLeft, upperRight);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal Grzegorz = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, Grzegorz);
    }
}
