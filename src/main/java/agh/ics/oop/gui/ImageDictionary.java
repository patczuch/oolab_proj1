package agh.ics.oop.gui;

import javafx.scene.image.Image;

import java.util.HashMap;

public class ImageDictionary {
    private final HashMap<String, Image> images;
    public ImageDictionary() {
        this.images = new HashMap<>();
    }

    public Image getImage(String path)
    {
        if (!images.containsKey(path))
            images.put(path,new Image(path));
        return images.get(path);
    }
}
