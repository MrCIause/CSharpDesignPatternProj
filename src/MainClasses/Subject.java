package MainClasses;

import java.util.ArrayList;
import java.util.List;
import Interfaces.IBook;
import Interfaces.ISubject;
import Interfaces.IObserver;


import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of a subject in the observer pattern.
 */
public class Subject implements ISubject {
    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(IBook book) {
        for (IObserver observer : observers) {
            observer.update(book);
        }
    }
}

