package kth.game.othello;

import junit.framework.Assert;
import kth.game.othello.board.OthelloBoard;

import org.junit.Test;

/**
 * Test the OthelloBoard class
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
@SuppressWarnings("deprecation")
public class BoardTest {
	
	/**
	 * 
	 */
	@Test
	public void testBoard() {
		OthelloBoard board = new OthelloBoard(8);
		Assert.assertEquals(64, board.getNodes().size());

		OthelloBoard board2 = new OthelloBoard(10);
		Assert.assertEquals(100, board2.getNodes().size());
	}
}
