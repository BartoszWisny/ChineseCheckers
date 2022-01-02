package games.chinesecheckers.game.gamesettings;

/**
 * Klasa odpowiada za ustalenie ustawieñ danej rozgrywki.
 */

public final class GameSettings {
    private int numberOfPlayers;
    public int[] playersNumbers;

    /**
     * Konstruktor ustala liczbê graczy w danej rozgrywce na podstawie otrzymanego komunikatu tekstowego.
     */
    
    public GameSettings(String line) {
    	String[] parameters = line.split("o");
    	String numberOfPlayersString = parameters[0];
    	numberOfPlayers = Integer.parseInt(numberOfPlayersString);
    }
    
    /**
     * Metoda zwraca liczbê graczy bior¹cych udzia³ w danej rozgrywce.
     */

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    /**
     * Metoda zwraca odpowiednie numery graczy bior¹cych udzia³ w danej rozgrywce w zale¿noœci od liczby graczy.
     */
    
    public int[] getPlayersNumbers() {
    	playersNumbers = new int[numberOfPlayers];
    	if (numberOfPlayers == 2) {
    		playersNumbers[0] = 0;
    		playersNumbers[1] = 3;
    	} else if (numberOfPlayers == 3) {
    		playersNumbers[0] = 0;
    		playersNumbers[1] = 2;
    		playersNumbers[2] = 4;
    	} else if (numberOfPlayers == 4) {
    		playersNumbers[0] = 1;
    		playersNumbers[1] = 2;
    		playersNumbers[2] = 4;
    		playersNumbers[3] = 5;
    	} else if (numberOfPlayers == 6) {
    		playersNumbers[0] = 0;
    		playersNumbers[1] = 1;
    		playersNumbers[2] = 2;
    		playersNumbers[3] = 3;
    		playersNumbers[4] = 4;
    		playersNumbers[5] = 5;
    	}
    	
    	return playersNumbers;
    }
    
    /**
     * Metoda zwraca dane o ustawieniach dla danej gry w postaci tekstowej.
     */
    
    public String toString() {
    	return Integer.toString(numberOfPlayers) + "o";
    }
}
