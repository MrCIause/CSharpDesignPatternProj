package MainClasses;

import Interfaces.Identifiable;
import Interfaces.MemberInterface;

import java.util.Stack;

public class Member implements MemberInterface, Identifiable {
    private String id;
    private String name;

    // Constructor, getters, and setters
    public Member(String id, String name) {
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
