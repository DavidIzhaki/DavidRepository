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
 * The type Level one.
 */
public class LevelOne implements LevelInformation {
    private Sprite backGround;

    /**
     * Instantiates a new Level one.
     */
    public LevelOne() {
        this.backGround = new LevelOneBackGround(blocks().get(0));
    }

    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ls = new ArrayList<>();
        ls.add(Velocity.fromAngleAndSpeed(0, 5));
        return ls;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public int paddleStartX() {
        return 350;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return this.backGround;
    }


    /**
     * Blocks list.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        List<Block> ls = new ArrayList<>();
        ls.add(new Block(new Point(385, 180), 30, 30, Color.RED));
        return ls;
    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public List<Ball> balls() {
        List<Ball> lsBalls = new ArrayList<>();
        List<Velocity> lsVelocity = initialBallVelocities();
        Ball b = new Ball(new Point(400, 550), 4,
                Color.WHITE);
        b.setVelocity(lsVelocity.get(0));
        lsBalls.add(b);
        return lsBalls;

    }
}
