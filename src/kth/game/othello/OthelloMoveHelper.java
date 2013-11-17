package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

public class OthelloMoveHelper {

	private static int UP = -8, LEFT = -1, RIGHT = 1, DOWN = 8, UP_LEFT = -9,UP_RIGHT = -7, DOWN_LEFT = 7, DOWN_RIGHT = 9;
	
	/**
	 * 
	 * @param nodes
	 * @param index
	 * @param change
	 * @return
	 */
	static boolean isValidStep(List<Node> nodes, int index, int change) {
		return (index < nodes.size() && index >= 0 && (Math.abs(change) > 1 || ((index + 1) % 8 != 0 && change == LEFT) || ((index) % 8 != 0 && change == RIGHT)));
	}
	
	/**
	 * Checks whether a step in a diagonal direction is an illegal step.
	 * 
	 * @param board				-	The board that is played on
	 * @param lastXCoordinate	-	The last x coordinate
	 * @param index				-	The index for the node in the current step
	 * @param change			-	The number of nodes to skip in the list to make the step
	 * @return
	 */
	static boolean isIllegalDiagonalStep(Board board, int lastXCoordinate, int index, int change) {
		int dimension = (int)Math.sqrt(board.getNodes().size());
		if (index / dimension == lastXCoordinate && (change == UP_RIGHT || change == DOWN_LEFT))
			return true;
		else if (Math.abs(index / dimension - lastXCoordinate) == 2 && (change == UP_LEFT || change == DOWN_RIGHT))
			return true;
		return false;
	}
	
	/**
	 * Finds a valid move in the specified direction
	 * 
	 * @param board				-	The board that is played on
	 * @param xCoordinate		-	The x coordinate of the node that is played on
	 * @param yCoordinate		-	The y coordinate of the node that is played on
	 * @param playerId			-	The ID of the player making the move
	 * @param direction			-	The direction to find a valid move in
	 * @return List<Node> nodes	-	The nodes to be swapped in the direction specified
	 */
	static List<Node> findValidMoveInDirection(Board board, int xCoordinate, int yCoordinate, String playerId, int direction) {
		List<Node> nodes = board.getNodes();
		int i = getIndexFromCoordinates(board, xCoordinate, yCoordinate) + direction;
		boolean foundOpponent = false;
		List<Node> returnedNodes = new ArrayList<Node>();
		returnedNodes.add(nodes.get(i - direction));
		int lastXCoordinate = xCoordinate;
		while (isValidStep(nodes, i, direction)) {
			if (isIllegalDiagonalStep(board, lastXCoordinate, i, direction))
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
			i += direction;
			lastXCoordinate = currentNode.getXCoordinate();
		}
		return null;
	}

	/**
	 * Gets the index of a node from its coordinates
	 * 
	 * @param board			-	The board that is played on
	 * @param xCoordinate	-	The x coordinate of the node 
	 * @param yCoordinate	-	The y coordinate of the node
	 * @return The index of the node
	 */
	static int getIndexFromCoordinates(Board board, int xCoordinate, int yCoordinate) {
		int dimension = (int)Math.sqrt(board.getNodes().size());
		return dimension * xCoordinate + yCoordinate;
	}

	/**
	 * Gets the coordinates for a node from the ID
	 * 
	 * @param nodeId				-	The ID of the node
	 * @return int[] coordinates	-	The coordinates of the node { x, y }
	 */
	static int[] getCoordinatesFromId(String nodeId) {
		int[] coordinates = new int[2];
		String[] strCoordinates = nodeId.split(":");
		coordinates[0] = Integer.parseInt(strCoordinates[0]);
		coordinates[1] = Integer.parseInt(strCoordinates[1]);
		return coordinates;
	}
	
	static boolean isValidNode(String nodeId) {

		int xCoordinate = OthelloMoveHelper.getCoordinatesFromId(nodeId)[0];
		int yCoordinate = OthelloMoveHelper.getCoordinatesFromId(nodeId)[1];
		if(xCoordinate >= 0 && xCoordinate < 8 && yCoordinate >= 0 && yCoordinate < 8)
			return true;
		return false;
	}
}
