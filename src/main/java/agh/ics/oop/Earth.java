package agh.ics.oop;

import java.util.HashMap;
import java.util.Random;

public class Earth extends AbstractWorldMap{

    public Earth(int width, int height, int plantAmount)
    {
        this(width, height, plantAmount, new Random());
    }

    public Earth(int width, int height, int plantAmount, Random rand)
    {
        super(width,height,plantAmount,rand);

    }
}
