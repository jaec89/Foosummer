package com.gamecodeschool.asteroidsfs;

//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.RectF;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
/*
	Player is a spacecraft that is able to shoot.
*/


public class Player {

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
	private float mPlayerSpeed;
	private RectF mRect1;
	private RectF mRect2;
	private RectF mCircle;
	private int radius = 50;
	private int degree;

	private boolean hit;
//	private boolean clockWise;
	// 0 = stopped, 1 = clockwise, 2 = counter-clockwise
	private int rotationStates[] = {0,1,2};
	private int rotateState;

	// A bitmap for each direction the ship can face
	private Bitmap mBitmapHeadUp;
	private Bitmap mBitmapHeadCurrent;

	Player(int screenX, int screenY) {

		// Configure the size of the player's
		// ship based on the screen resolution

		mLength = screenX / 15;
		mHeight = screenY / 15;


		// start player ship location at center
		// of the screen
		mXCoord = screenX / 2;
		mYCoord = (screenY / 2);

		// Intialize mRect based on the size and position
		mRect = new RectF(mXCoord, mYCoord,
				mXCoord + mLength - 15,
				mYCoord + mLength - 15);
		float rectCenterX = mRect.centerX();
		float rectCenterY = mRect.centerY();

//		mRect1 = new RectF(mXCoord, mYCoord,
//				mXCoord + mLength,
//				mYCoord + mHeight);
//		mRect2 = new RectF(mXCoord, mYCoord,
//				mXCoord + mLength,
//				mYCoord + mHeight);
		mCircle = new RectF(mXCoord, mYCoord,
				mXCoord + mLength , mYCoord + mLength);
//		RectF oval = new RectF(width/2 - radius, width/2 - radius,
//				width/2 + radius, width/2 + radius);
//		canvas.drawArc(oval, 0, 360, false, paint1);


		// Configure the speed of the ship
		// This code means the ship can cover the
		// width of the screen in 2 seconds
		mPlayerSpeed = screenX / 2;
	}


	RectF getCirc() {return mCircle;}

	public RectF getRect(){
		return mRect;
	}

	float getRectLeft() {return mRect.left;}

	float getRectTop() {return mRect.top;}

	// Update the Player- Called each frame/loop
	// Update arguments within the AsteroidsGame class
	void update(long fps, Context ourContext, int blockSize) {
		if(rotateState == 1){
			degree -= 5;
		}
		else if(rotateState == 2){
			degree += 5;
		}
		else{
			degree = degree;
		}

//		// A bitmap for each direction the ship can face
//		Bitmap mBitmapHeadUp;
//		Bitmap mBitmapHeadCurrent;

//		// Create and scale the bitmaps
//		mBitmapHeadUp = BitmapFactory
//				.decodeResource(ourContext.getResources(),
//						R.drawable.sqspaceship);
//
//		mBitmapHeadCurrent = BitmapFactory
//				.decodeResource(ourContext.getResources(),
//						R.drawable.grayship);
//
//		// Modify the bitmaps to face the ship
//		// in the correct direction
//		mBitmapHeadUp = Bitmap
//				.createScaledBitmap(mBitmapHeadUp,
//						blockSize*2, blockSize*2, false);
//
//		Matrix matrix = new Matrix();
//
//		// set parameters depending on degree orientation vs location of box
//		matrix.preRotate(degree);
//		degree = degree + 5;
//		if(degree > 360){
//			degree = 0;
//		}
//
//		mBitmapHeadCurrent = Bitmap
//				.createBitmap(mBitmapHeadUp,
//						0, 0, (blockSize*2), (blockSize*2), matrix, true);
//		mBitmapHeadCurrent.setHasAlpha(true);
//
//
//





		// setRotate() function?
		// Accelerate()
	}

	public int getCenterX(){return (int)((this.mRect.left + this.mRect.right)/2);}
	public int getCenterY(){return (int)((this.mRect.top + this.mRect.bottom)/2);}

	void setRotationState(int playerRotate) { rotateState = rotationStates[playerRotate];}

	public int getDegree(){return this.degree;}

	public Bitmap getBitMap() {return this.mBitmapHeadCurrent;}

	public float getPlayerLength(){
		return this.mLength;
	}

	public float getPlayerHeight(){
		return this.mHeight;
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
		//Laser laser = new Laser(mRect.left, mRect.top, screenY/100, screenY/100, -(screenY/5), (screenY/5));
	}

}
