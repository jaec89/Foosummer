package com.gamecodeschool.asteroidsfs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.util.Random;


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

    // Number of hits to destroy PowerUps
    private int hitsLeft= 3;

    // Here is the Thread and two control variables
    private Thread myGameThread = null;
    // This volatile variable can be accessed
    // from inside and outside the thread
    private volatile boolean nowPlaying;
    private boolean nowPaused = true;

    // GAME OBJECTS
    private int levels[] = {0, 1, 2};
//    private Space mySpace;
    private Player myShip;
//    private OpponentShip npcShip; // make a vector of npc ships
    private Asteroid asteroids[]; // make a vector of asteroids
    private Laser myLaser;
//    private Laser npcLaser; // vector of lasers associated per npc ship?
    private PowerUps mineralPowerUps[]; // vector of mineral powerups


    //private Drawable mCustomImage;




    public AsteroidsGame(Context context, int x, int y) {
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
        myShip = new Player(screenX, screenY);
        myLaser = new Laser(screenX/2, screenY/2, screenY/100, screenY/100, -(screenY/5), (screenY/5));
        // Initialize asteroids
        asteroids = new Asteroid[3];
        for(int i = 0 ; i < asteroids.length ; i++) {
            Random rand = new Random();
            int asteroidXPosition = rand.nextInt(screenX);
            int asteroidYPosition = rand.nextInt(screenY);
            int asteroidWidth = screenY/ 50;
            int asteroidHeight = screenY/ 50;
            int asteroidXVelocity = -(screenY / 5);
            int asteroidYVelocity = (screenY / 5);

            // Pick a random direction
            // 0 -> left, down
            // 1 -> left, up
            // 2 -> right, down
            // 3 -> right, up
            int direction = rand.nextInt(4);
            switch (direction) {
                case 0:
                    asteroidXVelocity = -Math.abs(asteroidXVelocity);   // left
                    asteroidYVelocity = Math.abs(asteroidYVelocity);    // down
                    break;
                case 1:
                    asteroidXVelocity = -Math.abs(asteroidXVelocity);   // left
                    asteroidYVelocity = -Math.abs(asteroidYVelocity);   // up
                    break;
                case 2:
                    asteroidXVelocity = Math.abs(asteroidXVelocity);    // right
                    asteroidYVelocity = Math.abs(asteroidYVelocity);    // down
                    break;
                case 3:
                    asteroidXVelocity = Math.abs(asteroidXVelocity);    // right
                    asteroidYVelocity = -Math.abs(asteroidYVelocity);   // up
                    break;
            }


            asteroids[i] = new Asteroid(asteroidXPosition,
                                            asteroidYPosition,
                                            asteroidWidth,
                                            asteroidHeight,
                                            asteroidXVelocity,
                                            asteroidYVelocity);
        }

        // Initialize powerups - eventually have them scale with levels?
        // currently hardcoded to 1 for now
        // ill change it to spawn upon a certain point threshold or timed later
        mineralPowerUps = new PowerUps[1];
        Random rn = new Random();
        for(int i = 0; i < mineralPowerUps.length; i++){
            mineralPowerUps[i] = new PowerUps(rn.nextInt(screenX), rn.nextInt(screenY),
                    screenY / 50, screenY / 50, hitsLeft, -(screenY/8), (screenY/8));
        }


        // enemyShip = new ...()
        // myLaser = new ..()
        // enemyLaser = new ..()

        //startNewGame();

    }




    @Override
    public void run() {
        while(nowPlaying) {
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
            if(timeThisFrame > 0) {
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


    private void update() {
        // Update the asteroid
        //myShip.update(myFPS);
        myLaser.update(myFPS, screenX, screenY);
        for(int i = 0 ; i < asteroids.length ; i++) {
            asteroids[i].update(myFPS, screenX, screenY);
        }

        // PowerUp position - currently stationary
        for(int i = 0; i < mineralPowerUps.length; i++) {
            mineralPowerUps[i].update(myFPS, screenX, screenY);
        }
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
                    (255, 75, 180, 250));

            // Draw the objects
            myCanvas.drawRect(myShip.getRect(), myPaint);
            myLaser.draw(myCanvas);
            for(int i = 0 ; i < asteroids.length ; i++) {
                asteroids[i].draw(myCanvas);
            }
            for(int i = 0; i < mineralPowerUps.length; i++){
                mineralPowerUps[i].draw(myCanvas);
            }

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

