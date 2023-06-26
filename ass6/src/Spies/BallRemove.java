package Spies;

import Collidables.Objects.Block;
import GamePack.GameLevel;
import ProjectBasis.Counter;
import Sprites.Objects.Ball;

/**
 * The type Ball remove.
 */
public class BallRemove implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel     the game
     * @param removedBlocks the removed blocks
     */
    public BallRemove(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBlocks;

    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public Counter getCounter() {
        return this.remainingBalls;
    }


    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}
