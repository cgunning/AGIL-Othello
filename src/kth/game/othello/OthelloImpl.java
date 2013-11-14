package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.OthelloBoard;
import kth.game.othello.board.OthelloNode;
import kth.game.othello.player.Player;

public class OthelloImpl implements Othello {
	
	private Board board;
	private List<Player> players;
	private String playerInTurnId;
	
	public OthelloImpl(Player p1, Player p2, Board board) {
		players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		this.board = board;
	}
	
	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerInTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {

		List<Node> nodes = board.getNodes();
		String[] strCoordinates = nodeId.split(":");

		int xCoordinate = Integer.parseInt(strCoordinates[0]);
		int yCoordinate = Integer.parseInt(strCoordinates[1]);
		int[] changes = {9, 7, -9, -7, 1, 8, -1, -8};
		
		for(int change : changes) {
			if(isMoveValidInDirection(xCoordinate, yCoordinate, playerId, change))
				return true;
		}
		
		return false;
	}
	
	private boolean isMoveValidInDirection(int xCoordinate, int yCoordinate, String playerId, int change) {
		
		List<Node> nodes = board.getNodes();
		
		int maxSteps = Math.min(xCoordinate, yCoordinate);
		
		int i = 8*xCoordinate + yCoordinate + change;
		
		boolean foundOpponent = false;
		
		while(i < nodes.size() && i >= 0 && (i + 1)%8 != 0) {
			Node currentNode = nodes.get(i);
			
			if(currentNode.isMarked()) {
				if(!foundOpponent) {
					if(!currentNode.getOccupantPlayerId().equals(playerId))
						foundOpponent = true;
					else
						return false;
				} else if(currentNode.getOccupantPlayerId().equals(playerId)) {
					return true;
				}
			} else
				return false;
			
			i += change;
			
		}
		
		return false;
		
	}
	
	@Override
	public List<Node> move() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Node> move(String playerId, String nodeId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stubanObject
		return null;
	}

	private void initBoard(String whitePlayerId, String blackPlayerId) {
		List<Node> nodes = board.getNodes();

		((OthelloBoard)board).setOccupiedNode(4, 4, blackPlayerId);
		((OthelloBoard)board).setOccupiedNode(4, 2, whitePlayerId);
		((OthelloBoard)board).setOccupiedNode(5, 4, whitePlayerId);
		((OthelloBoard)board).setOccupiedNode(5, 5, blackPlayerId);
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(String playerId) {
		playerInTurnId = playerId;
		
		
	}
	
	private Player getPlayerFromId(String playerId) {
		for(Player player : players)
			if(player.getId().equals(playerId))
				return player;
		return null;
	}
}
