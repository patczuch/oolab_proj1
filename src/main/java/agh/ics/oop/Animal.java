package agh.ics.oop;

import javafx.scene.effect.ColorAdjust;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Animal implements IMapElement{
    private MapDirection orientation;
    private Vector2d position;
    private final AbstractWorldMap map;
    public ArrayList<IPositionChangeObserver> positionObservers = new ArrayList<>();
    public ArrayList<IDeathObserver> deathObservers = new ArrayList<>();
    public final MoveDirection[] moves;
    private final Random rand;
    private int currMove;
    private int energy;
    private int age;
    private int consumed = 0;
    private int childrenAmount;
    private SimulationConfig config;
    private boolean alive = true;

    public Animal(AbstractWorldMap map, Vector2d initialPosition, MoveDirection[] moves, Random rand, SimulationConfig config, int startEnergy)
    {
        this(map,initialPosition,moves,MapDirection.getRandom(rand),rand,config);
        this.energy = startEnergy;
    }
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
        age = 0;
        childrenAmount = 0;
        map.placeAnimal(this);
    }

    public void rotate(MoveDirection direction)
    {
        orientation = orientation.rotate(direction);
    }

    public void addEnergy(int e) {
        energy += e;
        consumed++;
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
        age++;
    }

    public void move() {
        moveInDir(moves[currMove]);
        currMove = (currMove + 1) % moves.length;
        if (config.animalBehaviourType == SimulationTypes.AnimalBehaviourType.SLIGHTLYCRAZY && rand.nextFloat() >= 0.8)
            currMove = rand.nextInt(config.animalGenesLength);
    }

    public MoveDirection getCurrentMove() {
        return moves[currMove];
    }

    public int getPlantsEaten() {
        return consumed;
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
        alive = false;
        for (IDeathObserver observer : deathObservers)
            observer.died(this);
    }

    public int getAge()
    {
        return age;
    }

    public int getChildrenAmount()
    {
        return childrenAmount;
    }

    public void addChild()
    {
        childrenAmount++;
    }

    public ColorAdjust getColorAdjust() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(0.6 * Math.min((double)energy/config.fedAnimalEnergy,1));
        colorAdjust.setBrightness(0);
        colorAdjust.setSaturation(1);
        return colorAdjust;
    }

    public boolean isAlive() {
        return alive;
    }
}
