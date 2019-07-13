/*
	Player is a spacecraft that is able to shoot.
*/

public class Player extends SpaceObjet {
	
	private boolean hit;
		
	public Player() {
		this(1,1,1,90);
	}
	public Player(double pos_x, double pos_y) {
		this(pos_x,pos_y,1,90);
	}
	public Player(double pos_x, double pos_y, double speed, double direction, boolean hit) {
		super(pos_x, pos_y, speed, direction);
	}
	
	public void shoot() {
		Laser laser;
		laser = new laser();
	}
	
}