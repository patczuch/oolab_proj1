package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.Iterator;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App extends Application {
    private ImageDictionary imageDictionary;
    @Override
    public void start(Stage primaryStage) {
        imageDictionary = new ImageDictionary();

        InputValuesConstraints inputValuesConstraints = new InputValuesConstraints(
                new NumberConstraint(0,100),
                new NumberConstraint(0,100),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(0,Integer.MAX_VALUE),
                new NumberConstraint(1,Integer.MAX_VALUE),
                new NumberConstraint(10,Integer.MAX_VALUE));
        SimulationConfig simulationConfig = new SimulationConfig(25,25, SimulationTypes.MapType.EARTH,30,
                10, 2, SimulationTypes.PlantGrowingType.FORESTYEQUATORS,10,10,
                10,5, 5,10,
                SimulationTypes.AnimalMutationType.FULLYRANDOM,20, SimulationTypes.AnimalBehaviourType.FULLYDETERMINED, 250);

        /*try {
            File f = new File("./configurations/1.json");
            f.getParentFile().mkdirs();
            FileWriter file = new FileWriter(f);
            file.write(simulationConfig.toJsonObject("configuration 1").toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("./configurations/1.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            new SimulationConfig(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/


        SimulationConfigInput simulationConfigInput = new SimulationConfigInput(simulationConfig,inputValuesConstraints);
        /*NumberField seedInput = new NumberField();
        CheckBox randomSeed = new CheckBox();
        randomSeed.setSelected(true);*/

        Button startButton = new Button("Start");
        VBox startButtonVBox = new VBox(startButton);
        startButtonVBox.setPadding(new Insets(0,20,0,20));
        Scene scene = new Scene(new VBox(simulationConfigInput,/*new HBox(new Label("Seed:"),seedInput,new Label("Losowy:"),randomSeed),*/startButtonVBox),800,800);
        startButton.setOnAction(e ->
        {
            if (simulationConfigInput.getSimulationConfig().minAnimalMutationsNumber > simulationConfigInput.getSimulationConfig().maxAnimalMutationsNumber) {
                new Alert(Alert.AlertType.WARNING, "Minimalna liczba mutacji musi być mniejsza lub równa maksymalnej!").showAndWait();
                return;
            }
            int seed;
            /*if (randomSeed.isSelected())
                seed = new Random().nextInt();
            else
                seed = seedInput.getValue();
            System.out.println(seed);*/
            seed = new Random().nextInt();
            new SimulationStage(simulationConfigInput.getSimulationConfig(), imageDictionary, new Random(seed));
        });
        primaryStage.setTitle("Konfiguracja symulacji");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
