package MainClasses;

import Interfaces.IMember;
import Interfaces.IMemberFactory;

/**
 * Factory class for creating Member instances.
 */
public class MemberFactory implements IMemberFactory {
    @Override
    public IMember createMember(String name, String id) {
        return new Member(name, id);
    }
}
