package MainClasses;

import Interfaces.BookInterface;
import Interfaces.Identifiable;

public class Book implements BookInterface, Identifiable {
    private String id;
    private String title;
    private String author;
    private boolean isLoaned;

    // Constructor, getters, and setters
    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isLoaned = false;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public boolean isLoaned() {
        return isLoaned;
    }

    public void setLoanStatus(boolean isLoaned) {
        this.isLoaned = isLoaned;
    }
}

