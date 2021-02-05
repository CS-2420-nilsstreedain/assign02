package assign02;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a CS2420 student, in which the uNID cannot change once
 * the student is created. Note that each student's uNID is unique. contact
 * information in the form of an email address is stored along with assignment
 * scores in several ArrayLists, depending on the assignment category.
 * 
 * @author Nils Streedain, Kyle Williams
 * @version Febuary 2, 2021
 */
public class CS2420StudentGeneric<Type> extends UofUStudent {

	private final double LAB_WEIGHT = .10;
	private final double QUIZ_WEIGHT = .10;
	private final double EXAM_WEIGHT = .30;
	private final double ASSIGNMENT_WEIGHT = .50;

	private Type contactInfo;

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
	public CS2420StudentGeneric(String firstName, String lastName, int uNID, Type contactInfo) {
		super(firstName, lastName, uNID);
		this.contactInfo = contactInfo;
	}

	/**
	 * Getter method for the contactInfo field of this CS2420 student object.
	 * 
	 * @return this student's contactInfo
	 */
	public Type getContactInfo() {
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
		// Adds the score to the provided assignment category
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
	 * Returns the final score of a student in the form of a percentage held by a
	 * double. This also uses averageArray to remove the lowest lab and quiz score
	 * from the final grade.
	 * 
	 * @return finalScoreSum
	 */
	public double computeFinalScore() {
		double finalScoreSum = 0;

		if ((labScores.size() < 2) || (quizScores.size() < 2) || (examScores.size() < 1) || (assignScores.size() < 1))
			return 0;

		Collections.sort(labScores);
		Collections.sort(quizScores);

		finalScoreSum += averageArray(labScores, true) * LAB_WEIGHT;
		finalScoreSum += averageArray(quizScores, true) * QUIZ_WEIGHT;
		finalScoreSum += averageArray(examScores, false) * EXAM_WEIGHT;
		finalScoreSum += averageArray(assignScores, false) * ASSIGNMENT_WEIGHT;

		return finalScoreSum;
	}

	/**
	 * Computes the average score out of all scores in a Double array. If remove is
	 * True, the lowest score in the array will be removed.
	 * 
	 * @param array
	 * @param remove - If true, the lowest score for that category will be removed
	 *               before taking the average.
	 * @return avg
	 */
	private double averageArray(ArrayList<Double> array, boolean remove) {
		double avg = 0;

		if (remove) {
			for (int i = 1; i < array.size(); i++) {
				avg += array.get(i);
			}
			avg = avg / (array.size() - 1);
		} else {
			for (double score : array) {
				avg += score;
			}
			avg = avg / array.size();
		}

		return avg;
	}

	/**
	 * Computes the final letter grade based on the grade percentage given by a
	 * double.
	 * 
	 * @return finalGrade
	 */
	public String computeFinalGrade() {
		double score = computeFinalScore();
		String finalGrade;

		// Checks to make sure there are enough assignments of each category
		if ((labScores.size() < 2) || (quizScores.size() < 2) || (examScores.size() < 1) || (assignScores.size() < 1))
			return "N/A";

		// Find the correct letter grade for the given percentage
		if (score >= 93.0)
			finalGrade =  "A";
		else if (score >= 90.0)
			finalGrade = "A-";
		else if (score >= 87.0)
			finalGrade = "B+";
		else if (score >= 83.0)
			finalGrade = "B";
		else if (score >= 80.0)
			finalGrade = "B-";
		else if (score >= 77.0)
			finalGrade = "C+";
		else if (score >= 73.0)
			finalGrade = "C";
		else if (score >= 70.0)
			finalGrade = "C-";
		else if (score >= 67.0)
			finalGrade = "D+";
		else if (score >= 63.0)
			finalGrade = "D";
		else if (score >= 60.0)
			finalGrade = "D-";
		else
			finalGrade = "E";
		
		return finalGrade;
	}
}
