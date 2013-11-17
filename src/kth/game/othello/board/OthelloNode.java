package kth.game.othello.board;

/**
 * The responsibility of a node is to keep information of which player is occupying it.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
public class OthelloNode implements Node {
	
	private String id;
	private String occupantPlayerId;
	private int xCoordinate;
	private int yCoordinate;
	private boolean marked;
	
	/**
	 * Creates an unmarked node
	 * @param xCoordinate	-	the x coordinate of the node
	 * @param yCoordinate	-	the y coordinate of the node
	 */
	public OthelloNode (int xCoordinate, int yCoordinate) {
		this.id = xCoordinate + ":" + yCoordinate;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.marked = false;
	}
	
	/**
	 * Creates a marked node
	 * @param xCoordinate		-	the x coordinate of the node
	 * @param yCoordinate		-	the y coordinate of the node
	 * @param occupantPlayerId	-	the playerId of the player occupying that node
	 */
	public OthelloNode (int xCoordinate, int yCoordinate, String occupantPlayerId) {
		this.id = xCoordinate + ":" + yCoordinate;
		this.occupantPlayerId = occupantPlayerId;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
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
