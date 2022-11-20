package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class GrassField implements IWorldMap{
    List<Grass> grasses = new ArrayList<>();
    List<Animal> animals = new ArrayList<>();
    MapVisualizer visualizer = new MapVisualizer(this);
    public GrassField(int n){
        Vector2d randomPosition;
        int flag;
        for (int i = 0; i < n; i++){
            flag = 0;
            randomPosition = new Vector2d((int) (Math.random()*Math.sqrt(n*10)),
                    (int) (Math.random()*Math.sqrt(n*10)));

            for (Grass grass : grasses){
                if (grass.position.equals(randomPosition)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0){
                grasses.add(new Grass(randomPosition));
            }

        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
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
        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(0, 0);
        for (Animal animal : animals){
            if (animal.getPosition().x < lowerLeft.x){
                lowerLeft = new Vector2d(animal.getPosition().x, lowerLeft.y);
            }
            if (animal.getPosition().y < lowerLeft.y){
                lowerLeft = new Vector2d(lowerLeft.x, animal.getPosition().y);
            }
            if (animal.getPosition().x > upperRight.x){
                upperRight = new Vector2d(animal.getPosition().x, upperRight.y);
            }
            if (animal.getPosition().y > upperRight.y){
                upperRight = new Vector2d(upperRight.x, animal.getPosition().y);
            }
        }
        return visualizer.draw(lowerLeft, upperRight);
    }
}
