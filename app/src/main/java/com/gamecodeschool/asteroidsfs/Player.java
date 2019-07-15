package com.gamecodeschool.asteroidsfs;

import android.graphics.RectF;
/*
	Player is a spacecraft that is able to shoot.
*/


public class Player extends SpaceObject {

	private int screenX;
	private int screenY;
	private RectF mRect;
	private float mLength;
	private float mHeight;
	private float mXCoord;
	private float mYCoord;
	private float mXVelocity;
	private float mYVelocity;
	private float mShipWidth;
	private float mShipHeight;
	private int lives = 3;
	private int score = 0;
	private float mShipSpeed;

	private boolean hit;

	public Player(int x, int y){
		screenX = x;
		screenY = y;

		// Configure the size of the player's
		// ship based on the screen resolution
		mLength = screenX / 25;
		mHeight = screenY / 30;

		// start player ship location at center
		// of the screen
		mXCoord = screenX / 2;
		mYCoord = (screenY / 2);

		// Intialize mRect based on the size and position
		mRect = new RectF(mXCoord, mYCoord,
				mXCoord + mLength,
				mYCoord + mHeight);

		// Configure the speed of the ship
		// This code means the ship can cover the
		// width of the screen in 2 seconds
		mShipSpeed = screenX / 2;
	}



	RectF getRect(){
		return mRect;
	}


	// Update the bat- Called each frame/loop
	void update(long fps){

	}

//	public Player() {
//		this(1,1,1,1,false);
//	}
//	public Player(double pos_x, double pos_y) {
//		this(pos_x,pos_y,1,1,false);
//	}
//	public Player(double pos_x, double pos_y, double velocity_x, double velocity_y, boolean hit) {
//		super(pos_x, pos_y, velocity_x, velocity_y);
//		this.hit = hit;
//	}
	
	public void shoot() {
		Laser laser;
		//laser = new Laser();
	}
	
}
