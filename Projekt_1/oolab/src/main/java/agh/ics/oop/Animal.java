package agh.ics.oop;

public class Animal implements IMapElement{
    private MapDirection currDirection;
    private Vector2d position;
    IWorldMap map;
    public Animal(){
        this.currDirection = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.map = new RectangularMap(4, 4);
    }
    public Animal(IWorldMap map){
        this.currDirection = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.map = map;
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.currDirection = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }


    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    public void move(MoveDirection direction){
        switch (direction) {
            case LEFT -> currDirection = currDirection.previous();
            case RIGHT -> currDirection = currDirection.next();
            case FORWARD -> {
                if (map.canMoveTo(position.add(currDirection.toUnitVector()))){

                    position = position.add(currDirection.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (map.canMoveTo(position.subtract(currDirection.toUnitVector()))){
                    position = position.subtract(currDirection.toUnitVector());
                }
            }
        }
    }

    public Vector2d getPosition() {
        return position;
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
