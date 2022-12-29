package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
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
        SimulationConfig simulationConfig = new SimulationConfig(
                25,
                25,
                SimulationTypes.MapType.EARTH,
                50,
                10,
                2,
                SimulationTypes.PlantGrowingType.FORESTYEQUATORS,
                10,
                30,
                10,
                5,
                5,
                10,
                SimulationTypes.AnimalMutationType.FULLYRANDOM,
                20,
                SimulationTypes.AnimalBehaviourType.FULLYDETERMINED,
                250
        );
        SimulationConfigInput simulationConfigInput = new SimulationConfigInput(simulationConfig,inputValuesConstraints);
        NumberField seedInput = new NumberField();
        CheckBox randomSeed = new CheckBox();
        randomSeed.setSelected(true);
        Scene scene = new Scene(new VBox(simulationConfigInput,new HBox(new Label("Seed:"),seedInput,new Label("Losowy:"),randomSeed),startButton),800,800);
        startButton.setOnAction(e ->
        {
            int seed;
            if (randomSeed.isSelected())
                seed = new Random().nextInt();
            else
                seed = seedInput.getValue();
            System.out.println(seed);
            new SimulationStage(simulationConfigInput.getSimulationConfig(), imageDictionary, new Random(seed));
        });
        primaryStage.setTitle("Konfiguracja symulacji");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
