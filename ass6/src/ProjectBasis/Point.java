package ProjectBasis;

/**
 * The Point class represents a point in a two-dimensional coordinate system.
 */

public class Point {
    /**
     * The x-coordinate of this point.
     */
    private double x;

    /**
     * The y-coordinate of this point.
     */
    private double y;

    public static final double EPSILON = Math.pow(10, -7);

    /**
     * Constructs a new point with the given x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double width = this.x - other.getX();
        double height = this.y - other.getY();
        return Math.sqrt(width * width + height * height);
    }

    static boolean thresholdComparison(double loc1, double loc2) {
        return (Math.abs(loc1 - loc2) < EPSILON);
    }


    /**
     * Compares this point to another point to see if they are equal.
     *
     * @param other the other point
     * @return true if the two points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (thresholdComparison(other.getX(), this.x)
                && thresholdComparison(other.getY(), this.y));
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
}