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
	private static int UP = -8, LEFT = -1, RIGHT = 1, DOWN = 8, UP_LEFT = -9, UP_RIGHT = -7, DOWN_LEFT = 7, DOWN_RIGHT = 9;
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
			if(!node.isMarked() && isMoveValid(playerId, node.getId())) {
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
		if(nodes.get(8*xCoordinate + yCoordinate).isMarked()) {
			return false;
		}
		
		for(int change : changes) {
			if(isMoveValidInDirection(xCoordinate, yCoordinate, playerId, change))
				return true;
		}
		
		return false;
	}
	
	private boolean isMoveValidInDirection(int xCoordinate, int yCoordinate, String playerId, int change) {
		List<Node> nodes = board.getNodes();
		
		int i = 8*xCoordinate + yCoordinate + change;
		
		boolean foundOpponent = false;
		int lastX = xCoordinate;
		
		while(i < nodes.size() && i >= 0 && (Math.abs(change) > 1 || ((i + 1)%8 != 0 && change == LEFT) || ((i)%8 != 0 && change == RIGHT))) { //0,7 //i=8
			if(i/8 == lastX && (change == -7 || change == 7)) 
				return false;
			if(Math.abs(i/8 - lastX) == 2 && (change == -9 || change == 9))
				return false;
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
			lastX = currentNode.getXCoordinate();
			
		}
		
		return false;
		
	}
	
	@Override
	public List<Node> move() {
		List<Node> nodes = board.getNodes();
		for(Node node : nodes) {
			if(isMoveValid(playerInTurnId, node.getId())) {
				List<Node> nodesToSwap = getNodesToSwap(playerInTurnId, node.getId());
				for(Node nodeToSwap : nodesToSwap) {
					this.board = new OthelloBoard(this.board, nodeToSwap.getXCoordinate(), nodeToSwap.getYCoordinate(), playerInTurnId);
				}
				playerInTurnId = getNextPlayerId(playerInTurnId);
				System.out.println(board.toString());
				return nodesToSwap;
			}
		}
		playerInTurnId = getNextPlayerId(playerInTurnId);
		return null;
		
	}
	
	private List<Node> moveHelper(int xCoordinate, int yCoordinate, String playerId, int change) {
		List<Node> nodes = board.getNodes();
		int i = 8*xCoordinate + yCoordinate + change;
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

	@Override
	public List<Node> move(String playerId, String nodeId)
			throws IllegalArgumentException {
		List<Node> nodesToSwap = getNodesToSwap(playerId, nodeId);
		for(Node node : nodesToSwap) {
			this.board = new OthelloBoard(this.board, node.getXCoordinate(), node.getYCoordinate(), playerId);
		}
		playerInTurnId = getNextPlayerId(playerInTurnId);
		System.out.println(board.toString());
		return nodesToSwap;
	}

	private void initBoard(String blackPlayerId, String whitePlayerId) {
		this.board = new OthelloBoard(this.board, 3, 3, blackPlayerId);
		this.board = new OthelloBoard(this.board, 3, 4, whitePlayerId);
		this.board = new OthelloBoard(this.board, 4, 3, whitePlayerId);
		this.board = new OthelloBoard(this.board, 4, 4, blackPlayerId);
	}
	
	@Override
	public void start() {
		initBoard("0","1");
	}

	@Override
	public void start(String playerId) {
		playerInTurnId = playerId;
		String opponentPlayer = (playerInTurnId == "1") ? "0" : "1";
		initBoard(playerInTurnId, opponentPlayer);
	}
	
	private Player getPlayerFromId(String playerId) {
		for(Player player : players)
			if(player.getId().equals(playerId))
				return player;
		System.out.println("WAT?");
		return null;
	}
	
	private String getNextPlayerId(String currentPlayerId) {
		for(Player player : players)
			if(!player.getId().equals(currentPlayerId))
				return player.getId();
		
		return null;
	}
}
