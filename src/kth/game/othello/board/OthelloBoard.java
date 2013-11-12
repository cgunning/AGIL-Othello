package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

public class OthelloBoard implements Board {

	List<Node> nodes;
	
	public OthelloBoard() {
		nodes = new ArrayList<Node>();
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				nodes.add(new OthelloNode(i, j));
			}
		}
	}
	
	@Override
	public List<Node> getNodes() {
		return nodes;
	}

}
