package kth.game.othello;

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class OthelloMoveHelperTest {

	@Test
	public void testMoveHelper() {
		
		OthelloFactory othelloFactory = new OthelloFactoryImpl();
		
		Othello othello = othelloFactory.createHumanVersusComputerGameOnOriginalBoard();
		
		Assert.assertEquals(false, OthelloMoveHelper.isIllegalDiagonalStep(othello.getBoard(), 5, 1, -9));
		
		
		
	}
}
