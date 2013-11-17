package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

public class OthelloBoard implements Board {

	List<Node> nodes;
	private int dimension;
	
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
	public OthelloBoard(Board board, int xCoordinate, int yCoordinate, String playerId) {
		dimension = ((OthelloBoard)board).getDimension();
		int index = dimension*xCoordinate + yCoordinate;
		List<Node> oldNodes = board.getNodes();
		oldNodes.set(index, new OthelloNode(xCoordinate, yCoordinate, playerId));
		nodes = oldNodes;
	}
	
	@Override
	public List<Node> getNodes() {
		return nodes;
	}
	
	int getDimension() {
		return dimension;
	}
	
	// TODO - Removezzz
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				Node currentNode = nodes.get(dimension*i+j);
				if(currentNode.getOccupantPlayerId() == null) {
					text.append("- ");
				}
				else {
					text.append(currentNode.getOccupantPlayerId() + " ");
				}
			}
			text.append("\n");
		}
		return text.toString();
	}
}
