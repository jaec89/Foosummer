package com.gamecodeschool.asteroidsfs;
/*
	Spacecraft
*/

public class SpaceObject {
	
	private double pos_x;	// position x coordinate
	private double pos_y;	// position y coordinate
	private double velocity_x;	// m/s?
	private double velocity_y;	// int or double? spaceship is so small int will probably do for 360 degrees
// 	private boolean hit;	// Do we want this in this class???
	
	
	
	public SpaceObject() {	// should we allow a spacecraft to be initialized without a position?
		this(1,1,1,1);
	}
	public SpaceObject(double pos_x, double pos_y) {
		this(pos_x,pos_y,1,1);	// Starting with velocity = 1 for now
	}
	public SpaceObject(double pos_x, double pos_y, double velocity_x, double velocity_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.velocity_x = velocity_x;
		this.velocity_y = velocity_y;
		//this.hit = hit;
	}
	
	
	
	public void setPos_x(double pos_x) {
		this.pos_x = pos_x;
	}
	public void setPos_y(double pos_y) {
		this.pos_y = pos_y;
	}
	public void setVelocity_x() {
		this.velocity_x = velocity_x;
	}
	public void setVelocity_y(int direction) {
		this.direction = direction;
	}
// 	public void setHit(boolean hit) {
// 		this.hit = hit;
// 	}
	
	
	
	public double getPos_x() {
		return pos_x;
	}
	public double getPos_y() {
		return pos_y;
	}
	public double getVelocity_x() {
		return speed;
	}
	public int getVelocity_y() {
		return direction;
	}
// 	public boolean getHit() {
// 		return hit;
// 	}
	
    
    
// 	public void draw() {
// 		
//     }


// 	public void move() {
// 		
//     }
	
	
}