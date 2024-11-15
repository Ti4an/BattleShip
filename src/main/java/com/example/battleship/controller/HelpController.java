package com.example.battleship.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The HelpController class handles the logic for the help window in the Battleship application.
 *
 * Author: Sebastian Bucheli Miranda
 * Version: 1.0
 */
public class HelpController {

    @FXML
    private ImageView howToImageView;

    /**
     * Closes the help window when the close button is clicked.
     *
     * @param event The action event triggered by clicking the close button.
     */
    @FXML
    void onXbutton(ActionEvent event) {
        Stage stage = (Stage) howToImageView.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the help view by loading the instructional image for the game.
     * This method is automatically called during the view's initialization phase.
     */
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/battleship/img/how-to.png"));
        this.howToImageView.setImage(image);
    }
}
