package com.luarca84.diego.pong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by USUARIO on 27/08/2016.
 */
public class Computer {
    Vector2 position;
    Vector2 velocity;
    Viewport viewport;
    int points =0;

    public Computer(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        position = new Vector2(viewport.getWorldWidth()-Constants.PLAYER_WIDTH, viewport.getWorldHeight()/2);
        velocity = new Vector2(0,100);
    }

    public void update(float delta, Ball ball)
    {
        if (ball.position.y > position.y) {
            position.y += delta * velocity.y;
        }
        else if(ball.position.y < position.y+ Constants.PLAYER_HEIGHT){
            position.y -= delta * velocity.y;
        }

        ensureInBounds();
    }

    private void ensureInBounds() {

        if (position.y < 0) {
            position.y = 0;
        }
        if (position.y + Constants.PLAYER_HEIGHT > viewport.getWorldHeight()) {
            position.y = viewport.getWorldHeight() - Constants.PLAYER_HEIGHT;
        }
    }

    public void render(ShapeRenderer renderer)
    {
        renderer.setColor(Color.WHITE);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.rect(position.x,position.y,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT);

    }
}
