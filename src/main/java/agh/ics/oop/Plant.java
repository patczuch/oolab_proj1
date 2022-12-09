package agh.ics.oop;

public class Plant implements IMapElement{

    private final Vector2d position;

    public Plant(Vector2d position)
    {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString() {
        return "*";
    }

    @Override
    public String getTexturePath() {
        return "grass.png";
    }
    @Override
    public String getLabel()
    {
        return "Trawa";
    }
}
