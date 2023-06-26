package Spies;

import Collidables.Objects.Block;
import ProjectBasis.Counter;
import Sprites.Objects.Ball;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }

    /**
     * Gets current score.
     *
     * @return the current score
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }
}