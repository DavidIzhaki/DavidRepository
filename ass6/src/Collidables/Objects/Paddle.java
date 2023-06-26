package Collidables.Objects;

import Collidables.Collidable;
import ProjectBasis.Line;
import ProjectBasis.Point;
import ProjectBasis.Velocity;
import ProjectBasis.Rectangle;
import Sprites.Objects.Ball;
import Sprites.Sprite;
import GamePack.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    //which gui the paddle belongs

    //paddle is rec.
    private Rectangle rec;
    //Paddle color
    private Color color;
    private int width;
    private int paddleSpeed;
    // The start angle of the hit velocity of the paddle's left piece.
    private static final int START_ANGLE = -60;

    // The difference between the angles of the hit velocity of the piece.
    private static final int ANGLE_STEPS = 30;
    // The amount of pieces that the paddle have
    private static final int PIECES = 5;

    // paddle height
    private static final int PADDLE_HEIGHT = 22;

    //paddle y start
    private static final int START_PADDLE_Y = 560;

    // the end of where the paddle can move by left
    private static final int LEFT_LIMIT_X = 30;
    // the end of where the paddle can move by right
    private static final int RIGHT_LIMIT_X = 770;


    /**
     * Instantiates a new Paddle.
     *
     * @param g              the g
     * @param startX         the start x
     * @param width          the width
     * @param paddleSpeed    the paddle speed
     * @param keyboardSensor the keyboard sensor
     */
    public Paddle(GameLevel g, int startX, int width, int paddleSpeed,
                  KeyboardSensor keyboardSensor) {
        this.keyboard = keyboardSensor;
        this.width = width;
        this.rec = new Rectangle(new Point(startX, START_PADDLE_Y),
                width, PADDLE_HEIGHT);
        this.color = Color.orange;
        this.paddleSpeed = paddleSpeed;
        this.addToGame(g);
    }


    /**
     * Move left.
     */
    public void moveLeft() {
        Point upperLeft = this.rec.getUpperLeft();
        //the limit of the left frame
        if (upperLeft.getX() - paddleSpeed >= LEFT_LIMIT_X) {
            this.rec.setUpperLeft(new Point(upperLeft.getX() - paddleSpeed,
                    upperLeft.getY()));
        } else {
            this.rec.setUpperLeft(new Point(LEFT_LIMIT_X, upperLeft.getY()));
        }

    }

    /**
     * Move right.
     */
    public void moveRight() {
        Point upperLeft = this.rec.getUpperLeft();
        if (upperLeft.getX() + this.paddleSpeed <= RIGHT_LIMIT_X - this.width) {
            this.rec.setUpperLeft(new Point(upperLeft.getX() + this.paddleSpeed,
                    upperLeft.getY()));
        } else {
            this.rec.setUpperLeft(new Point(RIGHT_LIMIT_X - this.width,
                    upperLeft.getY()));
        }
    }

    /**
     * Time passed.
     */
    // Sprite
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(),
                (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(),
                (int) this.rec.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(),
                (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(),
                (int) this.rec.getHeight());
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
     * @param hitter          the ball that's hit
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] lines = this.rec.getArrLines();
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        double angle;
        //leftLine
        if (lines[0].isOnLine(collisionPoint)) {
            //move to right by negative dx
            dx = -1 * Math.abs(dx);
        }
        //rightLine
        if (lines[2].isOnLine(collisionPoint)) {
            //move to left by positive dx
            dx = Math.abs(dx);
        }
        //upperLine
        if (lines[1].isOnLine(collisionPoint)) {
            // The width of every piece.
            double piece = this.getCollisionRectangle().getWidth() / PIECES;
            for (int i = 0; i < PIECES; i++) {
                if (collisionPoint.getX() >= this.getCollisionRectangle().getUpperLeft().getX() + (i * piece)
                        && collisionPoint.getX() <= this.getCollisionRectangle().getUpperLeft().getX()
                        + ((i + 1) * piece)) {
                    return Velocity.fromAngleAndSpeed(START_ANGLE + i * ANGLE_STEPS, currentVelocity.getSpeed());

                }
            }
        }
        double speed = Math.sqrt(dx * dx + dy * dy);
        angle = Math.toDegrees(Math.atan2(dx, -1 * dy));

        return new Velocity(dx, dy, angle, speed);
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
}