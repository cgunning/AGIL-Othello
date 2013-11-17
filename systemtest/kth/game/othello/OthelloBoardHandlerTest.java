package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import junit.framework.Assert;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;
import kth.game.othello.board.OthelloNode;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;

/**
 * Test basic methods for OthelloBoardHandler
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
public class OthelloBoardHandlerTest {
	
	@Test
	public void boardHandlerTest() {
		Board board = new OthelloBoard(8);
		board = OthelloBoardHandler.initBoard(board, "black", "white");
		Assert.assertEquals("white", board.getNodes().get(28).getOccupantPlayerId());
		Assert.assertEquals("black", board.getNodes().get(27).getOccupantPlayerId());
		Assert.assertEquals(null, board.getNodes().get(26).getOccupantPlayerId());
		Assert.assertEquals(null, board.getNodes().get(29).getOccupantPlayerId());
		
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new OthelloNode(1,1));
		nodes.add(new OthelloNode(7,7));
		OthelloBoardHandler.updateMovesOnBoard(board, nodes, "white");
		Assert.assertEquals("white", board.getNodes().get(9).getOccupantPlayerId());
		Assert.assertEquals("white", board.getNodes().get(63).getOccupantPlayerId());
		Assert.assertEquals(null,  board.getNodes().get(8).getOccupantPlayerId());
		Assert.assertEquals(null,  board.getNodes().get(62).getOccupantPlayerId());
		
	}
}
