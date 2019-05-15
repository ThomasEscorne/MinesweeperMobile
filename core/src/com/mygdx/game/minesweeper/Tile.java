package com.mygdx.game.minesweeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;


public class Tile {

    public enum State {
        HIDDEN,
        BOMB,
        FLAG,
        EMPTY,
        ERROR
    }

    private State m_state;
    private boolean m_isBomb;
    public BitmapFont m_TileNumber;
    private int m_AdjacentBombs;
    public Rectangle m_Rectangle;
    public Sprite m_Sprite;

    Tile(float _rectX, float _rectY, float _rectWidth) {
        m_state = State.HIDDEN;
        m_Sprite = new Sprite(new Texture(Gdx.files.internal("drawables/Cell.png")));
        m_Rectangle = new Rectangle(_rectX, _rectY, _rectWidth, _rectWidth);
        m_isBomb = false;
        m_AdjacentBombs = 0;
    }

    public State   getState() {
        return m_state;
    }
    public void    setState(State _state) {m_state = _state;}
    public boolean isBomb() {
        return m_isBomb;
    }
    public void setBomb() { m_isBomb = true; }
    public String getNbAdjacentBomb() {
        return (Integer.toString(m_AdjacentBombs));
    }
    public void setAdajcentBombNbr(int nbBomb) {
        if (nbBomb != 0){
            m_AdjacentBombs = nbBomb;
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/OperatorMono-Book.otf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 60;
            m_TileNumber = generator.generateFont(parameter);
            if (m_AdjacentBombs == 1)
                m_TileNumber.setColor(Color.BLUE);
            else if (m_AdjacentBombs == 2)
                m_TileNumber.setColor(Color.GREEN);
            else if (m_AdjacentBombs == 3)
                m_TileNumber.setColor(Color.RED);
            else if (m_AdjacentBombs == 4)
                m_TileNumber.setColor(Color.PURPLE);
            else if (m_AdjacentBombs == 5)
                m_TileNumber.setColor(Color.BROWN);
            else if (m_AdjacentBombs == 6)
                m_TileNumber.setColor(Color.BLACK);
            else
                m_TileNumber.setColor(Color.PINK);
            generator.dispose();
        }
    }
}
