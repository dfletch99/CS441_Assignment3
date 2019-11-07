package com.mygdx.galaga;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    float dx;
    boolean dead;
    public static final String ENEMY_IMG_PATH = "enemy-web.png";

    Rectangle enemyHitbox;

    public Enemy(){
        enemyHitbox = new Rectangle();
        enemyHitbox.width = 0.075f * Gdx.graphics.getWidth();
        enemyHitbox.height = 0.1f * Gdx.graphics.getHeight();
        dx = -1;
        dead = false;
    }
}
