package agh.ics.oop;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

public class Vector2dTest {
    @Test
    public void testEquals(){
        Assertions.assertTrue(new Vector2d(1, 2).equals(new Vector2d(1, 2)));
        Assertions.assertTrue(new Vector2d(188, 2865).equals(new Vector2d(188, 2865)));
        Assertions.assertTrue(new Vector2d(14456, -2).equals(new Vector2d(14456, -2)));
        Assertions.assertTrue(new Vector2d(13, -122).equals(new Vector2d(13, -122)));
        Assertions.assertFalse(new Vector2d(313, -122).equals(new Vector2d(13, -122)));
        Assertions.assertFalse(new Vector2d(3, -122).equals(new Vector2d(13, -122)));
        Assertions.assertFalse(new Vector2d(13, -122).equals(new Vector2d(13, 0)));
        Assertions.assertFalse(new Vector2d(0, -122).equals(new Vector2d(13, -122)));
    }
    @Test
    public void testToString(){
        Assertions.assertEquals("(2,1)", new Vector2d(2, 1).toString());
        Assertions.assertEquals("(634,9456)", new Vector2d(634, 9456).toString());
        Assertions.assertEquals("(11,44)", new Vector2d(11, 44).toString());
        Assertions.assertEquals("(-421,-44)", new Vector2d(-421,-44).toString());
    }
    @Test
    public void testPrecedes(){
        Assertions.assertFalse(new Vector2d(2, 1).precedes(new Vector2d(-2, 1)));
        Assertions.assertFalse(new Vector2d(-1, 1).precedes(new Vector2d(-2, 1)));
        Assertions.assertTrue(new Vector2d(-3, 1).precedes(new Vector2d(-2, 1)));
        Assertions.assertTrue(new Vector2d(-3, -1).precedes(new Vector2d(-2, 1)));
        Assertions.assertTrue(new Vector2d(-2, 1).precedes(new Vector2d(-2, 1)));
    }
    @Test
    public void testFollows() {
        Assertions.assertTrue(new Vector2d(2, 1).follows(new Vector2d(-2, 1)));
        Assertions.assertTrue(new Vector2d(-1, 1).follows(new Vector2d(-2, 1)));
        Assertions.assertFalse(new Vector2d(-3, 1).follows(new Vector2d(-2, 1)));
        Assertions.assertFalse(new Vector2d(-3, -1).follows(new Vector2d(-2, 1)));
        Assertions.assertTrue(new Vector2d(-2, 1).follows(new Vector2d(-2, 1)));
    }
    @Test
    public void testUpperRight(){
        Assertions.assertEquals(new Vector2d(2, 2), new Vector2d(2, 1).upperRight(new Vector2d(1, 2)));
        Assertions.assertEquals(new Vector2d(12, 31), new Vector2d(12, 1).upperRight(new Vector2d(1, 31)));
        Assertions.assertEquals(new Vector2d(-2, -2), new Vector2d(-2, -222).upperRight(new Vector2d(-2221, -2)));
        Assertions.assertEquals(new Vector2d(2, 2), new Vector2d(2, 2).upperRight(new Vector2d(2, 2)));
        for (int i = 0; i < 100; i++){
            int a = (int)(Math.random() * 200 - 100);
            int b = (int)(Math.random() * 200 - 100);
            int c = (int)(Math.random() * 200 - 100);
            int d = (int)(Math.random() * 200 - 100);
            Assertions.assertEquals(new Vector2d(Math.max(a, b), Math.max(c, d)), new Vector2d(a, c).upperRight(new Vector2d(b, d)));
        }
    }
    @Test
    public void testLowerLeft(){
        Assertions.assertEquals(new Vector2d(1, 1), new Vector2d(2, 1).lowerLeft(new Vector2d(1, 2)));
        Assertions.assertEquals(new Vector2d(1, 1), new Vector2d(12, 1).lowerLeft(new Vector2d(1, 31)));
        Assertions.assertEquals(new Vector2d(-2221, -222), new Vector2d(-2, -222).lowerLeft(new Vector2d(-2221, -2)));
        Assertions.assertEquals(new Vector2d(2, 2), new Vector2d(2, 2).lowerLeft(new Vector2d(2, 2)));
        for (int i = 0; i < 100; i++){
            int a = (int)(Math.random() * 200 - 100);
            int b = (int)(Math.random() * 200 - 100);
            int c = (int)(Math.random() * 200 - 100);
            int d = (int)(Math.random() * 200 - 100);
            Assertions.assertEquals(new Vector2d(Math.min(a, b), Math.min(c, d)), new Vector2d(a, c).lowerLeft(new Vector2d(b, d)));
        }
    }
    @Test
    public void testAdd(){
        Assertions.assertEquals(new Vector2d(2 + 4, 8 + 12), new Vector2d(2, 8).add(new Vector2d(4, 12)));
        Assertions.assertEquals(new Vector2d(32 - 900, 5 + 1), new Vector2d(32, 5).add(new Vector2d(-900, 1)));
        Assertions.assertEquals(new Vector2d(921 - 45, 45223 - 0), new Vector2d(921, 45223).add(new Vector2d(-45, 0)));
        Assertions.assertEquals(new Vector2d(-43 + 0, -452 + 1), new Vector2d(-43, -452).add(new Vector2d(0, 1)));
        for (int i = 0; i < 100; i++){
            int a = (int)(Math.random() * 200 - 100);
            int b = (int)(Math.random() * 200 - 100);
            int c = (int)(Math.random() * 200 - 100);
            int d = (int)(Math.random() * 200 - 100);
            Assertions.assertEquals(new Vector2d(a+b, c+d), new Vector2d(a, c).add(new Vector2d(b, d)));
        }
    }
    @Test
    public void testSubtract(){
        Assertions.assertEquals(new Vector2d(2 - 4, 8 - 12), new Vector2d(2, 8).subtract(new Vector2d(4, 12)));
        Assertions.assertEquals(new Vector2d(32 + 900, 5 - 1), new Vector2d(32, 5).subtract(new Vector2d(-900, 1)));
        Assertions.assertEquals(new Vector2d(921 + 45, 45223 + 0), new Vector2d(921, 45223).subtract(new Vector2d(-45, 0)));
        Assertions.assertEquals(new Vector2d(-43 - 0, -452 - 1), new Vector2d(-43, -452).subtract(new Vector2d(0, 1)));
        for (int i = 0; i < 100; i++){
            int a = (int)(Math.random() * 200 - 100);
            int b = (int)(Math.random() * 200 - 100);
            int c = (int)(Math.random() * 200 - 100);
            int d = (int)(Math.random() * 200 - 100);
            Assertions.assertEquals(new Vector2d(a-b, c-d), new Vector2d(a, c).subtract(new Vector2d(b, d)));
        }
    }
    @Test
    public void testOpposite(){
        Assertions.assertEquals(new Vector2d(2, 8), new Vector2d(-2, -8).opposite());
        Assertions.assertEquals(new Vector2d(32, 5), new Vector2d(-32, -5).opposite());
        Assertions.assertEquals(new Vector2d(921, 45223), new Vector2d(-921, -45223).opposite());
        Assertions.assertEquals(new Vector2d(-43, -452), new Vector2d(43, 452).opposite());
        for (int i = 0; i < 100; i++){
            int a = (int)(Math.random() * 200 - 100);
            int b = (int)(Math.random() * 200 - 100);
            Assertions.assertEquals(new Vector2d(-a, -b), new Vector2d(a, b).opposite());
        }
    }
}
