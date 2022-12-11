package agh.ics.oop;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public interface IMapElement {
    Vector2d getPosition();
    String toString();
    boolean isAt(Vector2d position);

    Image getVisualRepresentation() throws FileNotFoundException;

}
