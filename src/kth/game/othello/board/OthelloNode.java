package kth.game.othello.board;

public class OthelloNode implements Node {
	
	private String id;
	private String occupantPlayerId;
	private int xCoordinate;
	private int yCoordinate;
	private boolean marked;

	public OthelloNode (int xCoordinate, int yCoordinate) {
		this.id = xCoordinate + ":" + yCoordinate;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.marked = false;
	}
	
	public OthelloNode (int xCoordinate, int yCoordinate, String occupantPlayerId) {
		this.id = xCoordinate + ":" + yCoordinate;
		this.occupantPlayerId = occupantPlayerId;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.marked = true;
	}
	
	public void setOccupantPlayerId(String occupantPlayerId) {
		this.occupantPlayerId = occupantPlayerId;
	}
	
	public void setMarked() {
		this.marked = true;
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getOccupantPlayerId() {
		return occupantPlayerId;
	}

	@Override
	public int getXCoordinate() {
		return xCoordinate;
	}

	@Override
	public int getYCoordinate() {
		return yCoordinate;
	}

	@Override
	public boolean isMarked() {
		return marked;
	}

}
