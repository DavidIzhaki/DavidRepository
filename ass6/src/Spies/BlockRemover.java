package Spies;

import Collidables.Objects.Block;
import GamePack.GameLevel;
import ProjectBasis.Counter;
import Sprites.Objects.Ball;

/**
 * The type Block remover.
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel     the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;

    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public Counter getCounter() {
        return this.remainingBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        remainingBlocks.decrease(1);
    }


}