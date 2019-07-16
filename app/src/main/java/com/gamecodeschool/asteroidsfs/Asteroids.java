package com.gamecodeschool.asteroidsfs;

import android.graphics.RectF;
import java.util.Random;

public class Asteroids {
    private RectF mRect; // Give access to precise position and size of asteroid
    private float mXVelocity;
    private float mYVelocity;
    private float mWidth;
    private float mHeight;
    private boolean hit;

    private int screenX;
    private int screenY;


    /*Define different size asteroids*/

    // This is the constructor method.
    // It is called by the code:
    // myAsteroid = new Asteroid(screenX, screenY);
    // in the AsteroidsGame class

    Asteroids(int screenX, int screenY) {
        this.screenX = screenX;
        this.screenY = screenY;

        // Make the asteroid square 1% of screen width
        this.mWidth = screenX / 100;
        this.mHeight = screenX / 100;

        // Initialize the RectF
        // We do it here because we only want to do it once.
        // We will initialize the detail at the start of each game
        // Default constructor sets variables (left, top, right and bottom) to random
        this.mRect = new RectF();
        Random rand = new Random();
        mRect.left = rand.nextInt(screenX);
        mRect.top = rand.nextInt(screenX);
        mRect.right = mRect.left + mWidth;
        mRect.bottom = mRect.top + mHeight;

        // How fast will the ball travel
        // You could vary this to suit
        // You could even increase it as the game progresses
        // to make it harder
        mYVelocity = -(screenY / 5);
        mXVelocity = (screenY / 5);
    }



    // Return a reference to mRect to AsteroidsGame
    public RectF getRect() {
        return mRect;
    }



    // Update the asteroid position.
    // Called each frame/loop
    public void update(long fps){
        // Move the asteroid based upon the
        // horizontal (mXVelocity) and
        // vertical(mYVelocity) speed
        // and the current frame rate(mFPS)

        // Move the top left corner
        mRect.left = mRect.left + (mXVelocity / fps) ;
        mRect.top = mRect.top + (mYVelocity / fps) ;

        // If asteroid travels off the screen -> wrap around
        if(mRect.left < 0) {
            mRect.left = screenX;
        }
        if(mRect.left > screenX) {
            mRect.left = 0;
        }

        if(mRect.top < 0) {
            mRect.top = screenY;
        }
        if(mRect.top > screenY) {
            mRect.top = 0;
        }

        // Match up the bottom right corner
        // based on the size of the ball
        mRect.right = mRect.left + mWidth;
        mRect.bottom = mRect.top + mHeight;
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

