package Animations;

import GamePack.SpriteCollection;
import biuoop.DrawSurface;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private double countFrom;
    private SpriteCollection gameScreen;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.drawText(350, 350, "" + (int) countFrom, 150);
        countFrom--;
    }

    @Override
    public boolean shouldStop() {
        if (countFrom == 0) {
            return true;
        }
        return false;
    }
}