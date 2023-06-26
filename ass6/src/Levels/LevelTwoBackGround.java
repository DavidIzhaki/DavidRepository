package Levels;

import GamePack.GameLevel;
import ProjectBasis.Point;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level two back ground.
 */
public class LevelTwoBackGround implements Sprite {
    /**
     * constructor.
     */
    public LevelTwoBackGround() {
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.yellow);
        d.drawCircle(150, 160, 30); //small circle.
        d.drawCircle(150, 160, 40); //medium circle.
        d.drawCircle(150, 160, 50); //big circle.
        Color myColor = new Color(255, 255, 0);
        d.setColor(myColor);
        for (int i = 30; i <= 700; i += 10) {
            Point p = new Point(i, 250);
            d.drawLine((int) p.getX(), (int) p.getY(), 150, 160);
        }
        myColor = new Color(255, 255, 205);
        d.setColor(myColor);
        d.fillCircle(150, 160, 50); //big circle.
        myColor = new Color(255, 255, 100);
        d.setColor(myColor);
        d.fillCircle(150, 160, 40); //medium circle.
        myColor = new Color(255, 255, 0);
        d.setColor(myColor);
        d.fillCircle(150, 160, 30); //small circle.
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
