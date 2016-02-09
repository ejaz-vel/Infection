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

public class InfectionTest {
	
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
	public void testTotalInfection() {
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
	public void testTotalInfectionCornerCase() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user1
		Set<KAUser> actual = newFeature.getTotalInfectedUsers(users.get(0));
		Set<KAUser> expected = new HashSet<>();
		expected.add(users.get(0));
		expected.add(users.get(6));
		expected.add(users.get(5));
		expected.add(users.get(7));
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLimitedInfectionWithLevels1() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user1
		try {
			Set<KAUser> actual = newFeature.getLimitedInfectedUsers(users.get(0), 1);
			Set<KAUser> expected = new HashSet<>();
			expected.add(users.get(0));
			expected.add(users.get(6));
			assertEquals(expected, actual);
		} catch(Exception e) {
			fail("Exception Thrown: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testLimitedInfectionWithLevels2() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		try {
			Set<KAUser> actual = newFeature.getLimitedInfectedUsers(users.get(2), 1);
			Set<KAUser> expected = new HashSet<>();
			expected.add(users.get(2));
			expected.add(users.get(3));
			expected.add(users.get(4));
			expected.add(users.get(9));
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Exception Thrown: " + e.getMessage());
		}
	}
	
	@Test
	public void testLimitedInfectionWithLevels3() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		try {
			Set<KAUser> actual = newFeature.getLimitedInfectedUsers(users.get(2), 2);
			Set<KAUser> expected = new HashSet<>();
			expected.add(users.get(1));
			expected.add(users.get(2));
			expected.add(users.get(3));
			expected.add(users.get(4));
			expected.add(users.get(9));
		assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Exception Thrown: " + e.getMessage());
		}
	}
	
	@Test
	public void testLimitedInfectionWithLevelsCornerCase() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		try {
			Set<KAUser> actual = newFeature.getLimitedInfectedUsers(users.get(2), 0);
			Set<KAUser> expected = new HashSet<>();
			expected.add(users.get(2));
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Exception Thrown: " + e.getMessage());
		}
	}
	
	@Test
	public void testLimitedInfectionWithLevelsException() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		try {
			Set<KAUser> actual = newFeature.getLimitedInfectedUsers(users.get(2), 5);
			fail("Exception should be Thrown");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testLimitedInfectionWithMinUsers1() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		try {
			Set<KAUser> actual = newFeature.getLimitedInfectedUsersWithMinUserLimit(users.get(2), 2);
			Set<KAUser> expected = new HashSet<>();
			expected.add(users.get(2));
			expected.add(users.get(3));
			expected.add(users.get(4));
			expected.add(users.get(9));
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Exception Thrown: " + e.getMessage());
		}
	}
	
	@Test
	public void testLimitedInfectionWithMinUsers2() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		try {
			Set<KAUser> actual = newFeature.getLimitedInfectedUsersWithMinUserLimit(users.get(2), 4);
			Set<KAUser> expected = new HashSet<>();
			expected.add(users.get(2));
			expected.add(users.get(3));
			expected.add(users.get(4));
			expected.add(users.get(9));
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Exception Thrown: " + e.getMessage());
		}
	}
	
	@Test
	public void testLimitedInfectionWithMinUsers3() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		try {
			Set<KAUser> actual = newFeature.getLimitedInfectedUsersWithMinUserLimit(users.get(1), 1);
			Set<KAUser> expected = new HashSet<>();
			expected.add(users.get(1));
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Exception Thrown: " + e.getMessage());
		}
	}
	
	@Test
	public void testLimitedInfectionWithMinUsers4() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		try {
			Set<KAUser> actual = newFeature.getLimitedInfectedUsersWithMinUserLimit(users.get(1), 2);
			Set<KAUser> expected = new HashSet<>();
			expected.add(users.get(1));
			expected.add(users.get(3));
			expected.add(users.get(8));
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Exception Thrown: " + e.getMessage());
		}
	}
	
	@Test
	public void testLimitedInfectionWithMinUsersException() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		try {
			Set<KAUser> actual = newFeature.getLimitedInfectedUsersWithMinUserLimit(users.get(1), 10);
			fail("Exception should be Thrown");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testLimitedInfectionWithMaxUsers() {
		Infection newFeature = new Infection();
		
		// Get the list of infected users for user3
		Set<KAUser> actual = newFeature.getLimitedInfectedUsersWithMaxUserLimit(users.get(1), 2);
		Set<KAUser> expected = new HashSet<>();
		expected.add(users.get(1));
		assertEquals(expected, actual);
	}

}
