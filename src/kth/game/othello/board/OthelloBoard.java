package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

public class OthelloBoard implements Board {

	List<Node> nodes;
	
	public OthelloBoard() {
		nodes = new ArrayList<Node>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				nodes.add(new OthelloNode(i, j));
			}
		}
	}
	
	public OthelloBoard(Board board, int xCoordinate, int yCoordinate, String playerId) {
		int index = 8*xCoordinate + yCoordinate;
		List<Node> oldNodes = board.getNodes();
		oldNodes.set(index, new OthelloNode(xCoordinate, yCoordinate, playerId));
		nodes = oldNodes;
	}
	
	@Override
	public List<Node> getNodes() {
		return nodes;
	}
	
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Node currentNode = nodes.get(8*i+j);
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
