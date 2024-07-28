package Interfaces;

public interface IBook {
    int getId();
    String getTitle();
    String getAuthor();
    int getPublicationYear();
    boolean isAvailable();
    void setAvailable(boolean available);
}
