package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App extends Application {
    private ImageDictionary imageDictionary;
    private ObservableList<SimulationConfig> configs;
    private ComboBox<SimulationConfig> configsInput;
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
                new NumberConstraint(1,Integer.MAX_VALUE));

        /*
        try {
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


        /*NumberField seedInput = new NumberField();
        CheckBox randomSeed = new CheckBox();
        randomSeed.setSelected(true);*/

        this.configs = FXCollections.observableArrayList(new ArrayList<>());
        configsInput = new ComboBox<>(configs);

        updateConfigs();

        Button saveConfigButton = new Button("Zapisz jako");
        HBox saveConfigButtonHbox = new HBox(saveConfigButton);
        saveConfigButtonHbox.setPadding(new Insets(0,0,0,20));

        Button startButton = new Button("Start");
        VBox startButtonVBox = new VBox(startButton);
        startButtonVBox.setPadding(new Insets(0,20,0,20));

        SimulationConfig currConfig = configsInput.getValue();
        if (currConfig == null)
            currConfig = new SimulationConfig("domyślny",25,25, SimulationTypes.MapType.EARTH,30,
                    10, 2, SimulationTypes.PlantGrowingType.FORESTYEQUATORS,10,10,
                    10,5, 5,10,
                    SimulationTypes.AnimalMutationType.FULLYRANDOM,20, SimulationTypes.AnimalBehaviourType.FULLYDETERMINED, 250);

        SimulationConfigInput simulationConfigInput = new SimulationConfigInput(currConfig,inputValuesConstraints);
        configsInput.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
                simulationConfigInput.setValues(newValue);
        });

        saveConfigButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./configurations"));
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pliki JSON (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write(simulationConfigInput.getSimulationConfig().toJsonObject().toJSONString());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            updateConfigs();
        });

        HBox configsHBox = new HBox(configsInput,saveConfigButtonHbox);
        configsHBox.setPadding(new Insets(20,20,0,20));

        Scene scene = new Scene(new VBox(configsHBox,simulationConfigInput,/*new HBox(new Label("Seed:"),seedInput,new Label("Losowy:"),randomSeed),*/startButtonVBox),800,800);
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

    public static List<String> findFiles(Path path, String fileExtension) throws IOException {
        List<String> res;

        try (Stream<Path> walk = Files.walk(path)) {
            res = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }
        return res;
    }

    private void updateConfigs() {
        JSONParser parser = new JSONParser();
        configs.clear();
        try {
            List<String> files = findFiles(Paths.get("./configurations"), "json");
            files.forEach(f -> {
                try (Reader reader = new FileReader(f, StandardCharsets.UTF_8)) {
                    JSONObject jsonObject = (JSONObject) parser.parse(reader);
                    configs.add(new SimulationConfig(new File(f).getName().replace(".json",""), jsonObject));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        configsInput.getSelectionModel().select(0);
    }
}
