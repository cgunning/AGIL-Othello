package kth.game.othello.player;

/**
 * Abstract class for player
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
public class AbstractPlayer implements Player {
	
	private static Integer currentId = 0;
	private String id;
	private String name;
	private Type type;
	
	/**
	 * Creates a new abstract player
	 * @param name Name for the player
	 * @param type Type of the player
	 */
	public AbstractPlayer(String name, Type type) {
		
		this.name = name;
		this.id = currentId.toString();
		this.type = type;
		currentId += 1;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

}
