package kth.game.othello;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.Assert;
import kth.game.othello.board.OthelloBoard;
import kth.game.othello.player.HumanPlayer;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class OthelloTest {

	private OthelloBoard createBoard() {
		OthelloBoard board = mock(OthelloBoard.class);
		when(board.getNodes()).thenReturn(null);
		return board;
	}
	
	@Test
	public void testOthello() {
		//OthelloBoard board = createBoard();
		OthelloBoard board = new OthelloBoard(8);
		HumanPlayer p1 = new HumanPlayer("Black");
		HumanPlayer p2 = new HumanPlayer("White");
		OthelloImpl othello = new OthelloImpl(p1, p2, board);
		othello.start();
		Assert.assertEquals(64, othello.getBoard().getNodes().size());
		System.out.println(othello.getBoard().toString());
		Assert.assertEquals(true, othello.isMoveValid(p2.getId(), "2:3"));
		Assert.assertEquals(false, othello.isMoveValid(p1.getId(), "2:3"));
		Assert.assertEquals(true, othello.hasValidMove(p2.getId()));
		Assert.assertEquals(true, othello.hasValidMove(p1.getId()));
		
	}
	
	
}
