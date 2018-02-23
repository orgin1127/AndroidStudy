package com.study.oxgame.oxgame;

import android.widget.Button;

public class Numbers {

    private Button bt;
    private boolean checkable;
    private int playerMark;

    public Button getBt() {
        return bt;
    }

    public void setBt(Button bt) {
        this.bt = bt;
    }

    public boolean isCheckable() {
        return checkable;
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }

    public int getPlayerMark() {
        return playerMark;
    }

    public void setPlayerMark(int playerMark) {
        this.playerMark = playerMark;
    }

    public Numbers() {
    }

    public Numbers(Button bt) {
        this.bt = bt;
    }

    public Numbers(Button bt, boolean checkable) {
        this.bt = bt;
        this.checkable = checkable;
    }

    public Numbers(Button bt, boolean checkable, int playerMark) {
        this.bt = bt;
        this.checkable = checkable;
        this.playerMark = playerMark;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
