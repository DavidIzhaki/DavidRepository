package Collidables.Objects;

import Collidables.Collidable;
import Spies.HitListener;
import ProjectBasis.Line;
import ProjectBasis.Point;
import ProjectBasis.Rectangle;
import ProjectBasis.Velocity;
import Sprites.Sprite;
import biuoop.DrawSurface;
import GamePack.GameLevel;
import Spies.HitNotifier;
import Sprites.Objects.Ball;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rec;
    private List<HitListener> hitListeners;
    private Color color;


    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param c         the c
     */
    public Block(Point upperLeft, double width, double height, Color c) {
        this.hitListeners = new ArrayList<>();
        rec = new Rectangle(upperLeft, width, height);
        this.color = c;
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }


    /**
     * Hit velocity.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {

        Line[] lines = this.rec.getArrLines();
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        double angle;
        //leftLine
        if (lines[0].isOnLine(collisionPoint)) {
            //move to right by negative dx
            dx = -1 * Math.abs(dx);
        }
        //upperLine
        if (lines[1].isOnLine(collisionPoint)) {
            //move to down by negative dy
            dy = -1 * Math.abs(dy);
        }
        //rightLine
        if (lines[2].isOnLine(collisionPoint)) {
            //move to left by positive dx
            dx = Math.abs(dx);
        }
        //downLine
        if (lines[3].isOnLine(collisionPoint)) {
            //move up by positive dy
            dy = Math.abs(dy);
        }

        double speed = Math.sqrt(dx * dx + dy * dy);
        angle = Math.toDegrees(Math.atan2(dx, -1 * dy));
        this.notifyHit(hitter);
        return new Velocity(dx, dy, angle, speed);
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rec.getUpperLeft().getX(),
                (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(),
                (int) this.rec.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rec.getUpperLeft().getX(),
                (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(),
                (int) this.rec.getHeight());
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Time passed.
     */
    public void timePassed() {

    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
