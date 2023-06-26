package ProjectBasis;
import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;
    private Line[] arrLines;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.arrLines = new Line[4];
        //left line same x
        this.arrLines[0] = new Line(this.upperLeft,
                new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height));
        //upper line same y
        this.arrLines[1] = new Line(this.upperLeft,
                new Point(this.upperLeft.getX() + this.width,
                        this.upperLeft.getY()));
        //right line same x
        this.arrLines[2] =
                new Line(new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY()),
                new Point(this.upperLeft.getX() + this.width,
                        this.upperLeft.getY() + this.height));
        //down line same y
        this.arrLines[3] = new Line(new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height),
                new Point(this.upperLeft.getX() + this.width,
                        this.upperLeft.getY() + this.height));
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (line.isIntersecting(this.arrLines[i])) {
                Point p = line.intersectionWith(this.arrLines[i]);
                if (p != null) {
                    pointList.add(p);
                }

            }
        }
        return pointList;
    }

    /**
     * Update lines.
     *
     * @param upperLeft the upper left
     */
    public void updateLines(Point upperLeft) {
        //left line same x
        this.arrLines[0] = new Line(this.upperLeft,
                new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height));
        //upper line same y
        this.arrLines[1] = new Line(this.upperLeft,
                new Point(this.upperLeft.getX() + this.width,
                        this.upperLeft.getY()));
        //right line same x
        this.arrLines[2] =
                new Line(new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY()),
                new Point(this.upperLeft.getX() + this.width,
                        this.upperLeft.getY() + this.height));
        //down line same y
        this.arrLines[3] = new Line(new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height),
                new Point(this.upperLeft.getX() + this.width,
                        this.upperLeft.getY() + this.height));
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY());
    }

    /**
     * Sets upper left.
     *
     * @param upperLeft the upper left
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
        this.updateLines(upperLeft);
    }

    /**
     * Get arr lines line [ ].
     *
     * @return the line [ ]
     */
    public Line[] getArrLines() {
        return this.arrLines;
    }
}