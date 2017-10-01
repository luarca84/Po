package com.luarca84.diego.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by USUARIO on 27/08/2016.
 */
public class PlayScreen extends InputAdapter implements Screen{
    PongGame game;
    SpriteBatch batch;
    BitmapFont font;
    FitViewport viewport;
    ShapeRenderer renderer;
    Ball ball;
    Player player;
    Computer computer;
    int level;
    int maxpoints = 5;

    public PlayScreen(PongGame game,int level)
    {

        this.game = game;
        this.level = level;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        viewport = new FitViewport(Constants.DIFFICULTY_WORLD_SIZE_WIDTH, Constants.DIFFICULTY_WORLD_SIZE_HEIGHT);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        ball = new Ball(viewport, level);
        player = new Player(viewport);
        computer = new Computer(viewport);

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        if(computer.points == maxpoints)
        {
            game.ShowStatusScreen(false,level);
        }
        else if(player.points == maxpoints)
        {
            game.ShowStatusScreen(true,level);
        }
        else
        {
            ball.update(delta, player, computer);
            player.update(delta);
            computer.update(delta, ball);
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            viewport.apply();
            batch.setProjectionMatrix(viewport.getCamera().combined);
            batch.begin();
            float width = viewport.getWorldWidth();
            float height = viewport.getWorldHeight();
            font.setColor(Color.WHITE);
            font.draw(batch, "Player: " + player.points + "  Computer: " + computer.points, width / 2 - 100, height - 20);
            font.draw(batch, "Level: " + level , width / 2 - 200, height - 20);
            font.setColor(Color.CYAN);
            font.draw(batch, "Up", 40, viewport.getWorldHeight() - 40);
            font.draw(batch, "Down", 40, 60);
            batch.end();

            renderer.setProjectionMatrix(viewport.getCamera().combined);
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.setColor(Color.CYAN);
            renderer.circle(50, 50, 49, 20);
            renderer.circle(50, viewport.getWorldHeight() - 50, 49, 20);
            renderer.rect(100, 1, viewport.getWorldWidth() - 100, viewport.getWorldHeight() - 2);
            renderer.set(ShapeRenderer.ShapeType.Filled);
            ball.render(renderer);
            player.render(renderer);
            computer.render(renderer);
            renderer.end();


        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        ball.init();
        player.init();
        computer.init();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            game.ShowMenuScreen();
        }
        return false;
    }
}
