package com.example.javafx;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Stopwatch extends Application {

    private Timeline timeline;
    private Duration duration;
    private Label timeLabel;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Stopwatch");

        // Create UI elements
        timeLabel = new Label("00:00:00.000");
        timeLabel.setStyle("-fx-font-size: 24pt;");
        startButton = new Button("Start");
        stopButton = new Button("Stop");
        resetButton = new Button("Reset");

        // Set button sizes
        startButton.setPrefWidth(80);
        stopButton.setPrefWidth(80);
        resetButton.setPrefWidth(80);

        // Set button event handlers
        startButton.setOnAction(e -> startTimer());
        stopButton.setOnAction(e -> stopTimer());
        resetButton.setOnAction(e -> resetTimer());

        // Create button layout and add buttons
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(startButton, stopButton, resetButton);

        // Create root layout and add elements
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().addAll(timeLabel, buttonBox);

        // Set the scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to start the stopwatch timer
    private void startTimer() {
        if (timeline == null) {
            duration = Duration.ZERO;
            timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
                duration = duration.add(Duration.millis(10));
                updateTimer();
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
        }
        timeline.play();
    }

    // Method to stop the stopwatch timer
    private void stopTimer() {
        if (timeline != null) {
            timeline.pause();
        }
    }

    // Method to reset the stopwatch timer
    private void resetTimer() {
        stopTimer();
        duration = Duration.ZERO;
        updateTimer();
    }

    // Method to update the timer display
    private void updateTimer() {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) duration.toSeconds() % 60;
        int millis = (int) duration.toMillis() % 1000;
        String time = String.format("%02d:%02d:%03d", minutes, seconds, millis);
        timeLabel.setText(time);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

