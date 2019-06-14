package nl.saxion.budgetblackboard.dataProvider;

import nl.saxion.budgetblackboard.models.Course;
import nl.saxion.budgetblackboard.models.Subject;
import nl.saxion.budgetblackboard.models.Topic;
import nl.saxion.budgetblackboard.users.Person;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DataProvider {
	private static DataProvider dataProvider;
	private ArrayList<Person> users;
	private ArrayList<Course> courses;
	private HashMap<Subject, ArrayList<Topic>> subjectWithTopics;
	private static boolean hasBeenInitialized = false;

	private DataProvider() {
		this.users = new ArrayList<>();
		this.courses = new ArrayList<>();
		this.subjectWithTopics = new HashMap<>();
		init();
	}

	public static DataProvider getInstance() {
		if (dataProvider == null) {
			dataProvider = new DataProvider();
		}
		return dataProvider;
	}

	private void init() {
		//TODO: fill all the dummy data here.
		if (!hasBeenInitialized) {
			addCourse(new Course("ICT", 5, 48, 4));
			addCourse(new Course("Tourism management", 2, 51, 4));
			hasBeenInitialized = true;
		}
	}
	public ArrayList<Course> getCourses() {
		return new ArrayList<>(this.courses);
	}

	public boolean addCourse(Course course) {
		if (!this.courses.contains(course)) {
			this.courses.add(course);
			return true;
		}
		return false;
	}

	public Course findCourseByID(int ID){
		for (Course course :this.courses) {
			if (course.getID() == ID){
				return course;
			}
		}
		return null;
	}

	public void updateCourse(Course editedCourse, int ID) {
		this.courses.set(ID-1, editedCourse);
	}
}
