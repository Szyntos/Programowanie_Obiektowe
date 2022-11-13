package agh.ics.oop;

public class Animal {
    private MapDirection currDirection;
    private Vector2d coordinates;
    IWorldMap map;
    public Animal(){
        this.currDirection = MapDirection.NORTH;
        this.coordinates = new Vector2d(2, 2);
        this.map = new RectangularMap(4, 4);
    }
    public Animal(IWorldMap map){
        this.currDirection = MapDirection.NORTH;
        this.coordinates = new Vector2d(2, 2);
        this.map = map;
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.currDirection = MapDirection.NORTH;
        this.coordinates = initialPosition;
        this.map = map;
    }


    public boolean isAt(Vector2d position){
        return coordinates.equals(position);
    }
    public void move(MoveDirection direction){
        switch (direction) {
            case LEFT -> currDirection = currDirection.previous();
            case RIGHT -> currDirection = currDirection.next();
            case FORWARD -> {
                if (map.canMoveTo(coordinates.add(currDirection.toUnitVector()))){

                    coordinates = coordinates.add(currDirection.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (map.canMoveTo(coordinates.subtract(currDirection.toUnitVector()))){
                    coordinates = coordinates.subtract(currDirection.toUnitVector());
                }
            }
        }
    }

    public Vector2d getCoordinates() {
        return coordinates;
    }

    public MapDirection getCurrDirection() {
        return currDirection;
    }

    @Override
    public String toString() {
        return switch (currDirection){
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }
}
