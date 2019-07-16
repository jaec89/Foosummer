package com.gamecodeschool.asteroidsfs;

import android.graphics.RectF;

public class Laser {
    private RectF mRect; // Give access to precise position and size of asteroid
    private float xVelocity;
    private float yVelocity;
    private float width;
    private float height;


    public Laser(float xPosition, float yPosition, float width, float height, float xVelocity, float yVelocity) {
        this.mRect = new RectF(xPosition, yPosition, xPosition+width,yPosition+height);
        this.width = width;
        this.height = height;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        //super(xPosition, yPosition, width, height, xVelocity, yVelocity);
    }


    // Return a reference to mRect to AsteroidsGame
    public RectF getRect(){
        return mRect;
    }


    // Update the asteroid position (called each frame/loop)
    // Move the asteroid based on the velocity and current frame rate (mFPS)
    //OVERRIDE update() in SpaceObject
    public void update(long fps, int x, int y) {
        // Move the top left corner
        mRect.left = mRect.left + (xVelocity / fps) ;
        mRect.top = mRect.top + (yVelocity / fps) ;

//        // If asteroid travels off the screen -> wrap around
//        if (mRect.left < 0) {
//            mRect.left = x;
//        }
//        if (mRect.left > x) {
//            mRect.left = 0;
//        }
//        if (mRect.top < 0) {
//            mRect.top = y;
//        }
//        if (mRect.top > y) {
//            mRect.top = 0;
//        }

        // Match up the bottom right corner based on the size of the ball
        mRect.right = mRect.left + width;
        mRect.bottom = mRect.top + height;
    }



    public void increaseVelocity() {
        // increase the speed by 10%
        xVelocity = xVelocity * 1.1f;
        yVelocity = yVelocity * 1.1f;
    }
}