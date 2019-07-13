package com.gamecodeschool.asteroidsfs;
/*
	Player is a spacecraft that is able to shoot.
*/

public class Player extends SpaceObjet {
	
	private boolean hit;
		
	public Player() {
		this(1,1,1,1,false);
	}
	public Player(double pos_x, double pos_y) {
		this(pos_x,pos_y,1,1,false);
	}
	public Player(double pos_x, double pos_y, double velocity_x, double velocity_y, boolean hit) {
		super(pos_x, pos_y, velocity_x, velocity_y);
		this.hit = hit;
	}
	
	public void shoot() {
		Laser laser;
		laser = new laser();
	}
	
}
