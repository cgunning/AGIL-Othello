package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;

class OthelloMoveHandler {

	private static int UP = -8, LEFT = -1, RIGHT = 1, DOWN = 8, UP_LEFT = -9,UP_RIGHT = -7, DOWN_LEFT = 7, DOWN_RIGHT = 9;
	private static int[] changes = { UP, LEFT, RIGHT, DOWN, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT };

	static List<Node> getNodesToSwap(Board board, String playerId, String nodeId) {
		int[] coordinatesForNode = OthelloMoveHelper.getCoordinatesFromId(nodeId);
		List<Node> returnedNodes = new ArrayList<Node>();
		for (int change : changes) {
			List<Node> swappedNodes = OthelloMoveHelper.findValidMoveInDirection(board, coordinatesForNode[0],coordinatesForNode[1], playerId, change);
			if (swappedNodes != null) {
				returnedNodes.addAll(swappedNodes);
			}
		}
		return returnedNodes;
	}

	static boolean hasValidMove(Board board, String playerId) {
		List<Node> nodes = board.getNodes();
		for (Node node : nodes) {
			if (!node.isMarked() && isMoveValid(board, playerId, node.getId())) {
				return true;
			}
		}
		return false;
	}

	static boolean isMoveValid(Board board, String playerId, String nodeId) {
		List<Node> nodes = board.getNodes();
		int[] coordinatesForNode = OthelloMoveHelper.getCoordinatesFromId(nodeId);
		if (nodes.get(OthelloMoveHelper.getIndexFromCoordinates(board, coordinatesForNode[0],coordinatesForNode[1])).isMarked()) {
			return false;
		}
		for (int change : changes) {
			if (OthelloMoveHelper.findValidMoveInDirection(board, coordinatesForNode[0], coordinatesForNode[1],playerId, change) != null)
				return true;
		}
		return false;
	}

	static List<Node> move(Board board, String playerInTurnId) {
		List<Node> nodes = board.getNodes();
		for (Node node : nodes) {
			if (isMoveValid(board, playerInTurnId, node.getId())) {
				List<Node> nodesToSwap = getNodesToSwap(board, playerInTurnId,node.getId());
				return nodesToSwap;
			}
		}
		return new ArrayList<Node>();
	}

	static List<Node> move(Board board, String playerId, String nodeId)
			throws IllegalArgumentException {
		List<Node> nodesToSwap = getNodesToSwap(board, playerId, nodeId);
		return nodesToSwap;
	}

}
