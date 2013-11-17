package kth.game.othello;

import junit.framework.Assert;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;

import org.junit.Test;

/**
 * Test for OthelloFactoryImpl
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
@SuppressWarnings("deprecation")
public class OthelloFactoryTest {

	/**
	 * 
	 */
	@Test
	public void testOthelloFactory() {
		OthelloFactoryImpl othelloFactory = new OthelloFactoryImpl();

		Othello computerGame = othelloFactory.createComputerGameOnClassicalBoard();
		Othello humanGame = othelloFactory.createHumanGameOnOriginalBoard();
		Othello cVsHGame = othelloFactory.createHumanVersusComputerGameOnOriginalBoard();

		Assert.assertEquals(computerGame.getPlayers().get(0).getClass(), ComputerPlayer.class);
		Assert.assertEquals(computerGame.getPlayers().get(1).getClass(), ComputerPlayer.class);
		
		Assert.assertEquals(humanGame.getPlayers().get(0).getClass(), HumanPlayer.class);
		Assert.assertEquals(humanGame.getPlayers().get(1).getClass(), HumanPlayer.class);
		
		Assert.assertEquals(cVsHGame.getPlayers().get(0).getClass(), HumanPlayer.class);
		Assert.assertEquals(cVsHGame.getPlayers().get(1).getClass(), ComputerPlayer.class);
		
	}
}
