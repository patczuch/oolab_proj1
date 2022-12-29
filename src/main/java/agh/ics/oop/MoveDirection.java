package agh.ics.oop;

import java.util.Random;

public enum MoveDirection {
    FORWARD(0),
    FORWARDRIGHT(1),
    RIGHT(2),
    BACKWARDRIGHT(3),
    BACKWARD(4),
    BACKWARDLEFT(5),
    LEFT(6),
    FORWARDLEFT(7);

    public final int numberOfTurns;
    MoveDirection(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public static MoveDirection[] randomMoves(Random rand, int l)
    {
        MoveDirection[] genes = new MoveDirection[l];
        for (int i = 0; i<l; i++)
            genes[i] = MoveDirection.values()[rand.nextInt(MoveDirection.values().length)];
        return genes;
    }

    public static MoveDirection random(Random rand)
    {
        return MoveDirection.values()[rand.nextInt(MoveDirection.values().length)];
    }

    public MoveDirection next()
    {
        if (this.ordinal()+1 >= MoveDirection.values().length)
            return MoveDirection.values()[0];
        return MoveDirection.values()[this.ordinal()+1];
    }

    public MoveDirection previous()
    {
        if (this.ordinal()-1 < 0)
            return MoveDirection.values()[MoveDirection.values().length-1];
        return MoveDirection.values()[this.ordinal()-1];
    }

    public char humanReadable() {
        return switch (this) {
            case FORWARD -> '↑';
            case FORWARDRIGHT -> '↗';
            case RIGHT -> '→';
            case BACKWARDRIGHT -> '↘';
            case BACKWARD -> '↓';
            case BACKWARDLEFT -> '↙';
            case LEFT -> '←';
            case FORWARDLEFT -> '↖';
        };
    }
}
