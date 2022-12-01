package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    Map<Vector2d, Animal> animals = new HashMap<>();
    MapVisualizer visualizer = new MapVisualizer(this);
    abstract Vector2d[] getBorders();
    abstract void addAnimal(Animal animal);
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            addAnimal(animal);
            return true;
        }
        else return false;
    }
    public String toString() {
        Vector2d lowerLeft = this.getBorders()[0];
        Vector2d upperRight = this.getBorders()[1];
        return visualizer.draw(lowerLeft, upperRight);
    }
}
