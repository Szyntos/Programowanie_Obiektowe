package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
