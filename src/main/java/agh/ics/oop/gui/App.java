package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class App extends Application {

    private GridPane gridPane;
    private SimulationEngine engine;
    private AbstractWorldMap map;
    @Override
    public void start(Stage primaryStage) {
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        TextField movesInput = new TextField();
        Button startButton = new Button("Start");

        startButton.setOnAction(e -> start(movesInput.getText().split(" "), 250));

        VBox vbox = new VBox(movesInput, startButton);

        BorderPane border = new BorderPane();
        border.setTop(vbox);
        border.setCenter(gridPane);

        Scene scene = new Scene(border,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void start(String[] moves, int moveDelay)
    {
        map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        engine = new SimulationEngine(map, positions, this, moveDelay);
        engine.changeMoves(OptionsParser.parse(moves));
        new Thread(engine).start();
    }

    public void update()
    {
        gridPane.getChildren().clear();
        Label xyLabel = new Label ("y/x");
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        gridPane.add(xyLabel,0,0);

        for (int y = 0; y <= map.getUpperRight().subtract(map.getLowerLeft()).y; y++) {
            gridPane.getRowConstraints().add(new RowConstraints(40));
            Label label = new Label(Integer.toString(y+ map.getLowerLeft().y));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, 0, map.getUpperRight().subtract(map.getLowerLeft()).y - y+1);
        }
        for (int x = 1; x <= map.getUpperRight().subtract(map.getLowerLeft()).x+1; x++)
        {
            gridPane.getColumnConstraints().add(new ColumnConstraints(40));
            Label label = new Label(Integer.toString(x + map.getLowerLeft().x-1));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, x, 0);
        }
        gridPane.getColumnConstraints().add(new ColumnConstraints(40));

        for (int y = 0; y <= map.getUpperRight().subtract(map.getLowerLeft()).y; y++) {
            for (int x = 0; x <= map.getUpperRight().subtract(map.getLowerLeft()).x; x++) {
                Vector2d objPos = new Vector2d(x + map.getLowerLeft().x, y + map.getLowerLeft().y);
                if (map.objectAt(objPos) != null) {
                    GuiElementBox elBox = new GuiElementBox(map.objectAt(objPos));
                    gridPane.add(elBox, x + 1, map.getUpperRight().subtract(map.getLowerLeft()).y - y + 1);
                }
            }
        }
    }
}
