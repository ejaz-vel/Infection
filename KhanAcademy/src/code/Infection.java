package code;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Infection {
	
	/**
	 * Return the entire list of users infected by the new feature.
	 * @param origin The Khan Academy user who is the origin of the infection
	 * @return list of infected Khan Academy Users
	 */
	public Set<KAUser> getTotalInfectedUsers(KAUser origin) {
		Set<KAUser> infectedUsers = new HashSet<>();
		Queue<KAUser> que = new LinkedList<>();
		que.add(origin);
		while (!que.isEmpty()) {
			KAUser user = que.poll();
			for (KAUser dependent: user.getDependents()) {
				if (!dependent.isInfected()) {
					que.add(dependent);
				}
			}
			user.infect();
			infectedUsers.add(user);
		}
		return infectedUsers;
	}
	
	/**
	 * Return the list of users infected by the new feature. The infection is restricted to fixed number of hierarchy levels
	 * @param origin: The Khan Academy user who is the origin of the infection
	 * @param levelLimit: The restriction on the number of levels in the user hierarchy affected by the infection
	 * @return list of infected Khan Academy Users
	 */
	public List<KAUser> getLimitedInfectedUsers(KAUser origin, int levelLimit) {
		List<KAUser> infectedUsers = new ArrayList<>();
		return infectedUsers;
	}
	
	/**
	 * Return the list of users infected by the new feature. The infection is restricted to fixed number of users
	 * @param origin: The Khan Academy user who is the origin of the infection
	 * @param userlimit: The restriction on the number of users infected
	 * @return list of infected Khan Academy Users
	 */
	public List<KAUser> getLimitedInfectedUsersWithLimit(KAUser origin, int userlimit) {
		List<KAUser> infectedUsers = new ArrayList<>();
		return infectedUsers;
	}
	
}
