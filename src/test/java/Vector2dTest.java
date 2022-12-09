import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import agh.ics.oop.Vector2d;

public class Vector2dTest {
    @Test
    public void equalsTest()
    {
        Assertions.assertEquals(new Vector2d(2, 0), new Vector2d(2, 0));
        Assertions.assertNotEquals(new Vector2d(2, 0), new Vector2d(1, 0));
        Assertions.assertNotEquals(new Vector2d(2, 0), new Vector2d(2, 1));
        Assertions.assertNotEquals(new Vector2d(2, 0), new Object());
        Assertions.assertNotEquals(new Vector2d(2, 0), null);
    }
    @Test
    public void toStringTest()
    {
        Assertions.assertEquals(new Vector2d(2, 0).toString(), "(2,0)");
        Assertions.assertEquals(new Vector2d(-2, -1).toString(), "(-2,-1)");
    }
    @Test
    public void precedesTest()
    {
        Assertions.assertTrue(new Vector2d(2, 0).precedes(new Vector2d(4,1)));
        Assertions.assertTrue(new Vector2d(-2, -1).precedes(new Vector2d(-1,0)));
        Assertions.assertFalse(new Vector2d(-1,0).precedes(new Vector2d(-2, -1)));
        Assertions.assertTrue(new Vector2d(0,0).precedes(new Vector2d(0, 0)));
        Assertions.assertFalse(new Vector2d(2,0).precedes(new Vector2d(0, 0)));
    }
    @Test
    public void followsTest()
    {
        Assertions.assertTrue(new Vector2d(4,1).follows(new Vector2d(2,0)));
        Assertions.assertTrue(new Vector2d(-1,0).follows(new Vector2d(-2, -1)));
        Assertions.assertTrue(new Vector2d(-1,0).follows(new Vector2d(-2, -1)));
        Assertions.assertFalse(new Vector2d(-2, -1).follows(new Vector2d(-1,0)));
    }
    @Test
    public void upperRightTest()
    {
        Assertions.assertEquals(new Vector2d(2,0).upperRight(new Vector2d(2,2)), new Vector2d(2,2));
        Assertions.assertEquals(new Vector2d(2,0).upperRight(new Vector2d(1,2)), new Vector2d(2,2));
        Assertions.assertEquals(new Vector2d(-2,0).upperRight(new Vector2d(1,-2)), new Vector2d(1,0));
    }
    @Test
    public void lowerLeftTest()
    {
        Assertions.assertEquals(new Vector2d(2,0).lowerLeft(new Vector2d(2,2)), new Vector2d(2,0));
        Assertions.assertEquals(new Vector2d(2,0).lowerLeft(new Vector2d(1,2)), new Vector2d(1,0));
        Assertions.assertEquals(new Vector2d(-2,0).lowerLeft(new Vector2d(1,-2)), new Vector2d(-2,-2));
    }
    @Test
    public void addTest()
    {
        Assertions.assertEquals(new Vector2d(2,0).add(new Vector2d(0,0)),new Vector2d(2,0));
        Assertions.assertEquals(new Vector2d(2,1).add(new Vector2d(3,5)),new Vector2d(5,6));
        Assertions.assertEquals(new Vector2d(2,1).add(new Vector2d(-3,-5)),new Vector2d(-1,-4));
    }
    @Test
    public void subtractTest()
    {
        Assertions.assertEquals(new Vector2d(2,0).subtract(new Vector2d(0,0)),new Vector2d(2,0));
        Assertions.assertEquals(new Vector2d(2,1).subtract(new Vector2d(3,5)),new Vector2d(-1,-4));
        Assertions.assertEquals(new Vector2d(2,1).subtract(new Vector2d(-3,-5)),new Vector2d(5,6));
    }
    @Test
    public void oppositeTest()
    {
        Assertions.assertEquals(new Vector2d(2,0).opposite(),new Vector2d(-2,0));
        Assertions.assertEquals(new Vector2d(1,1).opposite(),new Vector2d(-1,-1));
    }

    @Test
    public void hashcodeTest()
    {
        Assertions.assertEquals(new Vector2d(2,0).hashCode(),new Vector2d(2,0).hashCode());
    }
}
