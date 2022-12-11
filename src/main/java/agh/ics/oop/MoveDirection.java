package agh.ics.oop;

import java.util.Random;

public enum MoveDirection {
    FORWARD(0),
    BACKWARD(4),
    RIGHT(2),
    LEFT(6),
    FORWARDLEFT(7),
    BACKWARDLEFT(5),
    FORWARDRIGHT(1),
    BACKWARDRIGHT(3);

    public final int numberOfTurns;
    MoveDirection(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public static MoveDirection[] randomMoves(Random rand, int l)
    {
        MoveDirection[] genes = new MoveDirection[l];
        for (int i = 0; i<l; i++)
            genes[i] = MoveDirection.values()[rand.nextInt()&Integer.MAX_VALUE%MoveDirection.values().length];
        return genes;
    }
}
