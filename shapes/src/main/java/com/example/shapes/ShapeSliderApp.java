package com.example.shapes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShapeSliderApp extends Application {
    private Shape[] shapes = new Shape[3];
    private int currentShapeIndex = 0;
    private Pane shapePane;
    private Button previousButton;
    private Button nextButton;
    String css = getClass().getResource("style.css").toExternalForm().toString();

    @Override
    public void start(Stage primaryStage) {
        Text text = new Text("Shapes");
        text.setFont(Font.font("Arial", 30));
        text.setFill(Color.WHITE);

        // Apply drop shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setColor(Color.RED);
        text.setEffect(dropShadow);

        // Create shapes
        shapes[0] = new Rectangle(100, 100, Color.BLUE);
        shapes[1] = new Circle(50, Color.RED);
        shapes[2] = new Polygon(100, 0, 200, 100, 0, 100);
        shapes[2].setFill(Color.GREEN);

        // Create shape pane
        shapePane = new Pane(shapes[currentShapeIndex]);
        shapePane.setOnMouseDragged(e -> {
            Shape shape = shapes[currentShapeIndex];
            shape.setLayoutX(e.getX() - shape.getBoundsInLocal().getWidth() / 2);
            shape.setLayoutY(e.getY() - shape.getBoundsInLocal().getHeight() / 2);
        });
        shapePane.getStyleClass().add("shapes");
        // Create navigation buttons
        previousButton = new Button("Previous");
        previousButton.getStyleClass().add("btn");
        previousButton.setOnAction(e -> showPreviousShape());

        nextButton = new Button("Next");
        nextButton.getStyleClass().add("btn");
        nextButton.setOnAction(e -> showNextShape());

        // Create change background button
        Button changeBackgroundButton = new Button("Change Background");
        changeBackgroundButton.getStyleClass().add("btn");
        changeBackgroundButton.setOnAction(e -> changeShapeBackground());

        // Create layout
        BorderPane root = new BorderPane();
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(shapePane);
        root.setCenter(hBox1);
        hBox1.getStyleClass().add("softie");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(changeBackgroundButton);

        HBox hBox2 = new HBox();
        hBox2.getChildren().add(previousButton);
        hBox2.getStyleClass().add("pre");
        HBox hBox3 = new HBox(30);
        hBox3.getStyleClass().addAll("nxt");
        hBox3.getChildren().addAll(previousButton,changeBackgroundButton,nextButton);
        root.setBottom(hBox3);
        HBox hBox4 = new HBox();
        hBox4.getChildren().add(text);
        hBox4.getStyleClass().add("header");
        root.setTop(hBox4);
        Scene scene = new Scene(root, 890, 580);
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shape Slider App");
        primaryStage.show();
    }

    private void showPreviousShape() {
        currentShapeIndex = (currentShapeIndex - 1 + shapes.length) % shapes.length;
        shapePane.getChildren().set(0, shapes[currentShapeIndex]);
    }

    private void showNextShape() {
        currentShapeIndex = (currentShapeIndex + 1) % shapes.length;
        shapePane.getChildren().set(0, shapes[currentShapeIndex]);
    }

    private void changeShapeBackground() {
        Shape shape = shapes[currentShapeIndex];
        Color newColor = Color.rgb(
                (int) (Math.random() * 256),
                (int) (Math.random() * 256),
                (int) (Math.random() * 256)
        );
        shape.setFill(newColor);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
