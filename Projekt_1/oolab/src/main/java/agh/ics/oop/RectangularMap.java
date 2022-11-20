package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    List<Animal> animals = new ArrayList<>();
    int width;
    int height;
    MapVisualizer visualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.x < this.width && position.y < this.height &&
                position.x >= 0 && position.y >= 0) {
            return !isOccupied(position);
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.add(animal);
            return true;
        }
        else return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(this.width-1, this.height-1));
    }

}
