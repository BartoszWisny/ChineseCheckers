package games.chinesecheckers.game.gamebuilder;

import games.chinesecheckers.game.Game;

/**
 * Klasa definiuje metodê odpowiadaj¹c¹ za zbudowanie danej gry.
 */

public interface GameBuilder {
    
	/**
     * Metoda buduje grê - buduje planszê gry, buduje pionki dla graczy i buduje graczy dla danej gry.
     */
	
	Game buildGame(Game game);
}
