package games.chinesecheckers.game;

import games.chinesecheckers.game.board.Board;
import games.chinesecheckers.game.board.field.Field;
import games.chinesecheckers.game.board.field.FieldColor;
import games.chinesecheckers.game.board.pawn.Pawn;
import games.chinesecheckers.game.gamebuilder.ConcreteGameBuilder;
import games.chinesecheckers.game.gamebuilder.GameBuilder;
import games.chinesecheckers.game.gamesettings.GameSettings;
import games.chinesecheckers.game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public final class Game {
    private GameSettings gameSettings;
    private Player[] players;
    private Pawn[] pawns;
    private List<Player> winners = new ArrayList<Player>();
    private Board board;
    private GameBuilder gameBuilder;

    public Game(GameSettings settings) {
        setSettings(settings);
        gameBuilder = new ConcreteGameBuilder();
    }

    public Player getPlayerByNumber(int number) {
        return players[number];
    }
   
    public GameSettings getSettings() {
    	return this.gameSettings;
    }

    public Board getBoard() {
        return board;
    }
    
    public Pawn[] getPawns() {
    	return pawns;
    }

    public Pawn[] getPlayerPawns(FieldColor playerColor) {
        Pawn[] playerPawns = new Pawn[10];
        int i = 0;
        for (Pawn pawn : pawns) {
            if (pawn.getPawnColor() == playerColor) {
                playerPawns[i] = pawn;
                i++;
            }
        }
        return playerPawns;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }

    public void setSettings(GameSettings settings) {
        gameSettings = settings;
    }

    public int getNumberOfPlayers() {
       return gameSettings.getNumberOfPlayers();
    }
    
    public int[] getPlayersNumbers() {
    	return gameSettings.getPlayersNumbers();
    }

    public void setUp() {
        gameBuilder.buildGame(this);
    }

    public Pawn getPawnByField(final Field field) throws Exception {
        Optional<Pawn> pawn = Arrays.stream(pawns).filter(new Predicate<Pawn>() {
			public boolean test(Pawn pawn) {
				return pawn.getField() == field;
			}
		}).findFirst();
        
        if (pawn.isPresent()) {
            return pawn.get();
        }
        else {
            throw new Exception("Field is empty");
        }
    }

    public void addWinner(Player winner) {
    	this.winners.add(winner);
    }
    
    public boolean isWinner(Player player) {
    	return this.winners.contains(player);
    }
    
    public int getNumberOfWinners() {
    	return this.winners.size();
    }
}
