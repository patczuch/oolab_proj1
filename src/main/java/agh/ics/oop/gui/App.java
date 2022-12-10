package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
    private ImageDictionary imageDictionary;
    @Override
    public void start(Stage primaryStage) {
        imageDictionary = new ImageDictionary();

        TextField movesInput = new TextField();
        Button startButton = new Button("Start");
        SimulationConfigInput simulationConfigInput = new SimulationConfigInput();
        startButton.setOnAction(e -> new SimulationStage(new SimulationConfig(simulationConfigInput), imageDictionary));

        Scene scene = new Scene(new VBox(simulationConfigInput,startButton),800,800);

        primaryStage.setTitle("Konfiguracja symulacji");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
