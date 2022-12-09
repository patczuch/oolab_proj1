import agh.ics.oop.MoveDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import agh.ics.oop.OptionsParser;

public class OptionsParserTest {
    @Test
    public void parseTest() {
        Assertions.assertArrayEquals(OptionsParser.parse(new String[]{"f","b","r","l","forward","backward","left","right"}),
                new MoveDirection[]{MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.RIGHT,MoveDirection.LEFT,MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.RIGHT});
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> OptionsParser.parse(new String[]{"x"}));
    }
}
