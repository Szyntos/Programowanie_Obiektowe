package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapBoundary implements IPositionChangeObserver{
    Comparator<Vector2d> byX = (Vector2d x1, Vector2d x2) -> x1.x - x2.x;
    Comparator<Vector2d> byY = (Vector2d y1, Vector2d y2) -> y1.y - y2.y;
    SortedMap<Vector2d, IMapElement> sortedObjectsX = new TreeMap<Vector2d, IMapElement>(byX);
    SortedMap<Vector2d, IMapElement> sortedObjectsY = new TreeMap<Vector2d, IMapElement>(byY);

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement Grzegorz = sortedObjectsX.get(oldPosition);
        sortedObjectsX.remove(oldPosition);
        sortedObjectsX.put(newPosition, Grzegorz);
        sortedObjectsY.remove(oldPosition);
        sortedObjectsY.put(newPosition, Grzegorz);
    }
}
