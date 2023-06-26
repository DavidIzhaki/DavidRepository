package Levels;

import Collidables.Objects.Block;
import ProjectBasis.Point;
import ProjectBasis.Velocity;
import Sprites.Objects.Ball;
import Sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level two.
 */
public class LevelTwo implements LevelInformation {
    /**
     * constructor.
     */
    private Sprite backGround;

    private static final int BLOCK_HEIGHT = 20;
    private static final double BLOCK_WIDTH = 148.2 / 3;

    /**
     * Instantiates a new Level two.
     */
    public LevelTwo() {
        this.backGround = new LevelTwoBackGround();
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ls = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            int angle = 310 + i * 10;
            double dx = Velocity.fromAngleAndSpeed(angle, 4).getDx();
            double dy = Velocity.fromAngleAndSpeed(-angle, 4).getDy();
            ls.add(new Velocity(dx, dy, angle, 4));
        }
        return ls;
    }


    @Override
    public List<Ball> balls() {
        List<Ball> lsBalls = new ArrayList<>();
        List<Velocity> lsVelocity = initialBallVelocities();
        double startX = 400;
        int startY = 550;
        Ball b;
        for (int i = 0; i < numberOfBalls(); i++) {
            b = new Ball(new Point(startX, startY), 4,
                    Color.WHITE);
            b.setVelocity(lsVelocity.get(i));
            lsBalls.add(b);
        }
        return lsBalls;
    }


    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 700;
    }

    @Override
    public int paddleStartX() {
        return 60;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return this.backGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> ls = new ArrayList<>();
        Color c = Color.red;
        for (int i = 0; i < 15; i++) {
            ls.add(new Block(new Point(30 + i * BLOCK_WIDTH, 250),
                    148.2 / 3,
                    BLOCK_HEIGHT,
                    c));
            if (i == 1) {
                c = Color.orange;
            }
            if (i == 3) {
                c = Color.yellow;
            }
            if (i == 5) {
                c = Color.green;
            }
            if (i == 8) {
                c = Color.BLUE;
            }
            if (i == 10) {
                c = Color.pink;
            }
            if (i == 12) {
                c = Color.CYAN;
            }
        }
        return ls;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
