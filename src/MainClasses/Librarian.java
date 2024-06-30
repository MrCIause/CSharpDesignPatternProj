package MainClasses;

import Interfaces.Identifiable;
import Interfaces.LibrarianInterface;

public class Librarian implements LibrarianInterface, Identifiable {
    private String id;
    private String name;

    // Constructor, getters, and setters
    public Librarian(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}


