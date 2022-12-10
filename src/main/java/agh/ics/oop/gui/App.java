package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.Random;

public class App extends Application {
    private ImageDictionary imageDictionary;
    @Override
    public void start(Stage primaryStage) {
        imageDictionary = new ImageDictionary();

        Button startButton = new Button("Start");
        InputValuesConstraints inputValuesConstraints = new InputValuesConstraints(
                new NumberConstraint(10,100),
                new NumberConstraint(10,100),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE));
        SimulationConfig simulationConfig = new SimulationConfig(30,30, SimulationTypes.MapType.EARTH,30,
                10, 10, SimulationTypes.PlantGrowingType.TOXICDEAD,10,10,
                10,5, 5,10,
                SimulationTypes.AnimalMutationType.FULLYRANDOM,20, SimulationTypes.AnimalBehaviourType.FULLYDETERMINED);
        SimulationConfigInput simulationConfigInput = new SimulationConfigInput(simulationConfig,inputValuesConstraints);
        NumberField seedInput = new NumberField();
        CheckBox randomSeed = new CheckBox();
        randomSeed.setSelected(true);
        Scene scene = new Scene(new VBox(simulationConfigInput,new HBox(new Label("Seed:"),seedInput,new Label("Losowy:"),randomSeed),startButton),800,800);
        int seed;
        if (randomSeed.isSelected())
            seed = new Random().nextInt();
        else
            seed = seedInput.getValue();
        startButton.setOnAction(e -> new SimulationStage(simulationConfigInput.getSimulationConfig(), imageDictionary, seed));

        primaryStage.setTitle("Konfiguracja symulacji");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
