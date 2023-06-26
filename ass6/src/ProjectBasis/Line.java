package ProjectBasis;

import java.util.List;

/**
 * Represents a line in a two-dimensional coordinate system.
 */
public class Line {
    /**
     * The starting point of the line.
     */
    private Point start;

    /**
     * The ending point of the line.
     */
    private Point end;

    /**
     * The constant EPSILON.
     */
    public static final double EPSILON = Math.pow(10, -7);

    /**
     * Constructs a new line with the specified starting and ending points.
     *
     * @param start the starting point of the line.
     * @param end   the ending point of the line.
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new line with the specified x and y coordinates
     * for the starting and ending points. If the starting point has a
     * greater x value than the ending point, they are swapped.
     * If the starting point has the same x value as the ending point,
     * they are swapped if the starting point has a greater y value.
     *
     * @param x1 the x coordinate of the starting point.
     * @param y1 the y coordinate of the starting point.
     * @param x2 the x coordinate of the ending point.
     * @param y2 the y coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Gets min point.
     *
     * @param p1 the p 1
     * @param p2 the p 2
     * @return the min point
     */
    public Point getMinPoint(Point p1, Point p2) {
        if (p1.getX() > p2.getX()) {
            return p2;
        } else {
            if (p1.getX() < p2.getX()) {
                return p1;
            } else {
                if (p1.getY() < p2.getY()) {
                    return p1;
                } else {
                    return p2;
                }
            }
        }
    }

    /**
     * Calculates the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculates the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point(Math.abs(this.start.getX() + this.end.getX()) / 2,
                Math.abs(this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return the ending point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Determines whether the given line is parallel to the y-axis.
     *
     * @return true if the line is parallel to the y-axis, false otherwise.
     */
    public boolean isParallelToY() {
        return (this.start.getX() - this.end.getX()) == 0;
    }

    /**
     * Determines whether the given line is parallel to the x-axis.
     *
     * @return true if the line is parallel to the x-axis, false otherwise.
     */
    public boolean isParallelToX() {
        return (this.start.getY() - this.end.getY()) == 0;
    }

    /**
     * Calculates the free number in the equation y = mx + b for the
     * given slope and point.
     *
     * @param slope the slope of the line.
     * @param point the point on the line.
     * @return the free number in the equation y = mx + b.
     */
    static double findFreeNumber(double slope, Point point) {
        return point.getY() - slope * point.getX();
    }

    /**
     * Calculates the x-coordinate of the intersection point between two lines.
     *
     * @param line1 the first line.
     * @param line2 the second line.
     * @return the x-coordinate of the intersection point.
     */
    static double findIntersectionPointX(Line line1, Line line2) {
        double slope1 = findSlope(line1);
        double slope2 = findSlope(line2);
        double freeNumber1 = findFreeNumber(slope1, line1.start());
        double freeNumber2 = findFreeNumber(slope2, line2.start());
        return (freeNumber2 - freeNumber1) / (slope1 - slope2);
    }

    /**
     * Calculates the y-coordinate of the intersection point between
     * a line and a given x-coordinate.
     *
     * @param line the line.
     * @param x    the x-coordinate.
     * @return the y-coordinate of the intersection point.
     */
    static double findIntersectionPointY(Line line, double x) {
        double slope = findSlope(line);
        return slope * x + findFreeNumber(slope, line.start());
    }

    /**
     * Calculates the slope of this line.
     *
     * @param line the line to calculate the slope of
     * @return the slope of the line
     */
    static double findSlope(Line line) {
        return (line.end.getY() - line.start.getY())
                / (line.end.getX() - line.start.getX());
    }

    /**
     * Checks if a given x-coordinate is on this line.
     *
     * @param x the x-coordinate to check
     * @return true if the given x-coordinate is on the line, false otherwise
     */
    public boolean isOnLineByXCoordination(double x) {
        return x >= this.start().getX() && x <= this.end().getX();
    }

    /**
     * Checks if a given y-coordinate is on this line.
     *
     * @param y the x-coordinate to check
     * @return true if the given x-coordinate is on the line, false otherwise
     */
    public boolean isOnLineByYCoordination(double y) {
        return y >= this.start().getY() && y <= this.end().getY();
    }


    /**
     * Is on line boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean isOnLine(Point p) {
        double distanceA = this.start.distance(p);
        double distanceB = this.end.distance(p);

        return (Math.abs(this.length() - (distanceA + distanceB)) < EPSILON);
    }

    /**
     * Is on edges boolean.
     *
     * @param p     the p
     * @param line1 the line 1
     * @param line2 the line 2
     * @return the boolean
     */
    static boolean isOnEdges(Point p, Line line1, Line line2) {
        if (line1.start().equals(p) && line2.start().equals(p)) {
            return true;
        }
        if (line1.start().equals(p) && line2.end().equals(p)) {
            return true;
        }
        if (line1.end().equals(p) && line2.start().equals(p)) {
            return true;
        }
        return line1.end().equals(p) && line2.end().equals(p);
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other the other line to check for intersection with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.start.equals(other.end) || this.start.equals(other.start)
                || this.end.equals(other.start) || this.end.equals(other.end)) {
            return true;
        }
        Line thisCheck, otherCheck;
        if (getMinPoint(this.start, this.end) == this.start) {
            thisCheck = new Line(this.start, this.end);
        } else {
            thisCheck = new Line(this.end, this.start);
        }
        if (getMinPoint(other.start(), other.end()) == other.start()) {
            otherCheck = new Line(other.start(), other.end());
        } else {
            otherCheck = new Line(other.end(), other.start());
        }


        //checks if the first line is parallel to the y-axis
        if (thisCheck.isParallelToY()) {
            //checks if the second line is parallel to the y-axis
            if (otherCheck.isParallelToY()) {
                //checks if they on the same x
                if (thisCheck.start().getX() == otherCheck.start().getX()) {
                    //two checks if the line is start between the other line
                    if (otherCheck.isOnLineByYCoordination(thisCheck.start().getY())) {
                        return true;
                    }
                    if (thisCheck.isOnLineByYCoordination(otherCheck.start().getY())) {
                        return true;
                    }
                }
                return false;
            }
            //the last check for parallel to the x-axis and a normal line
            if (otherCheck.isOnLineByXCoordination(thisCheck.start().getX())) {
                if (otherCheck.isParallelToX()) {
                    if (thisCheck.isOnLineByYCoordination(otherCheck.start().getY())) {
                        return true;
                    }
                    return false;
                }
                double intersectionPointY = findIntersectionPointY(otherCheck,
                        thisCheck.start().getX());
                return (thisCheck.isOnLineByYCoordination(intersectionPointY)
                        && otherCheck.isOnLineByXCoordination(thisCheck.start().getX()));
            }
        }
        //checks if the first line is parallel to the x-axis
        if (thisCheck.isParallelToX()) {
            //checks if the second line is parallel to the x-axis
            if (otherCheck.isParallelToX()) {
                //checks if they on the same y
                if (thisCheck.start().getY() == otherCheck.start().getY()) {
                    //two checks if the line is start between the other line
                    return isBetweenByTwoLines(thisCheck, otherCheck);
                }
                return false;
            }
            //checks if the second line is parallel to the y-axis
            if (otherCheck.isParallelToY()) {
                //checks if the line is on this line
                return thisCheck.isOnLineByXCoordination(otherCheck.start().getX());
            }
            //the line is normal so we need his slope
            double slope = findSlope(otherCheck);
            //find the intersection point between the lines
            double intersectionPointX = (thisCheck.start().getY()
                    - findFreeNumber(slope, otherCheck.start())) / slope;
            //check if the point find on the lines
            return otherCheck.isOnLineByXCoordination(intersectionPointX)
                    && thisCheck.isOnLineByXCoordination(intersectionPointX)
                    || isOnEdges(new Point(intersectionPointX,
                    thisCheck.start().getY()), thisCheck, otherCheck);
        }
        //the line have slope

        if (otherCheck.isParallelToY()) {
            if (thisCheck.isOnLineByXCoordination(otherCheck.start().getX())) {
                double intersectionPointY = findIntersectionPointY(thisCheck,
                        otherCheck.start().getX());
                return (otherCheck.isOnLineByYCoordination(intersectionPointY)
                        && thisCheck.isOnLineByXCoordination(otherCheck.start().getX()));
            }
            return false;
        }
        if (otherCheck.isParallelToX()) {
            //the line is normal so we need his slope
            double slope = findSlope(thisCheck);
            //find the intersection point between the lines
            double intersectionPointX = (otherCheck.start().getY()
                    - findFreeNumber(slope, thisCheck.start())) / slope;

            //check if the point find on the lines
            return otherCheck.isOnLineByXCoordination(intersectionPointX)
                    && thisCheck.isOnLineByXCoordination(intersectionPointX)
                    || isOnEdges(new Point(intersectionPointX,
                    otherCheck.start().getY()), thisCheck, otherCheck);
        }
        //finding the slope for the two lines
        double slope1 = findSlope(thisCheck);
        double slope2 = findSlope(otherCheck);
        //if the two slope are not equal
        if (slope1 != slope2) {
            double intersectionPointX = findIntersectionPointX(thisCheck, otherCheck);
            double intersectionPointY = findIntersectionPointY(thisCheck,
                    intersectionPointX);
            //check if the point that we found on the lines
            return otherCheck.isOnLineByXCoordination(intersectionPointX)
                    && thisCheck.isOnLineByXCoordination(intersectionPointX)
                    || isOnEdges(new Point(intersectionPointX,
                    intersectionPointY), thisCheck, otherCheck);
        }
        //if the slope is equal we check if the free number equal
        if (findFreeNumber(slope1, thisCheck.start()) != findFreeNumber(slope2,
                otherCheck.start())) {
            return false;
        }

        //check if there is between line to line
        return isBetweenByTwoLines(thisCheck, otherCheck);
    }

    /**
     * Checks if two lines overlap on the X-axis, i.e., if the start and
     * end points of one line are within the start and end points of the
     * other line.
     *
     * @param line1 the first line to check for overlap
     * @param line2 the second line to check for overlap
     * @return true if the two lines overlap on the X-axis, false otherwise
     */
    static boolean isBetweenByTwoLines(Line line1, Line line2) {
        return line2.isOnLineByXCoordination(line1.start().getX())
                || line1.isOnLineByXCoordination(line2.start().getX());
    }

    private static boolean isOverLapping(Line line1, Line line2) {
        if (line1.start().getX() > line2.start().getX()
                && line1.start().getX() < line2.end().getX()) {
            return true;
        }
        return (line2.start().getX() > line1.start().getX()
                && line2.start().getX() < line1.end().getX());
    }

    /**
     * Returns the point of intersection between two lines.
     *
     * @param other the other line to find intersection with
     * @return the point of intersection, or null if the lines do not intersect
     */
    public Point intersectionWith(Line other) {
        double slope1, slope2;
        Line thisCheck, otherCheck;
        if (getMinPoint(this.start, this.end) == this.start) {
            thisCheck = new Line(this.start, this.end);
        } else {
            thisCheck = new Line(this.end, this.start);
        }
        if (getMinPoint(other.start(), other.end()) == other.start()) {
            otherCheck = new Line(other.start(), other.end());
        } else {
            otherCheck = new Line(other.end(), other.start());
        }
        //first we check if the lines are intersecting
        if (thisCheck.isIntersecting(otherCheck)) {
            //checks if the first line is parallel to the x-axis
            if (thisCheck.isParallelToX()) {
                //checks if the second line is parallel to the x-axis
                if (otherCheck.isParallelToX()) {
                    if (thisCheck.start().equals(otherCheck.end())) {
                        return thisCheck.start();
                    }
                    if (thisCheck.end().equals(otherCheck.start())) {
                        return other.start();
                    }
                    return null;
                }
                //checks if the second line is parallel to the y-axis
                if (otherCheck.isParallelToY()) {
                    //checks if the line is on this line

                    if (otherCheck.isOnLineByYCoordination(thisCheck.start().getY())) {
                        return new Point(otherCheck.start().getX(),
                                thisCheck.start().getY());
                    }
                    return null;
                }
                //the line is normal so we need his slope
                double slope = findSlope(otherCheck);
                //find the intersection point between the lines
                double intersectionPointX = (thisCheck.start().getY()
                        - findFreeNumber(slope, otherCheck.start())) / slope;
                //check if the point find on the lines
                if (thisCheck.isOnLineByXCoordination(intersectionPointX)
                        && otherCheck.isOnLineByXCoordination(intersectionPointX)) {
                    return new Point(intersectionPointX,
                            thisCheck.start().getY());
                }
                return null;

            }
            if (thisCheck.isParallelToY()) {
                //checks if the second line is parallel to the y-axis
                if (otherCheck.isParallelToY()) {
                    if (thisCheck.start().equals(otherCheck.end())) {
                        return thisCheck.start();
                    }
                    if (thisCheck.end().equals(otherCheck.start())) {
                        return otherCheck.start();
                    }
                    return null;
                }
                if (otherCheck.isParallelToX()) {
                    if (thisCheck.isOnLineByYCoordination(otherCheck.start().getY())) {
                        return new Point(thisCheck.start().getX(),
                                otherCheck.start().getY());
                    }
                    return null;
                }
                double intersectionPointY = findIntersectionPointY(otherCheck,
                        thisCheck.start().getX());
                if (thisCheck.isOnLineByYCoordination(intersectionPointY)
                        && otherCheck.isOnLineByXCoordination(thisCheck.start().getX())) {
                    return new Point(thisCheck.start().getX(),
                            intersectionPointY);
                }
                return null;
            }


            if (otherCheck.isParallelToY()) {
                double intersectionPointY = findIntersectionPointY(thisCheck,
                        otherCheck.start().getX());
                if (thisCheck.isOnLineByXCoordination(otherCheck.start().getX())
                        && otherCheck.isOnLineByYCoordination(intersectionPointY)) {
                    return new Point(otherCheck.start().getX(), intersectionPointY);
                }
                return null;
            }
            if (otherCheck.isParallelToX()) {
                //the line is normal so we need his slope
                double slope = findSlope(thisCheck);
                //find the intersection point between the lines
                double intersectionPointX = (otherCheck.start().getY()
                        - findFreeNumber(slope, thisCheck.start())) / slope;
                if (thisCheck.isOnLineByXCoordination(intersectionPointX)
                        && otherCheck.isOnLineByXCoordination(intersectionPointX)) {
                    return new Point(intersectionPointX,
                            findIntersectionPointY(otherCheck, intersectionPointX));
                }
                return null;
            }
            //slopes for the lines
            slope1 = findSlope(thisCheck);
            slope2 = findSlope(otherCheck);
            //find if the rwo lines have the same slope
            if (slope1 == slope2) {
                if (isOverLapping(thisCheck, otherCheck)) {
                    return null;
                }
                if (thisCheck.start().equals(otherCheck.end())) {
                    return thisCheck.start();
                }
                if (thisCheck.end().equals(otherCheck.start())) {
                    return otherCheck.start();
                }
                return null;
            }
            if (thisCheck.start().equals(otherCheck.end())) {
                return thisCheck.start();
            }
            if (thisCheck.end().equals(otherCheck.start())) {
                return otherCheck.start();
            }
            double xInter = findIntersectionPointX(thisCheck, otherCheck);
            return new Point(xInter, findIntersectionPointY(thisCheck, xInter));
        }
        // not intersecting return null
        return null;
    }

    /**
     * Determines if this line is equal to another line.
     * Two lines are considered equal if they have the same start and end points.
     *
     * @param other the other line to compare to
     * @return true if the two lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start()) && this.end.equals(other.end());
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point if there is none point return null;
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = rect.intersectionPoints(this);
        if (pointList.size() != 0) {
            double min = this.start().distance(pointList.get(0));
            int saverIndex = 0, index = 0;
            double dis;
            for (Point p : pointList) {
                dis = this.start().distance(p);
                if (min > dis) {
                    min = dis;
                    saverIndex = index;
                }
                index++;
            }
            return pointList.get(saverIndex);
        }
        return null;
    }

}
