package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap{
    List<Animal> animals = new ArrayList<>();
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
