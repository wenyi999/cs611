//This is an abstract concept of game, which contains abstract methods to be implemented in specific games.
public abstract class Game {
	private String name;
	private int gameId;
	private String rule;
	
	//no-argument constructor to set default value
	public Game() {
		this.name = "new game";
		this.gameId = 0;
		this.rule = "just for fun";
	}
	
	public Game(String n, int gid) {
		this.name = n;
		this.gameId = gid;
		this.rule = "waiting for description";
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setGameId(int id) {
		if(id > 0) {
			this.gameId = id;
		}else {
			System.out.println("the ID of a game must be postive!");
		}
	}
	
	public int getGameId() {
		return this.gameId;
	}
	
	public void setRule(String r) {
		this.rule = r;
	}
	
	public String getRule() {
		return this.rule;
	}
	
	// methods waiting to be implemented
	public abstract void startGame();
	
	public abstract void endGame();
	
	public abstract void showResult();

}
