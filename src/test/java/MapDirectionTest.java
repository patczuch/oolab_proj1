import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import agh.ics.oop.MapDirection;
import agh.ics.oop.Vector2d;

public class MapDirectionTest {
    @Test
    public void nextTest()
    {
        Assertions.assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        Assertions.assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
        Assertions.assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
        Assertions.assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
    }

    @Test
    public void previousTest()
    {
        Assertions.assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST);
        Assertions.assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH);
        Assertions.assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST);
        Assertions.assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH);
    }

    @Test
    public void toStringTest()
    {
        Assertions.assertEquals(MapDirection.NORTH.toString(), "Północ");
        Assertions.assertEquals(MapDirection.EAST.toString(), "Wschód");
        Assertions.assertEquals(MapDirection.SOUTH.toString(), "Południe");
        Assertions.assertEquals(MapDirection.WEST.toString(), "Zachód");
    }

    @Test
    public void toUnitVectorTest()
    {
        Assertions.assertEquals(MapDirection.NORTH.toUnitVector(), new Vector2d(0,1));
        Assertions.assertEquals(MapDirection.EAST.toUnitVector(), new Vector2d(1,0));
        Assertions.assertEquals(MapDirection.SOUTH.toUnitVector(), new Vector2d(0,-1));
        Assertions.assertEquals(MapDirection.WEST.toUnitVector(), new Vector2d(-1,0));
    }
}
