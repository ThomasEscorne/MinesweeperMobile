package com.mygdx.game.minesweeper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;


public class GestureListener implements GestureDetector.GestureListener {

    private boolean longTouch;

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        if (longTouch) {
            longTouch = false;
            return false;
        }

        y = Gdx.graphics.getHeight() - 1 - y;
        //System.out.println("Taped screen at " + x + " ," + y);
        for (int i = 0; i < MineSweeperGame.BoardWidth; i++)
        {
            for (int j = 0; j < MineSweeperGame.BoardHeight; j++)
            {
                if (MineSweeperGame.Board[i][j].getState() == Tile.State.HIDDEN) {

                    if (MineSweeperGame.Board[i][j].m_Rectangle.contains(x, y)) {

                            if (MineSweeperGame.Board[i][j].isBomb()) {
                                MineSweeperGame.Board[i][j].m_Sprite.setTexture(new Texture(Gdx.files.internal("drawables/ExplodedMineCell.png")));
                                MineSweeperGame.Board[i][j].setState(Tile.State.BOMB);
                            } else {
                                UnveilGoodTiles(i, j);
                            }

                    }
                }
            }
        }
        return false;
    }

    private int CountSurroundingBombs(int x, int y) {
        int res = 0;

        if (x > 0 && y > 0){
            if (MineSweeperGame.Board[x - 1][y - 1].isBomb())
               res ++;
        }

        if (y > 0) {
            if (MineSweeperGame.Board[x][y - 1].isBomb())
                res++;
        }

        if (x < MineSweeperGame.BoardWidth -1 && y > 0) {
            if (MineSweeperGame.Board[x + 1][y - 1].isBomb())
                res++;
        }

        if (x > 0) {
            if (MineSweeperGame.Board[x - 1][y].isBomb())
                res++;
        }

        if (x < MineSweeperGame.BoardWidth -1) {
            if (MineSweeperGame.Board[x + 1][y].isBomb())
                res++;
        }

        if (x > 0 && y < MineSweeperGame.BoardHeight -1) {
            if (MineSweeperGame.Board[x - 1][y + 1].isBomb())
                res++;
        }

        if (y < MineSweeperGame.BoardHeight-1) {
            if (MineSweeperGame.Board[x][y + 1].isBomb())
                res++;
        }

        if (x < MineSweeperGame.BoardWidth-1 && y < MineSweeperGame.BoardHeight-1) {
            if (MineSweeperGame.Board[x + 1][y + 1].isBomb())
                res++;
        }
        return res;
    }

    private void UnveilGoodTiles(int x, int y) {
        MineSweeperGame.Board[x][y].m_Sprite.setTexture(new Texture(Gdx.files.internal("drawables/EmptyCell.png")));
        MineSweeperGame.Board[x][y].setState(Tile.State.EMPTY);
        MineSweeperGame.Board[x][y].setAdajcentBombNbr(CountSurroundingBombs(x, y));

        if (CountSurroundingBombs(x, y) != 0)
            return;
        else
        {
            if (x > 0 && y > 0){
                if (!MineSweeperGame.Board[x-1][y-1].isBomb() && MineSweeperGame.Board[x-1][y-1].getState() == Tile.State.HIDDEN)
                    UnveilGoodTiles(x-1, y-1);
            }

            if (y > 0) {
                if (!MineSweeperGame.Board[x][y-1].isBomb() && MineSweeperGame.Board[x][y-1].getState() == Tile.State.HIDDEN)
                    UnveilGoodTiles(x, y-1);
            }

            if (x < MineSweeperGame.BoardWidth -1 && y > 0) {
                if (!MineSweeperGame.Board[x+1][y-1].isBomb() && MineSweeperGame.Board[x+1][y-1].getState() == Tile.State.HIDDEN)
                    UnveilGoodTiles(x + 1, y-1);
            }

            if (x > 0) {
                if (!MineSweeperGame.Board[x-1][y].isBomb() && MineSweeperGame.Board[x-1][y].getState() == Tile.State.HIDDEN)
                    UnveilGoodTiles(x -1 , y);
            }

            if (x < MineSweeperGame.BoardWidth -1) {
                if (!MineSweeperGame.Board[x+1][y].isBomb() && MineSweeperGame.Board[x+1][y].getState() == Tile.State.HIDDEN)
                    UnveilGoodTiles(x + 1, y);
            }

            if (x > 0 && y < MineSweeperGame.BoardHeight -1) {
                if (!MineSweeperGame.Board[x-1][y+1].isBomb() && MineSweeperGame.Board[x-1][y+1].getState() == Tile.State.HIDDEN)
                    UnveilGoodTiles(x -1, y+1);
            }

            if (y < MineSweeperGame.BoardHeight-1) {

                if (!MineSweeperGame.Board[x][y+1].isBomb() && MineSweeperGame.Board[x][y+1].getState() == Tile.State.HIDDEN)
                    UnveilGoodTiles(x, y+1);
            }

            if (x < MineSweeperGame.BoardWidth-1 && y < MineSweeperGame.BoardHeight-1) {
                if (!MineSweeperGame.Board[x+1][y+1].isBomb() && MineSweeperGame.Board[x+1][y+1].getState() == Tile.State.HIDDEN)
                    UnveilGoodTiles(x + 1, y + 1);
            }
        }
    }

    @Override
    public boolean longPress(float x, float y) {

        longTouch = true;
        y = Gdx.graphics.getHeight() - 1 - y;
        for (int i = 0; i < MineSweeperGame.BoardWidth; i++)
        {
            for (int j = 0; j < MineSweeperGame.BoardHeight; j++)
            {
                if (MineSweeperGame.Board[i][j].m_Rectangle.contains(x, y)) {

                    if (MineSweeperGame.Board[i][j].getState() == Tile.State.HIDDEN) {
                        MineSweeperGame.Board[i][j].m_Sprite.setTexture(new Texture(Gdx.files.internal("drawables/FlaggedCell.png")));
                        MineSweeperGame.Board[i][j].setState(Tile.State.FLAG);
                    }
                    else if (MineSweeperGame.Board[i][j].getState() == Tile.State.FLAG) {
                        MineSweeperGame.Board[i][j].m_Sprite.setTexture(new Texture(Gdx.files.internal("drawables/Cell.png")));
                        MineSweeperGame.Board[i][j].setState(Tile.State.HIDDEN);
                    }
                }
            }
        }
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