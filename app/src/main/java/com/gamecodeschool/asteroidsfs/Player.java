package com.gamecodeschool.asteroidsfs;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

public class Player {

	private RectF mRect;
	private float mLength;
	private float mHeight;
	private float mXCoord;
	private float mYCoord;
	private float maxXCoord;
	private float maxYCoord;
	private float mXVelocity;
	private float mYVelocity;
	float dx;
	float dy;
	private float mShipWidth;
	private float mShipHeight;
	private int lives = 3;
	private int score = 0;
	private float mPlayerSpeed;
	private int degree;
	private Point centerCoords;
	private float movementMagnitude;

	private boolean hit;
	// 0 = stopped, 1 = clockwise, 2 = counter-clockwise
	private int rotationStates[] = { 0, 1, 2 };
	private int rotateState;

	// true if player is moving, false if player is stationary
	private boolean moveState;

	// A bitmap for each direction the ship can face
	private Bitmap mBitmapHeadUp;
	private Bitmap mBitmapHeadCurrent;

	//Laser Gun
	ArrayList<Laser> lasers;

	Player(int screenX, int screenY) {

		maxXCoord = screenX;
		maxYCoord = screenY;

		// Configure the size of the player's
		// ship based on the screen resolution

		mLength = screenX / 25;
		mHeight = screenY / 25;

		// start player ship location at center
		// of the screen
		mXCoord = screenX / 2;
		mYCoord = (screenY / 2);

		// Intialize mRect based on the size and position

		mRect = new RectF(mXCoord, mYCoord, mXCoord + mLength - 15, mYCoord + mLength - 15);
		// float rectCenterX = mRect.centerX();
		// float rectCenterY = mRect.centerY();
		centerCoords = new Point((int) (mRect.left + 0.5 * (mRect.right - mRect.left)),
				(int) (mRect.top + 0.5 * (mRect.bottom - mRect.top)));


		// RectF oval = new RectF(width/2 - radius, width/2 - radius,
		// width/2 + radius, width/2 + radius);
		// canvas.drawArc(oval, 0, 360, false, paint1);

		// Configure the speed of the ship
		// This code means the ship can cover the
		// width of the screen in 2 seconds
		mPlayerSpeed = screenX / 2;

		mXVelocity = 0;
		mYVelocity = 0;
		movementMagnitude = 0;


		lasers = new ArrayList<Laser>();
	}

	public RectF getHitbox() {
		return mRect;
	}
	public ArrayList<Laser> getLasers(){
		return lasers;
	}
	// Update the Player- Called each frame/loop
	// Update arguments within the AsteroidsGame class

	void update(long fps, Context ourContext, int blockSize, int x, int y) {
		if(rotateState == 1){
			if(degree < 0){
				degree = 360;
			}
			degree -= 5;
		} else if (rotateState == 2) {
			if (degree > 360) {
				degree = 0;
			}
			degree += 5;
		} else {
			degree = degree;
		}

		if (moveState == true) {
			movementMagnitude += 1.1f;
			this.mXVelocity = movementMagnitude * (float) Math.cos(degree * 0.0174533);
			this.mYVelocity = movementMagnitude * (float) Math.sin(degree * 0.0174533);
			// + mXVelocity/fps;
			// + mYVelocity/fps;
			this.dx += mXVelocity / fps;
			this.dy += mYVelocity / fps;
			// mRect.offset(this.dx, this.dy);
			mRect.offset(this.dx, this.dy);

			centerCoords = new Point((int)(mRect.left+0.5*(mRect.right-mRect.left)),
					(int)(mRect.top+0.5*(mRect.bottom-mRect.top)));
			if(centerCoords.x > maxXCoord){
				centerCoords.x = (int)maxXCoord;
			}
			else if(centerCoords.x < 0){
				centerCoords.x = 0;
			}
			else if(centerCoords.y > maxYCoord){
				centerCoords.y = (int)maxYCoord;
			}
			else if(centerCoords.y < 0){
				centerCoords.y = 0;
			}


			Log.d("player: ", "degree: " + degree);
			Log.d("player: ", "value of mXVelocity: " + mXVelocity);
			Log.d("player: ", "value of mYVelocity: " + mYVelocity);
			Log.d("player: ", "value of dx: " + dx);
			Log.d("player: ", "value of dy: " + dy);
			Log.d("player: ", "value of mRect.left: " + mRect.left);

			Log.d("player: ", "value of mRect.top: " + mRect.top);
			Log.d("player: ", "value of shipCenter.x: " + centerCoords.x);
			Log.d("player: ", "value of shipCenter.y: " + centerCoords.y)
    }
		else{
			this.mXVelocity = 0;
			this.mYVelocity = 0;
			this.dx = 0;
			this.dy = 0;
			movementMagnitude = 0;

		}

		// setRotate() function?
		// Accelerate()
	}

	void setMoveState(boolean playerMove) {
		moveState = playerMove;
	}

	void setRotationState(int playerRotate) {
		rotateState = rotationStates[playerRotate];
	}

	public Point getCenterCoords() {
		return this.centerCoords;
	}

	public int getDegree() {
		return this.degree;
	}

	public Bitmap getBitMap() {
		return this.mBitmapHeadCurrent;
	}

	public float getPlayerLength() {
		return this.mLength;
	}

	public float getPlayerHeight() {
		return this.mHeight;
	}

	public void shoot(int x, int y) {
		lasers.add(new Laser(new PointF(x/2,y/2),y/100, y/100, -(y/5), (y/5)));

//		for(int i = 0; i < lasers.size(); i++) {
//			lasers.get(i).update(fps, x, y);
//		}
	}
}
