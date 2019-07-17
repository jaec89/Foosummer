package com.gamecodeschool.asteroidsfs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.BitmapFactory;


import android.graphics.Bitmap;

class AsteroidsGame extends SurfaceView implements Runnable{
    private final int NUM_BLOCKS_WIDE = 40;
    int blockSize;


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

    private int degree;

    // Here is the Thread and two control variables
    private Thread myGameThread = null;
    // This volatile variable can be accessed
    // from inside and outside the thread
    private volatile boolean nowPlaying;
    private boolean nowPaused = true;


    // game objects
//    private Space mySpace;
    private Player myShip;
//    private OpponentShip npcShip; // make a vector of npc ships
//    private Asteroids myAsteroids; // make a vector of asteroids
//    private Laser myLaser;
//    private Laser npcLaser; // vector of lasers associated per npc ship?
//    private Power.Ups mineralPowerUps; // vector of mineral powerups

    // temp Context
    Context ourContext;

    //private Drawable mCustomImage;




    public AsteroidsGame(Context context, int x, int y){
        // calls parent class constructor of SurfaceView
        super(context);
        ourContext = context;
        blockSize = x / NUM_BLOCKS_WIDE;

        screenX = x;
        screenY = y;

        // Initialize the objects
        // ready for drawing with
        // getHolder is a method of Surfaceview
        myHolder = getHolder();
        myPaint = new Paint();

        // Initialize the objects
        myShip = new Player(screenX, screenY);
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

            // Choose a color to paint with
            myPaint.setColor(Color.argb
                    (255, 26, 190, 26));

            // Draw the objects
            myCanvas.drawRect(myShip.getRect(), myPaint);

            // A bitmap for each direction the ship can face
            Bitmap mBitmapHeadUp;
            Bitmap mBitmapHeadLeft;
            Bitmap mBitmapHeadDown;
            Bitmap mBitmapHeadRight;
            Bitmap mBitmapHeadCurrent;




            // Create and scale the bitmaps
            mBitmapHeadUp = BitmapFactory
                    .decodeResource(ourContext.getResources(),
                            R.drawable.sqspaceship);
            mBitmapHeadLeft = BitmapFactory
                    .decodeResource(ourContext.getResources(),
                            R.drawable.sqspaceship);
            mBitmapHeadDown = BitmapFactory
                    .decodeResource(ourContext.getResources(),
                            R.drawable.sqspaceship);
            mBitmapHeadRight = BitmapFactory
                    .decodeResource(ourContext.getResources(),
                            R.drawable.sqspaceship);

            mBitmapHeadCurrent = BitmapFactory
                    .decodeResource(ourContext.getResources(),
                            R.drawable.grayship);

            // Modify the bitmaps to face the ship
            // in the correct direction
            mBitmapHeadUp = Bitmap
                    .createScaledBitmap(mBitmapHeadUp,
                            blockSize*3, blockSize*3, false);

            // A matrix for scaling
            Matrix matrix = new Matrix();

            // set parameters depending on degree orientation vs location of box
            matrix.preRotate(degree);
            degree++;
            if(degree > 360){
                degree = 0;
            }
            Log.d("ADebugTag", "Degree: " + Float.toString(degree));
            mBitmapHeadCurrent = Bitmap
                    .createBitmap(mBitmapHeadUp,
                            0, 0, blockSize*3, blockSize*3, matrix, true);
            mBitmapHeadCurrent.setHasAlpha(true);
            myCanvas.drawBitmap(mBitmapHeadCurrent,
                    myShip.getRectLeft(),
                    myShip.getRectTop(), myPaint);

//            Log.d("ADebugTag", "RectLeft: " + Float.toString(myShip.getRectLeft()));
//            Log.d("ADebugTag", "RectLeft: " + Float.toString(myShip.getRectTop()));
//            mBitmapHeadCurrent.setHasAlpha(true);
//            matrix.mapRect(myShip.getRect());

           /* matrix.postRotate(20);
            mBitmapHeadCurrent = Bitmap
                    .createBitmap(mBitmapHeadUp,
                            0, 0, blockSize*4, blockSize*4, matrix, true);
            myCanvas.drawBitmap(mBitmapHeadCurrent,
                    screenX / 2,
                    screenY / 4, myPaint);*/

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





    // Handle all the screen touches
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        // This switch block replaces the
        // if statement from the Sub Hunter game
        switch (motionEvent.getAction() &
                MotionEvent.ACTION_MASK) {

            // The player has put their finger on the screen
            case MotionEvent.ACTION_DOWN:

                // If the game was paused unpause
                nowPaused = false;

                // If finger pressed on right side of screen
                // then the ship will accelerate
                if(motionEvent.getX() > screenX / 2){
                    // call method that will accelerate ship
                }

                // If finger pressed on left side of screen...
                if(motionEvent.getX() < screenX / 2){
                    // If finger pressed on upper left of screen
                    // then the ship will rotate counter-clockwise
                    if(motionEvent.getY() < screenY / 2){
                        // rotate ship counter-clockwise
                    }

                    if(motionEvent.getY() > screenY / 2){
                        // rotate ship clockwise
                    }
                }

                break;

            // The player lifted their finger
            // from anywhere on screen.
            // It is possible to create bugs by using
            // multiple fingers. We will use more
            // complicated and robust touch handling
            // in later projects
            case MotionEvent.ACTION_UP:
                if(motionEvent.getX() > screenX / 2){
                    // stop position
                }

                if(motionEvent.getX() < screenX / 2){
                    // stop rotation / fix orientation
                }

                break;
        }
        return true;
    }



    @Override
    public void run(){

        while(nowPlaying){
            //What time is it now at the start of the loop?
            long frameStartTime = System.currentTimeMillis();

            if(!nowPaused){
                update();

//            detectCollisions();
            }

            // The movement has been handled and collisions
            // detected now we can draw the scene.
            draw();

            // How long did this frame/loop take?
            // Store the answer in timeThisFrame
            long timeThisFrame = System.currentTimeMillis() - frameStartTime;

            // Make sure timeThisFrame is at least 1 millisecond
            // because accidentally dividing by zero crashes the game
            if(timeThisFrame > 0){
                // Store the current frame rate in myFPS
                // ready to pass to the update methods of
                // myShip..... next frame/loop
                myFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }

        // change condition for this later...
//        while(nowPlaying){
//            draw();
//        }

    }

    private void update(){
        myShip.update(myFPS);
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
