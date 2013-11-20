package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

/**
 * Class that handles moves on a Othello board
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
class OthelloMoveHandler {

	private static int UP = -8, LEFT = -1, RIGHT = 1, DOWN = 8, UP_LEFT = -9,
			UP_RIGHT = -7, DOWN_LEFT = 7, DOWN_RIGHT = 9;
	private static int[] changes = { UP, LEFT, RIGHT, DOWN, UP_LEFT, UP_RIGHT,
			DOWN_LEFT, DOWN_RIGHT };
	private OthelloNodeHelper nodeHelper;
	private OthelloMoveHelper moveHelper;
	private OthelloBoardHandler boardHandler;

	private OthelloMoveHandler(OthelloNodeHelper nodeHelper,
			OthelloBoardHandler boardHandler, OthelloMoveHelper moveHelper) {
		this.nodeHelper = nodeHelper;
		this.moveHelper = moveHelper;
		this.boardHandler = boardHandler;
	}

	OthelloMoveHandler(OthelloNodeHelper nodeHelper,
			OthelloBoardHandler boardHandler) {
		this.nodeHelper = nodeHelper;
		this.moveHelper = new OthelloMoveHelper();
		this.boardHandler = boardHandler;
	}

	/**
	 * Gets the nodes to be swapped if the player with id playerId would place a
	 * piece on the node with id nodeId on the specified board
	 * 
	 * @param board
	 *            - The board that is be played on
	 * @param player
	 *            - The player whose turn it is
	 * @param nodeId
	 *            - The id of the node that will be played on
	 * @return List<Node> nodes - The nodes to be swapped
	 */
	List<Node> getNodesToSwap(Player player, String nodeId) {
		int[] coordinatesForNode = nodeHelper.getCoordinatesFromId(nodeId);
		List<Node> returnedNodes = new ArrayList<Node>();
		for (int change : changes) {
			List<Node> swappedNodes = moveHelper.findValidMoveInDirection(
					boardHandler.getBoard(), coordinatesForNode[0],
					coordinatesForNode[1], player, change);
			if (swappedNodes != null) {
				returnedNodes.addAll(swappedNodes);
			}
		}
		return returnedNodes;
	}

	/**
	 * Checks whether the player can make a valid move in the board
	 * 
	 * @param board
	 *            - The board played on
	 * @param playerId
	 *            - The id of the player whose turn it is
	 * @return boolean - True if the player can make a valid move, false
	 *         otherwise
	 */
	boolean hasValidMove(Player player) {
		List<Node> nodes = boardHandler.getBoard().getNodes();
		for (Node node : nodes) {
			if (!node.isMarked() && isMoveValid(player, node.getId())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the specified move is a valid move
	 * 
	 * @param board
	 *            - The board that is played on
	 * @param playerId
	 *            - The player whose turn it is
	 * @param nodeId
	 *            - The id of the node played on
	 * @return boolean - Returns true if the move is valid, false otherwise
	 */
	boolean isMoveValid(Player player, String nodeId) {
		Board board = boardHandler.getBoard();
		List<Node> nodes = board.getNodes();
		int[] coordinatesForNode = nodeHelper.getCoordinatesFromId(nodeId);
		if (nodes.get(
				nodeHelper.getIndexFromCoordinates(board,
						coordinatesForNode[0], coordinatesForNode[1]))
				.isMarked()) {
			return false;
		}
		for (int change : changes) {
			if (moveHelper.findValidMoveInDirection(board,
					coordinatesForNode[0], coordinatesForNode[1], player,
					change) != null)
				return true;
		}
		return false;
	}

	/**
	 * Method that chooses a random valid move to be played
	 * 
	 * @param board
	 *            The board that is played on
	 * @param playerInTurnId
	 *            The Id of the player in turn
	 * @return List of nodes that will be effected for that move or an empty
	 *         ArrayList if there are no moves.
	 */
	List<Node> move(Player player) {
		List<Node> nodes = boardHandler.getBoard().getNodes();
		for (Node node : nodes) {
			if (isMoveValid(player, node.getId())) {
				return move(player, node.getId());
			}
		}
		return new ArrayList<Node>();
	}

	/**
	 * Method that makes a move to be played for a certain node.
	 * 
	 * @param board
	 *            The board that is played on
	 * @param playerId
	 *            The Id for the player who makes the move
	 * @param nodeId
	 *            The Id for the node where the move is played
	 * @return List of nodes that will be effected for that move.
	 */
	List<Node> move(Player player, String nodeId) {
		// TODO Check so that the id is in the range of the game
		if (!nodeHelper.isValidNodeId(nodeId) || player == null)
			throw new IllegalArgumentException();
		List<Node> nodesToSwap = getNodesToSwap(player, nodeId);
		boardHandler.updateMovesOnBoard(nodesToSwap, player);
		return nodesToSwap;
	}

}
