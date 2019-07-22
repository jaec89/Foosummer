package com.gamecodeschool.asteroidsfs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.view.View;
import android.app.Activity;

import java.util.ArrayList;

// make this an interface
public class GameView extends Activity {

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
    void draw(RectF myShipHitbox, int blockSize, int myShipDegree, Point shipCenter, ArrayList<Asteroid> asteroids, PowerUps[] mineralPowerUps) {
        //include position of ship (updating move location to be drawn)

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
//            canvas.drawRect(myShipHitbox, paint);
//            myCanvas.drawArc(myShipHitbox.getCirc(), 0, 360, false, paint);


            // A bitmap for each direction the ship can face
            Bitmap shipBitmap;
            shipBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sqspaceship);


//            // Modify the bitmaps to face the ship
//            // in the correct direction
            shipBitmap = Bitmap
                    .createScaledBitmap(shipBitmap,
                            blockSize*2, blockSize*2, true);
//
//
//            // set parameters depending on degree orientation vs location of box
//            // shipMatrix.preRotate(myShipDegree);
//            shipBitmap = Bitmap
//                    .createBitmap(shipBitmap,
//                            0, 0, (blockSize*2), (blockSize*2), shipMatrix, true);

            Matrix shipMatrix = new Matrix();
            shipMatrix.setRotate(myShipDegree, shipBitmap.getWidth()/2, shipBitmap.getHeight()/2);
//            shipMatrix.setTranslate();
//            shipMatrix.postTranslate((myShipHitbox.left - shipBitmap.getWidth()+blockSize),
////                    (myShipHitbox.top - shipBitmap.getHeight()+blockSize)
////            );
            shipMatrix.postTranslate((shipCenter.x)-blockSize,
                    (shipCenter.y)-blockSize
            );
            shipBitmap.setHasAlpha(true);

//            Log.e("draw: ", "value of shiphitbox.left: " + myShipHitbox.left);
//            Log.e("draw: ", "value of shiphitbox.right: " + myShipHitbox.right);
//            Log.e("draw: ", "value of shiphitbox.top: " + myShipHitbox.top);
//            Log.e("draw: ", "value of shiphitbox.bottom: " + myShipHitbox.bottom);
//            Log.d("draw:", "value of shipcenter.x: " + shipCenter.x);
//            Log.d("draw:", "value of shipcenter.y: " + shipCenter.y);
//            Log.d("draw:", "value of blockSize: " + blockSize);
//            Log.d("draw:", "value of shipbitmap.height: " + shipBitmap.getHeight());
//            Log.d("draw:", "value of shipbitmap.width: " + shipBitmap.getWidth());


            // myShipHitbox func that will return shipBitmap
            canvas.drawRect(myShipHitbox, paint);
            canvas.drawBitmap(shipBitmap, shipMatrix, paint);
//            myCanvas.drawBitmap(shipBitmap,

//                    shipMatrix, paint);
//            shipMatrix.mapRect(myShipHitbox.getRect());







            // ASTEROIDS
            Bitmap mAsteroids;
            mAsteroids = BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid);
            for(int i = 0 ; i < asteroids.size(); i++) {
                mAsteroids = Bitmap.createScaledBitmap(mAsteroids, (int)asteroids.get(i).getWidth(), (int) asteroids.get(i).getHeight(), false);
                canvas.drawBitmap(mAsteroids, asteroids.get(i).getHitbox().left, asteroids.get(i).getHitbox().top, paint);
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
