package com.gamecodeschool.asteroidsfs;

/*
 * This object takes care of spawning new objects.
 * The main use will be on every level. All objects are spawned at beginning of level. (for now)
 */
public class ObjectFactory {
        final private int maxAngle = 360;
        final private Zone zone1; // Area in between 25% to 100% of screen
        final private Zone zone2; // Area in between 50% to 100% of screen

        // When this object is first made for the game engine. The screen 
        ObjectFactory(Display display) {
                zone1 = new Zone((float)(display.width * .25), (float)(display.height * .25),
                                (float)display.width, (float)display.height);
                zone2 = new Zone((float)(display.width * .50), (float)(display.height * .50),
                                (float)display.width, (float)display.height);
        }
}

class Zone {
        // defines minimal rectangle areas where an object may spawn.
        float minX;
        float minY;
        float maxX;
        float maxY;

        Zone(float minWidth, float minHeight, float maxWidth, float maxHeight) {
            minX = minWidth;
            minY = minHeight;
            maxX = maxWidth;
            maxY = maxHeight;
        }
}
