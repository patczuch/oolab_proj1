package agh.ics.oop;

import java.util.Random;

public enum MapDirection {
    NORTH("Północ", new Vector2d(0,1)),
    NORTHEAST("Północny wschód", new Vector2d(1,1)),
    EAST("Wschód", new Vector2d(1,0)),
    SOUTHEAST("Południowy wschód", new Vector2d(1,-1)),
    SOUTH("Południe", new Vector2d(0,-1)),
    SOUTHWEST("Południowy zachód", new Vector2d(-1,-1)),
    WEST("Zachód", new Vector2d(-1,0)),
    NORTHWEST("Północny zachód", new Vector2d(-1,1));


    final String name;
    final Vector2d unitVector;
    MapDirection(String name, Vector2d unitVector) {
        this.name = name;
        this.unitVector = unitVector;
    }

    public String toString()
    {
        return this.name;
    }

    public MapDirection rotate(MoveDirection direction) {
        int rotation = direction.numberOfTurns;

        return MapDirection.values()[
                (this.ordinal() + rotation) % 8];
    }

    public MapDirection next()
    {
        return rotate(MoveDirection.FORWARDRIGHT);
    }

    public MapDirection previous()
    {
        return rotate(MoveDirection.FORWARDLEFT);
    }

    public static MapDirection getRandom(Random rand)
    {
        return MapDirection.values()[rand.nextInt()&Integer.MAX_VALUE%MapDirection.values().length];
    }

    public Vector2d toUnitVector()
    {
        return this.unitVector;
    }
}
