package com.gamecodeschool.asteroidsfs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.BitmapFactory;


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
    private int myLives = 3; // abstract this to UserShip class?

    // Here is the Thread and two control variables
    private Thread myGameThread = null;
    // This volatile variable can be accessed
    // from inside and outside the thread
    private volatile boolean nowPlaying;
    private boolean nowPaused = true;

    // game objects
//    private Space mySpace;
//    private UserShip myShip;
//    private OpponentShip npcShip; // make a vector of npc ships
//    private Asteroids myAsteroids; // make a vector of asteroids
//    private Laser myLaser;
//    private Laser npcLaser; // vector of lasers associated per npc ship?
//    private Power.Ups mineralPowerUps; // vector of mineral powerups


    //private Drawable mCustomImage;


<<<<<<< HEAD
=======


>>>>>>> origin/master
    public AsteroidsGame(Context context, int x, int y){
        // calls parent class constructor of SurfaceView
        super(context);

        screenX = x;
        screenY = y;

        // Initialize the objects
        // ready for drawing with
        // getHolder is a method of Surfaceview
        myHolder = getHolder();
        myPaint = new Paint();

        // Initialize the objects
        // myShip = new UserShip(...)
        // Asteroids = new Asteroids()
        // enemyShip = new ...()
        // myLaser = new ..()
        // enemyLaser = new ..()

        //startNewGame();

    }



    // Draw the game objects and the HUD
    void draw() {
        if (myHolder.getSurface().isValid()) {
            // Lock the canvas (graphics memory) ready to draw
            myCanvas = myHolder.lockCanvas();

            // Fills the screen with background "space" image
            myCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
                    R.drawable.outerspacebackground1), 0, 0, null);

//            // Change the paint color to white
//            myPaint.setColor(Color.argb(255, 255, 255, 255));



            // Fill the screen with a solid color
//            myCanvas.drawColor(Color.argb
//                    (255, 42, 10, 200));

            // Choose a color to paint with
//            myPaint.setColor(Color.argb
//                    (255, 255, 255, 255));

            // Draw the bat and ball
//            myCanvas.drawRect(mBall.getRect(), myPaint);
//            myCanvas.drawRect(mBat.getRect(), myPaint);



            // Choose the font size
            myPaint.setTextSize(fontSize);

//            // Draw the HUD
//            myCanvas.drawText("Score: " + mScore +
//                            "   Lives: " + mLives,
//                    fontMargin , fontSize, myPaint);

//            if(DEBUGGING){
//                printDebuggingText();
//            }
            // Display the drawing on screen
            // unlockCanvasAndPost is a method of SurfaceView
            myHolder.unlockCanvasAndPost(myCanvas);
        }

    }



    @Override
    public void run(){
        // change condition for this later...
        while(true){
            draw();
        }

    }

    public void resume(){
        nowPlaying = true;

        // Initialize the instance of Thread
        myGameThread = new Thread(this);

        // Start the thread
        myGameThread.start();

    }

    public void pause(){
        // Set nowPlaying to false
        // Stopping the thread isn't
        // always instant
        nowPlaying = false;
        try {
            myGameThread.join();
        } catch (InterruptedException e){
            Log.e("Error:", "joining thread");
        }
    }


}
<<<<<<< HEAD
=======

>>>>>>> origin/master
