package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    int width;
    int height;
    MapBoundary boundary;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        boundary = new MapBoundary();
        boundary.addObject(new Vector2d(0, 0));
        boundary.addObject(new Vector2d(width, height));
    }
    public Vector2d[] getBorders(){
        return boundary.getBoundary();
    }

    @Override
    void addAnimal(Animal animal) {
        animals.put(animal.getPosition(), animal);
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
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)){
            return animals.get(position);
        }
        return null;
    }

}
