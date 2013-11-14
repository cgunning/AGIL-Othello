package kth.game.othello.player;

public class AbstractPlayer implements Player {
	
	private static Integer currentId = 0;
	
	private String id;
	private String name;
	private Type type;
	
	public AbstractPlayer(String name, Type type) {
		this.name = name;
		this.id = currentId.toString();
		this.type = type;
		currentId++;
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
