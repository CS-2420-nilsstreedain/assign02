package assign02;

import java.util.ArrayList;

/**
 * This class represents a CS2420 student, in which the uNID cannot change once
 * the student is created. Note that each student's uNID is unique. contact
 * information in the form of an email address is stored along with assignment
 * scores in several ArrayLists, depending on the assignment category.
 * 
 * @author Nils Streedain, Kyle Williams
 * @version Febuary 2, 2021
 */
public class CS2420Student extends UofUStudent {

	private final double LAB_WEIGHT = .10;
	private final double QUIZ_WEIGHT = .10;
	private final double EXAM_WEIGHT = .30;
	private final double ASSIGNMENT_WEIGHT = .50;

	private EmailAddress contactInfo;

	private ArrayList<Double> labScores = new ArrayList<>();
	private ArrayList<Double> assignScores = new ArrayList<>();
	private ArrayList<Double> quizScores = new ArrayList<>();
	private ArrayList<Double> examScores = new ArrayList<>();

	/**
	 * Creates a CS2420 student from the given first name, last name, uNID and Email
	 * Address.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param uNID
	 * @param contactInfo
	 */
	public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
		super(firstName, lastName, uNID);
		this.contactInfo = contactInfo;
	}

	/**
	 * Getter method for the contactInfo field of this CS2420 student object.
	 * 
	 * @return this student's contactInfo
	 */
	public EmailAddress getContactInfo() {
		return contactInfo;
	}

	/**
	 * Adds the score of an assignment of a certain type to the ArrayList of scores
	 * for that type. Only accepts categories of type "lab", "assignment", "quiz",
	 * or "exam".
	 * 
	 * @param score
	 * @param category
	 */
	public void addScore(double score, String category) {
		switch (category) {
		case "lab":
			labScores.add(score);
			break;
		case "assignment":
			assignScores.add(score);
			break;
		case "quiz":
			quizScores.add(score);
			break;
		case "exam":
			examScores.add(score);
			break;
		default:
			throw new IllegalArgumentException("Category must be lab, assignment, quiz, or exam");
		}
	}

	/**
	 * Undefined
	 * 
	 * @return undefined
	 */
	public double computeFinalScore() {
		if ((labScores.size() < 2) || (quizScores.size() < 2) || (examScores.size() < 1) || (assignScores.size() < 1)){
			return 0;
		}
		double finalScoreSum = 0;
		finalScoreSum += averageArray(labScores) * LAB_WEIGHT;
		finalScoreSum += averageArray(assignScores) * ASSIGNMENT_WEIGHT;
		finalScoreSum += averageArray(quizScores) * QUIZ_WEIGHT;
		finalScoreSum += averageArray(examScores) * EXAM_WEIGHT;

		return finalScoreSum;
	}

	/**
	 * Undefined
	 * 
	 * @return avg
	 */
	private double averageArray(ArrayList<Double> array) {
		double avg = 0;
		for (double score : array) {
			avg += score;
		}
		avg = avg / array.size();
		return avg;
	}

	/**
	 * Undefined
	 * 
	 * @return undefined
	 */
	public String computeFinalGrade() {
		double score = computeFinalScore();
		if ((labScores.size() < 2) || (quizScores.size() < 2) || (examScores.size() < 1) || (assignScores.size() < 1)){
			return "N/A";
		}
		
		if (score >= 93.0) {
			return "A";
		} else if (score >= 90.0) {
			return "A-";
		} else if (score >= 87.0) {
			return "B+";
		} else if (score >= 83.0) {
			return "B";
		} else if (score >= 80.0) {
			return "B-";
		} else if (score >= 77.0) {
			return "C+";
		} else if (score >= 73.0) {
			return "C";
		} else if (score >= 70.0) {
			return "C-";
		} else if (score >= 67.0) {
			return "D+";
		} else if (score >= 63.0) { 
			return "D";
		} else if (score >= 60.0) { 
			return "D-";
		} else {
			return "E";
		}
	}
}
