package code;
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
	public Set<KAUser> getLimitedInfectedUsers(KAUser origin, int levelLimit) {
		Set<KAUser> infectedUsers = new HashSet<>();
		Queue<Pair<KAUser, Integer>> que = new LinkedList<>();
		que.add(new Pair<KAUser, Integer>(origin, 0));
		while (!que.isEmpty()) {
			Pair<KAUser, Integer> user = que.poll();
			List<KAUser> dependents = user.getLeft().getDependents();
			Integer level = user.getRight();
			
			if (level > levelLimit) {
				break;
			}
			
			addDependentsToQueue(dependents, que, level);
			user.getLeft().infect();
			infectedUsers.add(user.getLeft());
		}
		return infectedUsers;
	}
	
	/**
	 * Return the list of users infected by the new feature. The infection is needs to infect a fixed number of users
	 * @param origin: The Khan Academy user who is the origin of the infection
	 * @param userlimit: The restriction on the number of users infected. This provides the Lower Bound on the number of users
	 * @return list of infected Khan Academy Users
	 */
	public Set<KAUser> getLimitedInfectedUsersWithLowerLimit(KAUser origin, int userlimit) {
		Set<KAUser> infectedUsers = new HashSet<>();
		Queue<Pair<KAUser, Integer>> que = new LinkedList<>();
		que.add(new Pair<KAUser, Integer>(origin, 0));
		int userCount = 0;
		int levelToBeCompleted = 0;
		while (!que.isEmpty()) {
			Pair<KAUser, Integer> user = que.poll();
			List<KAUser> dependents = user.getLeft().getDependents();
			Integer level = user.getRight();
			
			if (userCount >= userlimit) {
				// It does not make sense to have some users in a class to have different experience.
				// Make sure that all KAUsers with thin the same level (i.e. students taught by the same coach) have the same user experience
				if (level > levelToBeCompleted) {
					break;
				}
			}
			
			addDependentsToQueue(dependents, que, level);
			user.getLeft().infect();
			infectedUsers.add(user.getLeft());
			
			userCount++;
			levelToBeCompleted = level;
		}
		return infectedUsers;
	}
	
	/**
	 * Return the list of users infected by the new feature. The infection can affect maximum a given number of users
	 * @param origin: The Khan Academy user who is the origin of the infection
	 * @param userlimit: The restriction on the number of users infected. This provides the Upper Bound on the number of users
	 * @return list of infected Khan Academy Users
	 */
	public Set<KAUser> getLimitedInfectedUsersWithMaxLimit(KAUser origin, int userlimit) {
		Set<KAUser> infectedUsers = new HashSet<>();
		Set<KAUser> tempUsers = new HashSet<>();
		Queue<Pair<KAUser, Integer>> que = new LinkedList<>();
		que.add(new Pair<KAUser, Integer>(origin, 0));
		int userCount = 0;
		int levelToBeCompleted = 0;
		while (!que.isEmpty() && userCount < userlimit) {
			Pair<KAUser, Integer> user = que.poll();
			List<KAUser> dependents = user.getLeft().getDependents();
			Integer level = user.getRight();
			
			if (level > levelToBeCompleted) {
				infectedUsers.addAll(tempUsers);
				tempUsers.clear();
			}
			
			addDependentsToQueue(dependents, que, level);
			user.getLeft().infect();
			tempUsers.add(user.getLeft());
			
			userCount++;
			levelToBeCompleted = level;
		}
		return infectedUsers;
	}
	
	private void addDependentsToQueue(List<KAUser> dependents, Queue<Pair<KAUser, Integer>> que, int currentLevel) {
		for (KAUser dependent: dependents) {
			if (!dependent.isInfected()) {
				que.add(new Pair<KAUser, Integer>(dependent, currentLevel + 1));
			}
		}
	}
	
}
