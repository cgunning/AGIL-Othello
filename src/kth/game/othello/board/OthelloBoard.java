package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an OthelloBoard
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
public class OthelloBoard implements Board {

	List<Node> nodes;
	private int dimension;
	
	/**
	 * Creates a square board of specified dimension. Results in a board of size dimension*dimension
	 * @param dimension		-	the dimension of the board
	 */
	public OthelloBoard(int dimension) {
		this.dimension = dimension;
		nodes = new ArrayList<Node>();
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				nodes.add(new OthelloNode(i, j));
			}
		}
	}
	
	/**
	 * Creates a new board from an old board with the specified node marked
	 * @param board			-	the old board
	 * @param xCoordinate	-	the x coordinate of the node to be marked
	 * @param yCoordinate	-	the y coordinate of the node to be marked
	 * @param playerId		-	the player ID of the player that marks the node
	 */
	
	@Override
	public List<Node> getNodes() {
		return nodes;
	}
	
	/**
	 * Gets the dimension of the board
	 * @return dimension	-	the dimension of the board
	 */
	int getDimension() {
		return dimension;
	}
}
