package com.gamecodeschool.asteroidsfs;

import android.graphics.RectF;

public class SpaceObject {

    private RectF mRect; // Give access to precise position and size of asteroid
    private float mXVelocity;
    private float mYVelocity;
    private float mWidth;
    private float mHeight;

    SpaceObject(int screenX, int screenY) {
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
    }



    // Return a reference to mRect to AsteroidsGame
    public RectF getRect(){
        return mRect;
    }



    // Update the asteroid position.
    // Called each frame/loop
    public void update(long fps){
        // Move the object based upon the
        // horizontal (mXVelocity) and
        // vertical(mYVelocity) speed
        // and the current frame rate(fps)
        // Move the top left corner
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top + (mYVelocity / fps);

        // Match up the bottom right corner based on the size of the asteroid
        mRect.right = mRect.left + mWidth;
        mRect.bottom = mRect.top + mHeight;
    }



    public void increaseVelocity(){
        // increase the speed by 10%
        mXVelocity = mXVelocity * 1.1f;
        mYVelocity = mYVelocity * 1.1f;
    }

}
