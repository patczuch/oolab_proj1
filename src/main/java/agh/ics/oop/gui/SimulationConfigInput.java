package agh.ics.oop.gui;

import agh.ics.oop.SimulationConfig;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import agh.ics.oop.SimulationTypes.*;
public class SimulationConfigInput extends GridPane {
    private final NumberField mapHeightInput;
    private final NumberField mapWidthInput;
    private final ComboBox<MapType> mapTypeInput;
    private final NumberField plantNumberInput;
    private final NumberField plantEnergyInput;
    private final NumberField plantGrowingNumberInput;
    private final ComboBox<PlantGrowingType> plantGrowingType;
    private final NumberField startAnimalNumberInput;
    private final NumberField startAnimalEnergyInput;
    private final NumberField fedAnimalEnergyInput;
    private final NumberField breedingEnergyUsedAnimalInput;
    private final NumberField minAnimalMutationsNumberInput;
    private final NumberField maxAnimalMutationsNumberInput;
    private final ComboBox<AnimalMutationType> animalMutationTypeInput;
    private final NumberField animalGenesLengthInput;
    private final ComboBox<AnimalBehaviourType> animalBehaviourTypeInput;
    private final NumberField moveDelayInput;
    SimulationConfigInput(SimulationConfig config, InputValuesConstraints inputValuesConstraints)
    {
        this(inputValuesConstraints);
        setValues(config);
    }
    SimulationConfigInput(InputValuesConstraints inputValuesConstraints)
    {
        mapHeightInput = new NumberField(inputValuesConstraints.mapHeightInputConstraint);
        mapWidthInput = new NumberField(inputValuesConstraints.mapWidthInputConstraint);
        mapTypeInput = new ComboBox<>(
                FXCollections.observableArrayList(
                        MapType.EARTH,
                        MapType.HELLPORTAL
                ));
        plantNumberInput = new NumberField(inputValuesConstraints.plantNumberInputConstraint);
        plantEnergyInput = new NumberField(inputValuesConstraints.plantEnergyInputConstraint);
        plantGrowingNumberInput = new NumberField(inputValuesConstraints.plantGrowingNumberInputConstraint);
        plantGrowingType = new ComboBox<>(
                FXCollections.observableArrayList(
                        PlantGrowingType.TOXICDEAD,
                        PlantGrowingType.FORESTYEQUATORS
                ));
        startAnimalNumberInput = new NumberField(inputValuesConstraints.startAnimalNumberInputConstraint);
        startAnimalEnergyInput = new NumberField(inputValuesConstraints.startAnimalEnergyInputConstraint);
        fedAnimalEnergyInput = new NumberField(inputValuesConstraints.fedAnimalEnergyInputConstraint);
        breedingEnergyUsedAnimalInput = new NumberField(inputValuesConstraints.breedingEnergyUsedAnimalInputConstraint);
        minAnimalMutationsNumberInput = new NumberField(inputValuesConstraints.minAnimalMutationsNumberInputConstraint);
        maxAnimalMutationsNumberInput = new NumberField(inputValuesConstraints.maxAnimalMutationsNumberInputConstraint);
        animalMutationTypeInput = new ComboBox<>(
                FXCollections.observableArrayList(
                        AnimalMutationType.FULLYRANDOM,
                        AnimalMutationType.LESSRANDOM
                ));
        animalGenesLengthInput = new NumberField(inputValuesConstraints.animalGenesLengthConstraint);
        animalBehaviourTypeInput = new ComboBox<>(
                FXCollections.observableArrayList(
                        AnimalBehaviourType.FULLYDETERMINED,
                        AnimalBehaviourType.SLIGHTLYCRAZY
                ));
        moveDelayInput = new NumberField(inputValuesConstraints.moveDelayConstraint);
        this.add(new Label("Wysokość mapy:"),0,0);
        this.add(mapHeightInput,1,0);
        this.add(new Label("Szerokość mapy:"),0,1);
        this.add(mapWidthInput,1,1);
        this.add(new Label("Wariant mapy:"),0,2);
        this.add(mapTypeInput,1,2);
        this.add(new Label("Początkowa liczba roślin:"),0,3);
        this.add(plantNumberInput,1,3);
        this.add(new Label("Energia roślin:"),0,4);
        this.add(plantEnergyInput,1,4);
        this.add(new Label("Liczba roślin rosnących każdego dnia:"),0,5);
        this.add(plantGrowingNumberInput,1,5);
        this.add(new Label("Wariant wzrostu roślin:"),0,6);
        this.add(plantGrowingType,1,6);
        this.add(new Label("Początkowa liczba zwierząt:"),0,7);
        this.add(startAnimalNumberInput,1,7);
        this.add(new Label("Początkowa energia zwierząt:"),0,8);
        this.add(startAnimalEnergyInput,1,8);
        this.add(new Label("Energia najedzenia:"),0,9);
        this.add(fedAnimalEnergyInput,1,9);
        this.add(new Label("Energia zużywana na stworzenie potomka:"),0,10);
        this.add(breedingEnergyUsedAnimalInput,1,10);
        this.add(new Label("Minimalna liczba mutacji:"),0,11);
        this.add(minAnimalMutationsNumberInput,1,11);
        this.add(new Label("Maksymalna liczba mutacji:"),0,12);
        this.add(maxAnimalMutationsNumberInput,1,12);
        this.add(new Label("Wariant mutacji:"),0,13);
        this.add(animalMutationTypeInput,1,13);
        this.add(new Label("Długość genomu zwierząt:"),0,14);
        this.add(animalGenesLengthInput,1,14);
        this.add(new Label("Wariant zachowania zwierząt:"),0,15);
        this.add(animalBehaviourTypeInput,1,15);
        this.add(new Label("Opóźnienie pomiędzy ruchami:"),0,16);
        this.add(moveDelayInput,1,16);

        this.setPadding(new Insets(20,20,20,20));
        this.setHgap(10);
        this.setVgap(5);
    }

    public void setValues(SimulationConfig simulationConfig)
    {
        mapHeightInput.setNumber(simulationConfig.mapHeight);
        mapWidthInput.setNumber(simulationConfig.mapWidth);
        mapTypeInput.getSelectionModel().select(simulationConfig.mapType);
        plantNumberInput.setNumber(simulationConfig.plantNumber);
        plantEnergyInput.setNumber(simulationConfig.plantEnergy);
        plantGrowingNumberInput.setNumber(simulationConfig.plantGrowingNumber);
        plantGrowingType.getSelectionModel().select(simulationConfig.plantGrowingType);
        startAnimalNumberInput.setNumber(simulationConfig.startAnimalNumber);
        startAnimalEnergyInput.setNumber(simulationConfig.startAnimalEnergy);
        fedAnimalEnergyInput.setNumber(simulationConfig.fedAnimalEnergy);
        breedingEnergyUsedAnimalInput.setNumber(simulationConfig.breedingEnergyUsedAnimal);
        minAnimalMutationsNumberInput.setNumber(simulationConfig.minAnimalMutationsNumber);
        maxAnimalMutationsNumberInput.setNumber(simulationConfig.maxAnimalMutationsNumber);
        animalMutationTypeInput.getSelectionModel().select(simulationConfig.animalMutationType);
        animalGenesLengthInput.setNumber(simulationConfig.animalGenesLength);
        animalBehaviourTypeInput.getSelectionModel().select(simulationConfig.animalBehaviourType);
        moveDelayInput.setNumber(simulationConfig.moveDelay);
    }

    public SimulationConfig getSimulationConfig()
    {
        return new SimulationConfig("nowy",mapHeightInput.getValue(),mapWidthInput.getValue(),mapTypeInput.getValue(),plantNumberInput.getValue(),
                plantEnergyInput.getValue(),plantGrowingNumberInput.getValue(),plantGrowingType.getValue(),startAnimalNumberInput.getValue(),
                startAnimalEnergyInput.getValue(),fedAnimalEnergyInput.getValue(),breedingEnergyUsedAnimalInput.getValue(),
                minAnimalMutationsNumberInput.getValue(), maxAnimalMutationsNumberInput.getValue(),
                animalMutationTypeInput.getValue(), animalGenesLengthInput.getValue(), animalBehaviourTypeInput.getValue(), moveDelayInput.getValue());
    }
}
