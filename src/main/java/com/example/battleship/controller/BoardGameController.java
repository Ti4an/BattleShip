package com.example.battleship.controller;

import com.example.battleship.models.Ship;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * The BoardGameController class manages the Battleship game logic.
 * @author Sebastian Bucheli Miranda
 * @version 1.0
 */
public class BoardGameController {

    @FXML
    private ImageView infoImageView;

    @FXML
    private AnchorPane onAnchorPane;

    private List<Rectangle> barcosVisuales = new ArrayList<>();
    private List<Ship> barcosLogicos = new ArrayList<>();
    private boolean[][] tablero = new boolean[10][10];

    /**
     * Initializes the game board by setting the title image and placing ships randomly.
     */
    @FXML
    public void initialize() {
        Image title = new Image(getClass().getResourceAsStream("/com/example/battleship/img/title.png"));
        infoImageView.setImage(title);
        initShips();
    }

    /**
     * Initializes the ships on the board and sets up their initial positions.
     * Adds listeners for dragging and rotating the ships.
     */
    private void initShips() {
        barcosVisuales.clear();
        barcosLogicos.clear();

        placeShip(4, Color.BLUE);
        for (int i = 0; i < 2; i++) {
            placeShip(3, Color.GREEN);
        }
        for (int i = 0; i < 3; i++) {
            placeShip(2, Color.ORANGE);
        }
        for (int i = 0; i < 4; i++) {
            placeShip(1, Color.RED); 
        }

        for (int i = 0; i < barcosVisuales.size(); i++) {
            addDragListeners(barcosVisuales.get(i), barcosLogicos.get(i));
            addRotateListener(barcosVisuales.get(i), barcosLogicos.get(i));
        }
    }

    /**
     * Places a single ship randomly on the board in a valid position.
     *
     * @param length The length of the ship.
     * @param color  The color representing the ship.
     */
    private void placeShip(int length, Color color) {
        Ship ship = new Ship(length);
        Rectangle shipRect = createShipRectangle(ship, color);
        int x, y;
        boolean positionValid;

        do {
            x = (int) (Math.random() * (10 - length));
            y = (int) (Math.random() * 10);
            ship.setPosicion(x, y);

            if (Math.random() > 0.5) {
                ship.rotar();
            }
            positionValid = isValidPosition(ship, x, y);
        } while (!positionValid);

        ship.ocuparPosicionEnTablero(tablero);
        shipRect.setLayoutX(x * 30);
        shipRect.setLayoutY(y * 30);
        onAnchorPane.getChildren().add(shipRect);

        barcosVisuales.add(shipRect);
        barcosLogicos.add(ship);
    }

    /**
     * Adds a click listener to a ship to enable rotation.
     *
     * @param shipRect The visual representation of the ship.
     * @param ship     The logical representation of the ship.
     */
    private void addRotateListener(Rectangle shipRect, Ship ship) {
        shipRect.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                rotateShip(shipRect, ship);
            }
        });
    }

    /**
     * Creates a rectangle to visually represent a ship.
     *
     * @param ship  The ship object.
     * @param color The color of the ship.
     * @return A rectangle representing the ship.
     */
    private Rectangle createShipRectangle(Ship ship, Color color) {
        Rectangle shipRect = new Rectangle(ship.getLongitud() * 30, 30);
        shipRect.setFill(color);
        shipRect.setUserData(ship);
        return shipRect;
    }

    /**
     * Adds drag functionality to a ship, allowing it to be repositioned on the board.
     *
     * @param shipRect The visual representation of the ship.
     * @param ship     The logical representation of the ship.
     */
    private void addDragListeners(Rectangle shipRect, Ship ship) {
        shipRect.setOnMousePressed(event -> {
            double initialX = event.getSceneX() - shipRect.getLayoutX();
            double initialY = event.getSceneY() - shipRect.getLayoutY();
            shipRect.setUserData(new double[]{initialX, initialY});
            ship.liberarPosicionEnTablero(tablero);
        });

        shipRect.setOnMouseDragged(event -> {
            double[] initialPos = (double[]) shipRect.getUserData();
            double offsetX = event.getSceneX() - initialPos[0];
            double offsetY = event.getSceneY() - initialPos[1];

            if (offsetX >= 0 && offsetX + shipRect.getWidth() <= 300 &&
                    offsetY >= 0 && offsetY + shipRect.getHeight() <= 300) {
                shipRect.setLayoutX(offsetX);
                shipRect.setLayoutY(offsetY);
            }
        });

        shipRect.setOnMouseReleased(event -> {
            double newX = Math.round(shipRect.getLayoutX() / 30) * 30;
            double newY = Math.round(shipRect.getLayoutY() / 30) * 30;
            int posX = (int) newX / 30;
            int posY = (int) newY / 30;

            if (isValidPosition(ship, posX, posY)) {
                ship.setPosicion(posX, posY);
                shipRect.setLayoutX(newX);
                shipRect.setLayoutY(newY);
            } else {
                double[] initialPos = (double[]) shipRect.getUserData();
                shipRect.setLayoutX(initialPos[0]);
                shipRect.setLayoutY(initialPos[1]);
                ship.ocuparPosicionEnTablero(tablero);
            }
        });
    }

    /**
     * Rotates a ship and updates its position on the board.
     *
     * @param shipRect The visual representation of the ship.
     * @param ship     The logical representation of the ship.
     */
    private void rotateShip(Rectangle shipRect, Ship ship) {
        ship.liberarPosicionEnTablero(tablero);
        ship.rotar();
        shipRect.setRotate(ship.isHorizontal() ? 0 : 90);
        ship.ocuparPosicionEnTablero(tablero);
    }

    /**
     * Validates if a ship can be placed at a specific position.
     *
     * @param ship The ship to validate.
     * @param x    The x-coordinate on the board.
     * @param y    The y-coordinate on the board.
     * @return {@code true} if the position is valid, {@code false} otherwise.
     */
    private boolean isValidPosition(Ship ship, int x, int y) {
        int length = ship.getLongitud();

        if (ship.isHorizontal()) {
            if (x + length > 10) return false;
            for (int i = 0; i < length; i++) {
                if (tablero[x + i][y]) return false;
            }
        } else {
            if (y + length > 10) return false;
            for (int i = 0; i < length; i++) {
                if (tablero[x][y + i]) return false;
            }
        }
        return true;
    }

    /**
     * Starts the game by locking ship positions and printing the board layout.
     *
     * @param event The action event triggered by the play button.
     */
    @FXML
    void onPlayButton(ActionEvent event) {
        clearBoard();
        for (Ship ship : barcosLogicos) {
            ship.ocuparPosicionEnTablero(tablero);
        }
        printBoard();
    }

    /**
     * Repositions all ships randomly on the board.
     *
     * @param event The action event triggered by the reorder button.
     */
    @FXML
    void onReorderButton(ActionEvent event) {
        clearBoard();
        onAnchorPane.getChildren().clear();
        barcosVisuales.clear();
        barcosLogicos.clear();
        initShips();
    }

    /**
     * Closes the game window.
     *
     * @param event The action event triggered by the close button.
     */
    @FXML
    void onXButton(ActionEvent event) {
        Stage stage = (Stage) infoImageView.getScene().getWindow();
        stage.close();
    }

    /**
     * Clears the board, resetting all occupied positions.
     */
    private void clearBoard() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = false;
            }
        }
    }

    /**
     * Prints the board layout to the console.
     */
    private void printBoard() {
        System.out.println("Board layout:");
        for (int y = 0; y < tablero.length; y++) {
            for (int x = 0; x < tablero[y].length; x++) {
                System.out.print(tablero[y][x] ? "B " : ". ");
            }
            System.out.println();
        }
    }
}
