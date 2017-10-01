package com.luarca84.diego.pong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

/**
 * Created by USUARIO on 27/08/2016.
 */
public class Ball {
    Vector2 position;
    Vector2 velocity;
    Viewport viewport;
    int level;
    public Ball(Viewport viewport,int level) {
        this.viewport = viewport;
        this.level = level;
        init();
    }

    public void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, viewport.getWorldHeight()/2);
        velocity = new Vector2(200,100);
        switch (level)
        {
            case 1:velocity = new Vector2(220,120);
                break;
            case 2:velocity = new Vector2(240,140);
                break;
            case 3:velocity = new Vector2(260,160);
                break;
            case 4:velocity = new Vector2(280,180);
                break;
            case 5:velocity = new Vector2(300,200);
                break;
            case 6:velocity = new Vector2(300,200);
                break;
            case 7:velocity = new Vector2(310,210);
                break;
            case 8:velocity = new Vector2(320,220);
                break;
            case 9:velocity = new Vector2(330,230);
                break;
            case 10:velocity = new Vector2(340,240);
                break;
            case 11:velocity = new Vector2(350,250);
                break;
            case 12:velocity = new Vector2(360,260);
                break;
            case 13:velocity = new Vector2(370,270);
                break;
            case 14:velocity = new Vector2(380,280);
                break;
            case 15:velocity = new Vector2(390,290);
                break;
            case 16:velocity = new Vector2(400,300);
                break;
            case 17:velocity = new Vector2(410,310);
                break;
            case 18:velocity = new Vector2(420,320);
                break;
            case 19:velocity = new Vector2(430,330);
                break;
            case 20:velocity = new Vector2(440,340);
                break;

        }
        Random random = new Random();
        if(random.nextBoolean())
            velocity.x = -velocity.x;
        if(random.nextBoolean())
            velocity.y = -velocity.y;
    }

    public void update(float delta, Player player, Computer computer)
    {
        Rectangle rectangleBall = new Rectangle(position.x,position.y,Constants.BALL_RADIUS,Constants.BALL_RADIUS);
        Rectangle rectanglePlayer = new Rectangle(player.position.x,player.position.y,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT);
        Rectangle rectangleComputer = new Rectangle(computer.position.x,computer.position.y,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT);

        if(position.y-Constants.BALL_RADIUS<0)
            velocity.y = - velocity.y;
        else if(position.y+Constants.BALL_RADIUS > viewport.getWorldHeight())
            velocity.y = -velocity.y;
        else if(position.x-Constants.BALL_RADIUS <100 - Constants.PLAYER_WIDTH)
        {
            computer.points++;
            init();
        }
        else if(position.x+Constants.BALL_RADIUS > viewport.getWorldWidth())
        {
            player.points++;
            init();
        }
        else if(rectangleBall.overlaps(rectangleComputer))
            velocity.x = -velocity.x;
        else if(rectangleBall.overlaps(rectanglePlayer))
            velocity.x = -velocity.x;


        position.x+=delta*velocity.x;
        position.y+=delta*velocity.y;


    }

    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.WHITE);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.circle(position.x, position.y,Constants.BALL_RADIUS, 20);

    }
}
