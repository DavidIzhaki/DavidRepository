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
 * The type Level three.
 */
public class LevelThree implements LevelInformation {
    private Sprite backGround;

    private Color[] color = new Color[5];
    private static final int BLOCK_HEIGHT = 20;
    private static final double BLOCK_WIDTH = 148.2 / 3;

    /**
     * constructor.
     */
    public LevelThree() {
        color[0] = Color.GRAY;
        color[1] = Color.RED;
        color[2] = Color.YELLOW;
        color[3] = Color.BLUE;
        color[4] = Color.WHITE;
        this.backGround = new LevelThreeBackGround();
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ls = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            int angle = 310 + i * 100;
            double dx = Velocity.fromAngleAndSpeed(angle, 4).getDx();
            double dy = Velocity.fromAngleAndSpeed(-angle, 4).getDy();
            Velocity velocity = new Velocity(dx, dy, angle, 4);
            ls.add(velocity);
        }
        return ls;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public int paddleStartX() {
        return 350;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return this.backGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> ls = new ArrayList<>();
        for (int i = 0; i < 5; i++) { //create rows of blocks and
            for (int j = i; j < 10; j++) {
                Block block = new Block(new Point(770 - BLOCK_WIDTH * (j + 1 - i),
                        200 + i * BLOCK_HEIGHT),
                        BLOCK_WIDTH, BLOCK_HEIGHT, color[i]);
                ls.add(block);
            }
        }
        return ls;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
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
}
