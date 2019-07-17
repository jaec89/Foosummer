package com.gamecodeschool.asteroidsfs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class SpaceObject {

    private RectF mRect; // Give access to precise position and size of asteroid
    private float xVelocity;
    private float yVelocity;
    private float width;
    private float height;


    public SpaceObject(float xPosition, float yPosition, float width, float height, float xVelocity, float yVelocity) {
        this.mRect = new RectF(xPosition, yPosition, xPosition+width,yPosition+height);
        this.width = width;
        this.height = height;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }


    // Return a reference to mRect to AsteroidsGame
    public RectF getRect() {
        return mRect;
    }


    // Draw object
    public void draw(Canvas myCanvas) {
        Paint myPaint = new Paint();
        myPaint.setColor(Color.argb(255, 255, 255, 255));
        myCanvas.drawRect(mRect, myPaint);
    }


    // Update the object's position (called each frame/loop)
    // Move the object based on the velocity and current frame rate (mFPS)
    public void update(long fps){
        // Move the top left corner
        mRect.left = mRect.left + (xVelocity / fps);
        mRect.top = mRect.top + (yVelocity / fps);
        // Match up the bottom right corner based on the size of the asteroid
        mRect.right = mRect.left + width;
        mRect.bottom = mRect.top + height;
    }

}
