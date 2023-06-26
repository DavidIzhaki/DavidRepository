package Animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    private Sleeper sleeper;
    private static final int FPS = 60;
    private int milSeconds;

    /**
     * Instantiates a new Animation.
     *
     * @param gui     the gui
     * @param fps     the fps
     * @param sleeper the sleeper
     */
    public AnimationRunner(GUI gui, int fps, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = fps;
        this.sleeper = sleeper;
        this.milSeconds = 15;
    }


    /**
     * Sets mil seconds.
     *
     * @param milSeconds the mil seconds
     */
    public void setMilSeconds(int milSeconds) {
        this.milSeconds = milSeconds;
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
// ...
    public void run(Animation animation) {
        int millisecondsPerFrame = milSeconds;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Close gui.
     */
    public void closeGui() {
        this.gui.close();
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return this.gui;
    }
}