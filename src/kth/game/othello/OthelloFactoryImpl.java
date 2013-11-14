package kth.game.othello;

import kth.game.othello.board.OthelloBoard;
import kth.game.othello.player.HumanPlayer;

public class OthelloFactoryImpl implements OthelloFactory {
	
	@Override
	public Othello createComputerGameOnClassicalBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Othello createHumanGameOnOriginalBoard() {
		HumanPlayer p1 = new HumanPlayer("0");
		HumanPlayer p2 = new HumanPlayer("1");
		OthelloBoard board = new OthelloBoard();
		OthelloImpl othello = new OthelloImpl(p1, p2, board);
		othello.start();
		
		return null;
	}

	@Override
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
