package agh.ics.oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Grass implements IMapElement{
    private Vector2d position;
    public Grass(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    @Override
    public Image getVisualRepresentation() throws FileNotFoundException {
        return new Image(new FileInputStream("src/main/resources/Grass.jpg"));
    }

    @Override
    public String toString() {
        return "*";
    }
}
