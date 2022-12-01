package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrassField extends AbstractWorldMap{
    Map<Vector2d, Grass> grasses = new HashMap<>();
    int n;
    public GrassField(int n){
        this.n = n;
        Grass newGrass;
        for (int i = 0; i < n; i++){
            newGrass = addGrass(new Vector2d(0, 0), true);
            grasses.put(newGrass.getPosition(), newGrass);
        }
    }
    public Grass addGrass(Vector2d notHere, boolean everywhere){
        Vector2d randomPosition;
        int flag;
        randomPosition = new Vector2d((int) (Math.random()*Math.sqrt(n*10)),
                (int) (Math.random()*Math.sqrt(n*10)));
        flag = 0;
        for (Map.Entry<Vector2d, Grass> set : grasses.entrySet()){
            if (set.getKey().equals(randomPosition)) {
                flag = 1;
                break;
            }
        }
        for (Map.Entry<Vector2d, Animal> set : animals.entrySet()){
            if (set.getKey().equals(randomPosition)) {
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
            for (Map.Entry<Vector2d, Grass> set : grasses.entrySet()){
                if (set.getKey().equals(randomPosition)) {
                    flag = 1;
                    break;
                }
            }
            for (Map.Entry<Vector2d, Animal> set : animals.entrySet()){
                if (set.getKey().equals(randomPosition)) {
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
        Vector2d defaultMinimalPosition = new Vector2d(0, 0);
        Vector2d defaultMaximalPosition = new Vector2d(1, 1);

        for (Map.Entry<Vector2d, Grass> set : grasses.entrySet()){
            defaultMinimalPosition = set.getKey();
            defaultMaximalPosition = set.getKey();
            break;
        }
        if (grasses.size() > 0){
            borders[0] = defaultMinimalPosition.x;
            borders[1] = defaultMinimalPosition.y;
            borders[2] = defaultMaximalPosition.x;
            borders[3] = defaultMaximalPosition.y;
        }else if(animals.size() > 0){
            borders[0] = defaultMinimalPosition.x;
            borders[1] = defaultMinimalPosition.y;
            borders[2] = defaultMaximalPosition.x;
            borders[3] = defaultMaximalPosition.y;
        }else {
            borderVectors[0] = new Vector2d(0, 0);
            borderVectors[1] = new Vector2d(1, 1);
            return borderVectors;
        }
        for (Map.Entry<Vector2d, Grass> set : grasses.entrySet()){
            if (set.getKey().x < borders[0]){
                borders[0] = set.getKey().x;
            }
            if (set.getKey().y < borders[1]){
                borders[1] = set.getKey().y;
            }
            if (set.getKey().x > borders[2]){
                borders[2] = set.getKey().x;
            }
            if (set.getKey().y > borders[3]){
                borders[3] = set.getKey().y;
            }
        }
        for (Map.Entry<Vector2d, Animal> set : animals.entrySet()){
            if (set.getKey().x < borders[0]){
                borders[0] = set.getKey().x;
            }
            if (set.getKey().y < borders[1]){
                borders[1] = set.getKey().y;
            }
            if (set.getKey().x > borders[2]){
                borders[2] = set.getKey().x;
            }
            if (set.getKey().y > borders[3]){
                borders[3] = set.getKey().y;
            }
        }
        borderVectors[0] = new Vector2d(borders[0], borders[1]);
        borderVectors[1] = new Vector2d(borders[2], borders[3]);
        return borderVectors;
    }

    void addAnimal(Animal animal) {
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        for (Map.Entry<Vector2d, Animal> set : animals.entrySet()){
            if (set.getValue().isAt(position)) {
                return false;
            }
        }
        Grass grassToDelete = new Grass(new Vector2d(0, 0));
        boolean deleting = false;
        for (Map.Entry<Vector2d, Grass> set : grasses.entrySet()){
            Grass grass = set.getValue();
            if (grass.isAt(position)){
                grassToDelete = grass;
                deleting = true;
                break;
            }
        }
        if (deleting){
            grasses.remove(grassToDelete.getPosition());
            Grass newGrass = addGrass(position, false);
            grasses.put(newGrass.getPosition(), newGrass);
        }
        return true;
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position) || grasses.containsKey(position);

    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)){
            return animals.get(position);
        }
        if (grasses.containsKey(position)){
            return grasses.get(position);
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
