package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloNode;
import kth.game.othello.player.Player;

/**
 * This class initializes and updates a board.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
class OthelloBoardHandler {

	Board board;

	OthelloBoardHandler(Board board) {
		this.board = board;
	}

	/**
	 * Method for initializing an quadratic OthelloBoard, with the starting
	 * pieces for both players
	 * 
	 * @param board
	 *            Board to initialized
	 * @param blackPlayer
	 *            the black player
	 * @param whitePlayerId
	 *            the white player
	 */
	void initBoard(Player blackPlayer, Player whitePlayer) {
		int dimension = (int) Math.sqrt(board.getNodes().size());
		int start = dimension / 2 - 1;

		List<Node> initMovesForBlack = new ArrayList<Node>();
		List<Node> initMovesForWhite = new ArrayList<Node>();

		initMovesForBlack
				.add(new OthelloNode(start, start, blackPlayer.getId()));
		initMovesForWhite.add(new OthelloNode(start, start + 1, whitePlayer
				.getId()));
		initMovesForWhite.add(new OthelloNode(start + 1, start, whitePlayer
				.getId()));
		initMovesForBlack.add(new OthelloNode(start + 1, start + 1, blackPlayer
				.getId()));

		updateMovesOnBoard(initMovesForBlack, blackPlayer);
		updateMovesOnBoard(initMovesForWhite, whitePlayer);
	}

	/**
	 * Method for updating nodes on a existing board
	 * 
	 * @param board
	 *            Board to be updated
	 * @param nodesToSwap
	 *            a list of all the nodes that should be swapped
	 * @param player
	 *            the player whom turn it is
	 */
	void updateMovesOnBoard(List<Node> nodesToSwap, Player player) {
		for (Node node : nodesToSwap) {
			int xCoordinate = node.getXCoordinate();
			int yCoordinate = node.getYCoordinate();
			int index = 8 * xCoordinate + yCoordinate;
			List<Node> oldNodes = board.getNodes();
			oldNodes.set(index, new OthelloNode(xCoordinate, yCoordinate,
					player.getId()));
		}
	}

	/**
	 * Returns the Othello board
	 * 
	 * @return - Returns the board to be played at
	 */
	Board getBoard() {
		return board;
	}

}
