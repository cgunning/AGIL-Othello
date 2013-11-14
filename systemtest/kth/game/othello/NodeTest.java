package kth.game.othello;

import junit.framework.Assert;
import kth.game.othello.board.OthelloNode;

import org.junit.Test;

@SuppressWarnings("deprecation")
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

		Assert.assertEquals(true, node.isMarked());
		Assert.assertEquals("2", node.getOccupantPlayerId());
	}
}
