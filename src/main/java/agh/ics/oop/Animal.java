package agh.ics.oop;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Animal implements IMapElement{
    private MapDirection orientation;
    private Vector2d position;
    private final AbstractWorldMap map;
    public ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private final MoveDirection[] moves;
    private final Random rand;
    private int currMove;
    private int energy;
    public Animal(AbstractWorldMap map, Vector2d initialPosition, Random rand, int genesLength, int energy)
    {
        this(map,initialPosition,MoveDirection.randomMoves(rand,genesLength),MapDirection.getRandom(rand),rand,energy);
    }
    public Animal(AbstractWorldMap map, Vector2d initialPosition, MoveDirection[] moves, MapDirection initialOrientation, Random rand, int energy)
    {
        this.position = initialPosition;
        this.map = map;
        this.moves = moves;
        this.orientation = initialOrientation;
        this.rand = rand;
        this.energy = energy;
        currMove = 0;
        map.placeAnimal(this);
    }

    public void rotate(MoveDirection direction)
    {
        for (int i = 0; i<direction.numberOfTurns; i++)
            orientation = orientation.next();
    }

    public void addEnergy(int e)
    {
        energy+=e;
    }

    public void takeEnergy(int e)
    {
        energy-=e;
        if (energy<0)
            energy=0;
    }

    private void moveInDir(MoveDirection direction)
    {
        rotate(direction);
        Vector2d newPosition = position.add(orientation.toUnitVector());
        Vector2d oldPosition = position;
        position = newPosition;
        positionChanged(oldPosition);
    }

    public void move()
    {
        moveInDir(moves[currMove]);
        currMove++;
        if (currMove >= moves.length)
            currMove = 0;
    }

    public void setPosition(Vector2d pos)
    {
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
    public void addObserver(IPositionChangeObserver observer)
    {
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer)
    {
        observers.remove(observer);
    }
    private void positionChanged(Vector2d oldPosition)
    {
        for (IPositionChangeObserver observer : observers)
            observer.positionChanged(this, oldPosition);
    }
}
