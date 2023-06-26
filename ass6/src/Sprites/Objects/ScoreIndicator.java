package Sprites.Objects;

import GamePack.GameLevel;
import ProjectBasis.Counter;
import ProjectBasis.Rectangle;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {

    private Counter scoreIndicator;
    private Color color;
    private Rectangle rec;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreIndicator the score indicator
     */
    public ScoreIndicator(Counter scoreIndicator) {
        this.scoreIndicator = scoreIndicator;
    }

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreIndicator the score indicator
     * @param c              the c
     * @param rec            the rec
     */
    public ScoreIndicator(Counter scoreIndicator, Color c, Rectangle rec) {
        this.scoreIndicator = scoreIndicator;
        this.color = c;
        this.rec = rec;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(),
                (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(),
                (int) this.rec.getHeight());
        d.setColor(Color.black);
        d.drawText(350, 22, "Score:" + this.scoreIndicator.getValue(), 25);

        d.drawRectangle((int) this.rec.getUpperLeft().getX(),
                (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(),
                (int) this.rec.getHeight());
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
