package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;

class OthelloMoveHandler {
	
	private static int UP = -8, LEFT = -1, RIGHT = 1, DOWN = 8, UP_LEFT = -9, UP_RIGHT = -7, DOWN_LEFT = 7, DOWN_RIGHT = 9;
	private static int[] changes = {9, 7, -9, -7, 1, 8, -1, -8};
	
	static List<Node> getNodesToSwap(Board board, String playerId, String nodeId) {
		String[] strCoordinates = nodeId.split(":");
		int xCoordinate = Integer.parseInt(strCoordinates[0]);
		int yCoordinate = Integer.parseInt(strCoordinates[1]);
		List<Node> returnedNodes = new ArrayList<Node>();
		for(int change : changes) {
			List<Node> swappedNodes = moveHelper(board, xCoordinate, yCoordinate, playerId, change);
			if(swappedNodes != null) {
				returnedNodes.addAll(swappedNodes);
				
			}
		}
		return returnedNodes;
	}
	
	static boolean hasValidMove(Board board, String playerId) {
		List<Node> nodes = board.getNodes();
		for(Node node : nodes) {
			if(!node.isMarked() && isMoveValid(board, playerId, node.getId())) {
					return true;
			}
		}
		return false;
	}
	
	
	static boolean isMoveValid(Board board, String playerId, String nodeId) {
		List<Node> nodes = board.getNodes();
		String[] strCoordinates = nodeId.split(":");
		int xCoordinate = Integer.parseInt(strCoordinates[0]);
		int yCoordinate = Integer.parseInt(strCoordinates[1]);
		if(nodes.get(getIndexFromCoordinates(xCoordinate, yCoordinate)).isMarked()) {
			return false;
		}
		
		for(int change : changes) {
			if(moveHelper(board, xCoordinate, yCoordinate, playerId, change) != null)
				return true;
		}
		
		return false;
	}
	
	static List<Node> move(Board board, String playerInTurnId) {
		List<Node> nodes = board.getNodes();
		for(Node node : nodes) {
			if(isMoveValid(board, playerInTurnId, node.getId())) {
				List<Node> nodesToSwap = getNodesToSwap(board, playerInTurnId, node.getId());
				return nodesToSwap;
			}
		}
		return new ArrayList<Node>();
	}
	
	static List<Node> move(Board board, String playerId, String nodeId)
			throws IllegalArgumentException {
		List<Node> nodesToSwap = getNodesToSwap(board, playerId, nodeId);
		return nodesToSwap;
	}
	
	private static List<Node> moveHelper(Board board, int xCoordinate, int yCoordinate, String playerId, int change) {
		List<Node> nodes = board.getNodes();
		int i = getIndexFromCoordinates(xCoordinate, yCoordinate) + change;
		boolean foundOpponent = false;
		List<Node> returnedNodes = new ArrayList<Node>();
		returnedNodes.add(nodes.get(i - change));
		int lastX = xCoordinate;
		while(i < nodes.size() && i >= 0 && (Math.abs(change) > 1 || ((i + 1)%8 != 0 && change == LEFT) || ((i)%8 != 0 && change == RIGHT))) {
			if(i/8 == lastX && (change == -7 || change == 7)) 
				return null;
			if(Math.abs(i/8 - lastX) == 2 && (change == -9 || change == 9))//2,5   //i=12
				return null;
			Node currentNode = nodes.get(i);
			if(currentNode.isMarked()) {
				if(!foundOpponent) {
					if(!currentNode.getOccupantPlayerId().equals(playerId)) {
						foundOpponent = true;
						returnedNodes.add(currentNode);
					}
					else {
						return null;
					}
				} else if(currentNode.getOccupantPlayerId().equals(playerId)) {
					return returnedNodes;
				} else {
					returnedNodes.add(currentNode);
				}
			} else
				return null;
			i += change;
			lastX = currentNode.getXCoordinate();
		}
		return null;
	}
	
	private static int getIndexFromCoordinates(int xCoordinate, int yCoordinate) {
		return 8*xCoordinate + yCoordinate;
	}
}
