package kth.game.othello;

import java.util.List;

import kth.game.othello.player.Player;

/**
 * Class that handles the player for an Othello game.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
class OthelloPlayerHandler {
	
	/**
	 * Method for getting a Player from an Id
	 * @param playerId Id of the player
	 * @param players List of all the players
	 * @return the Player corresponding to the right Id
	 */
	static Player getPlayerFromId(String playerId, List<Player> players) {
		for(Player player : players)
			if(player.getId().equals(playerId))
				return player;
		return null;
	}
	
	/**
	 * Method for getting the opponents Id 
	 * @param currentPlayerId Id for the player in turn
	 * @param players List of all the players
	 * @return the opponents Id
	 */
	static String getOpponentId(String currentPlayerId, List<Player> players) {
		for(Player player : players)
			if(!player.getId().equals(currentPlayerId))
				return player.getId();
		return null;
	}
}
