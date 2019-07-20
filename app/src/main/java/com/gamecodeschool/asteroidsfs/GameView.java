package com.gamecodeschool.asteroidsfs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.content.Context;
import android.graphics.RectF;

import java.util.ArrayList;

// make this an interface
public class GameView {

    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;
    private Context context;


    GameView(Context context, SurfaceHolder surfHolder) {
        this.context = context;
        surfaceHolder = surfHolder;
        paint = new Paint();
    }




    // Draw the game objects and the HUD
    void draw(RectF myShipHitbox, int blockSize, int myShipDegree,
              int myShipCenterX, int myShipCenterY, float myShipHitboxLeft,
              float myShipHitboxTop, ArrayList<Asteroid> asteroids,
              ArrayList<Laser> myLasers, PowerUps[] mineralPowerUps) {
        if (surfaceHolder.getSurface().isValid()) {
            // Lock the canvas (graphics memory) ready to draw
            canvas = surfaceHolder.lockCanvas();


            // Fills the screen with background "space" image
            canvas.drawBitmap(BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.outerspacebackground1), 0, 0, null);

            // Choose a color to paint with
            paint.setColor(Color.argb
                    (255, 75, 180, 250));


            // Draw the objects
            canvas.drawRect(myShipHitbox, paint);
//            myCanvas.drawArc(myShipHitbox.getCirc(), 0, 360, false, paint);


            // A bitmap for each direction the ship can face
            Bitmap shipBitmap;


            shipBitmap = BitmapFactory
                    .decodeResource(context.getResources(),
                            R.drawable.sqspaceship);


            // Modify the bitmaps to face the ship
            // in the correct direction
            shipBitmap = Bitmap
                    .createScaledBitmap(shipBitmap,
                            blockSize*2, blockSize*2, false);


            // set parameters depending on degree orientation vs location of box
            // shipMatrix.preRotate(myShipDegree);
            Matrix shipMatrix = new Matrix();
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
//                    myShipHitbox.getRect(), paint);



            // myShipHitbox func that will return shipBitmap
            canvas.drawBitmap(shipBitmap,
                    myShipHitboxLeft+5,
                    myShipHitboxTop+5, paint);
//            myCanvas.drawBitmap(shipBitmap,

//                    shipMatrix, paint);
//            shipMatrix.mapRect(myShipHitbox.getRect());




            // LASERS
            // Draw lasers
            for(int i = 0; i < myLasers.size(); i++) {
                myLasers.get(i).draw(canvas);
            }

            // ASTEROIDS
            Bitmap mAsteroids;
            mAsteroids = BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid);
            for(int i = 0 ; i < asteroids.size(); i++) {
                mAsteroids = BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid);
                mAsteroids = Bitmap.createScaledBitmap(mAsteroids, (blockSize*2), (blockSize*2), false);
                canvas.drawBitmap(mAsteroids, asteroids.get(i).getHitbox().left,
                        asteroids.get(i).getHitbox().top, paint);
            }

            // POWER UPS
            for(int i = 0; i < mineralPowerUps.length; i++){
                mineralPowerUps[i].draw(canvas);
            }



            // Choose the font size
            //paint.setTextSize(fontSize);

            // Draw the HUD
            //myCanvas.drawText("Score: " + score + "   Lives: " + lives, fontMargin , fontSize, paint);

//            if(DEBUGGING){
//                printDebuggingText();
//            }
            // Display the drawing on screen
            // unlockCanvasAndPost is a method of SurfaceView
            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

}
