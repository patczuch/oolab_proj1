package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class App extends Application {

    private GridPane gridPane;
    private SimulationEngine engine;
    private AbstractWorldMap map;
    private ImageDictionary imageDictionary;
    private Thread simulationThread;
    private int cellSize = 30;
    @Override
    public void start(Stage primaryStage) {
        gridPane = new GridPane();
        imageDictionary = new ImageDictionary();

        gridPane.setGridLinesVisible(true);

        TextField movesInput = new TextField("f f f f f f f f f f f f f f f f f f f");
        Button startButton = new Button("Start");

        startButton.setOnAction(e -> start(movesInput.getText().split(" "), 100));

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
        if (simulationThread != null)
            simulationThread.interrupt();
        map = new Earth(10,10, 20);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        engine = new SimulationEngine(map, positions, this, moveDelay);
        engine.changeMoves(OptionsParser.parse(moves));
        simulationThread = new Thread(engine);
        simulationThread.start();
    }

    @Override
    public void stop(){
        simulationThread.interrupt();
    }

    public void update()
    {
        for (int y = 0; y <= map.getUpperRight().subtract(map.getLowerLeft()).y; y++) {
            for (int x = 0; x <= map.getUpperRight().subtract(map.getLowerLeft()).x; x++) {
                Vector2d objPos = new Vector2d(x + map.getLowerLeft().x, y + map.getLowerLeft().y);
                Rectangle r = new Rectangle(cellSize,cellSize);
                if (map.grassAt(objPos) != null)
                    r.setFill(Color.GREEN);
                else
                    r.setFill(Color.BEIGE);
                gridPane.add(r, x, map.getUpperRight().subtract(map.getLowerLeft()).y - y);
                if (map.animalsAt(objPos) != null && map.animalsAt(objPos).size() > 0) {
                    Animal a = map.animalsAt(objPos).get(map.animalsAt(objPos).size()-1);
                    ImageView imgV = new ImageView(imageDictionary.getImage(a.getTexturePath()));
                    imgV.setFitWidth(cellSize);
                    imgV.setFitHeight(cellSize);
                    gridPane.add(imgV, x, map.getUpperRight().subtract(map.getLowerLeft()).y - y);
                }
            }
        }
    }
}
