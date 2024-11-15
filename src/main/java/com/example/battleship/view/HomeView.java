package com.example.battleship.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * HomeView class creates and configures the main window of the Battleship application.
 * Author: Sebastian Bucheli Miranda
 * Version: 1.0
 */
public class HomeView extends Stage {

    /**
     * Constructs the HomeView by loading the FXML file, setting up the scene, and configuring the window properties.
     *
     * @throws IOException If the FXML file cannot be loaded.
     */
    public HomeView() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/example/battleship/home-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.initStyle(StageStyle.UNDECORATED);
        this.setTitle("Battle Ship");
        this.getIcons().add(new Image(this.getClass().getResourceAsStream("/com/example/battleship/img/battleship.png")));
        this.setScene(scene);
        this.show();
    }

    /**
     * Returns a unique instance of {@code HomeView} using the Singleton pattern.
     *
     * @return The single instance of {@code HomeView}.
     * @throws IOException If an error occurs during instance creation.
     */
    public static HomeView getInstance() throws IOException {
        return HomeView.HomeViewHolder.INSTANCE = new HomeView();
    }

    /**
     * Static nested class to hold the Singleton instance of {@code HomeView}.
     * Ensures thread-safe lazy initialization.
     */
    private static class HomeViewHolder {
        private static HomeView INSTANCE;
    }
}
