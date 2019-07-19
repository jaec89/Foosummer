package com.gamecodeschool.asteroidsfs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap;
// these imports deal with ArrayList class in java
import java.util.*;
import java.util.Random;
import android.graphics.Bitmap;

class AsteroidsGame extends SurfaceView implements Runnable{
    private final int NUM_BLOCKS_WIDE = 40;
    int blockSize;


    // Toggle for debugging
    private final boolean DEBUGGING = true;

    // Drawing objects
    private SurfaceHolder myHolder;
    private Canvas myCanvas;
    private Paint myPaint;

    // Frames per second
    private long myFPS;
    // Number of milliseconds in a second
    private final int MILLIS_IN_SECOND = 1000;

    // Screen resolution
    private int screenX;
    private int screenY;

    // Text size
    private int fontSize = blockSize*10;
    private int fontMargin;

    private int i = 0;

    private int degree;

    // Number of hits to destroy PowerUps
    private int hitsLeft= 3;

    // Here is the Thread and two control variables
    private Thread myGameThread = null;
    // This volatile variable can be accessed
    // from inside and outside the thread
    private volatile boolean nowPlaying;
    private boolean nowPaused = true;

    Matrix shipMatrix = new Matrix();

    // GAME OBJECTS
    private GameProgress gameProgress;
//    private Space mySpace;
    private Player myShip;
    private Player myShipHitbox;
//    private OpponentShip npcShip; 
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Laser> myLasers;
//    private Laser npcLaser; // vector of lasers associated per npc ship?
    private PowerUps mineralPowerUps[]; // vector of mineral powerups
//    private Drawable mCustomImage;


    // temp Context
    Context ourContext;



    public AsteroidsGame(Context context, int x, int y) {
        // calls parent class constructor of SurfaceView
        super(context);
        ourContext = context;

        ourContext = context;
        blockSize = x / NUM_BLOCKS_WIDE;


        this.screenX = x;
        this.screenY = y;


        // Initialize the objects
        // ready for drawing with
        // getHolder is a method of Surfaceview
        myHolder = getHolder();
        myPaint = new Paint();

        // Initialize the objects
        myShip = new Player(screenX, screenY);
        myShipHitbox = new Player(screenX, screenY);

        // Asteroids = new Asteroids()

        myLasers = new ArrayList<Laser>();

        // Initialize asteroids
        asteroids = new ArrayList<Asteroid>();
        for(int i = 0 ; i < 5 ; i++) {

            Random rand = new Random();
            int asteroidXPosition = rand.nextInt(screenX);
            int asteroidYPosition = rand.nextInt(screenY);
            int asteroidWidth = screenY / 25;
            int asteroidHeight = screenY / 25;
            int asteroidXVelocity = -(screenY / 10);
            int asteroidYVelocity = (screenY / 10);

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

            asteroids.add(new Asteroid(asteroidXPosition,
                                        asteroidYPosition,
                                        asteroidWidth,
                                        asteroidHeight,
                                        asteroidXVelocity,
                                        asteroidYVelocity));
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
      
        gameProgress = new GameProgress();

        // enemyShip = new ...()
        // enemyShipHitbox = new ...()
        // myLaser = new ..()
        // enemyLaser = new ..()

        startNewGame();

    }






    /*
        When we start the game we reset the game state such as level
        initial meteor count.
        and clear all lasers and enemy space ships.
    */
    private void startNewGame() {
//        // FIXME: Change 3 to asteroid count variable that can be changed.
//        for(int i = 0 ; i < 3 ; i++) {
//            Random rand = new Random();
//            int asteroidXPosition = rand.nextInt(screenX);
//            int asteroidYPosition = rand.nextInt(screenY);
//            int asteroidWidth = screenY/ 25;
//            int asteroidHeight = screenY/ 25;
//            int asteroidXVelocity = -(screenY / 5);
//            int asteroidYVelocity = (screenY / 5);
//
//            // Pick a random direction
//            // 0 -> left, down
//            // 1 -> left, up
//            // 2 -> right, down
//            // 3 -> right, up
//            int direction = rand.nextInt(4);
//            switch (direction) {
//                case 0:
//                    asteroidXVelocity = -Math.abs(asteroidXVelocity);   // left
//                    asteroidYVelocity = Math.abs(asteroidYVelocity);    // down
//                    break;
//                case 1:
//                    asteroidXVelocity = -Math.abs(asteroidXVelocity);   // left
//                    asteroidYVelocity = -Math.abs(asteroidYVelocity);   // up
//                    break;
//                case 2:
//                    asteroidXVelocity = Math.abs(asteroidXVelocity);    // right
//                    asteroidYVelocity = Math.abs(asteroidYVelocity);    // down
//                    break;
//                case 3:
//                    asteroidXVelocity = Math.abs(asteroidXVelocity);    // right
//                    asteroidYVelocity = -Math.abs(asteroidYVelocity);   // up
//                    break;
//            }
//
//
//            asteroids.add(new Asteroid(asteroidXPosition,
//                    asteroidYPosition,
//                    asteroidWidth,
//                    asteroidHeight,
//                    asteroidXVelocity,
//                    asteroidYVelocity));
//        }

//        gameProgress.reset();
    }






    @Override
    public void run() {
        while(nowPlaying) {
            //What time is it now at the start of the loop?
            long frameStartTime = System.currentTimeMillis();

            if(!nowPaused){
                update();

                // check for collision between player and asteroids
                Asteroid myAsteroid = asteroids.get(i);
                boolean asteroidPlayerHit = detectCollision(myShip.getRect(), myAsteroid.getRect());
                i++;
                if(i > 4){
                    i = 0;
                }

                /*
                Log.d("ADebugTag", "collision detected: " + hit);
                Log.d("ADebugTag", "value of i: " + i);
                */

                //sprint 2
                // asteroid hit player's ship - decrement player's hitpoints
                //if(asteroidPlayerHit){}

                // check for collision between player and police laser
                // check for collision between player's laser and powerup
                // check for collision between player's laser and asteroids
                //detectCollisions();
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
                // myShipHitbox..... next frame/loop
                myFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }

        // change condition for this later...
//        while(nowPlaying){
//            draw();
//        }
    }






    private void update() {
        //myShip.update(myFPS);
        myShipHitbox.update(myFPS, ourContext, blockSize);
        for(int i = 0; i < myLasers.size(); i++) {
            myLasers.get(i).update(myFPS, screenX, screenY);
        }
        for(int i = 0 ; i < asteroids.size() ; i++) {
            asteroids.get(i).update(myFPS, screenX, screenY);
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

            Bitmap mAsteroids;
            mAsteroids = BitmapFactory.decodeResource(ourContext.getResources(),
                    R.drawable.asteroid);

            // Fills the screen with background "space" image
            myCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
                    R.drawable.outerspacebackground1), 0, 0, null);

            // Choose a color to paint with
            myPaint.setColor(Color.argb
                    (255, 75, 180, 250));



            // Draw the objects
            //myCanvas.drawRect(myShip.getRect(), myPaint);
            myCanvas.drawRect(myShipHitbox.getRect(), myPaint);
//            myCanvas.drawArc(myShipHitbox.getCirc(), 0, 360, false, myPaint);



// CTRL-Z TO HERE FOR ORIGINAL CODE DELETE IN THIS FUNC
            // A bitmap for each direction the ship can face
            Bitmap mBitmapHeadCurrent;



            // Create and scale the bitmaps
            mBitmapHeadCurrent = BitmapFactory
                    .decodeResource(ourContext.getResources(),
                            R.drawable.sqspaceship);

            // Modify the bitmaps to face the ship
            // in the correct direction
            mBitmapHeadCurrent = Bitmap
                    .createScaledBitmap(mBitmapHeadCurrent,
                            blockSize*2, blockSize*2, false);


            // set parameters depending on degree orientation vs location of box
            // shipMatrix.preRotate(myShipHitbox.getDegree());
            shipMatrix.setRotate(myShipHitbox.getDegree(), myShipHitbox.getCenterX(), myShipHitbox.getCenterY());
            shipMatrix.postTranslate(myShipHitbox.getRectLeft()-(mBitmapHeadCurrent.getWidth()),
                    myShipHitbox.getRectTop()-(mBitmapHeadCurrent.getHeight()));
//            myShipHitbox.setDegree();


            mBitmapHeadCurrent = Bitmap
                    .createBitmap(mBitmapHeadCurrent,
                            0, 0, (blockSize*2), (blockSize*2), shipMatrix, true);
            mBitmapHeadCurrent.setHasAlpha(true);
//            myCanvas.drawBitmap(mBitmapHeadCurrent,
//                    null,
//                    myShipHitbox.getRect(), myPaint);


            // myShipHitbox func that will return mBitmapHeadCurrent
            myCanvas.drawBitmap(mBitmapHeadCurrent,
                    myShipHitbox.getRectLeft()+5,
                    myShipHitbox.getRectTop()+5, myPaint);
//            myCanvas.drawBitmap(mBitmapHeadCurrent,
//                    shipMatrix, myPaint);
//            shipMatrix.mapRect(myShipHitbox.getRect());



            // LASERS
            for(int i = 0; i < myLasers.size(); i++) {
                myLasers.get(i).draw(myCanvas);
            }

            // ASTEROIDS
            Bitmap mAsteroids;
            mAsteroids = BitmapFactory.decodeResource(ourContext.getResources(), R.drawable.asteroid);
            for(int i = 0 ; i < asteroids.size(); i++) {
                myCanvas.drawBitmap(mAsteroids, asteroids.get(i).getHitbox().left,
                                                            asteroids.get(i).getHitbox().top, myPaint);
            }
            
            // POWER UPS
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
                        myShipHitbox.setRotationState(1);
                    }
                    else{
                        // rotate ship clockwise
                        myShipHitbox.setRotationState(2);
                    }

//                    if(motionEvent.getY() > screenY / 2){
//                        // rotate ship clockwise
//                    }
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
                    myShipHitbox.setRotationState(0);
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






    public void pause() {
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






    /* 
        We go through run through all object pairs that can be collided.
        meteor - player's laser.
        meteor - player
        enemy - player
        enemy laser - player
        enemy - player's laser

        These should cover the basic cases of collision within the game.
    */
    public boolean detectCollision(RectF objectA, RectF objectB) {
            return RectF.intersects(objectA, objectB);
    }

}
