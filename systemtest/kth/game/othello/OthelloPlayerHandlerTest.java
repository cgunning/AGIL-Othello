package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import junit.framework.Assert;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;


public class OthelloPlayerHandlerTest {

	@Test
	public void testPlayerMethods() {
		List<Player> players = new ArrayList<Player>();
		
		//Test method without players in list
		Assert.assertEquals(null, OthelloPlayerHandler.getPlayerFromId("", players));
		
		//Add one player to a list
		players.add(new HumanPlayer("Test1"));
		Assert.assertEquals(HumanPlayer.class, OthelloPlayerHandler.getPlayerFromId(players.get(0).getId(), players).getClass());
		Assert.assertEquals(null, OthelloPlayerHandler.getOpponentId(players.get(0).getId(), players));
		
		
		//Add one more player
		players.add(new HumanPlayer("Test2"));
		Assert.assertEquals(HumanPlayer.class, OthelloPlayerHandler.getPlayerFromId(players.get(0).getId(), players).getClass());
		Assert.assertEquals(players.get(1).getId(), OthelloPlayerHandler.getOpponentId(players.get(0).getId(), players));
		Assert.assertNotSame(null, OthelloPlayerHandler.getOpponentId("0", players));

	}
}
