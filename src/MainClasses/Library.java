package MainClasses;

import Interfaces.IBook;
import Interfaces.IMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class representing the library.
 */
public class Library {
    private static Library instance;
    private List<IBook> books;
    private List<IMember> members;

    private Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public List<IBook> getBooks() {
        return books;
    }

    public void addBook(IBook book) {
        books.add(book);
    }

    public void removeBook(IBook book) {
        books.remove(book);
    }

    public List<IMember> getMembers() {
        return members;
    }

    public void addMember(IMember member) {
        members.add(member);
    }

    public void removeMember(IMember member) {
        members.remove(member);
    }

    public String getLibrarySummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Library Summary:\n");

        summary.append("Books:\n");
        for (IBook book : books) {
            summary.append(book.toString()).append("\n");
        }

        summary.append("Members:\n");
        for (IMember member : members) {
            summary.append(member.toString()).append("\n");
        }

        return summary.toString();
    }
}
