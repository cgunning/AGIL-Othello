package kth.game.othello;

import kth.game.othello.player.HumanPlayer;

public class OthelloFactoryImpl implements OthelloFactory {
	
	@Override
	public Othello createComputerGameOnClassicalBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Othello createHumanGameOnOriginalBoard() {
		HumanPlayer p1 = new HumanPlayer("Black");
		HumanPlayer p2 = new HumanPlayer("White");
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
