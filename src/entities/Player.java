package entities;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    private int x, y;
    private int width, height;
    private int speed;

    public Player(int startX, int startY, int width, int height) {
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        this.speed = 25;
    }

    public void move(int dx, int dy) {
        this.x += dx * speed;
        this.y += dy * speed;
    }

    public void render (Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
}
