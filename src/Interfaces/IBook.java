package Interfaces;

/**
 * Interface for book operations.
 */
public interface IBook {
    String getTitle();
    String getAuthor();
    int getPublicationYear();
    boolean isAvailable();
    void setAvailable(boolean isAvailable);
    IBook clone() throws CloneNotSupportedException;
}

