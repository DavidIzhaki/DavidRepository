package Sprites.Objects;

/**
 * Represents a frame for a ball in a two-dimensional coordinate system.
 */
public class BallLimits {

    /**
     * The x-coordinate of the starting point of the frame.
     */
    private int startX;

    /**
     * The y-coordinate of the starting point of the frame.
     */
    private int startY;

    /**
     * The x-coordinate of the ending point of the frame.
     */
    private int endX;

    /**
     * The y-coordinate of the ending point of the frame.
     */
    private int endY;

    /**
     * Constructs a new FrameBall object with the given starting and ending coordinates.
     *
     * @param startX the x-coordinate of the starting point of the frame.
     * @param startY the y-coordinate of the starting point of the frame.
     * @param endX   the x-coordinate of the ending point of the frame.
     * @param endY   the y-coordinate of the ending point of the frame.
     */
    public BallLimits(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    /**
     * Returns the x-coordinate of the starting point of the frame.
     *
     * @return the x-coordinate of the starting point of the frame.
     */
    public int getStartX() {
        return this.startX;
    }

    /**
     * Returns the y-coordinate of the starting point of the frame.
     *
     * @return the y-coordinate of the starting point of the frame.
     */
    public int getStartY() {
        return this.startY;
    }

    /**
     * Returns the x-coordinate of the ending point of the frame.
     *
     * @return the x-coordinate of the ending point of the frame.
     */
    public int getEndX() {
        return this.endX;
    }

    /**
     * Returns the y-coordinate of the ending point of the frame.
     *
     * @return the y-coordinate of the ending point of the frame.
     */
    public int getEndY() {
        return this.endY;
    }
}