package Interfaces;

/**
 * Interface for subject in the observer pattern.
 */
public interface ISubject {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers(IBook book);
}
