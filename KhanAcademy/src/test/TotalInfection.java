package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import code.Infection;
import code.KAUser;

public class TotalInfection {
	
	List<KAUser> users = new ArrayList<>();

	@Before
	public void setup() {
		KAUser user1 = new KAUser("johnDoe");
		KAUser user2 = new KAUser("leoMessi");
		KAUser user3 = new KAUser("cr7");
		KAUser user4 = new KAUser("ejaz-vel");
		KAUser user5 = new KAUser("wayneRooney");
		KAUser user6 = new KAUser("kaka");
		KAUser user7 = new KAUser("ronado");
		KAUser user8 = new KAUser("mesutOzil");
		KAUser user9 = new KAUser("neymarJr");
		KAUser user10 = new KAUser("garethBale");
		
		user2.addStudent(user9);
		user3.addStudent(user5);
		user3.addStudent(user10);
		user4.addStudent(user2);
		user4.addStudent(user3);
		
		user1.addStudent(user7);
		user6.addStudent(user8);
		user7.addStudent(user6);
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);
		users.add(user8);
		users.add(user9);
		users.add(user10);
	}
	
	@Test
	public void test1() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		Set<KAUser> actual = newFeature.getTotalInfectedUsers(users.get(2));
		Set<KAUser> expected = new HashSet<>();
		expected.add(users.get(1));
		expected.add(users.get(2));
		expected.add(users.get(3));
		expected.add(users.get(4));
		expected.add(users.get(8));
		expected.add(users.get(9));
		assertEquals(expected, actual);
	}
	
	@Test
	public void test2() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		Set<KAUser> actual = newFeature.getTotalInfectedUsers(users.get(0));
		Set<KAUser> expected = new HashSet<>();
		expected.add(users.get(0));
		expected.add(users.get(6));
		expected.add(users.get(5));
		expected.add(users.get(7));
		assertEquals(expected, actual);
	}

}
