package agh.ics.oop;

import java.util.Random;

public enum MapDirection {
    NORTH("Północ", new Vector2d(0,1)),
    SOUTH("Południe", new Vector2d(0,-1)),
    WEST("Zachód", new Vector2d(-1,0)),
    EAST("Wschód", new Vector2d(1,0)),
    NORTHEAST("Północny wschód", new Vector2d(1,1)),
    NORTHWEST("Północny zachód", new Vector2d(-1,1)),
    SOUTHEAST("Południowy wschód", new Vector2d(1,-1)),
    SOUTHWEST("Południowy zachód", new Vector2d(-1,-1));


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

    public MapDirection next()
    {
        return switch(this)
        {
             case EAST -> SOUTHEAST;
             case SOUTHEAST -> SOUTH;
             case SOUTH -> SOUTHWEST;
             case SOUTHWEST -> WEST;
             case WEST -> NORTHWEST;
             case NORTHWEST -> NORTH;
             case NORTH -> NORTHEAST;
             case NORTHEAST -> EAST;
        };
    }

    public MapDirection previous()
    {
        return switch(this)
                {
                    case SOUTHEAST -> EAST;
                    case SOUTH -> SOUTHEAST;
                    case SOUTHWEST -> SOUTH;
                    case WEST -> SOUTHWEST;
                    case NORTHWEST -> WEST;
                    case NORTH -> NORTHWEST;
                    case NORTHEAST -> NORTH;
                    case EAST -> NORTHEAST;
                };
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
