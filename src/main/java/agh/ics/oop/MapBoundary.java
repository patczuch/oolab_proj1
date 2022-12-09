package agh.ics.oop;

import java.util.TreeSet;
import javafx.util.Pair;

public class MapBoundary implements IPositionChangeObserver{
    private final TreeSet<Pair<Vector2d,Class<?>>> elementsSortedByX = new TreeSet<>((p1, p2) -> {
        Vector2d v1 = p1.getKey();
        Vector2d v2 = p2.getKey();

        if (v1.equals(v2))
            return p1.getValue().getName().compareTo(p2.getValue().getName());
        if (v1.x == v2.x)
            return v1.y - v2.y;
        return v1.x - v2.x;
    });
    private final TreeSet<Pair<Vector2d,Class<?>>> elementsSortedByY = new TreeSet<>((p1, p2) -> {
        Vector2d v1 = p1.getKey();
        Vector2d v2 = p2.getKey();

        if (v1.equals(v2))
            return p1.getValue().getName().compareTo(p2.getValue().getName());
        if (v1.y == v2.y)
            return v1.x - v2.x;
        return v1.y - v2.y;
    });

    public void place(Animal a)
    {
        place((IMapElement)a);
        a.addObserver(this);
    }

    public void place(IMapElement el)
    {
        elementsSortedByX.add(new Pair<>(el.getPosition(),el.getClass()));
        elementsSortedByY.add(new Pair<>(el.getPosition(),el.getClass()));
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        elementsSortedByX.remove(new Pair<>(oldPosition,Animal.class));
        elementsSortedByX.add(new Pair<>(newPosition,Animal.class));
        elementsSortedByY.remove(new Pair<>(oldPosition,Animal.class));
        elementsSortedByY.add(new Pair<>(newPosition,Animal.class));
    }

    @Override
    public String toString()
    {
        return elementsSortedByX + " " + elementsSortedByY;
    }

    public Vector2d getLowerLeft() {
        return  new Vector2d(
                elementsSortedByX.first().getKey().x,
                elementsSortedByY.first().getKey().y
        );
    }

    public Vector2d getUpperRight() {
        return  new Vector2d(
                elementsSortedByX.last().getKey().x,
                elementsSortedByY.last().getKey().y
        );
    }
}
