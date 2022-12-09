package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class AbstractWorldMap implements IPositionChangeObserver{
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final HashMap<Vector2d, ArrayList<Animal>> animals;
    private final HashMap<Vector2d, Plant> plants;
    Random rand;
    protected AbstractWorldMap(int width, int height, int grassAmount, Random rand) {
        animals = new HashMap<>();
        plants = new HashMap<>();
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width-1,height-1);
        this.rand = rand;

        for (int i = 0; i<grassAmount; i++)
            createRandomGrass();
    }

    public boolean canMoveTo(Vector2d position) {
        return position.follows(getLowerLeft()) && position.precedes(getUpperRight());
    }

    public void place(Animal a) {
        if (!animals.containsKey(a.getPosition()))
            animals.put(a.getPosition(), new ArrayList<>());
        animals.get(a.getPosition()).add(a);
        a.addObserver(this);
    }

    public void positionChanged(Animal a, Vector2d oldPosition)
    {
        animals.get(oldPosition).remove(a);
        a.removeObserver(this);
        place(a);
    }

    private void createRandomGrass()
    {
        Vector2d newPosition = randomPosition();
        while (isPlantAt(newPosition))
            newPosition = randomPosition();
        Plant newPlant = new Plant(newPosition);
        plants.put(newPosition,newPlant);
    }

    private Vector2d randomPosition()
    {
        return new Vector2d(rand.nextInt(getUpperRight().x),rand.nextInt(getUpperRight().y));
    }

    public Plant plantAt(Vector2d position) {
        return plants.get(position);
    }

    public boolean isPlantAt(Vector2d position){
        return plantAt(position) != null;
    }
    public ArrayList<Animal> animalsAt(Vector2d position) {
        return animals.get(position);
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }
    public Vector2d getUpperRight() {
        return upperRight;
    }
}
