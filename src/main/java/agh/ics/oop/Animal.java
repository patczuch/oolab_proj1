package agh.ics.oop;

import java.util.ArrayList;
import java.util.Objects;

public class Animal implements IMapElement{
    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;
    private final ArrayList<IPositionChangeObserver> observers;

    public Animal(IWorldMap map)
    {
        this(map,new Vector2d(0,0));
    }

    public Animal(IWorldMap map, Vector2d initialPosition)
    {
        orientation = MapDirection.NORTH;
        position = initialPosition;
        this.map = map;
        observers = new ArrayList<>();
        map.place(this);
    }

    public String toString()
    {
        return switch (orientation)
                {
                    case EAST -> ">";
                    case WEST -> "<";
                    case NORTH -> "^";
                    case SOUTH -> "v";
                };
    }

    public boolean move(MoveDirection direction)
    {
        switch (direction)
        {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (!map.canMoveTo(newPosition))
                    return false;
                Vector2d oldPosition = position;
                position = newPosition;
                positionChanged(oldPosition);
            }
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(orientation.toUnitVector());
                if (!map.canMoveTo(newPosition))
                    return false;
                Vector2d oldPosition = position;
                position = newPosition;
                positionChanged(oldPosition);
            }
        }
        return true;
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
                };
    }

    @Override
    public String getLabel()
    {
        return getPosition().toString();
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

    private void positionChanged (Vector2d oldPosition)
    {
        for (IPositionChangeObserver observer : observers)
            observer.positionChanged(oldPosition, position);
    }
}
