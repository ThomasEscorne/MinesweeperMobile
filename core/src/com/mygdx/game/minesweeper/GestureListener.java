package com.mygdx.game.minesweeper;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import sun.security.util.Debug;

public class GestureListener implements GestureDetector.GestureListener {

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        //for (int i = 0; i < MineSweeperGame.getInstance().BoardWidth; i++)
        //{
        //    for (int j = 0; j < MineSweeperGame.getInstance().BoardHeight; j++)
        //    {
        //        if (MineSweeperGame.getInstance().Board[i][j].m_Sprite.contains)
        //    }
        //}
        System.out.println("Taped screen at " + x + " ," + y);
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {

        System.out.println("Longpress screen at " + x + " ," + y);
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean zoom (float originalDistance, float currentDistance){

        return false;
    }

    @Override
    public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer){

        return false;
    }
    @Override
    public void pinchStop () {
    }
}