package agh.ics.oop;

public class RectangularMap implements IWorldMap{
    Animal[][] map;
    int width;
    int height;
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.map = new Animal[height][width];
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
