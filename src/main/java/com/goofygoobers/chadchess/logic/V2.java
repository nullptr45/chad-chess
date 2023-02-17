package com.goofygoobers.chadchess.logic;

import java.util.Vector;

public class V2 {
    private int x;
    private int y;

    public V2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public V2() {

    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public V2 subtract(V2 vector) {
        int dx = x - vector.getX();
        int dy = y - vector.getY();

        return new V2(dx, dy);
    }

    public V2 add(V2 vector) {
        int dx = x + vector.getX();
        int dy = y + vector.getY();

        return new V2(dx, dy);
    }

    public V2 multiply(int factor) {
        return new V2(x*factor, y*factor);
    }

    public V2 divide(int factor) {
        return new V2(x/factor, y/factor);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof V2)) {
            return false;
        }

        V2 vector = (V2) obj;

        if(vector.getX() == getX() && vector.getY() == getY()) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "x:" + x + ", y:" + y;
    }
}
