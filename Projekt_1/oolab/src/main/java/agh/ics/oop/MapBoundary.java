package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;


public class MapBoundary implements IPositionChangeObserver{
    Comparator<Vector2d> byX = Comparator.comparingInt((Vector2d x) -> x.x).thenComparingInt((Vector2d x) -> x.y);
    Comparator<Vector2d> byY = Comparator.comparingInt((Vector2d y) -> y.y).thenComparingInt((Vector2d y) -> y.x);
    SortedSet<Vector2d> sortedObjectsX = new TreeSet<>(byX);
    SortedSet<Vector2d> sortedObjectsY = new TreeSet<>(byY);
    public Vector2d[] getBoundary(){
        Vector2d[] borderVectors = new Vector2d[2];
        borderVectors[0] = new Vector2d(sortedObjectsX.first().x, sortedObjectsY.first().y);
        borderVectors[1] = new Vector2d(sortedObjectsX.last().x, sortedObjectsY.last().y);
        return borderVectors;
    }
    public void addObject(Vector2d position){
        sortedObjectsX.add(position);
        sortedObjectsY.add(position);
    }
    public void removeObject(Vector2d position){
        sortedObjectsX.remove(position);
        sortedObjectsY.remove(position);
    }
    public String toString(){
        return "X: " + sortedObjectsX.toString() + "Y: " + sortedObjectsY.toString();
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeObject(oldPosition);
        addObject(newPosition);
//        System.out.println(this);
    }
}
