package agh.ics.oop;

public interface IPositionChangeObserver {
    void positionChanged(Animal a, Vector2d oldPosition);
}
