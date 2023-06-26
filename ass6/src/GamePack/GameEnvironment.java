package GamePack;

import Collidables.Collidable;
import Collidables.CollisionInfo;
import ProjectBasis.Line;
import ProjectBasis.Point;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {

    private List<Collidable> list;


    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.list = new ArrayList<>();
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        list.add(c);
    }

    /**
     * Gets col list.
     *
     * @return the col list
     */
    public List<Collidable> getColList() {
        return this.list;
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point[] arrPoints = new Point[list.size()];
        int index = 0, saverindex = 0;
        double min = -1;
        for (Collidable c : this.list) {
            arrPoints[index] = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (arrPoints[index] != null) {
                double dis = trajectory.start().distance(arrPoints[index]);
                if (min == -1) {
                    min = dis;
                    saverindex = index;
                } else {
                    if (min > dis) {
                        min = dis;
                        saverindex = index;
                    }
                }
            }
            index++;
        }
        if (min != -1) {
            return new CollisionInfo(arrPoints[saverindex], this.list.get(saverindex));
        }

        return null;

    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (Collidable c : this.list) {
            c.drawOn(d);
        }
    }

}