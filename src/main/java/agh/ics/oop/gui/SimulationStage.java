package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Random;

public class SimulationStage extends Stage {
    private final GridPane gridPane;
    public final int cellSize;
    ImageDictionary imageDictionary;
    AbstractWorldMap map;
    Random random;
    SimulationStage(SimulationConfig config, ImageDictionary imageDictionary, int seed)
    {
        super();
        this.gridPane = new GridPane();
        this.cellSize = 30;
        this.imageDictionary = imageDictionary;

        MoveDirection[] moves = OptionsParser.parse("f f f f f f f f f f f f f f f f f f f".split(" "));
        map = new Earth(config.mapWidth,config.mapHeight,config.plantNumber);
        Vector2d[] initialPositions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        SimulationEngine engine = new SimulationEngine(moves,map,initialPositions,250,this);
        Thread simulationThread = new Thread(engine);
        setTitle("Symulacja");
        random = new Random(seed);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        //simulationStage.setScene(new Scene(gridPane, screenBounds.getWidth()*2/3, screenBounds.getHeight()*2/3));
        setScene(new Scene(gridPane, 600, 600));
        setAlwaysOnTop(true);
        setResizable(false);
        setOnCloseRequest(e -> {
            simulationThread.interrupt();
        });
        show();
        simulationThread.start();
    }

    public void update()
    {
        for (int y = 0; y <= map.getUpperRight().subtract(map.getLowerLeft()).y; y++) {
            for (int x = 0; x <= map.getUpperRight().subtract(map.getLowerLeft()).x; x++) {
                Vector2d objPos = new Vector2d(x + map.getLowerLeft().x, y + map.getLowerLeft().y);
                Rectangle r = new Rectangle(cellSize,cellSize);
                if (map.plantAt(objPos) != null)
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
