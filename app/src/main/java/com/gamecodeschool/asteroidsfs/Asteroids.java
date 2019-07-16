package com.gamecodeschool.asteroidsfs;

import android.graphics.RectF;

public class Asteroids {
    private RectF mRect; // Give access to precise position and size of asteroid
    private float mXVelocity;
    private float mYVelocity;
    private float mWidth;
    private float mHeight;
    private boolean hit;
    private float mAsteroidWidth;
    private float mAsteroidHeight;


    /*Define different size asteroids*/

    // This is the constructor method.
    // It is called by the code:
    // mAsteroid = new Asteroid(mScreenX);
    // in the AsteroidsGame class

    Asteroids(int screenX, int mScreenY) {
        // Make the ball square and 1% of screen width
        // of the screen width
        mWidth = screenX / 100;
        mHeight = screenX / 100;

        // Initialize the RectF with 0, 0, 0, 0
        // We do it here because we only want to
        // do it once.
        // We will initialize the detail
        // at the start of each game
        // default constructor sets variables (left, top, right and bottom) to zero
        mRect = new RectF();
        mRect.left = screenX / 2;
        mRect.top = 0;
        mRect.right = screenX / 2 + mWidth;
        mRect.bottom = mHeight;

        // How fast will the ball travel
        // You could vary this to suit
        // You could even increase it as the game progresses
        // to make it harder
        mYVelocity = -(mScreenY / 3);
        mXVelocity = (mScreenY / 3);
    }

    // Return a reference to mRect to AsteroidsGame
    RectF getRect(){
        return mRect;
    }

    // Update the asteroid position.
    // Called each frame/loop
    void update(long fps){
        // Move the asteroid based upon the
        // horizontal (mXVelocity) and
        // vertical(mYVelocity) speed
        // and the current frame rate(fps)
        // Move the top left corner
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top + (mYVelocity / fps);
        // Match up the bottom right corner
        // based on the size of the asteroid
        mRect.right = mRect.left + mWidth;
        mRect.bottom = mRect.top + mHeight;
    }

    void reset(int x, int y){
        // Initialise the four points of
        // the rectangle which defines the asteroid
        mRect.left = x / 2;
        mRect.top = 0;
        mRect.right = x / 2 + mAsteroidWidth;
        mRect.bottom = mAsteroidHeight;
        // How fast will the asteroid travel
        // You could vary this to suit
        // You could even increase it as the game progresses
        // to make it harder
        mYVelocity = -(y / 3);
        mXVelocity = (y / 3);
    }

    void increaseVelocity(){
        // increase the speed by 10%
        mXVelocity = mXVelocity * 1.1f;
        mYVelocity = mYVelocity * 1.1f;
    }

    // Detect collision with a laser object
    // Has the laser hit the asteroid?
    private void detectCollisions(RectF laserPosition){
        hit = RectF.intersects(laserPosition, mRect);
    }

    // Large asteroid breaks into medium pieces
    // medium piece breaks into small pieces
    void disintegrate(){

    }

}

