package Levels;

import Collidables.Objects.Block;
import GamePack.GameLevel;
import ProjectBasis.Line;
import ProjectBasis.Point;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level one back ground.
 */
public class LevelOneBackGround implements Sprite {
    private Block block;

    /**
     * constructor.
     *
     * @param block Block.
     */
    public LevelOneBackGround(Block block) {
        this.block = block;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.blue);
        ProjectBasis.Rectangle rec = this.block.getCollisionRectangle();
        ProjectBasis.Point middleOfBlockLeft = rec.getArrLines()[0].middle();
        ProjectBasis.Point middleOfBlockUp = rec.getArrLines()[1].middle();
        ProjectBasis.Point middleOfBlockRight = rec.getArrLines()[2].middle();
        ProjectBasis.Point middleOfBlockDown = rec.getArrLines()[3].middle();
        Point middleOfblock = new Line(middleOfBlockDown, middleOfBlockUp).middle();
        d.drawLine((int) middleOfBlockRight.getX() + 10,
                (int) middleOfBlockRight.getY(),
                (int) middleOfBlockRight.getX() + 140,
                (int) middleOfBlockRight.getY()); //right line.
        d.drawLine((int) middleOfBlockLeft.getX() - 10,
                (int) middleOfBlockLeft.getY(),
                (int) middleOfBlockLeft.getX() - 140,
                (int) middleOfBlockLeft.getY()); //left line.
        d.drawLine((int) middleOfBlockUp.getX(),
                (int) middleOfBlockUp.getY() - 10,
                (int) middleOfBlockUp.getX(),
                (int) middleOfBlockUp.getY() - 140); //up line.
        d.drawLine((int) middleOfBlockDown.getX(),
                (int) middleOfBlockDown.getY() + 10,
                (int) middleOfBlockDown.getX(),
                (int) middleOfBlockDown.getY() + 140); //down line.
        d.drawCircle((int) middleOfblock.getX(), (int) middleOfblock.getY(),
                50); //small circle.
        d.drawCircle((int) middleOfblock.getX(), (int) middleOfblock.getY(),
                90); //medium circle.
        d.drawCircle((int) middleOfblock.getX(), (int) middleOfblock.getY(),
                130); //big circle.
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
