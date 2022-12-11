package agh.ics.oop;

public class Plant implements IMapElement{

    private final Vector2d position;
    public final AbstractWorldMap map;

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
}
