package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.mockito.Mockito.*;
import junit.framework.Assert;

import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;
import kth.game.othello.board.OthelloNode;

/**
 * Test the OthelloBoard class
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
public class BoardTest {
	
	/**
	 * 
	 */
	@Test
	public void testBoard() {
		OthelloBoard board = new OthelloBoard(8);
		Assert.assertEquals(64, board.getNodes().size());
	}
}
