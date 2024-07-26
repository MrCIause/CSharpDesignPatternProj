package MainClasses;

import Interfaces.IMember;
import Interfaces.IMemberFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Factory class for creating members.
 */
public class MemberFactory implements IMemberFactory {
    private static int idCounter = 0;
    private static Queue<IMember> memberQueue = new LinkedList<>();

    @Override
    public IMember createMember(String name) {
        if (doesMemberExist(name)) {
            return null; // Indicating that the member already exists
        }
        int uniqueId = generateUniqueId();
        IMember newMember = new Member(name, uniqueId);
        memberQueue.add(newMember);
        return newMember;
    }

    private boolean doesMemberExist(String name) {
        for (IMember member : memberQueue) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static synchronized int generateUniqueId() {
        return ++idCounter;
    }

    public static Queue<IMember> getMemberQueue() {
        return memberQueue;
    }

    public static void removeMember(IMember member) {
        memberQueue.remove(member);
    }
}
