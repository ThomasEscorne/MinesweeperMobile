package com.mygdx.game.minesweeper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;

import java.util.Random;

public class MineSweeperGame extends ApplicationAdapter {
    
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private int ScreenWidth;
    private int ScreenHeight;
    private int NbBombs;
    public static int BoardWidth;
    public static int BoardHeight;
    public static Tile[][] Board;

    @Override
    public void create() {

        GestureListener _gestureListener = new GestureListener();
        Gdx.input.setInputProcessor(new GestureDetector(_gestureListener));

        BoardWidth = 18;
        BoardHeight = 25;
        NbBombs = 60;
        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();
        Board = new Tile[BoardWidth][BoardHeight];

        //Create Board
        for (int i = 0; i < BoardWidth; i++)
        {
            for (int j = 0; j < BoardHeight; j++)
            {
                float _rectX = (float)i * (float)ScreenWidth / (float)BoardWidth;
                float _rectY = (float)ScreenHeight / (float)20 + (float)j * (float)ScreenWidth / (float)BoardWidth;
                float _rectWidth = (float)ScreenWidth / (float)BoardWidth;

                Board[i][j] = new Tile(_rectX, _rectY, _rectWidth);
            }
        }

        for (int i = 0; i < NbBombs; i++) {
            //Get random position for the next bomb
            Random rand = new Random();
            int row = rand.nextInt(BoardWidth);
            int col = rand.nextInt(BoardHeight);
            while(Board[row][col].isBomb()) { //if this position is a bomb
                //we get new position
                row = rand.nextInt(BoardWidth);
                col = rand.nextInt(BoardHeight);
            }
            Board[row][col].setBomb();
        }


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
                batch.draw(Board[i][j].m_Sprite, Board[i][j].m_Rectangle.x, Board[i][j].m_Rectangle.y , Board[i][j].m_Rectangle.width, Board[i][j].m_Rectangle.height);
                if (Board[i][j].getState() == Tile.State.EMPTY)
                {
                    if (Board[i][j].m_TileNumber != null)
                        Board[i][j].m_TileNumber.draw(batch, Board[i][j].getNbAdjacentBomb(), Board[i][j].m_Rectangle.x + ((float)ScreenWidth / (float)BoardWidth) / 3, Board[i][j].m_Rectangle.y  + ((float)ScreenWidth / (float)BoardWidth) / 1.5f);
                }
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
