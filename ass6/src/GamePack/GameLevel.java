package GamePack;

import Animations.Animation;
import Animations.AnimationRunner;
import Animations.CountdownAnimation;
import Animations.EndScreen;
import Animations.PauseScreen;
import Animations.KeyPressStoppableAnimation;
import Collidables.Objects.Block;
import Collidables.Collidable;
import Collidables.Objects.Paddle;
import Levels.LevelInformation;
import ProjectBasis.Counter;
import ProjectBasis.Point;
import Spies.BallRemove;
import Spies.BlockRemover;
import Spies.ScoreTrackingListener;
import Sprites.Objects.Ball;
import Sprites.Objects.BallLimits;
import Sprites.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private LevelInformation levelInformation;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private AnimationRunner runner;
    private boolean running;

    private static final int FRAME_X_START = 0;
    private static final int FRAME_Y_START = 0;
    private static final int FRAME_X_END = 800;
    private static final int FRAME_Y_END = 600;

    private static final int WIDTH_HEIGHT_BLOCKS_FRAME = 30;
    private static final int SCORE_HEIGHT = 25;
    private KeyboardSensor keyboard;
    private ScoreTrackingListener scoreTrackingListener;

    /**
     * Instantiates a new Game.
     *
     * @param sc                    the sc
     * @param ge                    the ge
     * @param keyboardSensor        the keyboard sensor
     * @param runner                the runner
     * @param levelInformation      the level information
     * @param scoreTrackingListener the score tracking listener
     */
    public GameLevel(SpriteCollection sc, GameEnvironment ge, KeyboardSensor keyboardSensor,
                     AnimationRunner runner,
                     LevelInformation levelInformation,
                     ScoreTrackingListener scoreTrackingListener) {
        this.sprites = sc;
        this.environment = ge;
        this.ballsCounter = new Counter();
        this.blocksCounter = new Counter();
        this.runner = runner;
        this.keyboard = keyboardSensor;
        this.levelInformation = levelInformation;
        this.running = true;
        this.scoreTrackingListener = scoreTrackingListener;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Create blocks.
     */
    public void createBlocks() {
        BlockRemover blockRemove = new BlockRemover(this, this.blocksCounter);
        BallRemove ballRemove = new BallRemove(this, this.ballsCounter);
        Block leftWall = new Block(new Point(FRAME_X_START, FRAME_Y_START + SCORE_HEIGHT
                + WIDTH_HEIGHT_BLOCKS_FRAME), WIDTH_HEIGHT_BLOCKS_FRAME,
                FRAME_Y_END - WIDTH_HEIGHT_BLOCKS_FRAME, Color.gray);
        Block topWall = new Block(new Point(FRAME_X_START, FRAME_Y_START + SCORE_HEIGHT),
                FRAME_X_END, WIDTH_HEIGHT_BLOCKS_FRAME, Color.gray);
        Block bottomWall = new Block(new Point(FRAME_X_START,
                FRAME_Y_END), FRAME_X_END, FRAME_Y_END, Color.gray);
        Block rightWall = new Block(new Point(FRAME_X_END - WIDTH_HEIGHT_BLOCKS_FRAME,
                WIDTH_HEIGHT_BLOCKS_FRAME + SCORE_HEIGHT), WIDTH_HEIGHT_BLOCKS_FRAME,
                FRAME_Y_END - WIDTH_HEIGHT_BLOCKS_FRAME, Color.gray);
        Block[] mainBlocks = new Block[]{leftWall, topWall, bottomWall,
                rightWall};
        for (Block block : mainBlocks) {
            block.addToGame(this);
        }
        bottomWall.addHitListener(ballRemove);
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemove);
            block.addHitListener(scoreTrackingListener);
        }
        this.blocksCounter.increase(this.levelInformation.numberOfBlocksToRemove());
    }

    /**
     * Create balls.
     */
    public void createBalls() {
        BallLimits bl = new BallLimits(FRAME_X_START, FRAME_Y_START,
                FRAME_X_END, FRAME_Y_END);
        for (Ball b : this.levelInformation.balls()) {
            b.setGameEnvironment(this.environment);
            b.addToGame(this);
            b.setBallLimits(bl);
        }
        this.ballsCounter.increase(this.levelInformation.numberOfBalls());
    }

    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        Paddle p = new Paddle(this, this.levelInformation.paddleStartX(),
                this.levelInformation.paddleWidth(),
                this.levelInformation.paddleSpeed(), this.keyboard);
        createBlocks();
        createBalls();
    }


    /**
     * Run.
     */
    public void run() {
        this.runner.setMilSeconds(600 / 3);
        this.runner.run(new CountdownAnimation(600, 3, this.sprites));
        this.running = true;
        this.runner.setMilSeconds(15);
        this.runner.run(this);

    }

    /**
     * Remmining balls int.
     *
     * @return the int
     */
    public int remainingBalls() {
        return this.ballsCounter.getValue();
    }

    @Override
    public boolean shouldStop() {
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
            Animation end =
                    new EndScreen(this.scoreTrackingListener.getCurrentScore(),
                            false);
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, end));
            return true;
        }
        if (this.blocksCounter.getValue() == 0) {
            this.scoreTrackingListener.getCurrentScore().increase(100);
            this.running = false;
            return true;
        }
        return false;
    }

    /**
     * Gets running.
     *
     * @return the running
     */
    public boolean getRunning() {
        return this.running;
    }


    @Override
    public void doOneFrame(DrawSurface d) {

        if (keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen();
            Animation keyPauseScreen =
                    new KeyPressStoppableAnimation(this.keyboard,
                            KeyboardSensor.SPACE_KEY,
                            pauseScreen);
            this.runner.run(keyPauseScreen);
        }
        this.sprites.notifyAllTimePassed();
        this.levelInformation.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.environment.drawAllOn(d);
        d.drawText(500, 22, "Game Level:" + this.levelInformation.levelName(),
                25);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.getColList().remove(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteList().remove(s);
    }


}