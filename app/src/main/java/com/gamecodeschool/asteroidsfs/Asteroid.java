package com.gamecodeschool.asteroidsfs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Bitmap;

public class Asteroid extends SpaceObject {
    private int size;       //Define different size asteroids
    private boolean hit;


    public Asteroid(PointF position, float width, float height, float velocityX, float velocityY) {
        super(position, width, height, velocityX, velocityY);
        this.hit = false;
    }


    // Draw asteroid w/ picture
    public void draw(Canvas canvas, Bitmap asteroidsBitmap) {
        Paint myPaint = new Paint();
        canvas.drawBitmap(asteroidsBitmap, 100, 100, myPaint);
    }


    public void increaseVelocity() {
        // increase the speed by 10%
        super.setVelocityX(super.getVelocityX() * 1.1f);
        super.setVelocityY(super.getVelocityY() * 1.1f);
    }


    // Detect collision with a laser object
    // Has the laser hit the asteroid?
    private void detectCollisions(RectF laserPosition){
        hit = RectF.intersects(laserPosition, super.getHitbox());
    }

    // Large asteroid breaks into medium pieces
    // medium piece breaks into small pieces
    void disintegrate(){
    }
}

