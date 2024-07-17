package Interfaces;

/**
 * Interface for observer in the observer pattern.
 */
public interface IObserver {
    /**
     * Updates the observer with the book status change.
     *
     * @param book The book whose status has changed.
     */
    void update(IBook book);
}
