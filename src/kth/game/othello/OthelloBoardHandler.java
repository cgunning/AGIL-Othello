package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;
import kth.game.othello.board.OthelloNode;


/**
 * This class initializes and updates a board.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
class OthelloBoardHandler {
	
	/**
	 * Method for initializing an quadratic OthelloBoard, with the starting pieces for both players
	 * @param board Board to initialized
	 * @param blackPlayerId the Id for the black player
	 * @param whitePlayerId the Id for the white player
	 */
	static void initBoard(Board board, String blackPlayerId, String whitePlayerId) {
		int dimension = (int) Math.sqrt(board.getNodes().size());
		int start = dimension/2 - 1;

		List<Node> initMovesForBlack = new ArrayList<Node>();
		List<Node> initMovesForWhite = new ArrayList<Node>();

		initMovesForBlack.add(new OthelloNode(start, start, blackPlayerId));
		initMovesForWhite.add(new OthelloNode(start, start + 1, whitePlayerId));
		initMovesForWhite.add(new OthelloNode(start + 1, start, whitePlayerId));
		initMovesForBlack.add(new OthelloNode(start + 1, start + 1, blackPlayerId));

		updateMovesOnBoard(board, initMovesForBlack, blackPlayerId);
		updateMovesOnBoard(board, initMovesForWhite, whitePlayerId);
	}
	
	
	/**
	 * Method for updating nodes on a existing board
	 * @param board Board to be updated
	 * @param nodesToSwap a list of all the nodes that should be swapped
	 * @param playerId the Id of the player whom turn it is 
	 */
	static void updateMovesOnBoard(Board board, List<Node> nodesToSwap, String playerId) {
		for(Node node : nodesToSwap) {
				int xCoordinate = node.getXCoordinate();
				int yCoordinate = node.getYCoordinate();
				int index = 8*xCoordinate + yCoordinate;
				List<Node> oldNodes = board.getNodes();
				oldNodes.set(index, new OthelloNode(xCoordinate, yCoordinate, playerId));
		}
	}
	
}
