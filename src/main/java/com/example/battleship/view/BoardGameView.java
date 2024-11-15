package com.example.battleship.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * The BoardGameView class creates and configures the main game window for the Battleship application.
 */
public class BoardGameView extends Stage {

    /**
     * Constructs the BoardGameView by loading the FXML file, setting up the scene, and configuring the stage properties.
     *
     * @throws IOException If the FXML file cannot be loaded.
     */
    public BoardGameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/example/battleship/player-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.initStyle(StageStyle.UNDECORATED);
        this.setTitle("Battle Ship");
        this.getIcons().add(new Image(this.getClass().getResourceAsStream("/com/example/battleship/img/battleship.png")));
        this.setScene(scene);
        this.show();
    }

    /**
     * Returns a unique instance of {@code BoardGameView} using the Singleton pattern.
     *
     * @return The single instance of {@code BoardGameView}.
     * @throws IOException If an error occurs while creating the instance.
     */
    public static BoardGameView getInstance() throws IOException {
        return BoardGameView.BoardGameViewHolder.INSTANCE = new BoardGameView();
    }

    /**
     * Static nested class to hold the Singleton instance of {@code BoardGameView}.
     * Ensures lazy initialization and thread-safe access to the instance.
     */
    private static class BoardGameViewHolder {
        private static BoardGameView INSTANCE;
    }
}
