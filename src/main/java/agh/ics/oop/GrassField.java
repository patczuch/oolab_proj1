package agh.ics.oop;

import java.util.HashMap;
import java.util.Random;

public class GrassField extends AbstractWorldMap{

    Random rand;
    int grassAmount;
    private final HashMap<Vector2d, Grass> grass;
    private final MapBoundary boundary;
    public GrassField(int grassAmount)
    {
        this(grassAmount,0);
    }

    public GrassField(int grassAmount, int seed)
    {
        this.grassAmount = grassAmount;
        this.rand = new Random(seed);
        boundary = new MapBoundary();

        grass = new HashMap<>();

        for (int i = 0; i<grassAmount; i++)
            createRandomGrass();
    }

    @Override
    public boolean place(Animal animal)
    {
        boolean res = super.place(animal);
        boundary.place(animal);
        return res;
    }

    private void createRandomGrass()
    {
        int maxRand = (int) Math.sqrt(grassAmount * 10);
        Vector2d newPosition = new Vector2d(rand.nextInt(maxRand),rand.nextInt(maxRand));
        while (isOccupied(newPosition))
            newPosition = new Vector2d(rand.nextInt(maxRand),rand.nextInt(maxRand));
        Grass newGrass = new Grass(newPosition);
        grass.put(newPosition,newGrass);
        boundary.place(newGrass);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return objectAt(position) == null || objectAt(position).getClass() == Grass.class;
    }

    @Override
    public Vector2d getLowerLeft() {
        return boundary.getLowerLeft();
    }

    @Override
    public IMapElement objectAt(Vector2d position) {
        if (animals.containsKey(position))
            return animals.get(position);
        return grass.get(position);
    }

    @Override
    public Vector2d getUpperRight() {
        return boundary.getUpperRight();
    }
}
