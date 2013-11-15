package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;

class OthelloBoardHandler {
	
	static Board initBoard(Board board, String blackPlayerId, String whitePlayerId) {
		board = new OthelloBoard(board, 3, 3, blackPlayerId);
		board = new OthelloBoard(board, 3, 4, whitePlayerId);
		board = new OthelloBoard(board, 4, 3, whitePlayerId);
		board = new OthelloBoard(board, 4, 4, blackPlayerId);
		return board;
	}
	
	static Board updateMovesOnBoard(Board board, List<Node> nodesToSwap, String playerId) {
		for(Node node : nodesToSwap) {
			board = new OthelloBoard(board, node.getXCoordinate(), node.getYCoordinate(), playerId);
		}
		return board;
	}
	
}
