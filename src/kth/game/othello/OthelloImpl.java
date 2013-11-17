package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;
import kth.game.othello.player.Player;

public class OthelloImpl implements Othello {
	
	private Board board;
	private List<Player> players;
	private String playerInTurnId;
	
	public OthelloImpl(Player blackPlayer, Player whitePlayer, Board board) {
		//TODO
		int dimension = (int)Math.sqrt(board.getNodes().size());
		
		players = new ArrayList<Player>();
		players.add(blackPlayer);
		players.add(whitePlayer);
		this.board = board;
	}
	
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return OthelloMoveHandler.getNodesToSwap(board, playerId, nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		return OthelloPlayerHandler.getPlayerFromId(playerInTurnId, players);
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return OthelloMoveHandler.hasValidMove(this.board, playerId);
	}

	@Override
	public boolean isActive() {
		for(Player player : players) {
			if(OthelloMoveHandler.hasValidMove(this.board, player.getId()))
				return true;
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return OthelloMoveHandler.isMoveValid(this.board, playerId, nodeId);
	}
	
	
	@Override
	public List<Node> move() {
		List<Node> nodesToSwap = OthelloMoveHandler.move(this.board, playerInTurnId);
		this.board = OthelloBoardHandler.updateMovesOnBoard(this.board, nodesToSwap, playerInTurnId);
		playerInTurnId = OthelloPlayerHandler.getOpponentId(playerInTurnId, players);
		System.out.println(board.toString());
		return nodesToSwap;
	}

	@Override
	public List<Node> move(String playerId, String nodeId)
			throws IllegalArgumentException {
		if(!OthelloMoveHelper.isValidNode(nodeId) || OthelloPlayerHandler.getPlayerFromId(playerId, players) == null || !playerId.equals(playerInTurnId))
			throw new IllegalArgumentException();
		List<Node> nodesToSwap = OthelloMoveHandler.move(board, playerId, nodeId);
		this.board = OthelloBoardHandler.updateMovesOnBoard(this.board, nodesToSwap, playerId);
		playerInTurnId = OthelloPlayerHandler.getOpponentId(playerInTurnId, players);
		System.out.println(board.toString());
		return nodesToSwap;
	}

	@Override
	public void start() {
		// get random player
		String playerId = players.get(0).getId();
		start(playerId);
	}

	@Override
	public void start(String playerId) {
		playerInTurnId = playerId;
		this.board = OthelloBoardHandler.initBoard(board, playerId, OthelloPlayerHandler.getOpponentId(playerId, players));
	}
}
