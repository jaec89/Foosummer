package com.gamecodeschool.asteroidsfs;

// the power ups kinda function the same as asteroids so we might want to inherit from that
// and extra functionality?

public class PowerUps {
    double x, y, xVelocity, yVelocity, radius;
    int hitsLeft, numSplit;

    public PowerUps(double x, double y, double radius, int hitsLeft, int numSplit) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.hitsLeft = hitsLeft;
        this.numSplit = numSplit;

        // when we figure out movement I'll add this in
        //xVelocity = ;
        //yVelocity = ;
    }

//    public void move(int screenWidth, int screenHeight){
//        something with radius and x.y
//    }

//    public void draw(){
//        find some cool image
//    }

    public int getHitsLeft(){
        //checks hitpoints to determine whether it is destroyed or split up
        return hitsLeft;
    }

    public int getNumSplit(){
        return numSplit;
    }

    public void shootFaster(Player spaceShip){
        //do something to alter shooting speed
    }
    public void moreLives(Player spaceShip){
        //spaceShip.setLives += 1 or something
    }
}
