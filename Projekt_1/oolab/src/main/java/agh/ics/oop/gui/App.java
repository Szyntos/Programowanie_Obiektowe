package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
    AbstractWorldMap map;
    GridPane grid;
    int moveDelay = 500;
    Stage primaryStage;
    Scene scene;
    int noMoves;
    int cellWidth = 40;
    int cellHeight = 40;
    double labelScale = 1;
    Vector2d[] borders;
    TextField textField;
    Button startButton;
    SimulationEngine engine;
    @Override
    public void init(){
//        MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
        map = new GrassField(20);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)};
        engine = new SimulationEngine(map, positions, this, moveDelay);


    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        grid = new GridPane();
        makeGrid();
        grid.setGridLinesVisible(true);

        startButton = new Button("Start");
        startButton.setOnAction(e -> clickedStart());
        textField = new TextField();
        HBox hbox = new HBox(textField, startButton);
        VBox container = new VBox(hbox, grid);
        this.scene = new Scene(container, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void clickedStart(){
        String directionsString = textField.getText();
        String[] directionsArray = directionsString.split(" ");
        MoveDirection[] directions = OptionsParser.parse(directionsArray);
        this.noMoves = directions.length;
        engine.setMoves(directions);
        Thread engineThread = new Thread(engine);
        engineThread.start();
    }
    public void makeGrid(){

        Label label;
        this.borders = map.getBorders();
//        System.out.println(borders[0].toString() + borders[1].toString());


//        System.out.println(borders);

        label = new Label("x/y");
        label.setScaleX(labelScale);
        label.setScaleY(labelScale);
        grid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();

        grid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        grid.getRowConstraints().add(new RowConstraints(cellHeight));
        for (int i = borders[0].y; i <= borders[1].y; i++){
            grid.getRowConstraints().add(new RowConstraints(cellHeight));
            label = new Label(Integer.toString(i));
            label.setScaleX(labelScale);
            label.setScaleY(labelScale);
            grid.add(label, 0, borders[1].y - i + 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int j = borders[0].x; j <= borders[1].x; j++){
            grid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            label = new Label(Integer.toString(j));
            label.setScaleX(labelScale);
            label.setScaleY(labelScale);
            grid.add(label, j-borders[0].x+1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = borders[0].y; i <= borders[1].y; i++){
            for (int j = borders[0].x; j <= borders[1].x; j++){
                if (map.objectAt(new Vector2d(j, i)) != null){
                    GuiElementBox elementBox = new GuiElementBox((IMapElement) map.objectAt(new Vector2d(j, i)));
                    grid.add(elementBox.getVBox(), j-borders[0].x + 1, borders[1].y - i+1);
                    GridPane.setHalignment(elementBox.getVBox(), HPos.CENTER);
                }

            }
        }


    }
    public void updateGrid(){
        grid.getChildren().clear();
        grid.setGridLinesVisible(false);
        makeGrid();
        grid.setGridLinesVisible(true);


    }
}
