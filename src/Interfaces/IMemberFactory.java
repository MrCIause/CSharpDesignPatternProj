package Interfaces;

/**
 * Factory interface for creating Member instances.
 */
public interface IMemberFactory {
    /**
     * Creates a new member instance.
     *
     * @param name The name of the member.
     * @param id   The ID of the member.
     * @return A new IMember instance.
     */
    IMember createMember(String name, String id);
}
