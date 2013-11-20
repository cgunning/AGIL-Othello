package kth.game.othello.demo;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.OthelloFactoryImpl;

public class Demo {
	public static void main(String[] args) {
		
	}
	
	private void demo1() {
		OthelloFactory othelloFactory = new OthelloFactoryImpl();
		Othello othello = othelloFactory.createComputerGameOnClassicalBoard();
		
		
	}
	
	private void demo2() {
		
	}
}
