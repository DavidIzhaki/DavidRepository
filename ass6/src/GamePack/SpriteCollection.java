package GamePack;

import Sprites.Objects.Ball;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;


    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        spriteList = new ArrayList<Sprite>();
    }


    /**
     * Gets sprite list.
     *
     * @return the sprite list
     */
    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> newSpriteList = new ArrayList<>();
        newSpriteList.addAll(this.spriteList);
        for (Sprite s : newSpriteList) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.spriteList) {
            s.drawOn(d);
        }
    }

    /**
     * Gets balls.
     *
     * @return the balls
     */
    public List<Ball> getBalls() {
        return spriteList.stream()
                .filter(s -> s.getClass() == Ball.class)
                .map(s -> (Ball) s)
                .collect(Collectors.toList());
    }
}