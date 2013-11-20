//package kth.game.othello;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Assert;
//import kth.game.othello.player.HumanPlayer;
//import kth.game.othello.player.Player;
//
//import org.junit.Test;
//
//public class OthelloPlayerHandlerTest {
//
//	@Test
//	public void testPlayerMethods() {
//		OthelloPlayerHandler playerHandler = new OthelloPlayerHandler();
//		List<Player> players = new ArrayList<Player>();
//
//		// Test method without players in list
//		Assert.assertEquals(null, playerHandler.getPlayerFromId("", players));
//
//		// Add one player to a list
//		players.add(new HumanPlayer("Test1"));
//		Assert.assertEquals(HumanPlayer.class,
//				playerHandler.getPlayerFromId(players.get(0).getId(), players)
//						.getClass());
//		Assert.assertEquals(null,
//				playerHandler.getOpponentId(players.get(0).getId(), players));
//
//		// Add one more player
//		players.add(new HumanPlayer("Test2"));
//		Assert.assertEquals(HumanPlayer.class,
//				playerHandler.getPlayerFromId(players.get(0).getId(), players)
//						.getClass());
//		Assert.assertEquals(players.get(1).getId(),
//				playerHandler.getOpponentId(players.get(0).getId(), players));
//		Assert.assertNotSame(null, playerHandler.getOpponentId("0", players));
//
//	}
// }
