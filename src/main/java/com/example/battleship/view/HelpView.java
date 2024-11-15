package com.example.battleship.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 *  HelpView class creates and configures a window that displays game instructions for Battleship.
 * Author: Sebastian Bucheli Miranda
 * Version: 1.0
 */
public class HelpView extends Stage {

    /**
     * Constructs the HelpView by loading the FXML file, configuring the stage, and displaying the window.
     *
     * @param owner The parent stage that owns this window.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public HelpView(Stage owner) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/example/battleship/how-to.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.initStyle(StageStyle.UNDECORATED);

        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(owner);

        this.centerOnScreen();
        this.setX(this.getX() + 100);

        this.setTitle("Sudoku");
        this.getIcons().add(new Image(this.getClass().getResourceAsStream("/com/example/battleship/img/question.png")));
        this.setScene(scene);
        this.showAndWait();
    }

    /**
     * Returns a unique instance of {@code HelpView} using the Singleton pattern.
     * Ensures the help window is only displayed once at a time.
     *
     * @param owner The parent stage that owns this window.
     * @return The single instance of {@code HelpView}.
     * @throws IOException If an error occurs while creating the instance.
     */
    public static HelpView getInstance(Stage owner) throws IOException {
        if (HelpViewHolder.INSTANCE == null || !HelpViewHolder.INSTANCE.isShowing()) {
            HelpViewHolder.INSTANCE = new HelpView(owner);
        }
        return HelpViewHolder.INSTANCE;
    }

    /**
     * Static nested class that holds the Singleton instance of {@code HelpView}.
     * Ensures thread-safe lazy initialization of the instance.
     */
    private static class HelpViewHolder {
        private static HelpView INSTANCE;
    }
}
