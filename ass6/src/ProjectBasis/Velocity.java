package ProjectBasis;

/**
 * The Velocity class specifies the change in position on the x and the y axes.
 */
public class Velocity {

    private double dx; // the change in position on the x axis
    private double dy; // the change in position on the y axis

    private double angle;

    private double speed;

    /**
     * Constructs a new Velocity object with given dx and dy values.
     *
     * @param dx the change in position on the x axis.
     * @param dy the change in position on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructs a new Velocity object with given dx and dy values.
     *
     * @param dx    the change in position on the x axis.
     * @param dy    the change in position on the y axis.
     * @param angle the angle
     * @param speed the speed
     */
    public Velocity(double dx, double dy, double angle, double speed) {
        this.dx = dx;
        this.dy = dy;
        this.angle = angle;
        this.speed = speed;
    }

    /**
     * Returns the change in position on the x axis.
     *
     * @return the change in position on the x axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the change in position on the y axis.
     *
     * @return the change in position on the y axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     *
     * @param p the point to apply the velocity to.
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Gets angle.
     *
     * @return the angle
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Sets angle.
     *
     * @param angle the angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Calculates and returns a new velocity from given angle and speed.
     *
     * @param angle the angle of the velocity.
     * @param speed the speed of the velocity.
     * @return a new velocity from given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = speed * Math.sin(angle);
        //we multiply in -1 because the y-axis is reverse
        double dy = -1 * speed * Math.cos(angle);
        return new Velocity(dx, dy, Math.toDegrees(angle), speed);
    }
}