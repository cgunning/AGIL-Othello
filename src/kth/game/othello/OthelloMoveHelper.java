package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * A class to help find moves on a Othello board.
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
	 * @param lastXCoordinate	-	The last x coordinate
	 * @param index				-	The index for the node in the current step
	 * @param change			-	The number of nodes to skip in the list to make the step
	 * @return boolean			-	True if the step is an illegal step in that diagonal direction, otherwise false.
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
		int i = OthelloNodeHelper.getIndexFromCoordinates(board, xCoordinate, yCoordinate) + direction;
		boolean foundOpponent = false;
		List<Node> returnedNodes = new ArrayList<Node>();
		returnedNodes.add(nodes.get(i - direction));
		int lastXCoordinate = xCoordinate;
		while (isValidStep(board, i, direction)) {
			if (isIllegalDiagonalStep(board, lastXCoordinate, i, direction))
				return null;
			
			Node currentNode = nodes.get(i);
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
			lastXCoordinate = currentNode.getXCoordinate();
		}
		return null;
	}
}
