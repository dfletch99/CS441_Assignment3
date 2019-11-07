package com.mygdx.galaga;

import com.badlogic.gdx.math.Rectangle;

public class Shot {
    float dy;
    public static final String SHOT_IMG_PATH = "bullet.png";

    Rectangle shotHitbox;

    public Shot(){
        shotHitbox = new Rectangle();
        shotHitbox.width = 1;
        shotHitbox.height = 3;
        dy = 15;
    }
}
