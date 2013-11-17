package kth.game.othello;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.OthelloBoard;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;

/**
 * Test for the OthelloMoveHandler class
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
public class OthelloMoveHandlerTest {

	/**
	 * Test the basic methods for the OthelloMoveHandler
	 */
	@Test
	public void testMoveHandler(){
		Board board = new OthelloBoard(8);
		Player computer = new ComputerPlayer("Computer");
		Player human = new HumanPlayer("Human");
		Othello othello = new OthelloImpl(computer, human, board);
		othello.start();
		Assert.assertEquals(true, OthelloMoveHandler.hasValidMove(board, computer.getId()));
		Assert.assertEquals(true, OthelloMoveHandler.hasValidMove(board, human.getId()));
		
		Assert.assertEquals(ArrayList.class, OthelloMoveHandler.getNodesToSwap(board,computer.getId(), "0:0").getClass());
		Assert.assertEquals(2, OthelloMoveHandler.getNodesToSwap(board,computer.getId(), "5:3").size());
		Assert.assertEquals(0, OthelloMoveHandler.getNodesToSwap(board,human.getId(), "5:3").size());
		
		Assert.assertEquals(2, OthelloMoveHandler.move(board,computer.getId(), "5:3").size());
		Assert.assertEquals(0, OthelloMoveHandler.move(board,human.getId(), "5:3").size());
		
		Assert.assertEquals(2, OthelloMoveHandler.move(board,computer.getId()).size());
		Assert.assertEquals(2, OthelloMoveHandler.move(board,human.getId()).size());
		
		Assert.assertEquals(true, OthelloMoveHandler.isMoveValid(board, computer.getId(), "2:4"));
		Assert.assertEquals(false, OthelloMoveHandler.isMoveValid(board, human.getId(), "2:4"));
		
		
		
	}
}
