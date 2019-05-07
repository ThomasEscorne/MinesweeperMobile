package com.mygdx.game.minesweeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tile {

    enum State {
        HIDDEN,
        CLICKED,
        BOMB,
        FLAG,
        EMPTY,
        ERROR
    }

    private State m_state;
    private boolean m_isBomb;
    public Sprite m_Sprite;

    Tile(boolean _isBomb) {
        m_state = State.HIDDEN;
        m_isBomb = _isBomb;
        m_Sprite = new Sprite(new Texture(Gdx.files.internal("drawables/Cell.png")));
    }

    State   getState() {
        return m_state;
    }

    boolean isBomb() {
        return m_isBomb;
    }
}
