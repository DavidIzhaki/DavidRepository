package ProjectBasis;

/**
 * The type Counter.
 */
public class Counter {
    private int number;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.number = 0;
    }

    /**
     * Instantiates a new Counter.
     *
     * @param number the number
     */
    public Counter(int number) {
        this.number = number;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    public void increase(int number) {
        this.number += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    public void decrease(int number) {
        this.number -= number;
    }

    /**
     * Get value int.
     *
     * @return the int
     */
// get current count.
    public int getValue() {
        return this.number;
    }
}