package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for CS2420Class
 * 
 * @author Erin Parker, Nils Streedain and Kyle Williams
 * @version January 27, 2021
 */
public class CS2420ClassTester {

	private CS2420Class emptyClass, verySmallClass, smallClass;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyClass = new CS2420Class();
		
		verySmallClass = new CS2420Class();
		verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

		smallClass = new CS2420Class();
		smallClass.addAll("src/assign02/a_small_2420_class.txt");
		
		// FILL IN -- Extend this tester to add more tests for the CS 2420 classes above, as well as to
		// create and test larger CS 2420 classes.
		// (HINT: For larger CS 2420 classes, generate random names, uNIDs, contact info, and scores in a 
		// loop, instead of typing one at a time.)
	}
	
	// Empty CS 2420 class tests --------------------------------------------------------------------------

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}
	
	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420Student> students = emptyClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(0, students.size());
	}
	
	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}
	
	@Test
	public void testEmptyContactList() {
		ArrayList<EmailAddress> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420Student actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}
	
	@Test
	public void testVerySmallLookupUNIDNonExistant() {
		CS2420Student actual = verySmallClass.lookup(3141592);
		assertEquals(null, actual);
	}
	
	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, 
				new EmailAddress("hi", "gmail.com")));
		assertFalse(actual);
	}
	
	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010100, 
				new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);		
	}
	
	@Test
	public void testVerySmallAddScoreError() {
		CS2420Student student = new CS2420Student("Drew", "Hall", 2323232,
				new EmailAddress("wow","aol.com"));
		assertThrows(IllegalArgumentException.class, () -> {
			student.addScore(86.5, "homework");
		});
	}
	
	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	
	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		student.addScore(89.2, "quiz");
		assertEquals("N/A", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(86.795, student.computeFinalScore(), 0.001);
	}
	
	@Test
	public void testVerySmallStudentFinalGradeB() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeBPlus() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86, "assignment");
		student.addScore(88, "exam");
		student.addScore(88.5, "lab");
		student.addScore(67, "quiz");
		student.addScore(91, "assignment");
		student.addScore(90, "lab");
		student.addScore(85, "quiz");
		assertEquals("B+", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeBMinus() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(79, "assignment");
		student.addScore(80, "exam");
		student.addScore(85, "lab");
		student.addScore(81, "quiz");
		student.addScore(83, "assignment");
		student.addScore(80, "lab");
		student.addScore(82, "quiz");
		assertEquals("B-", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B", student.computeFinalGrade());
	}
	
	
	@Test
	public void testVerySmallStudentFinalGradeAMinus() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(89, "assignment");
		student.addScore(90, "exam");
		student.addScore(95, "lab");
		student.addScore(91, "quiz");
		student.addScore(93, "assignment");
		student.addScore(90, "lab");
		student.addScore(92, "quiz");
		assertEquals("A-", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeC() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(75, "assignment");
		student.addScore(70, "exam");
		student.addScore(80, "lab");
		student.addScore(80, "quiz");
		student.addScore(77, "assignment");
		student.addScore(60, "lab");
		student.addScore(23.2, "quiz");
		assertEquals("C", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeCPlus() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(76, "assignment");
		student.addScore(78, "exam");
		student.addScore(78.5, "lab");
		student.addScore(67, "quiz");
		student.addScore(81, "assignment");
		student.addScore(70, "lab");
		student.addScore(75, "quiz");
		assertEquals("C+", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeCMinus() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(69, "assignment");
		student.addScore(70, "exam");
		student.addScore(75, "lab");
		student.addScore(71, "quiz");
		student.addScore(73, "assignment");
		student.addScore(70, "lab");
		student.addScore(72, "quiz");
		assertEquals("C-", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeD() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(65, "assignment");
		student.addScore(66, "exam");
		student.addScore(60, "lab");
		student.addScore(63, "quiz");
		student.addScore(65, "assignment");
		student.addScore(67, "lab");
		student.addScore(63.3, "quiz");
		assertEquals("D", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeDPlus() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(66, "assignment");
		student.addScore(68, "exam");
		student.addScore(68.5, "lab");
		student.addScore(67, "quiz");
		student.addScore(71, "assignment");
		student.addScore(70, "lab");
		student.addScore(65, "quiz");
		assertEquals("D+", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeDMinus() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(59, "assignment");
		student.addScore(60, "exam");
		student.addScore(65, "lab");
		student.addScore(61, "quiz");
		student.addScore(63, "assignment");
		student.addScore(60, "lab");
		student.addScore(62, "quiz");
		assertEquals("D-", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalGradeE() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(50, "assignment");
		student.addScore(45, "exam");
		student.addScore(20, "lab");
		student.addScore(0, "quiz");
		student.addScore(36, "assignment");
		student.addScore(4, "lab");
		student.addScore(60.0, "quiz");
		assertEquals("E", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentComputeScoreTwice() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore();   // do not remove dropped scores, since may change
		student.addScore(70, "lab");
		student.addScore(67.7, "quiz");				
		assertEquals(85.72, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}	
	
	// Small CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupContactInfo() {
		UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
		UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

		ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
		assertEquals(2, actualStudents.size());
		assertTrue(actualStudents.contains(expectedStudent1));
		assertTrue(actualStudents.contains(expectedStudent2));
	}
	
	@Test
	public void testSmallGetContactList() {
		ArrayList<EmailAddress> actual = smallClass.getContactList();
		assertEquals(9, actual.size());
	}
		
	@Test
	public void testSmallStudentFinalScore() {
		CS2420Student student = smallClass.lookup(333333);
		assertEquals(95.540, student.computeFinalScore(), 0.001);
	}
		
	@Test
	public void testSmallComputeClassAverage() {
		assertEquals(78.006, smallClass.computeClassAverage(), 0.001);
	}
}