package agh.ics.oop;

public enum MapDirection {
    NORTH("Północ", new Vector2d(0,1)),
    SOUTH("Południe", new Vector2d(0,-1)),
    WEST("Zachód", new Vector2d(-1,0)),
    EAST("Wschód", new Vector2d(1,0));


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
             case EAST -> SOUTH;
             case WEST -> NORTH;
             case NORTH -> EAST;
             case SOUTH -> WEST;
        };
    }

    public MapDirection previous()
    {
        return switch(this)
                {
                    case EAST -> NORTH;
                    case WEST -> SOUTH;
                    case NORTH -> WEST;
                    case SOUTH -> EAST;
                };
    }

    public Vector2d toUnitVector()
    {
        return this.unitVector;
    }
}
