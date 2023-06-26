package Spies;


/**
 * The interface Hit notifier.
 */
public interface HitNotifier {


    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
// Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    // Remove hl from the list of listeners to hit events.

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
    void removeHitListener(HitListener hl);

    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.

}