package kth.game.othello;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Constructor;

import org.junit.Test;

/**
 * Test for the OthelloMoveHandler class
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
@SuppressWarnings("deprecation")
public class OthelloMoveHandlerTest {

	/**
	 * Test the basic methods for the OthelloMoveHandler
	 */
	@Test
	public void testMoveHandler() {
		OthelloNodeHelper nodeHelper = mock(OthelloNodeHelper.class);
		OthelloBoardHandler boardHandler = mock(OthelloBoardHandler.class);
		OthelloMoveHelper moveHelper = mock(OthelloMoveHelper.class);

		Constructor<OthelloMoveHandler> constructor;
		try {
			constructor = OthelloMoveHandler.class.getDeclaredConstructor(
					OthelloNodeHelper.class, OthelloBoardHandler.class,
					OthelloMoveHelper.class);
			constructor.setAccessible(true);
			OthelloMoveHandler moveHandler = constructor.newInstance(
					nodeHelper, boardHandler, moveHelper);
		} catch (Exception e) {
		}

		// Board board = new OthelloBoard(8);
		// Player computer = new ComputerPlayer("Computer");
		// Player human = new HumanPlayer("Human");
		// Othello othello = new OthelloImpl(computer, human, board);
		// othello.start(computer.getId());
		// Assert.assertEquals(true,
		// moveHandler.hasValidMove(board, computer.getId()));
		// Assert.assertEquals(true,
		// moveHandler.hasValidMove(board, human.getId()));
		//
		// Assert.assertEquals(ArrayList.class,
		// moveHandler.getNodesToSwap(board, computer.getId(), "0:0")
		// .getClass());
		// Assert.assertEquals(2,
		// moveHandler.getNodesToSwap(board, computer.getId(), "5:3")
		// .size());
		// Assert.assertEquals(0,
		// moveHandler.getNodesToSwap(board, human.getId(), "5:3").size());
		//
		// Assert.assertEquals(2, moveHandler.move(board, computer.getId(),
		// "5:3")
		// .size());
		// Assert.assertEquals(0, moveHandler.move(board, human.getId(), "5:3")
		// .size());
		//
		// Assert.assertEquals(true,
		// moveHandler.isMoveValid(board, computer.getId(), "2:4"));
		// Assert.assertEquals(false,
		// moveHandler.isMoveValid(board, human.getId(), "2:4"));

	}
}
