package agh.ics.oop;

public class Animal {
    private MapDirection currDirection = MapDirection.NORTH;
    private Vector2d coordinates = new Vector2d(2, 2);

    public boolean isAt(Vector2d position){
        return coordinates.equals(position);
    }
    public void move(MoveDirection direction){
        int border = 4;
        Vector2d newCoordinates;
        switch (direction) {
            case LEFT -> currDirection = currDirection.previous();
            case RIGHT -> currDirection = currDirection.next();
            case FORWARD -> {
                newCoordinates = coordinates.add(currDirection.toUnitVector());
                if (newCoordinates.x <= border && newCoordinates.y <= border &&
                        newCoordinates.x >= 0 && newCoordinates.y >= 0) {
                    coordinates = newCoordinates;
                }
            }
            case BACKWARD -> {
                newCoordinates = coordinates.subtract(currDirection.toUnitVector());
                if (newCoordinates.x <= border && newCoordinates.y <= border &&
                        newCoordinates.x >= 0 && newCoordinates.y >= 0) {
                    coordinates = newCoordinates;
                }
                ;
            }
        }
    }

    @Override
    public String toString() {
        return "orientacja: " + currDirection +
                ", pozycja: " + coordinates;
    }
}
