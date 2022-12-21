package agh.ics.oop;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Animal implements IMapElement{
    private MapDirection orientation;
    private Vector2d position;
    private final AbstractWorldMap map;
    public ArrayList<IPositionChangeObserver> positionObservers = new ArrayList<>();
    public ArrayList<IDeathObserver> deathObservers = new ArrayList<>();
    private final MoveDirection[] moves;
    private final Random rand;
    private int currMove;
    private int energy;
    private SimulationConfig config;
    public Animal(AbstractWorldMap map, Vector2d initialPosition, Random rand, SimulationConfig config)
    {
        this(map,initialPosition,MoveDirection.randomMoves(rand,config.animalGenesLength),MapDirection.getRandom(rand),rand,config);
    }
    public Animal(AbstractWorldMap map, Vector2d initialPosition, MoveDirection[] moves, MapDirection initialOrientation, Random rand, SimulationConfig config)
    {
        this.position = initialPosition;
        this.map = map;
        this.moves = moves;
        this.orientation = initialOrientation;
        this.rand = rand;
        this.config = config;
        this.energy = config.startAnimalEnergy;
        currMove = 0;
        map.placeAnimal(this);
    }

    public void rotate(MoveDirection direction)
    {
        orientation = orientation.rotate(direction);
    }

    public void addEnergy(int e) {
        energy+=e;
    }

    public void takeEnergy(int e) {
        energy -= e;
    }

    public int getEnergy()
    {
        return energy;
    }

    private void moveInDir(MoveDirection direction) {
        rotate(direction);
        Vector2d newPosition = position.add(orientation.toUnitVector());
        Vector2d oldPosition = position;
        position = newPosition;
        positionChanged(oldPosition);
        takeEnergy(1);
    }

    public void move() {
        moveInDir(moves[currMove]);
        currMove++;
        if (currMove >= moves.length)
            currMove = 0;
    }

    public void setPosition(Vector2d pos) {
        this.position = pos;
    }

    public int hashCode() {
        return Objects.hash(orientation, position);
    }
    public boolean isFacing(MapDirection orientation)
    {
        return this.orientation.equals(orientation);
    }
    public boolean isAt(Vector2d position)
    {
        return this.position.equals(position);
    }

    @Override
    public String getTexturePath() {
        return switch (orientation)
                {
                    case EAST -> "right.png";
                    case WEST -> "left.png";
                    case NORTH -> "up.png";
                    case SOUTH -> "down.png";
                    case NORTHEAST -> "up_right.png";
                    case NORTHWEST -> "up_left.png";
                    case SOUTHEAST -> "down_right.png";
                    case SOUTHWEST -> "down_left.png";
                };
    }
    public Vector2d getPosition()
    {
        return position;
    }
    public void addPosObserver(IPositionChangeObserver observer)
    {
        positionObservers.add(observer);
    }
    public void removePosObserver(IPositionChangeObserver observer)
    {
        positionObservers.remove(observer);
    }
    public void addDeathObserver(IDeathObserver observer)
    {
        deathObservers.add(observer);
    }
    public void removeDeathObserver(IDeathObserver observer)
    {
        deathObservers.remove(observer);
    }
    private void positionChanged(Vector2d oldPosition)
    {
        for (IPositionChangeObserver observer : positionObservers)
            observer.positionChanged(this, oldPosition);
    }
    public void die()
    {
        for (IDeathObserver observer : deathObservers)
            observer.died(this);
    }
}
