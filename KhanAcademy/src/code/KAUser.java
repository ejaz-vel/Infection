package code;
import java.util.ArrayList;
import java.util.List;

// This class represents a Khan Academy User
public class KAUser {
	
	// Name of the User
	private String userName;
	
	// Each user is coached by a list of Coaches
	private List<KAUser> coaches;
	
	// Each user can also coach a list of students
	private List<KAUser> students;
	
	// A flag to keep track whether this user is infected with the new feature or not
	private boolean isInfected;
	
	// Minor Assumption is that each Khan Academy user has a uniqueName
	public KAUser(String name) {
		this.userName = name;
		this.students = new ArrayList<>();
		this.coaches = new ArrayList<>();
	}
	
	// Add a student that is being coached by this user
	public void addStudent(KAUser student) {
		this.students.add(student);
		student.coaches.add(this);
	}
	
	// Return the list of users related to this user by both the “coaches” and “is coached by” relations.
	public List<KAUser> getDependents() {
		List<KAUser> dependentUsers = new ArrayList<>();
		dependentUsers.addAll(students);
		dependentUsers.addAll(coaches);
		return dependentUsers;
	}

	// Is this user infected by the new feature?
	public boolean isInfected() {
		return isInfected;
	}

	// Infect this user with the new feature
	public void infect() {
		this.isInfected = true;
	}
	
	public String toString() {
		return this.userName;
	}
}
