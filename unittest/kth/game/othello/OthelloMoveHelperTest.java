package kth.game.othello;

import junit.framework.Assert;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloNode;

import org.junit.Test;

/**
 * Test for the OthelloMoveHelper class
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
@SuppressWarnings("deprecation")
public class OthelloMoveHelperTest {

	/**
	 * Basic tests for methods in OthelloMoveHelper
	 */
	@Test
	public void testMoveHelper() {
		
		OthelloFactory othelloFactory = new OthelloFactoryImpl();
		
		Othello othello = othelloFactory.createHumanVersusComputerGameOnOriginalBoard();
		
		othello.start(othello.getPlayers().get(0).getId());
		
		Node node1 = new OthelloNode(3,0);
		Node node2 = new OthelloNode(1,7);
		Node node3 = new OthelloNode(5, 3);
		Node node4 = new OthelloNode(6, 2);
		
		Assert.assertEquals(true, OthelloMoveHelper.isIllegalDiagonalStep(othello.getBoard(), node1, node2, -9));
		Assert.assertEquals(false, OthelloMoveHelper.isIllegalDiagonalStep(othello.getBoard(), node3, node4, 7));

		Assert.assertEquals(false, OthelloMoveHelper.isValidStep(othello.getBoard(), 7, -1));
		Assert.assertEquals(false, OthelloMoveHelper.isValidStep(othello.getBoard(), 16, 1));
		Assert.assertEquals(false, OthelloMoveHelper.isValidStep(othello.getBoard(), 23, -1));
		Assert.assertEquals(false, OthelloMoveHelper.isValidStep(othello.getBoard(), 32, 1));
		Assert.assertEquals(false, OthelloMoveHelper.isValidStep(othello.getBoard(), 69, -8));
		Assert.assertEquals(false, OthelloMoveHelper.isValidStep(othello.getBoard(), -1, 8));
		

		Assert.assertEquals(true, OthelloMoveHelper.isValidStep(othello.getBoard(), 7, 1));
		Assert.assertEquals(true, OthelloMoveHelper.isValidStep(othello.getBoard(), 8, -1));
		Assert.assertEquals(true, OthelloMoveHelper.isValidStep(othello.getBoard(), 16, 8));
		Assert.assertEquals(true, OthelloMoveHelper.isValidStep(othello.getBoard(), 16, -8));
		Assert.assertEquals(true, OthelloMoveHelper.isValidStep(othello.getBoard(), 9, -1));
		
		Assert.assertEquals(null, OthelloMoveHelper.findValidMoveInDirection(othello.getBoard(), 0, 1, othello.getPlayerInTurn().getId(), -1));
		Assert.assertEquals(null, OthelloMoveHelper.findValidMoveInDirection(othello.getBoard(), 4, 7, othello.getPlayerInTurn().getId(), 9));
	}
}
