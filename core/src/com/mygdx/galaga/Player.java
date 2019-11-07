package com.mygdx.galaga;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    public static final String PLAYER_IMG_PATH = "ship-web.png";

    Rectangle playerHitbox;
    float dx;

    public Player() {
        playerHitbox = new Rectangle();
        playerHitbox.x = 0;
        playerHitbox.y = 0;
        playerHitbox.width = 0.075f * Gdx.graphics.getWidth();
        playerHitbox.height = 0.1f * Gdx.graphics.getHeight();
        dx = 5;
    }


}
