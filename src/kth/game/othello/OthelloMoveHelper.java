package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * A class to 
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
public class OthelloMoveHelper {

	private static int UP = -8, LEFT = -1, RIGHT = 1, DOWN = 8, UP_LEFT = -9,UP_RIGHT = -7, DOWN_LEFT = 7, DOWN_RIGHT = 9;
	
	/**
	 * Method to see if the node in the given direction is a valid step. 
	 * 
	 * @param board		- 	The board that is played on	
	 * @param index		-	Index of the node to be checked if is valid step
	 * @param direction	-	The direction of movement
	 * @return boolean	-	True if this step is valid in this direction, otherwise false.
	 */
	static boolean isValidStep(Board board, int index, int direction) {
		return (index < board.getNodes().size() && index >= 0 && (Math.abs(direction) > 1 || ((index + 1) % 8 != 0 && direction == LEFT) || ((index) % 8 != 0 && direction == RIGHT)));
	}
	
	/**
	 * Checks whether a step in a diagonal direction is an illegal step.
	 * 
	 * @param board				-	The board that is played on
	 * @param lastNode	-	The last x coordinate
	 * @param currentNode				-	The index for the node in the current step
	 * @param change			-	The number of nodes to skip in the list to make the step
	 * @return boolean			-	True if the step is an illegal step in that diagonal direction, otherwise false.
	 */
	static boolean isIllegalDiagonalStep(Board board, Node lastNode, Node currentNode, int change) {
		int dimension = (int)Math.sqrt(board.getNodes().size());
		if (currentNode.getXCoordinate() == lastNode.getXCoordinate() && (change == UP_RIGHT || change == DOWN_LEFT))
			return true;
		else if (Math.abs(currentNode.getXCoordinate() - lastNode.getXCoordinate()) == 2 && (change == UP_LEFT || change == DOWN_RIGHT))
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
		int i = OthelloNodeHelper.getIndexFromCoordinates(board, xCoordinate, yCoordinate);
		Node lastNode = nodes.get(i);
		boolean foundOpponent = false;
		List<Node> returnedNodes = new ArrayList<Node>();
		returnedNodes.add(nodes.get(i));
		i += direction;
		while (isValidStep(board, i, direction)) {
			Node currentNode = nodes.get(i);
			
			if (isIllegalDiagonalStep(board, lastNode, currentNode, direction))
				return null;
			
			if (currentNode.isMarked()) {
				if (!foundOpponent) { //The first node in a direction needs to be an opponents
					if (!currentNode.getOccupantPlayerId().equals(playerId)) {
						foundOpponent = true;
						returnedNodes.add(currentNode);
					} else {
						return null;
					}
				} else if (currentNode.getOccupantPlayerId().equals(playerId)) { //Found a node that is occupied with me and has a occupant node betwwen.
					return returnedNodes;
				} else { 
					returnedNodes.add(currentNode);
				}
			} else
				return null;
			i += direction; //Continue in the given direction
			lastNode = currentNode;
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
	 * @param nodeId	-	The ID of the node
	 * @return The coordinates of the node on the form { x, y }
	 */
	static int[] getCoordinatesFromId(String nodeId) {
		int[] coordinates = new int[2];
		String[] strCoordinates = nodeId.split(":");
		coordinates[0] = Integer.parseInt(strCoordinates[0]);
		coordinates[1] = Integer.parseInt(strCoordinates[1]);
		return coordinates;
	}
	
	/**
	 * Checks if an ID of a node is a valid ID
	 * 
	 * @param nodeId	-	The ID of the node
	 * @return True if the ID is valid, false otherwise
	 */
	static boolean isValidNodeId(String nodeId) {

		int xCoordinate = OthelloMoveHelper.getCoordinatesFromId(nodeId)[0];
		int yCoordinate = OthelloMoveHelper.getCoordinatesFromId(nodeId)[1];
		if(xCoordinate >= 0 && xCoordinate < 8 && yCoordinate >= 0 && yCoordinate < 8)
			return true;
		return false;
	}
}
