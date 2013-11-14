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
	
	@Override
	public List<Node> getNodes() {
		return nodes;
	}
	
	public void setOccupiedNode(int xCoordinate, int yCoordinate, String playerId) {
		int index = 8*xCoordinate + yCoordinate;
		OthelloNode modifiedNode = (OthelloNode)nodes.get(index);
		modifiedNode.setOccupantPlayerId(playerId);
		nodes.set(index, modifiedNode);
	}
	
	public String toString() {
		StringBuilder text = new StringBuilder();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Node currentNode = nodes.get(8*i+j);
				text.append(currentNode.getOccupantPlayerId() + " ");
			}
			text.append("\n");
		}
		return text.toString();
	}
}
