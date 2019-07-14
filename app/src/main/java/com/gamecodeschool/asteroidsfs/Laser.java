package com.gamecodeschool.asteroidsfs;

import android.graphics.RectF;

public class Laser{
    private RectF mRect;
    private float mXVelocity;
    private float mYVelocity;
    private static float mLaserWidth;
    private static float mLaserHeight;
    private float laserRange;

    Laser(int screenX){
        mLaserWidth = screenX/90;
        mLaserHeight = screenX/100;

        mRect = new RectF();
    }

    RectF getRect(){
        return mRect;
    }

//    void update(long fps) {}
//    void reset(int x, int y){}




}