package com.gamecodeschool.asteroidsfs;

import android.graphics.RectF;
import java.util.Random;

public class Asteroid {
    private RectF mRect; // Give access to precise position and size of asteroid
    private float xVelocity;
    private float yVelocity;
    private float width;
    private float height;
    private boolean hit;

    /*Define different size asteroids*/

    // The constructor is called in AsteroidsGame:
    // asteroid[i] = new Asteroid(xPosition, yPosition, width, height, xVelocity, yVelocity);
    public Asteroid(float xPosition, float yPosition, float width, float height, float xVelocity, float yVelocity) {
        this.mRect = new RectF(xPosition, yPosition, xPosition+width,yPosition+height);
        this.width = width;
        this.height = height;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        //super(xPosition, yPosition, width, height, xVelocity, yVelocity);


//        // Pick a random direction
//        // 0 -> left, down
//        // 1 -> left, up
//        // 2 -> right, down
//        // 3 -> right, up
//        Random rand = new Random();
//        int direction = rand.nextInt(4);
//        switch (direction) {
//            case 0:
//                xVelocity = -Math.abs(xVelocity);   // left
//                yVelocity = Math.abs(yVelocity);    // down
//                break;
//            case 1:
//                xVelocity = -Math.abs(xVelocity);   // left
//                yVelocity = -Math.abs(yVelocity);   // up
//                break;
//            case 2:
//                xVelocity = Math.abs(xVelocity);    // right
//                yVelocity = Math.abs(yVelocity);    // down
//                break;
//            case 3:
//                xVelocity = Math.abs(xVelocity);    // right
//                yVelocity = -Math.abs(yVelocity);   // up
//                break;
//        }
    }


    // Return a reference to mRect to AsteroidsGame
    public RectF getRect(){
        return mRect;
    }


    // Update the asteroid position (called each frame/loop)
    // Move the asteroid based on the velocity and current frame rate (mFPS)
    //OVERRIDE update() in SpaceObject
    public void update(long fps, int x, int y){
        // Move the top left corner
        mRect.left = mRect.left + (xVelocity / fps) ;
        mRect.top = mRect.top + (yVelocity / fps) ;

        // If asteroid travels off the screen -> wrap around
        if (mRect.left < 0) {
            mRect.left = x;
        }
        if (mRect.left > x) {
            mRect.left = 0;
        }
        if (mRect.top < 0) {
            mRect.top = y;
        }
        if (mRect.top > y) {
            mRect.top = 0;
        }

        // Match up the bottom right corner based on the size of the ball
        mRect.right = mRect.left + width;
        mRect.bottom = mRect.top + height;
    }



    void increaseVelocity(){
        // increase the speed by 10%
        xVelocity = xVelocity * 1.1f;
        yVelocity = yVelocity * 1.1f;
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

