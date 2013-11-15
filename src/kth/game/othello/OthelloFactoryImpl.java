package kth.game.othello;

import kth.game.othello.board.OthelloBoard;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;

public class OthelloFactoryImpl implements OthelloFactory {
	
	@Override
	public Othello createComputerGameOnClassicalBoard() {
		ComputerPlayer p1 = new ComputerPlayer("0");
		ComputerPlayer p2 = new ComputerPlayer("1");
		OthelloBoard board = new OthelloBoard();
		OthelloImpl othello = new OthelloImpl(p1, p2, board);
		return othello;
	}

	@Override
	public Othello createHumanGameOnOriginalBoard() {
		HumanPlayer p1 = new HumanPlayer("0");
		HumanPlayer p2 = new HumanPlayer("1");
		OthelloBoard board = new OthelloBoard();
		OthelloImpl othello = new OthelloImpl(p1, p2, board);
		return othello;
	}

	@Override
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		HumanPlayer p1 = new HumanPlayer("0");
		ComputerPlayer p2 = new ComputerPlayer("1");
		OthelloBoard board = new OthelloBoard();
		OthelloImpl othello = new OthelloImpl(p1, p2, board);
		return othello;
	}

}
