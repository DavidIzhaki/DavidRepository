package Animations;

import ProjectBasis.Counter;
import biuoop.DrawSurface;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private Counter score;
    private boolean winner;

    /**
     * Instantiates a new End screen.
     *
     * @param score  the score
     * @param winner the winner
     */
    public EndScreen(Counter score, boolean winner) {
        this.score = score;
        this.winner = winner;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.winner) {
            d.drawText(10, d.getHeight() / 2,
                    "Game Over. Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2,
                    "You Win! Your score is " + this.score.getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
