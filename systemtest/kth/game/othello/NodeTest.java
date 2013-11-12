package kth.game.othello;

import java.awt.List;

import org.junit.Test;

import junit.framework.Assert;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloNode;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

public class NodeTest {
	
	private OthelloNode createNode(int xCoordinate, int yCoordinate) {
		return new OthelloNode(xCoordinate, yCoordinate);
	}
	
	@Test
	public void testNodes() {
		OthelloNode node = createNode(0, 0);

		Assert.assertEquals(0, node.getXCoordinate());
		Assert.assertEquals(0, node.getYCoordinate());
		Assert.assertEquals("0:0", node.getId());
		Assert.assertEquals(null, node.getOccupantPlayerId());
		Assert.assertEquals(false, node.isMarked());
		
		node.setMarked();
		node.setOccupantPlayerId("2");
		Assert.assertEquals(true, node.isMarked());
		Assert.assertEquals("2", node.getOccupantPlayerId());
	}
}
