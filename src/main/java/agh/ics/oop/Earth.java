package agh.ics.oop;

import java.util.HashMap;
import java.util.Random;

public class Earth extends AbstractWorldMap{

    public Earth(int width, int height)
    {
        this(width, height, new Random());
    }

    public Earth(int width, int height, Random rand)
    {
        super(width,height,rand);
    }

    @Override
    public boolean isPreferableForPlants(Vector2d position) {
        int height = (this.getUpperRight().subtract(this.getLowerLeft())).y + 1;
        return Math.abs(height / 2 - position.y) <= 0.1 * height;
    }
}
