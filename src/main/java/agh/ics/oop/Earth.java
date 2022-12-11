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
}
