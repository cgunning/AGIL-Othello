package kth.game.othello;

import java.util.List;
import java.util.Random;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

/**
 * This class represents an Othello game.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class OthelloImpl implements Othello {

	private OthelloMoveHandler moveHandler;
	private OthelloPlayerHandler playerHandler;
	private OthelloNodeHelper nodeHelper;
	private OthelloBoardHandler boardHandler;

	/**
	 * Creates an Othello game
	 * 
	 * @param blackPlayer
	 *            The black player
	 * @param whitePlayer
	 *            The white player
	 * @param board
	 *            Board to be played at
	 */
	public OthelloImpl(Player blackPlayer, Player whitePlayer, Board board) {
		playerHandler = new OthelloPlayerHandler(blackPlayer, whitePlayer);
		nodeHelper = new OthelloNodeHelper();
		boardHandler = new OthelloBoardHandler(board);
		moveHandler = new OthelloMoveHandler(nodeHelper, boardHandler);
	}

	@Override
	public Board getBoard() {
		return boardHandler.getBoard();
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return moveHandler.getNodesToSwap(
				playerHandler.getPlayerFromId(playerId), nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		return playerHandler.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return playerHandler.getPlayers();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return moveHandler
				.hasValidMove(playerHandler.getPlayerFromId(playerId));
	}

	@Override
	public boolean isActive() {
		for (Player player : playerHandler.getPlayers()) {
			if (moveHandler.hasValidMove(player))
				return true;
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return moveHandler.isMoveValid(playerHandler.getPlayerFromId(playerId),
				nodeId);
	}

	@Override
	public List<Node> move() {
		List<Node> nodesToSwap = moveHandler.move(playerHandler
				.getPlayerInTurn());
		playerHandler.changePlayerInTurn();
		return nodesToSwap;

	}

	@Override
	public List<Node> move(String playerId, String nodeId)
			throws IllegalArgumentException {
		List<Node> nodesToSwap = moveHandler.move(
				playerHandler.getPlayerFromId(playerId), nodeId);
		playerHandler.changePlayerInTurn();
		return nodesToSwap;
	}

	@Override
	public void start() {
		Random rnd = new Random();
		Player player = playerHandler.getRandomPlayer();
		start(player.getId());
	}

	@Override
	public void start(String playerId) {
		playerHandler.setPlayerInTurn(playerHandler.getPlayerFromId(playerId));
		boardHandler.initBoard(playerHandler.getPlayerInTurn(),
				playerHandler.getOpponent(playerHandler.getPlayerInTurn()));
	}
}
