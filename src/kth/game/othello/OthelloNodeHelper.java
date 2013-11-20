package kth.game.othello;

import kth.game.othello.board.Board;

/**
 * Help class for Othello nodes.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class OthelloNodeHelper {

	/**
	 * Gets the index of a node from its coordinates
	 * 
	 * @param board
	 *            - The board that is played on
	 * @param xCoordinate
	 *            - The x coordinate of the node
	 * @param yCoordinate
	 *            - The y coordinate of the node
	 * @return The index of the node
	 */
	int getIndexFromCoordinates(Board board, int xCoordinate, int yCoordinate) {
		int dimension = (int) Math.sqrt(board.getNodes().size());
		return dimension * xCoordinate + yCoordinate;
	}

	/**
	 * Gets the coordinates for a node from the ID
	 * 
	 * @param nodeId
	 *            - The ID of the node
	 * @return The coordinates of the node on the form { x, y }
	 */
	int[] getCoordinatesFromId(String nodeId) {
		int[] coordinates = new int[2];
		String[] strCoordinates = nodeId.split(":");
		coordinates[0] = Integer.parseInt(strCoordinates[0]);
		coordinates[1] = Integer.parseInt(strCoordinates[1]);
		return coordinates;
	}

	/**
	 * Checks if an ID of a node is a valid ID
	 * 
	 * @param nodeId
	 *            - The ID of the node
	 * @return True if the ID is valid, false otherwise
	 */
	boolean isValidNodeId(String nodeId) {
		int xCoordinate = getCoordinatesFromId(nodeId)[0];
		int yCoordinate = getCoordinatesFromId(nodeId)[1];
		if (xCoordinate >= 0 && xCoordinate < 8 && yCoordinate >= 0
				&& yCoordinate < 8)
			return true;
		return false;
	}
}
