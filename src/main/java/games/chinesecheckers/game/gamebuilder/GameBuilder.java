package games.chinesecheckers.game.gamebuilder;

import games.chinesecheckers.game.Game;

/**
 * Klasa definiuje metod� odpowiadaj�c� za zbudowanie danej gry.
 */

public interface GameBuilder {
    
	/**
     * Metoda buduje gr� - buduje plansz� gry, buduje pionki dla graczy i buduje graczy dla danej gry.
     */
	
	Game buildGame(Game game);
}
