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
	private static int[] changes = {9, 7, -9, -7, 1, 8, -1, -8};
	
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
		List<Node> nodes = board.getNodes();
		String[] strCoordinates = nodeId.split(":");
		int xCoordinate = Integer.parseInt(strCoordinates[0]);
		int yCoordinate = Integer.parseInt(strCoordinates[1]);
		List<Node> returnedNodes = new ArrayList<Node>();
		for(int change : changes) {
			List<Node> swappedNodes = moveHelper(xCoordinate, yCoordinate, playerId, change);
			if(swappedNodes != null) {
				returnedNodes.addAll(swappedNodes);
				
			}
		}
		return returnedNodes;
	}

	@Override
	public Player getPlayerInTurn() {
		return getPlayerFromId(playerInTurnId);
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		List<Node> nodes = board.getNodes();
		for(Node node : nodes) {
			if(isMoveValid(playerInTurnId, node.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isActive() {
		for(Player player : players) {
			if(hasValidMove(player.getId()));
				return true;
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {

		List<Node> nodes = board.getNodes();
		String[] strCoordinates = nodeId.split(":");

		int xCoordinate = Integer.parseInt(strCoordinates[0]);
		int yCoordinate = Integer.parseInt(strCoordinates[1]);
		
		
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
		List<Node> nodes = board.getNodes();
		List<Node> returnedNodes = new ArrayList<Node>();
		for(Node node : nodes) {
			if(isMoveValid(playerInTurnId, node.getId())) {
				return getNodesToSwap(playerInTurnId, node.getId());
			}
		}
		
		return null;
		
	}
	
private List<Node> moveHelper(int xCoordinate, int yCoordinate, String playerId, int change) {
		List<Node> nodes = board.getNodes();
		int maxSteps = Math.min(xCoordinate, yCoordinate);
		int i = 8*xCoordinate + yCoordinate + change;
		boolean foundOpponent = false;
		List<Node> returnedNodes = new ArrayList<Node>();
		returnedNodes.add(nodes.get(i - change));
		
		while(i < nodes.size() && i >= 0 && (i + 1)%8 != 0) {
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
		}
		return null;
	}

	@Override
	public List<Node> move(String playerId, String nodeId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stubanObject
		return null;
	}

	private void initBoard( String blackPlayerId, String whitePlayerId) {
		List<Node> nodes = board.getNodes();

		((OthelloBoard)board).setOccupiedNode(4, 4, blackPlayerId);
		((OthelloBoard)board).setOccupiedNode(4, 5, whitePlayerId);
		((OthelloBoard)board).setOccupiedNode(5, 4, whitePlayerId);
		((OthelloBoard)board).setOccupiedNode(5, 5, blackPlayerId);
		System.out.println(board.toString());
	}
	
	@Override
	public void start() {
		initBoard("0","1");
	}

	@Override
	public void start(String playerId) {
		playerInTurnId = playerId;
		String opponentPlayer = (playerInTurnId == "0") ? "1" : "0";
		initBoard(playerInTurnId, opponentPlayer);
	}
	
	private Player getPlayerFromId(String playerId) {
		for(Player player : players)
			if(player.getId().equals(playerId))
				return player;
		return null;
	}
}
