package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    int width;
    int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }
    public Vector2d[] getBorders(){
        return new Vector2d[]{new Vector2d(0, 0), new Vector2d(this.width-1, this.height-1)};
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

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal Grzegorz = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, Grzegorz);
    }
}
