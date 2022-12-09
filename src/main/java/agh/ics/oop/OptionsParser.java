package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] options)
    {
        int length = 0;
        for (String option : options)
            if (option.equals("forward") || option.equals("f") || option.equals("backward") || option.equals("b") ||
                    option.equals("left") || option.equals("l") ||
                    option.equals("right") || option.equals("r"))
                length++;

        MoveDirection[] result = new MoveDirection[length];
        int i = 0;
        for (String option : options)
        {
            switch (option) {
                case "forward", "f" -> result[i] = MoveDirection.FORWARD;
                case "backward", "b" -> result[i] = MoveDirection.BACKWARD;
                case "left", "l" -> result[i] = MoveDirection.LEFT;
                case "right", "r" -> result[i] = MoveDirection.RIGHT;
                default -> throw new IllegalArgumentException(option + " is not legal move specification");
            }
            i++;
        }
        return result;
    }

}
