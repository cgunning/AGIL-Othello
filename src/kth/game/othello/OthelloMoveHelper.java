package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

public class OthelloMoveHelper {

	private static int UP = -8, LEFT = -1, RIGHT = 1, DOWN = 8, UP_LEFT = -9,UP_RIGHT = -7, DOWN_LEFT = 7, DOWN_RIGHT = 9;
	
	static boolean isValidStep(List<Node> nodes, int index, int change) {
		return (index < nodes.size() && index >= 0 && (Math.abs(change) > 1 || ((index + 1) % 8 != 0 && change == LEFT) || ((index) % 8 != 0 && change == RIGHT)));
	}
	
	static boolean isIllegalStep(Board board, int lastXCoordinate, int index, int change) {
		int dimension = (int)Math.sqrt(board.getNodes().size());
		if (index / dimension == lastXCoordinate && (change == UP_RIGHT || change == DOWN_LEFT))
			return true;
		else if (Math.abs(index / dimension - lastXCoordinate) == 2 && (change == UP_LEFT || change == DOWN_RIGHT))
			return true;
		return false;
	}
	
	static List<Node> findValidMoveInDirection(Board board, int xCoordinate, int yCoordinate, String playerId, int change) {
		List<Node> nodes = board.getNodes();
		int i = getIndexFromCoordinates(board, xCoordinate, yCoordinate) + change;
		boolean foundOpponent = false;
		List<Node> returnedNodes = new ArrayList<Node>();
		returnedNodes.add(nodes.get(i - change));
		int lastXCoordinate = xCoordinate;
		while (isValidStep(nodes, i, change)) {
			if (isIllegalStep(board, lastXCoordinate, i, change))
				return null;
			
			Node currentNode = nodes.get(i);
			if (currentNode.isMarked()) {
				if (!foundOpponent) {
					if (!currentNode.getOccupantPlayerId().equals(playerId)) {
						foundOpponent = true;
						returnedNodes.add(currentNode);
					} else {
						return null;
					}
				} else if (currentNode.getOccupantPlayerId().equals(playerId)) {
					return returnedNodes;
				} else {
					returnedNodes.add(currentNode);
				}
			} else
				return null;
			i += change;
			lastXCoordinate = currentNode.getXCoordinate();
		}
		return null;
	}

	static int getIndexFromCoordinates(Board board, int xCoordinate, int yCoordinate) {
		int dimension = (int)Math.sqrt(board.getNodes().size());
		return dimension * xCoordinate + yCoordinate;
	}

	static int[] getCoordinatesFromId(String nodeId) {
		int[] coordinates = new int[2];
		String[] strCoordinates = nodeId.split(":");
		coordinates[0] = Integer.parseInt(strCoordinates[0]);
		coordinates[1] = Integer.parseInt(strCoordinates[1]);
		return coordinates;
	}
}
