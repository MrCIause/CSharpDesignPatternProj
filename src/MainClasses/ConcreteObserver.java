package MainClasses;

import Interfaces.IBook;
import Interfaces.IObserver;

/**
 * Concrete implementation of an observer.
 */
public class ConcreteObserver implements IObserver {
    private String name;

    /**
     * Constructs a new ConcreteObserver instance.
     *
     * @param name The name of the observer.
     */
    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(IBook book) {
        System.out.println("Notification to " + name + ": Book " + book.getTitle() + " is now " + (book.isAvailable() ? "available" : "unavailable"));
    }
}
