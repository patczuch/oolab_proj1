package agh.ics.oop;

import java.util.Random;

public class Earth extends AbstractWorldMap {

    public Earth(SimulationConfig config, Random rand) {
        super(config, rand);
    }

    protected void outOfMap(Vector2d oldPos, Animal a) {
        if (a.getPosition().y > getUpperRight().y || a.getPosition().y < getLowerLeft().y) {
            a.setPosition(oldPos);
            a.rotate(MoveDirection.BACKWARD);
        } else if (a.getPosition().x < getLowerLeft().x)
            a.setPosition(new Vector2d(getUpperRight().x, a.getPosition().y));
        else if (a.getPosition().x > getUpperRight().x)
            a.setPosition(new Vector2d(getLowerLeft().x, a.getPosition().y));
    }
}
