package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IPositionChangeObserver, IDeathObserver{
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final HashMap<Vector2d, ArrayList<Animal>> animals;
    private final HashMap<Vector2d, Plant> plants;
    protected final SimulationConfig config;
    private final int[][] plantPreferred;
    private final LinkedList<ArrayList<Vector2d>> plantPreferredFields;
    Random rand;
    protected AbstractWorldMap(SimulationConfig config, Random rand) {
        animals = new HashMap<>();
        plants = new HashMap<>();
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(config.mapWidth-1,config.mapHeight-1);
        this.rand = rand;
        this.config = config;

        plantPreferred = new int[upperRight.y-lowerLeft.y+1][upperRight.x-lowerLeft.x+1];
        plantPreferredFields = new LinkedList<>();
        plantPreferredFields.add(new ArrayList<>());
        if (config.plantGrowingType == SimulationTypes.PlantGrowingType.FORESTYEQUATORS) {
            plantPreferredFields.add(new ArrayList<>());
            int equatorHeight = plantPreferred.length / 5;
            for (int i = 0; i < plantPreferred.length; i++)
                for (int j = 0; j < plantPreferred[i].length; j++) {
                    if (i < (plantPreferred.length - equatorHeight) / 2 || i > (plantPreferred.length + equatorHeight) / 2)
                        plantPreferred[i][j] = 1;
                    plantPreferredFields.get(plantPreferred[i][j]).add(new Vector2d(j,i));
                }
        }
        else if (config.plantGrowingType == SimulationTypes.PlantGrowingType.TOXICDEAD) {
            for (int i = 0; i < plantPreferred.length; i++)
                for (int j = 0; j < plantPreferred[i].length; j++)
                    plantPreferredFields.get(0).add(new Vector2d(j,i));
        }

        /*for(int i = 0; i<plantPreferred.length; i++) {
            for (int j = 0; j < plantPreferred[i].length; j++)
                System.out.print(plantPreferred[i][j]);
            System.out.println();
        }*/
    }

    public boolean isInMap(Vector2d position) {
        return position.follows(getLowerLeft()) && position.precedes(getUpperRight());
    }

    public void placeAnimal(Animal a) {
        if (!animals.containsKey(a.getPosition()))
            animals.put(a.getPosition(), new ArrayList<>());
        animals.get(a.getPosition()).add(a);
        a.addPosObserver(this);
        a.addDeathObserver(this);
    }

    public ArrayList<Vector2d> getPreferredFields()
    {
        for (ArrayList<Vector2d> a : plantPreferredFields)
            if (a.size() > 0)
                return a;
        return new ArrayList<>();
    }

    public ArrayList<Vector2d> getNotPreferredFields()
    {
        ArrayList<Vector2d> notPreferredFields = new ArrayList<>();
        for (int y = 0; y <= getUpperRight().subtract(getLowerLeft()).y; y++)
            for (int x = 0; x <= getUpperRight().subtract(getLowerLeft()).x; x++)
                notPreferredFields.add(new Vector2d(x,y));
        notPreferredFields.removeAll(getPreferredFields());
        return notPreferredFields;
    }

    public Plant spawnRandomPlant()
    {
        boolean inPreferredField = rand.nextFloat() <= 0.8;
        //boolean inPreferredField = true;

        int firstPossibleIndex = -1;
        int secondPossibleIndex = -1;
        for (int i = 0; i<plantPreferredFields.size();i++)
            if (plantPreferredFields.get(i).size() > 0)
                if (firstPossibleIndex == -1)
                    firstPossibleIndex = i;
                else
                {
                    secondPossibleIndex = i;
                    break;
                }

        if (firstPossibleIndex == -1)
            return null;

        if (secondPossibleIndex == -1)
            inPreferredField = true;

        int index = firstPossibleIndex;
        if (!inPreferredField) {
            index = firstPossibleIndex + 1 + rand.nextInt(plantPreferredFields.size() - firstPossibleIndex - 1);
            while (plantPreferredFields.get(index).size() == 0)
                index = firstPossibleIndex + 1 + rand.nextInt(plantPreferredFields.size() - firstPossibleIndex - 1);
        }

        Vector2d pos = plantPreferredFields.get(index).get(rand.nextInt(plantPreferredFields.get(index).size()));
        plantPreferredFields.get(index).remove(pos);

        return new Plant(this,pos);
    }

    public Animal spawnRandomAnimal() {
        return new Animal(this, randomPosition(),rand,config);
    }

    public Animal spawnBredAnimal(Animal _a1, Animal _a2) {
        Animal a1;
        Animal a2;

        if (_a1.getEnergy() > _a2.getEnergy())
        {
            a1 = _a1;
            a2 = _a2;
        }
        else
        {
            a1 = _a2;
            a2 = _a1;
        }

        a1.takeEnergy(config.breedingEnergyUsedAnimal);
        a1.addChild();
        a2.takeEnergy(config.breedingEnergyUsedAnimal);
        a2.addChild();

        boolean side = rand.nextBoolean();
        MoveDirection[] moves = new MoveDirection[config.animalGenesLength];
        if (side)
            for(int i = 0; i<config.animalGenesLength; i++)
                if (i < ((double) a1.getEnergy()/(a1.getEnergy()+a2.getEnergy()))*config.animalGenesLength)
                    moves[i] = a1.moves[i];
                else
                    moves[i] = a2.moves[i];
        else
            for(int i = config.animalGenesLength-1; i>=0; i--)
                if (i >= config.animalGenesLength - ((double)a1.getEnergy()/(a1.getEnergy()+a2.getEnergy())) * config.animalGenesLength)
                    moves[i] = a1.moves[i];
                else
                    moves[i] = a2.moves[i];

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < config.animalGenesLength; i++)
            indices.add(i);
        Collections.shuffle(indices);
        indices = indices.subList(0,config.minAnimalMutationsNumber + rand.nextInt(1 + config.maxAnimalMutationsNumber - config.minAnimalMutationsNumber));

        if(config.animalMutationType == SimulationTypes.AnimalMutationType.FULLYRANDOM)
            for(int i : indices)
                moves[i] = MoveDirection.random(rand);

        if(config.animalMutationType == SimulationTypes.AnimalMutationType.LESSRANDOM)
            for(int i : indices)
                moves[i] = rand.nextBoolean() ? moves[i].previous() : moves[i].next();

        return new Animal(this,a1.getPosition(),moves,true,rand,config,config.breedingEnergyUsedAnimal*2);
    }

    public void placePlant(Plant p) {
        plants.put(p.getPosition(),p);
        p.addDeathObserver(this);
    }

    public void positionChanged(Animal a, Vector2d oldPosition)
    {
        animals.get(oldPosition).remove(a);
        if(!isInMap(a.getPosition()))
            outOfMap(oldPosition,a);
        if (!animals.containsKey(a.getPosition()))
            animals.put(a.getPosition(), new ArrayList<>());
        animals.get(a.getPosition()).add(a);
    }

    public Vector2d randomPosition()
    {
        return new Vector2d(rand.nextInt(getUpperRight().x + 1), rand.nextInt(getUpperRight().y + 1));
    }

    public Plant plantAt(Vector2d position) {
        return plants.get(position);
    }
    public boolean isPlantAt(Vector2d position){
        return plantAt(position) != null;
    }
    public ArrayList<Animal> animalsAt(Vector2d position) {
        return animals.get(position);
    }
    public boolean isAnimalAt(Vector2d position) {
        return animalsAt(position) != null && animalsAt(position).size() > 0;
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    protected abstract void outOfMap(Vector2d oldPos, Animal a);
    public void died(Animal a)
    {
        if (config.plantGrowingType == SimulationTypes.PlantGrowingType.TOXICDEAD) {
            plantPreferredFields.get(plantPreferred[a.getPosition().y][a.getPosition().x]).remove(a.getPosition());
            plantPreferred[a.getPosition().y][a.getPosition().x]++;
            while (plantPreferredFields.size() <= plantPreferred[a.getPosition().y][a.getPosition().x])
                plantPreferredFields.add(new ArrayList<>());
            plantPreferredFields.get(plantPreferred[a.getPosition().y][a.getPosition().x]).add(a.getPosition());
        }
        animals.get(a.getPosition()).remove(a);
    }
    public void died(Plant p)
    {
        plants.put(p.getPosition(),null);
        plantPreferredFields.get(plantPreferred[p.getPosition().y][p.getPosition().x]).add(p.getPosition());
    }
}
