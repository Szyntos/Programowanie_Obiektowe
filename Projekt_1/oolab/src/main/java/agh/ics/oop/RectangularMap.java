package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RectangularMap implements IWorldMap{
    List<Animal> animals = new ArrayList<>();
    boolean[][] isOccupiedMap;
    int width;
    int height;
    MapVisualizer visualizer = new MapVisualizer(this);
    public void refreshMap(){
        for (int i = 0; i < this.height; i++){
            for (int j = 0; j < this.width; j++){
                isOccupiedMap[i][j] = false;
            }
        }
        for (Animal animal : animals) {
            isOccupiedMap[animal.getCoordinates().y][animal.getCoordinates().x] = true;
        }
    }
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.isOccupiedMap = new boolean[this.height][this.width];
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        this.refreshMap();
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getCoordinates())){
            isOccupiedMap[animal.getCoordinates().y][animal.getCoordinates().x] = true;
            animals.add(animal);
            return true;
        }
        else return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        this.refreshMap();
        if (position.x < this.width && position.y < this.height &&
                position.x >= 0 && position.y >= 0) {
            return isOccupiedMap[position.y][position.x];
        }
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        this.refreshMap();
        if (isOccupied(position)){
            for (Animal animal : animals) {
                if (animal.getCoordinates().equals(position))
                    return animal;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        this.refreshMap();
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(this.width-1, this.height-1));
//        return "";
    }

}
