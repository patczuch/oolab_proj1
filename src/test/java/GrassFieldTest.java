import agh.ics.oop.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {
    @Test
    public void fullTest()
    {
        GrassField map =  new GrassField(10);
        Assertions.assertTrue(map.canMoveTo(new Vector2d(5,5)));
        Assertions.assertTrue(map.canMoveTo(new Vector2d(-1,-1)));
        Animal a = new Animal(map,new Vector2d(2,2));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,3)));
        Animal b = new Animal(map,new Vector2d(3,4));
        b.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(b.isAt(new Vector2d(3,3)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(3,3)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(3,4)));
        Assertions.assertNull(map.objectAt(new Vector2d(3, 4)));
        Assertions.assertNotNull(map.objectAt(new Vector2d(3, 3)));
        Assertions.assertSame(map.objectAt(new Vector2d(3, 3)), b);
        Assertions.assertFalse(map.canMoveTo(new Vector2d(3,3)));
        a.move(MoveDirection.RIGHT);
        Assertions.assertTrue(a.isAt(new Vector2d(2,3)));
        b.move(MoveDirection.LEFT);
        Assertions.assertTrue(b.isAt(new Vector2d(3,3)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,3)));
        b.move(MoveDirection.FORWARD);
        Assertions.assertTrue(b.isAt(new Vector2d(3,3)));
        a.move(MoveDirection.RIGHT);
        Assertions.assertTrue(a.isAt(new Vector2d(2,3)));
        b.move(MoveDirection.RIGHT);
        Assertions.assertTrue(b.isAt(new Vector2d(3,3)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,2)));
        b.move(MoveDirection.FORWARD);
        Assertions.assertTrue(b.isAt(new Vector2d(3,4)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,1)));
        b.move(MoveDirection.FORWARD);
        Assertions.assertTrue(b.isAt(new Vector2d(3,5)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,0)));
        b.move(MoveDirection.FORWARD);
        Assertions.assertTrue(b.isAt(new Vector2d(3,6)));
        a.move(MoveDirection.FORWARD);
        Assertions.assertTrue(a.isAt(new Vector2d(2,-1)));
        b.move(MoveDirection.FORWARD);
        Assertions.assertTrue(b.isAt(new Vector2d(3,7)));
    }
}
