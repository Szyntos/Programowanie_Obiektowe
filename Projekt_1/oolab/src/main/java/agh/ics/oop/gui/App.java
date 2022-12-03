package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    AbstractWorldMap map;
    @Override
    public void init(){
        MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
        map = new GrassField(20);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
//        init();
        int cellWidth = 25;
        int cellHeight = 25;
        double labelScale = 1.2;
        Label label;
        GridPane grid = new GridPane();
        Vector2d[] borders = map.getBorders();


        label = new Label("x/y");
        label.setScaleX(labelScale);
        label.setScaleY(labelScale);
        grid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);

        grid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        grid.getRowConstraints().add(new RowConstraints(cellHeight));
        for (int i = 0; i <= borders[1].y - borders[0].y; i++){
            grid.getRowConstraints().add(new RowConstraints(cellHeight));
            label = new Label(Integer.toString(borders[1].y - i + borders[0].y + 1));
            label.setScaleX(labelScale);
            label.setScaleY(labelScale);
            grid.add(label, 0, i+1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int j = 0; j <= borders[1].x - borders[0].x; j++){
            grid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            label = new Label(Integer.toString(j + borders[0].x));
            label.setScaleX(labelScale);
            label.setScaleY(labelScale);
            grid.add(label, j+1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = 0; i <= borders[1].y - borders[0].y; i++){
            for (int j = 0; j <= borders[1].x - borders[0].x+1; j++){
                if (map.objectAt(new Vector2d(j, borders[1].y - i + borders[0].y + 1)) != null){
                    label = new Label(map.objectAt(new Vector2d(j, borders[1].y - i + borders[0].y + 1)).toString());
                    label.setScaleX(labelScale);
                    label.setScaleY(labelScale);
                    grid.add(label, j, i + borders[0].y + 2);
                    GridPane.setHalignment(label, HPos.CENTER);
                }

            }
        }



//        grid.add(new Label("Zwierzak"), 1, 1);

        grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
