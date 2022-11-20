package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class GrassField extends AbstractWorldMap{
    List<Grass> grasses = new ArrayList<>();
    int n;
    public GrassField(int n){
        this.n = n;
        for (int i = 0; i < n; i++){
            grasses.add(addGrass(new Vector2d(0, 0), true));
        }
    }
    public Grass addGrass(Vector2d notHere, boolean everywhere){
        Vector2d randomPosition;
        int flag;
        randomPosition = new Vector2d((int) (Math.random()*Math.sqrt(n*10)),
                (int) (Math.random()*Math.sqrt(n*10)));
        flag = 0;
        for (Grass grass : grasses){
            if (grass.isAt(randomPosition)) {
                flag = 1;
                break;
            }
        }
        for (Animal animal : animals){
            if (animal.isAt(randomPosition)) {
                flag = 1;
                break;
            }
        }
        if (randomPosition.equals(notHere)&&!everywhere){
            flag = 1;
        }
        while (flag == 1){
            randomPosition = new Vector2d((int) (Math.random()*Math.sqrt(n*10)),
                    (int) (Math.random()*Math.sqrt(n*10)));
            flag = 0;
            for (Grass grass : grasses){
                if (grass.isAt(randomPosition)) {
                    flag = 1;
                    break;
                }
            }
            for (Animal animal : animals){
                if (animal.isAt(randomPosition)) {
                    flag = 1;
                    break;
                }
            }
            if (randomPosition.equals(notHere)&&!everywhere){
                flag = 1;
            }
        }
        return new Grass(randomPosition);
    }
    public Vector2d[] getBorders(){
        Vector2d[] borderVectors = new Vector2d[2];
        int [] borders = {0, 0, 0, 0};
        if (grasses.size() > 0){
            borders[0] = grasses.get(0).getPosition().x;
            borders[1] = grasses.get(0).getPosition().y;
            borders[2] = grasses.get(0).getPosition().x;
            borders[3] = grasses.get(0).getPosition().y;
        }else if(animals.size() > 0){
            borders[0] = animals.get(0).getPosition().x;
            borders[1] = animals.get(0).getPosition().y;
            borders[2] = animals.get(0).getPosition().x;
            borders[3] = animals.get(0).getPosition().y;
        }else {
            borderVectors[0] = new Vector2d(0, 0);
            borderVectors[1] = new Vector2d(1, 1);
            return borderVectors;
        }
        for (Grass grass : grasses){
            if (grass.getPosition().x < borders[0]){
                borders[0] = grass.getPosition().x;
            }
            if (grass.getPosition().y < borders[1]){
                borders[1] = grass.getPosition().y;
            }
            if (grass.getPosition().x > borders[2]){
                borders[2] = grass.getPosition().x;
            }
            if (grass.getPosition().y > borders[3]){
                borders[3] = grass.getPosition().y;
            }
        }
        for (Animal animal : animals){
            if (animal.getPosition().x < borders[0]){
                borders[0] = animal.getPosition().x;
            }
            if (animal.getPosition().y < borders[1]){
                borders[1] = animal.getPosition().y;
            }
            if (animal.getPosition().x > borders[2]){
                borders[2] = animal.getPosition().x;
            }
            if (animal.getPosition().y > borders[3]){
                borders[3] = animal.getPosition().y;
            }
        }
        borderVectors[0] = new Vector2d(borders[0], borders[1]);
        borderVectors[1] = new Vector2d(borders[2], borders[3]);
        return borderVectors;
    }

    void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.isAt(position)) {
                return false;
            }
        }
        for (int i = 0; i < grasses.size(); i++){
            Grass grass = grasses.get(i);
            if (grass.isAt(position)){
                grasses.set(i, addGrass(position, false));
            }
        }
        return true;
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.isAt(position)) {
                return true;
            }
        }
        for (Grass grass: grasses) {
            if (grass.isAt(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        for (Grass grass: grasses) {
            if (grass.isAt(position)) {
                return grass;
            }
        }
        return null;
    }

}
