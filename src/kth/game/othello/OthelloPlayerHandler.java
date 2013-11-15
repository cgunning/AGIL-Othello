package kth.game.othello;

import java.util.List;
import kth.game.othello.player.Player;

class OthelloPlayerHandler {
	
	static Player getPlayerFromId(String playerId, List<Player> players) {
		for(Player player : players)
			if(player.getId().equals(playerId))
				return player;
		return null;
	}
	
	static String getOpponentId(String currentPlayerId, List<Player> players) {
		for(Player player : players)
			if(!player.getId().equals(currentPlayerId))
				return player.getId();
		return null;
	}
}
