package agh.ics.oop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangularGrassTests {


    @Test
    public void canMoveToRect(){
        IWorldMap RecMap = new RectangularMap(10, 10);
        RecMap.place(new Animal(RecMap, new Vector2d(0, 0)));
        RecMap.place(new Animal(RecMap, new Vector2d(23, 12)));
        RecMap.place(new Animal(RecMap, new Vector2d(2, 2)));
        Assertions.assertTrue(RecMap.canMoveTo(new Vector2d(1, 1)));
        Assertions.assertTrue(RecMap.canMoveTo(new Vector2d(0, 1)));
        Assertions.assertTrue(RecMap.canMoveTo(new Vector2d(4, 0)));
        Assertions.assertTrue(RecMap.canMoveTo(new Vector2d(9, 9)));
        Assertions.assertFalse(RecMap.canMoveTo(new Vector2d(0, 0)));
        Assertions.assertFalse(RecMap.canMoveTo(new Vector2d(2, 2)));
        Assertions.assertFalse(RecMap.canMoveTo(new Vector2d(22, 22)));
    }
    @Test
    public void canMoveToGrass(){
        IWorldMap GrassMap = new GrassField(10);
        GrassMap.place(new Animal(GrassMap, new Vector2d(0, 0)));
        GrassMap.place(new Animal(GrassMap, new Vector2d(23, 12)));
        GrassMap.place(new Animal(GrassMap, new Vector2d(2, 2)));
        Assertions.assertTrue(GrassMap.canMoveTo(new Vector2d(1, 1)));
        Assertions.assertTrue(GrassMap.canMoveTo(new Vector2d(0, 1)));
        Assertions.assertTrue(GrassMap.canMoveTo(new Vector2d(4, 0)));
        Assertions.assertTrue(GrassMap.canMoveTo(new Vector2d(9, 9)));
        Assertions.assertTrue(GrassMap.canMoveTo(new Vector2d(900, 900)));
        Assertions.assertFalse(GrassMap.canMoveTo(new Vector2d(0, 0)));
        Assertions.assertFalse(GrassMap.canMoveTo(new Vector2d(2, 2)));
        Assertions.assertFalse(GrassMap.canMoveTo(new Vector2d(23, 12)));
    }
    @Test
    public void placeRect(){
        IWorldMap RecMap = new RectangularMap(10, 10);
        Assertions.assertTrue(RecMap.place(new Animal(RecMap, new Vector2d(0, 0))));
        Assertions.assertTrue(RecMap.place(new Animal(RecMap, new Vector2d(2, 2))));
        Assertions.assertFalse(RecMap.place(new Animal(RecMap, new Vector2d(23, 12))));
        Assertions.assertFalse(RecMap.place(new Animal(RecMap, new Vector2d(234, 4234))));
        Assertions.assertTrue(RecMap.isOccupied(new Vector2d(0, 0)));
        Assertions.assertTrue(RecMap.isOccupied(new Vector2d(2, 2)));
        Assertions.assertFalse(RecMap.isOccupied(new Vector2d(23, 12)));
        Assertions.assertFalse(RecMap.isOccupied(new Vector2d(234, 4234)));
    }
    @Test
    public void placeGrass(){
        IWorldMap GrassMap = new GrassField(10);
        Assertions.assertTrue(GrassMap.place(new Animal(GrassMap, new Vector2d(0, 0))));
        Assertions.assertTrue(GrassMap.place(new Animal(GrassMap, new Vector2d(2, 2))));
        Assertions.assertTrue(GrassMap.place(new Animal(GrassMap, new Vector2d(23, 12))));
        Assertions.assertTrue(GrassMap.place(new Animal(GrassMap, new Vector2d(234, 4234))));
        Assertions.assertFalse(GrassMap.place(new Animal(GrassMap, new Vector2d(234, 4234))));
        Assertions.assertTrue(GrassMap.isOccupied(new Vector2d(0, 0)));
        Assertions.assertTrue(GrassMap.isOccupied(new Vector2d(2, 2)));
        Assertions.assertTrue(GrassMap.isOccupied(new Vector2d(23, 12)));
        Assertions.assertTrue(GrassMap.isOccupied(new Vector2d(234, 4234)));
    }
    @Test
    public void isOccupiedRect(){
        IWorldMap RecMap = new RectangularMap(10, 10);
        Assertions.assertTrue(RecMap.place(new Animal(RecMap, new Vector2d(0, 0))));
        Assertions.assertTrue(RecMap.place(new Animal(RecMap, new Vector2d(2, 2))));
        Assertions.assertFalse(RecMap.place(new Animal(RecMap, new Vector2d(23, 12))));
        Assertions.assertFalse(RecMap.place(new Animal(RecMap, new Vector2d(234, 4234))));
        Assertions.assertTrue(RecMap.isOccupied(new Vector2d(0, 0)));
        Assertions.assertTrue(RecMap.isOccupied(new Vector2d(2, 2)));
        Assertions.assertFalse(RecMap.isOccupied(new Vector2d(23, 12)));
        Assertions.assertFalse(RecMap.isOccupied(new Vector2d(234, 4234)));
    }
    @Test
    public void isOccupiedGrass(){
        IWorldMap GrassMap = new GrassField(10);
        Assertions.assertTrue(GrassMap.place(new Animal(GrassMap, new Vector2d(0, 0))));
        Assertions.assertTrue(GrassMap.place(new Animal(GrassMap, new Vector2d(2, 2))));
        Assertions.assertTrue(GrassMap.place(new Animal(GrassMap, new Vector2d(23, 12))));
        Assertions.assertTrue(GrassMap.place(new Animal(GrassMap, new Vector2d(234, 4234))));
        Assertions.assertFalse(GrassMap.place(new Animal(GrassMap, new Vector2d(234, 4234))));
        Assertions.assertTrue(GrassMap.isOccupied(new Vector2d(0, 0)));
        Assertions.assertTrue(GrassMap.isOccupied(new Vector2d(2, 2)));
        Assertions.assertTrue(GrassMap.isOccupied(new Vector2d(23, 12)));
        Assertions.assertTrue(GrassMap.isOccupied(new Vector2d(234, 4234)));
    }
    @Test
    public void objectAtRect(){
        IWorldMap RecMap = new RectangularMap(10, 10);
        Animal Zbyszek = new Animal(RecMap, new Vector2d(0, 0));
        Animal Janek = new Animal(RecMap, new Vector2d(2, 2));
        Animal Staszek = new Animal(RecMap, new Vector2d(23, 12));
        Animal Gosia = new Animal(RecMap, new Vector2d(234, 4234));
        RecMap.place(Zbyszek);
        RecMap.place(Janek);
        RecMap.place(Staszek);
        RecMap.place(Gosia);
        Assertions.assertEquals(Zbyszek, RecMap.objectAt(new Vector2d(0, 0)));
        Assertions.assertEquals(Janek, RecMap.objectAt(new Vector2d(2, 2)));
        Assertions.assertNotEquals(Staszek, RecMap.objectAt(new Vector2d(23, 12)));
        Assertions.assertNotEquals(Gosia, RecMap.objectAt(new Vector2d(234, 4234)));
    }
    @Test
    public void objectAtGrass(){
        IWorldMap GrassMap = new GrassField(10);
        Animal Zbyszek = new Animal(GrassMap, new Vector2d(0, 0));
        Animal Janek = new Animal(GrassMap, new Vector2d(2, 2));
        Animal Staszek = new Animal(GrassMap, new Vector2d(23, 12));
        Animal Gosia = new Animal(GrassMap, new Vector2d(234, 4234));
        GrassMap.place(Zbyszek);
        GrassMap.place(Janek);
        GrassMap.place(Staszek);
        GrassMap.place(Gosia);
        Assertions.assertEquals(Zbyszek, GrassMap.objectAt(new Vector2d(0, 0)));
        Assertions.assertEquals(Janek, GrassMap.objectAt(new Vector2d(2, 2)));
        Assertions.assertEquals(Staszek, GrassMap.objectAt(new Vector2d(23, 12)));
        Assertions.assertEquals(Gosia, GrassMap.objectAt(new Vector2d(234, 4234)));
    }
}
