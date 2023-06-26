package Sprites.Objects;

import GamePack.GameEnvironment;
import GamePack.GameLevel;
import Collidables.CollisionInfo;
import ProjectBasis.Point;
import ProjectBasis.Line;
import ProjectBasis.Velocity;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Ball class represents a ball object.
 * A Ball object is defined by its center, radius, and color.
 */
public class Ball implements Sprite {

    /**
     * The center point of the ball.
     */
    private Point center;
    /**
     * The radius of the ball.
     */
    private int radius;
    /**
     * The color of the ball.
     */
    private Color color;
    /**
     * The Velocity of the ball.
     */
    private Velocity v;
    /**
     * List of Collidables that the ball can hit.
     */
    private GameEnvironment ge;

    private BallLimits ballLimits;
    //start angle for dx dy
    private static final int START_ANGLE = 60;
    //ball speed
    private static final int SPEED = 8;
    //Limit of the ball by X
    private static final int LIMIT_X = 30;
    //Limit of the ball by Y
    private static final int LIMIT_Y = 55;


    /**
     * Creates a new ball object with the given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }


    /**
     * Creates a new ball object with the given x and y coordinates of the center point,
     * radius, and color.
     *
     * @param x     the x coordinate of the center point of the ball
     * @param y     the y coordinate of the center point of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Sets ball limits.
     *
     * @param bl the bl
     */
    public void setBallLimits(BallLimits bl) {
        this.ballLimits = bl;
    }

    /**
     * Sets game environment.
     *
     * @param ge the ge
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.ge = ge;
    }


    /**
     * Returns the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * Sets the velocity of the ball to the given velocity.
     *
     * @param v the new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball to the given dx and dy values.
     *
     * @param dx the new x-component of the velocity.
     * @param dy the new y-component of the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy, this.v.getAngle(), this.v.getSpeed());
    }

    /**
     * Sets first velocity.
     */
    public void setFirstVelocity() {
        Velocity v = Velocity.fromAngleAndSpeed(START_ANGLE, SPEED);
        this.v = v;
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Moves the ball one step according to its current velocity.
     */
    public void moveOneStep() {

        double x = this.center.getX();
        double y = this.center.getY();
        Velocity velocity = Velocity.fromAngleAndSpeed(this.v.getAngle(), 2 * Math.max(this.radius, this.v.getSpeed()));

        CollisionInfo c = ge.getClosestCollision(new Line(this.center, velocity.applyToPoint(this.center)));
        if (c != null) {
            this.v = c.collisionObject().hit(this, c.collisionPoint(), this.v);
        }

        this.center = this.getVelocity().applyToPoint(new Point(x, y));
        if (this.center.getY() + this.radius <= this.ballLimits.getStartY()) {
            this.center = new Point(this.center.getX(), this.ballLimits.getStartY() + this.radius + LIMIT_Y);
        }
        if (this.center.getX() + this.radius >= this.ballLimits.getEndX()) {
            this.center = new Point(this.ballLimits.getEndX() - radius - LIMIT_X, this.center.getY());
        }
        if (this.center.getX() + this.radius <= this.ballLimits.getStartX()) {
            this.center = new Point(this.ballLimits.getStartX() + this.radius + LIMIT_Y, this.center.getY());
        }

    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), this.radius);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Time passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}