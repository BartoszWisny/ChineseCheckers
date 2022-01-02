package games.chinesecheckers.game.board;

/**
 * Klasa generuje pola planszy w zale¿noœci od ustalonego rozmiaru planszy.
 */

public class BoardInfo {
	public static int n = BoardSize.boardSize; // HEXAGONAL_SIDE - 1
	public static int HEXAGONAL_SIDE = n + 1;
	public static int NUMBER_OF_ROWS = 4 * n + 1;
	public static int NUMBER_OF_FIELDS = 6 * n * n + 6 * n + 1;
	public static int NUMBER_OF_HOME_FIELDS = (int) (0.5 * n * (n + 1));
	public static int[][][] HOME_FIELDS = new int[6][NUMBER_OF_HOME_FIELDS][2];
	
	/**
	 * Metoda generuje pola startowe graczy w zale¿noœci od ustalonego rozmiaru planszy.
	 */
	
	public static int[][][] homeFields() {
		int k = 0, l = 0, m = 0;
		
		for (int i = 0; i < n; i++) {
            for (int j = 3 * n - i; j < 3 * n + 1; j++) {
            	HOME_FIELDS[k][l][m] = j;
                m++;
                HOME_FIELDS[k][l][m] = i;
                m = 0;
                l++;
            }
        }
        
		k++;
		l = 0;
		m = 0;
		
		for (int i = 0; i < n; i++) {
            for (int j = 3 * n + 1; j < 4 * n + 1 - i; j++) {
                HOME_FIELDS[k][l][m] = j;
                m++;
                HOME_FIELDS[k][l][m] = n + i;
                m = 0;
                l++;     
            }
        }
		
		k++;
		l = 0;
		m = 0;
		
		for (int i = 0; i < n; i++) {
            for (int j = 3 * n - i; j < 3 * n + 1; j++) {
                HOME_FIELDS[k][l][m] = j;
                m++;
                HOME_FIELDS[k][l][m] = 2 * n + 1 + i;
                m = 0;
                l++;
            }
        }
		
		k++;
		l = 0;
		m = 0;
		
		for (int i = 0; i < n; i++) {
            for (int j = n; j < 2 * n - i; j++) {
                HOME_FIELDS[k][l][m] = j;
                m++;
                HOME_FIELDS[k][l][m] = 3 * n + 1 + i;
                m = 0;
                l++;
            }
        }
		
		k++;
		l = 0;
		m = 0;
		
		for (int i = 0; i < n; i++) {
            for (int j = n - 1 - i; j < n; j++) {
                HOME_FIELDS[k][l][m] = j;
                m++;
                HOME_FIELDS[k][l][m] = 2 * n + 1 + i;
                m = 0;
                l++;
            }
        }
		
		k++;
		l = 0;
		m = 0;
		
		for (int i = 0; i < n; i++) {
            for (int j = n; j < 2 * n - i; j++) {
                HOME_FIELDS[k][l][m] = j;
                m++;
                HOME_FIELDS[k][l][m] = n + i;
                m = 0;
                l++;
            }
        }
		
		return HOME_FIELDS;		
	}
}
