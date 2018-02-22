package com.study.paint3.paint3;

//이벤트 상황을 담는 VO
public class Vertex {

    private float x;
    private float y;
    private int color;
    private boolean drawable;
    private String drawType;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }

    public String getDrawType() {
        return drawType;
    }

    public void setDrawType(String drawType) {
        this.drawType = drawType;
    }

    public Vertex() {
    }

    public Vertex(float x, float y, int color, boolean drawable) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.drawable = drawable;
    }

    public Vertex(float x, float y, int color, boolean drawable, String drawType) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.drawable = drawable;
        this.drawType = drawType;
    }
}
