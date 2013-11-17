package kth.game.othello;

import junit.framework.Assert;
import kth.game.othello.board.OthelloNode;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class NodeTest {
	
	@Test
	public void testNodes() {
		OthelloNode node = new OthelloNode(0, 0);
		OthelloNode markedNode = new OthelloNode(1, 1, "1");

		Assert.assertEquals(0, node.getXCoordinate());
		Assert.assertEquals(0, node.getYCoordinate());
		Assert.assertEquals("0:0", node.getId());
		Assert.assertEquals(null, node.getOccupantPlayerId());
		Assert.assertEquals(false, node.isMarked());

		Assert.assertEquals(1, markedNode.getXCoordinate());
		Assert.assertEquals(1, markedNode.getYCoordinate());
		Assert.assertEquals("1:1", markedNode.getId());
		Assert.assertEquals("1", markedNode.getOccupantPlayerId());
		Assert.assertEquals(true, markedNode.isMarked());		
	}
}
