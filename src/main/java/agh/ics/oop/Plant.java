package agh.ics.oop;

import java.util.ArrayList;

public class Plant implements IMapElement{

    private final Vector2d position;
    public final AbstractWorldMap map;
    public ArrayList<IDeathObserver> deathObservers = new ArrayList<>();

    public Plant(AbstractWorldMap map, Vector2d position)
    {
        this.position = position;
        this.map = map;
        map.placePlant(this);
    }
    public Vector2d getPosition() {
        return position;
    }
    @Override
    public String getTexturePath() {
        return "grass.png";
    }
    public void addDeathObserver(IDeathObserver observer)
    {
        deathObservers.add(observer);
    }
    public void removeDeathObserver(IDeathObserver observer)
    {
        deathObservers.remove(observer);
    }
    public void die()
    {
        for (IDeathObserver observer : deathObservers)
            observer.died(this);
    }
}
