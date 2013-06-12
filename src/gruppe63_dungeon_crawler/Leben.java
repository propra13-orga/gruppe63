package gruppe63_dungeon_crawler;

public class Leben extends Item {
	
	public static final int SIZE = 30;
	
	private Player player;
	
	
	public Leben (int x, int y, int xDim, int yDim, Player play) {
		super(x, y,/* xDim*/SIZE, /*yDim*/SIZE,"Ein Leben" );
		this.player = play;
	
	}
	
	public void pickUp(){
		
		System.out.println("Du hast ein "+ name +"erhalten!");
		player.addItem(this);
		remove= true;
	
	}

	public void update(){
		if (Physics.collision(this, player))
			pickUp();
	}
	
}
