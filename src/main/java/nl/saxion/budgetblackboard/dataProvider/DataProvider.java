package nl.saxion.budgetblackboard.dataProvider;

import nl.saxion.budgetblackboard.models.Course;
import nl.saxion.budgetblackboard.models.Subject;
import nl.saxion.budgetblackboard.models.Topic;
import nl.saxion.budgetblackboard.users.Person;
import nl.saxion.budgetblackboard.users.Student;
import nl.saxion.budgetblackboard.users.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;




@Service
public class DataProvider {

	private ArrayList<Person> users;
	private ArrayList<Course> courses;
	private HashMap<Subject, ArrayList<Topic>> subjectWithTopics;

	public DataProvider() {
		this.users = new ArrayList<>();
		this.courses = new ArrayList<>();
		this.subjectWithTopics = new HashMap<>();
		init();
	}

	private void init(){
		users.add(new Student("12345@student.saxion.nl", "123"));
		users.add(new Teacher("54321@saxion.nl", "321"));
	}

	public ArrayList<Person> getUsers() {
		return new ArrayList<>(this.users);
	}

	public ArrayList<Course> getCourses() {
		return new ArrayList<>(this.courses);
	}

	public HashMap<Subject, ArrayList<Topic>> getSubjectWithTopics() {
		return new HashMap<>(this.subjectWithTopics);
	}

	public void addUser(Person user) {
		if (!this.users.contains(user)) {
			this.users.add(user);
		}
	}

	public void addCours(Course course) {
		if (!this.courses.contains(course)) {
			this.courses.add(course);
		}
	}

	//TODO: not tested, might give an error.
	public void addTopic(Subject key, Topic topic) {
		ArrayList<Topic> currTopics = this.subjectWithTopics.get(key);
		currTopics.add(topic);
	}
}
