package kth.game.othello;

import junit.framework.Assert;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testPlayer(){
		Player computerPlayer = new ComputerPlayer("computer");
		Player computerPlayer2 = new ComputerPlayer("computer2");
		Player humanPlayer = new HumanPlayer("human");
		Player humanPlayer2 = new HumanPlayer("human2");
		Assert.assertEquals("computer", computerPlayer.getName());
		Assert.assertEquals("computer2", computerPlayer2.getName());
		Assert.assertEquals("human", humanPlayer.getName());
		Assert.assertEquals("human2", humanPlayer2.getName());
		
		Assert.assertEquals("0", computerPlayer.getId());
		Assert.assertEquals("1", computerPlayer2.getId());
		Assert.assertEquals("2", humanPlayer.getId());
		Assert.assertEquals("3", humanPlayer2.getId());
		
		Assert.assertEquals(Type.COMPUTER, computerPlayer.getType());
		Assert.assertEquals(Type.COMPUTER, computerPlayer2.getType());
		Assert.assertEquals(Type.HUMAN, humanPlayer.getType());
		Assert.assertEquals(Type.HUMAN, humanPlayer2.getType());


	}
}
