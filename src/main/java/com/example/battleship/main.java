package com.example.battleship;

import com.example.battleship.view.HomeView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author  Sebastian Bucheli
 * @version 1.0
 *
 * Main class for the Battleship application.
 * Initializes the JavaFX application and displays the home view.
 */
public class main extends Application {

    /**
     * Starts the JavaFX application and loads the home view.
     *
     * @param stage The primary stage for this application.
     * @throws IOException If the home view fails to load.
     */
    @Override
    public void start(Stage stage) throws IOException {
        HomeView.getInstance();
    }

    /**
     * The main entry point for the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch();
    }
}