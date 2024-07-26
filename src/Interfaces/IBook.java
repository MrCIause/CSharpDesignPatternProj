package Interfaces;

public interface IBook {
    String getTitle();
    String getAuthor();
    int getPublicationYear();
    boolean isAvailable();
    void setAvailable(boolean available);
}
