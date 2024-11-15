package com.example.battleship.controller;

import com.example.battleship.view.BoardGameView;
import com.example.battleship.view.HelpView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The HomeController class manages the interactions in the main menu of the Battleship application.
 */
public class HomeController {

    @FXML
    private ImageView logoImageView;

    /**
     * Closes the main menu window when the exit button is clicked.
     *
     * @param event The action event triggered by the exit button.
     */
    @FXML
    void onExitButtonClick(ActionEvent event) {
        Stage stage = (Stage) this.logoImageView.getScene().getWindow();
        stage.close();
    }

    /**
     * Opens a new window displaying the game instructions when the "How to Play" button is clicked.
     *
     * @param event The action event triggered by the "How to Play" button.
     * @throws IOException If the help view cannot be loaded.
     */
    @FXML
    void onHowToButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        HelpView.getInstance(stage);
    }

    /**
     * Closes the main menu window and opens the game view when the play button is clicked.
     *
     * @param event The action event triggered by the play button.
     * @throws IOException If the game view cannot be loaded.
     */
    @FXML
    void onPlayButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.logoImageView.getScene().getWindow();
        stage.close();
        BoardGameView.getInstance();
    }

    /**
     * Initializes the main menu view by loading the logo image for the home screen.
     */
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/battleship/img/battleship.png"));
        this.logoImageView.setImage(image);
    }
}
