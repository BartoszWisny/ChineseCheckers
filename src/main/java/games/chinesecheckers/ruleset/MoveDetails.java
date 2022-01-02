package games.chinesecheckers.ruleset;

import games.chinesecheckers.game.player.Player;

/**
 * Klasa pobiera informacje o danym ruchu.
 */

public class MoveDetails {
	private Player player;   
    private int oldDiagonal;
    private int oldRow;
    private int newDiagonal;
    private int newRow;

    /**
     * Konstruktor ustala gracza, kt�ry wykona� ruch oraz odczytuje informacje o wsp�rz�dnych p�l dla danego ruchu.
     */
    
    public MoveDetails(Player player, String moveLine) {
        this.player = player;
        String[] line = moveLine.split(" ");
        oldDiagonal = Integer.parseInt(line[0]);
        oldRow = Integer.parseInt(line[1]);
        newDiagonal = Integer.parseInt(line[2]);
        newRow = Integer.parseInt(line[3]);
    }
    
    /**
     * Metoda zwraca numer przek�tnej pola, na kt�rym powinien znajdowa� si� pionek przed ruchem.
     */

    public int getOldDiagonal() {
        return oldDiagonal;
    }
    
    /**
     * Metoda zwraca numer wiersza pola, na kt�rym powinien znajdowa� si� pionek przed ruchem.
     */
    
    public int getOldRow() {
        return oldRow;
    }
    
    /**
     * Metoda zwraca numer przek�tnej pola, na kt�rym powinien znajdowa� si� pionek po ruchu.
     */

    public int getNewDiagonal() {
        return newDiagonal;
    }
    
    /**
     * Metoda zwraca numer wiersza pola, na kt�rym powinien znajdowa� si� pionek po ruchu.
     */

    public int getNewRow() {
        return newRow;
    }
    
    /**
     * Metoda zwraca dane gracza, kt�ry wykona� ruch.
     */

    public Player getPlayer() {
        return player;
    }
    
    /**
     * Metoda zwraca informacje o ruchu (wsp�rz�dne pola pocz�tkowego, wsp�rz�dne pola ko�cowego) w formie tekstowej.
     */
    
    public String moveToString() {
    	return Integer.toString(oldDiagonal) + " " + Integer.toString(oldRow) + " " +
    	Integer.toString(newDiagonal) + " " + Integer.toString(newRow);
    }
}
