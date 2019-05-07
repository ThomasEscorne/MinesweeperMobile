package com.mygdx.game.minesweeper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;


public class MineSweeperGame extends ApplicationAdapter {

    private static MineSweeperGame s_instance;

    public static MineSweeperGame getInstance(){
        if (s_instance == null) {
            s_instance = new MineSweeperGame();
        }
        return s_instance;
    }

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private int ScreenWidth;
    private int ScreenHeight;
    public int BoardWidth;
    public int BoardHeight;
    public Tile[][] Board;
    private Sprite sprite;
    private GestureListener _gestureListener;

    @Override
    public void create() {

        _gestureListener = new GestureListener();
        Gdx.input.setInputProcessor(new GestureDetector(_gestureListener));

        BoardWidth = 18;
        BoardHeight = 25;
        Board = new Tile[BoardWidth][BoardHeight];
        sprite = new Sprite(new Texture(Gdx.files.internal("drawables/Cell.png")));
        for (int i = 0; i < BoardWidth; i++)
        {
            for (int j = 0; j < BoardHeight; j++)
            {
                Board[i][j] = new Tile(false);
            }
        }

        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OperatorMono-Book.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;
        font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);
        generator.dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();

        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.6f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Mine Sweeper", (int)(ScreenWidth / 3), (int)(ScreenHeight - ScreenHeight / 10));
        for (int i = 0; i < BoardWidth; i++)
        {
            for (int j = 0; j < BoardHeight; j++)
            {
                batch.draw(Board[i][j].m_Sprite, i * ScreenWidth / BoardWidth, (int)(ScreenHeight / 20) + j * ScreenWidth / BoardWidth, ScreenWidth / BoardWidth, ScreenWidth / BoardWidth);
            }
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
