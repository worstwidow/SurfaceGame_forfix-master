package ru.pavlenty.surfacegame2;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Enemy {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 50;
    private int speedy = 50;
    private boolean boosting;
    private final int GRAVITY = -10;
    private int maxY;
    private int minY;
    private int minX;
    private int maxX = 6200;
    private Rect leftEnemy;

    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private Rect detectCollision;

    public Enemy(Context context, int screenX, int screenY) {
        x = 6200;
        Random r = new Random();
        y = r.nextInt(750);
        speed = -50;
        speedy = -50;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        maxY = screenY - bitmap.getHeight();
        maxX = screenX;
        minY = 0;


        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }
    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }
    public void update() {
        if (boosting) {
            speed += 2;
        } else {
            speed -= 5;
        }

        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }

        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }


        x-= speed;
        y-=speedy;
        if (y < minY) {
            speedy = -speedy;
        }
        if (y > maxY) {
            speedy = -speedy;
        }
        if (x<minX){
            x = maxX;
            Random r = new Random();
            y = r.nextInt(1500);
        }


        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
        leftEnemy = new Rect(x,y,x+getBitmap().getWidth(), y+getBitmap().getHeight());
    }
    public Rect getDetectCollision() {
        return detectCollision;
    }
    public Rect getLeftEnemy(){return leftEnemy;}

    public Bitmap getBitmap() {
        return bitmap;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
