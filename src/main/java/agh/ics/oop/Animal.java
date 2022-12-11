package agh.ics.oop;

import java.util.ArrayList;
import java.util.Objects;

public class Animal implements IMapElement{
    private MapDirection orientation;
    private Vector2d position;
    private final AbstractWorldMap map;
    ArrayList<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(AbstractWorldMap map)
    {
        this(map,new Vector2d(0,0));
    }

    public Animal(AbstractWorldMap map, Vector2d initialPosition)
    {
        orientation = MapDirection.NORTH;
        position = initialPosition;
        this.map = map;
        map.place(this);
    }

    public void move(MoveDirection direction)
    {
        switch (direction)
        {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (!map.canMoveTo(newPosition))
                    return;
                Vector2d oldPosition = position;
                position = newPosition;
                positionChanged(oldPosition);
            }
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(orientation.toUnitVector());
                if (!map.canMoveTo(newPosition))
                    return;
                Vector2d oldPosition = position;
                position = newPosition;
                positionChanged(oldPosition);
            }
        }
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
