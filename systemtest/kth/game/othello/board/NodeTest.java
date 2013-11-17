package kth.game.othello.board;

import junit.framework.Assert;
import static org.mockito.Mockito.*;
import kth.game.othello.board.OthelloNode;

import org.junit.Test;

/**
 * Test the OthelloNode class
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
@SuppressWarnings("deprecation")
public class NodeTest {
	
	/**
	 * 
	 */
	@Test
	public void testNodes() {
		OthelloNode unMarkednode = new OthelloNode(0, 0);
		Assert.assertEquals(0, unMarkednode.getXCoordinate());
		Assert.assertEquals(0, unMarkednode.getYCoordinate());
		Assert.assertEquals("0:0", unMarkednode.getId());
		Assert.assertEquals(null, unMarkednode.getOccupantPlayerId());
		Assert.assertEquals(false, unMarkednode.isMarked());

		OthelloNode markedNode = new OthelloNode(1, 1, "1");
		Assert.assertEquals(1, markedNode.getXCoordinate());
		Assert.assertEquals(1, markedNode.getYCoordinate());
		Assert.assertEquals("1:1", markedNode.getId());
		Assert.assertEquals("1", markedNode.getOccupantPlayerId());
		Assert.assertEquals(true, markedNode.isMarked());
	}
}
