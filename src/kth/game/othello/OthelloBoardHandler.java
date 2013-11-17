package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;


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
	 * @return the new initialized board
	 */
	static Board initBoard(Board board, String blackPlayerId, String whitePlayerId) {
		int dimension = (int) Math.sqrt(board.getNodes().size());
		int start = dimension/2 - 1;
		
		board = new OthelloBoard(board, start, start, blackPlayerId);
		board = new OthelloBoard(board, start, start + 1, whitePlayerId);
		board = new OthelloBoard(board, start + 1, start, whitePlayerId);
		board = new OthelloBoard(board, start + 1, start + 1, blackPlayerId);
		return board;
	}
	
	
	/**
	 * Method for updating nodes on a existing board
	 * @param board Board to be updated
	 * @param nodesToSwap a list of all the nodes that should be swapped
	 * @param playerId the Id of the player whom turn it is 
	 * @return the new updated board
	 */
	static Board updateMovesOnBoard(Board board, List<Node> nodesToSwap, String playerId) {
		for(Node node : nodesToSwap) {
			board = new OthelloBoard(board, node.getXCoordinate(), node.getYCoordinate(), playerId);
		}
		return board;
	}
	
}
