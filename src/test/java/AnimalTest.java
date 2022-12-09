import agh.ics.oop.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AnimalTest {
    @Test
    public void toStringTest()
    {
        RectangularMap recMap = new RectangularMap(5,5);
        Animal a = new Animal(recMap, new Vector2d(2,2));
        Assertions.assertEquals(a.toString(),"^");
        //Assertions.assertEquals(a.toString(),"(2,2) Północ");
        a.move(MoveDirection.FORWARD);
        a.move(MoveDirection.RIGHT);
        Assertions.assertEquals(a.toString(),">");
        //Assertions.assertEquals(a.toString(),"(2,3) Wschód");
    }
    @Test
    public void moveTest()
    {
        RectangularMap recMap = new RectangularMap(5,5);
        Animal a = new Animal(recMap, new Vector2d(2,2));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new Animal(recMap, new Vector2d(2,2)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,3)));
        a.move(MoveDirection.LEFT);
        Assertions.assertTrue(a.isAt(new Vector2d(2,3)));
        Assertions.assertTrue(a.isFacing(MapDirection.WEST));
        a.move(MoveDirection.LEFT);
        Assertions.assertTrue(a.isFacing(MapDirection.SOUTH));
        a.move(MoveDirection.LEFT);
        Assertions.assertTrue(a.isFacing(MapDirection.EAST));
        a.move(MoveDirection.RIGHT);
        Assertions.assertTrue(a.isFacing(MapDirection.SOUTH));
        a.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,4)));
        a.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,4)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,3)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,2)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,1)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,0)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,0)));
        a.move(MoveDirection.RIGHT);
        Assertions.assertTrue(a.isFacing(MapDirection.WEST));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(1,0)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(0,0)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(0,0)));
        a.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(1,0)));
        a.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,0)));
        a.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(3,0)));
        a.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(4,0)));
        a.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(4,0)));
        a.move(MoveDirection.RIGHT);
        Assertions.assertTrue(a.isFacing(MapDirection.NORTH));
        a.move(MoveDirection.RIGHT);
        Assertions.assertTrue(a.isFacing(MapDirection.EAST));
        a.move(MoveDirection.RIGHT);
        Assertions.assertTrue(a.isFacing(MapDirection.SOUTH));
    }
    /*@Test
    public void equalsTest()
    {
        RectangularMap recMap = new RectangularMap(5,5);
        Animal a = new Animal(recMap);
        Animal b = new Animal(recMap);
        Assertions.assertEquals(a,b);
        Assertions.assertNotEquals(a,null);
        a.move(MoveDirection.FORWARD);
        Assertions.assertNotEquals(a,b);
        a.move(MoveDirection.BACKWARD);
        a.move(MoveDirection.LEFT);
        Assertions.assertNotEquals(a,b);
    }
    @Test
    public void hashCodeTest()
    {
        RectangularMap recMap = new RectangularMap(5,5);
        Animal a = new Animal(recMap);
        Animal b = new Animal(recMap);
        Assertions.assertEquals(a.hashCode(),b.hashCode());
    }*/
    @Test
    public void isPositionEqualTest()
    {
        RectangularMap recMap = new RectangularMap(5,5);
        Animal a = new Animal(recMap, new Vector2d(2,2));
        Assertions.assertTrue(a.isAt(new Vector2d(2,2)));
        Assertions.assertFalse(a.isAt(new Vector2d(2,0)));
    }
    @Test
    public void isOrientationEqualTest()
    {
        RectangularMap recMap = new RectangularMap(5,5);
        Animal a = new Animal(recMap);
        Assertions.assertTrue(a.isFacing(MapDirection.NORTH));
        Assertions.assertFalse(a.isFacing(MapDirection.SOUTH));
    }
}
