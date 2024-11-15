package com.example.battleship.models;

/**
 * Represents a ship in the Battleship game.
 * Manages the ship's position, orientation, and interactions with the game board.
 */
public class Ship {
    private int longitud;
    private boolean esHorizontal;
    private int posX, posY;

    /**
     * Creates a new Ship with the specified length.
     * The ship is horizontal by default and starts at position (0, 0).
     *
     * @param longitud The length of the ship.
     */
    public Ship(int longitud) {
        this.longitud = longitud;
        this.esHorizontal = true; // Default orientation is horizontal
        this.posX = 0;
        this.posY = 0;
    }

    /**
     * Rotates the ship, changing its orientation between horizontal and vertical.
     */
    public void rotar() {
        esHorizontal = !esHorizontal;
    }

    /**
     * Checks if the ship is oriented horizontally.
     *
     * @return {@code true} if the ship is horizontal, {@code false} otherwise.
     */
    public boolean isHorizontal() {
        return esHorizontal;
    }

    /**
     * Returns the length of the ship.
     *
     * @return The length of the ship.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Sets the position of the ship on the board.
     *
     * @param x The x-coordinate of the ship's position.
     * @param y The y-coordinate of the ship's position.
     */
    public void setPosicion(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    /**
     * Returns the x-coordinate of the ship's position.
     *
     * @return The x-coordinate.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Returns the y-coordinate of the ship's position.
     *
     * @return The y-coordinate.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Checks if the ship fits within the board limits at its current position.
     *
     * @param tableroTamano The size of the board.
     * @return {@code true} if the ship fits, {@code false} otherwise.
     */
    public boolean cabeEnTablero(int tableroTamano) {
        if (esHorizontal) {
            return posX + longitud <= tableroTamano;
        } else {
            return posY + longitud <= tableroTamano;
        }
    }

    /**
     * Validates if the ship can be placed at the specified position without overlapping other ships.
     *
     * @param x       The x-coordinate of the position to validate.
     * @param y       The y-coordinate of the position to validate.
     * @param tablero The game board represented as a boolean matrix.
     * @return {@code true} if the position is valid, {@code false} otherwise.
     */
    public boolean validarPosicion(int x, int y, boolean[][] tablero) {
        int finalPosX = x;
        int finalPosY = y;

        if (esHorizontal) {
            if (finalPosX + longitud > tablero.length) return false;
            for (int i = 0; i < longitud; i++) {
                if (tablero[finalPosX + i][finalPosY]) return false;
            }
        } else {
            if (finalPosY + longitud > tablero[0].length) return false;
            for (int i = 0; i < longitud; i++) {
                if (tablero[finalPosX][finalPosY + i]) return false;
            }
        }
        return true;
    }

    /**
     * Marks the positions occupied by the ship on the board.
     *
     * @param tablero The game board represented as a boolean matrix.
     */
    public void ocuparPosicionEnTablero(boolean[][] tablero) {
        if (esHorizontal) {
            for (int i = 0; i < longitud; i++) {
                tablero[posX + i][posY] = true;
            }
        } else {
            for (int i = 0; i < longitud; i++) {
                tablero[posX][posY + i] = true;
            }
        }
    }

    /**
     * Clears the positions currently occupied by the ship on the board.
     *
     * @param tablero The game board represented as a boolean matrix.
     */
    public void liberarPosicionEnTablero(boolean[][] tablero) {
        if (esHorizontal) {
            for (int i = 0; i < longitud; i++) {
                tablero[posX + i][posY] = false;
            }
        } else {
            for (int i = 0; i < longitud; i++) {
                tablero[posX][posY + i] = false;
            }
        }
    }
}
