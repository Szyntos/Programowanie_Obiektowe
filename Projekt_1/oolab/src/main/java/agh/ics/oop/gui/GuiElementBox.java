package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.FileNotFoundException;
import java.util.Objects;

public class GuiElementBox {
    VBox vBox;
    IMapElement mapElement;
    public GuiElementBox(IMapElement mapElement) {
        this.mapElement = mapElement;
        Label label;
        try{
            ImageView imageView = new ImageView(this.mapElement.getVisualRepresentation());
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            if (Objects.equals(this.mapElement.toString(), "*")){
                label = new Label("Trawa");
            }else{
                label = new Label("Z" + this.mapElement.getPosition().toString());
            }
            vBox = new VBox(imageView, label);
            vBox.setAlignment(Pos.CENTER);

        }catch (FileNotFoundException exception){
            System.out.println("No visual representation of an element on position "
                    + this.mapElement.getPosition().toString());
            label = new Label("Error \n" + this.mapElement.getPosition().toString());
            vBox = new VBox(label);
            vBox.setAlignment(Pos.CENTER);
        }
    }

    public VBox getVBox(){
        return vBox;
    }

}
