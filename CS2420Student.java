package assign02;

import java.util.ArrayList;

public class CS2420Student extends UofUStudent{
	
	private EmailAddress contactInfo;
	private ArrayList<Double> labScores = new ArrayList<>();
	private ArrayList<Double> assignScores = new ArrayList<>();
	private ArrayList<Double> quizScores = new ArrayList<>();
	private ArrayList<Double> examScores = new ArrayList<>();
	
	public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
		super(firstName,lastName,uNID);
		contactInfo = this.contactInfo;
	}
	
	public EmailAddress getContactInfo() {
		return contactInfo;
	}
	
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
	
	
	public double computeFinalScore() {
		return 0;
	}
	public String computeFinalGrade() {
		return null;
	}
	
}
