package com.gamecodeschool.asteroidsfs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.RectF;

import java.util.ArrayList;

// make this an interface
public class GameView{

    private SurfaceHolder myHolder;
    private Canvas myCanvas;
    private Paint myPaint;
    private Context ourContext;
    Matrix shipMatrix = new Matrix();
    Bitmap shipBitmap;
    Bitmap mAsteroids;
    
    GameView(Context context, SurfaceHolder surfHolder){
        ourContext = context;
        myHolder = surfHolder;
        myPaint = new Paint();
    }




    // Draw the game objects and the HUD
    void draw(RectF myShipHitbox, int blockSize, int myShipDegree,
              int myShipCenterX, int myShipCenterY, float myShipHitboxLeft,
              float myShipHitboxTop, ArrayList<Asteroid> asteroids,
              ArrayList<Laser> myLasers, PowerUps[] mineralPowerUps) {
        if (myHolder.getSurface().isValid()) {
            // Lock the canvas (graphics memory) ready to draw
            myCanvas = myHolder.lockCanvas();


            // Fills the screen with background "space" image
            myCanvas.drawBitmap(BitmapFactory.decodeResource(ourContext.getResources(),
                    R.drawable.outerspacebackground1), 0, 0, null);

            // Choose a color to paint with
            myPaint.setColor(Color.argb
                    (255, 75, 180, 250));


            // Draw the objects
            myCanvas.drawRect(myShipHitbox, myPaint);
//            myCanvas.drawArc(myShipHitbox.getCirc(), 0, 360, false, myPaint);


            // A bitmap for each direction the ship can face



            shipBitmap = BitmapFactory
                    .decodeResource(ourContext.getResources(),
                            R.drawable.sqspaceship);


            // Modify the bitmaps to face the ship
            // in the correct direction
            shipBitmap = Bitmap
                    .createScaledBitmap(shipBitmap,
                            blockSize*2, blockSize*2, false);


            // set parameters depending on degree orientation vs location of box
            // shipMatrix.preRotate(myShipDegree);
            shipMatrix.setRotate(myShipDegree, myShipCenterX, myShipCenterY);
            shipMatrix.postTranslate(myShipHitboxLeft-(shipBitmap.getWidth()),
                    myShipHitboxTop-(shipBitmap.getHeight()));
//            myShipHitbox.setDegree();


            shipBitmap = Bitmap
                    .createBitmap(shipBitmap,
                            0, 0, (blockSize*2), (blockSize*2), shipMatrix, true);
            shipBitmap.setHasAlpha(true);
//            myCanvas.drawBitmap(shipBitmap,

//                    null,
//                    myShipHitbox.getRect(), myPaint);



            // myShipHitbox func that will return shipBitmap
            myCanvas.drawBitmap(shipBitmap,
                    myShipHitboxLeft+5,
                    myShipHitboxTop+5, myPaint);
//            myCanvas.drawBitmap(shipBitmap,

//                    shipMatrix, myPaint);
//            shipMatrix.mapRect(myShipHitbox.getRect());




            // LASERS
            // Draw lasers
            for(int i = 0; i < myLasers.size(); i++) {
                myLasers.get(i).draw(myCanvas);
            }

            // ASTEROIDS

            mAsteroids = BitmapFactory.decodeResource(ourContext.getResources(), R.drawable.asteroid);
            for(int i = 0 ; i < asteroids.size(); i++) {
                mAsteroids = BitmapFactory.decodeResource(ourContext.getResources(), R.drawable.asteroid);
                mAsteroids = Bitmap.createScaledBitmap(mAsteroids, (blockSize*2), (blockSize*2), false);
                myCanvas.drawBitmap(mAsteroids, asteroids.get(i).getHitbox().left,
                        asteroids.get(i).getHitbox().top, myPaint);
            }

            // POWER UPS
            for(int i = 0; i < mineralPowerUps.length; i++){
                mineralPowerUps[i].draw(myCanvas);
            }



            // Choose the font size
            //myPaint.setTextSize(fontSize);

            // Draw the HUD
            //myCanvas.drawText("Score: " + score + "   Lives: " + lives, fontMargin , fontSize, myPaint);

//            if(DEBUGGING){
//                printDebuggingText();
//            }
            // Display the drawing on screen
            // unlockCanvasAndPost is a method of SurfaceView
            myHolder.unlockCanvasAndPost(myCanvas);
        }

    }

}
