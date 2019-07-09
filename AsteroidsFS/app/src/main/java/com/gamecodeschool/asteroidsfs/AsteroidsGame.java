package com.gamecodeschool.asteroidsfs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


class AsteroidsGame extends SurfaceView implements Runnable{

    // Toggle for debugging
    private final boolean DEBUGGING = true;

    // Necessary objects for drawing
    private SurfaceHolder myHolder;
    private Canvas myCanvas;
    private Paint myPaint;

    // How many frames per second did we get?
    private long myFPS;
    // The number of milliseconds in a second
    private final int MILLIS_IN_SECOND = 1000;

    // Retains screen resolution
    private int screenX;
    private int screenY;

    // Text size
    private int fontSize;
    private int fontMargin;

    // track user score and lives
    private int myScore = 0;
    private int myLives = 3;

    // game objects
//    private Space mySpace;
//    private UserShip myShip;
//    private OpponentShip npcShip; // make a vector of npc ships
//    private Asteroids myAsteroids; // make a vector of asteroids
//    private Laser myLaser;
//    private Laser npcLaser; // vector of lasers associated per npc ship?
//    private PowerUps mineralPowerUps; // vector of mineral powerups





    public AsteroidsGame(Context context, int x, int y){
        // calls parent class constructor of SurfaceView
        super(context);
    }

    @Override
    public void run(){
        
    }

}
