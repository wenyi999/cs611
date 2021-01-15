public abstract class RPGGame<T extends GeneralMap> extends Game{
	
	// maybe a class GeneralMap which is extended by LegendMap
	private T mainMap;
	
	
	public RPGGame() {
		super("A RPG game",5);
	}
	
	public RPGGame(String n) {
		super(n,5);
	}
	
	public void setMainMap(T m) {
		this.mainMap = m;
	}
	
	public T getMainMap() {
		return this.mainMap;
	}

	
	public abstract void startGame();



	public abstract void endGame();



	public abstract void showResult();


}
